package com.pizzaria.sis_pedido.controller;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pizzaria.sis_pedido.model.entity.Cliente;
import com.pizzaria.sis_pedido.model.entity.Item;
import com.pizzaria.sis_pedido.model.entity.Pedido;
import com.pizzaria.sis_pedido.model.entity.Usuario;
import com.pizzaria.sis_pedido.model.service.ClienteService;
import com.pizzaria.sis_pedido.model.service.ItemService;
import com.pizzaria.sis_pedido.model.service.PedidoService;

import jakarta.servlet.http.HttpSession;

//corrigir valor total

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private PedidoService pedidoService;
 

    private List<Item> listaPedido = new ArrayList<Item>();

    private Float precoTotal = 0.00f;

    @GetMapping
    public ModelAndView mostrarMenu(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("menu");

        List<Item> registrosP = itemService.buscarTodasPizzas();
        List<Item> registrosB = itemService.buscarTodasBebidas();

        if ((List<Item>) session.getAttribute("listaPedido") != null) {
            listaPedido = (List<Item>) session.getAttribute("listaPedido");
        }

        if (!listaPedido.isEmpty() && precoTotal == 0.0f) {
            for (Item item : listaPedido) {
                precoTotal = precoTotal + item.getValorQtd();
            }
        }


        modelAndView.addObject("registrosP", registrosP);
        modelAndView.addObject("registrosB", registrosB);
        modelAndView.addObject("itensSelecionados", listaPedido);
        modelAndView.addObject("precoTotal", precoTotal);

        return modelAndView;
    }

    @PostMapping
    public String adicionarAoPedido(@RequestParam("idItem") Integer idItem, @RequestParam("priceItem") Float priceItem,
            @RequestParam("nomeItem") String nomeItem, @RequestParam("descItem") String descItem,
            @RequestParam("tipoItem") String tipoItem, @RequestParam("Qtd") Integer Qtd, HttpSession session) {
        Item item = new Item(idItem, nomeItem, descItem, priceItem, tipoItem, Qtd);
        item.setValorQtd((Float)(priceItem*Qtd));
        listaPedido.add(item);
        precoTotal = precoTotal + priceItem;
        session.setAttribute("listaPedido", listaPedido);

        return "redirect:/pedido";
    }

    @PostMapping("/removeItem")
    public String removerItem(@RequestParam("removeItem") String removeItem, HttpSession session) {

        Item removerEsse = new Item();

        if ((List<Item>) session.getAttribute("listaPedido") != null) {
            listaPedido = (List<Item>) session.getAttribute("listaPedido");
        }

        for (Item item : listaPedido){
            if(item.getNomeItem().equals(removeItem)) {

                System.out.println(item.getNomeItem());
                removerEsse = item;
                break;
            }
        }

        listaPedido.remove(removerEsse);
        precoTotal = precoTotal - removerEsse.getValorQtd();
        session.setAttribute("listaPedido", listaPedido);

        return "redirect:/pedido";
    }

    @PostMapping("/enviarPedido")
    public String enviarPedido(HttpSession session) {
        // Com a variável logarUsuario, você tem o nome do cliente logado.
        // Você pode obter o cliente associado a esse nome.
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if(usuario == null){
            return "redirect:/logar";
        }
        Cliente cliente = clienteService.buscarClientePorIdUsuario(usuario);
        if ((List<Item>) session.getAttribute("listaPedido") != null) {
            listaPedido = (List<Item>) session.getAttribute("listaPedido");
        } else{
            return "redirect:/pedido";
        }

        if (!listaPedido.isEmpty() && precoTotal == 0.0f) {
            for (Item item : listaPedido) {
                precoTotal = precoTotal + item.getValorQtd();
            }
        }

        if (usuario.isLogged()) {

            // Crie um novo pedido
            Pedido pedido = new Pedido();

            pedido.setCliente(cliente);
            pedido.setPedidoValor(precoTotal);
            LocalDateTime instante = LocalDateTime.now();
            pedido.setPedidoTimestamp(instante);
            pedido.setPedidoPagamento("Em Digitação");
            pedido.setPedidoStatus("Em Digitação");

            pedidoService.salvarPedido(pedido);
            
            int pd_id = pedidoService.acharPedidoTS(instante).get(0).getIdPedido();
            System.out.println(pd_id);
            pedido.setIdPedido(pd_id);
            pedido.setItem(listaPedido);
            session.setAttribute("pedido", pedido);

            return "redirect:/confirmacaoPedido";
        } else {
            System.out.println("usuario não logado.");
            return "redirect:/logar";
        }
    }

    @PostMapping("/cancelarPedido")
    public String cancelarPedido(HttpSession session) {

        List<Item> listaVazia = new ArrayList<Item>();
        session.setAttribute("listaPedido", listaVazia);

        return "redirect:/pizzaria";
    }


}
