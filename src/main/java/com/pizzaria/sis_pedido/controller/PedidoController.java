package com.pizzaria.sis_pedido.controller;

import java.util.ArrayList;
import java.util.Date;
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
                precoTotal = precoTotal + item.getPriceItem();
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
            @RequestParam("tipoItem") String tipoItem, HttpSession session) {
        Item item = new Item(idItem, nomeItem, descItem, priceItem, tipoItem);
        listaPedido.add(item);
        precoTotal = precoTotal + priceItem;
        session.setAttribute("listaPedido", listaPedido);

        System.out.println("Item adicionado ao pedido: " + item.getNomeItem() + " - " + item.getDescItem() + " - "
                + item.getPriceItem());

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
        precoTotal = precoTotal - removerEsse.getPriceItem();
        session.setAttribute("listaPedido", listaPedido);

        return "redirect:/pedido";
    }

    @PostMapping("/enviarPedido")
    public String enviarPedido(@RequestParam("pedidoPagamento") String pedidoPagamento, HttpSession session) {
        // Com a variável logarUsuario, você tem o nome do cliente logado.
        // Você pode obter o cliente associado a esse nome.
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        Cliente cliente = clienteService.buscarClientePorIdUsuario(usuario.getIdUsuario());
        if ((List<Item>) session.getAttribute("listaPedido") != null) {
            listaPedido = (List<Item>) session.getAttribute("listaPedido");
        } else{
            return "redirect:/pedido";
        }

        if (!listaPedido.isEmpty() && precoTotal == 0.0f) {
            for (Item item : listaPedido) {
                precoTotal = precoTotal + item.getPriceItem();
            }
        }

        if (usuario.isLogged()) {

            // Crie um novo pedido
            Pedido pedido = new Pedido();

            pedido.setCliente(cliente);
           // pedido.setItem(listaPedido);
            pedido.setPedidoValor(precoTotal);
            pedido.setPedidoTimestamp(new Date());
            pedido.setPedidoPagamento(pedidoPagamento);
            pedido.setPedidoStatus("Confirmado");


            pedidoService.salvarPedido(pedido);

            // Redirecione para a página de confirmação, ou qualquer outra página desejada.
            return "redirect:/confirmacaoPedido";
        } else {
            System.out.println("usuario não logado.");
            return "redirect:/pedido";
        }
    }

    @PostMapping("/cancelarPedido")
    public String cancelarPedido(HttpSession session) {

        List<Item> listaVazia = new ArrayList<Item>();
        session.setAttribute("listaPedido", listaVazia);

        return "redirect:/pizzaria";
    }



    @RequestMapping("/confirmacaoPedido")
    public String ConfirmacaoPedido(HttpSession session) {
    ModelAndView modelAndView = new ModelAndView("confirmacaoPedido");

    //    ***** ENDEEREÇO DE ENTREGA DO  CLIENTE *****

    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
    Cliente cliente = clienteService.buscarClientePorIdUsuario(usuario.getIdUsuario());
    
    String endereco = cliente.getClienteEnd(); // Obtenha o endereço do cliente e salva na varivavel endereco

    modelAndView.addObject("nomeCliente", cliente.getClienteNome());
    modelAndView.addObject("enderecoCliente", cliente.getClienteEnd());
    modelAndView.addObject("telefoneCliente", cliente.getClienteTel());
   
  

    //        **** LISTA DO PEDIDO *****

    if ((List<Item>) session.getAttribute("listaPedido") != null) {
        List<Item> listaPedido = (List<Item>) session.getAttribute("listaPedido");
        float precoTotal = 0.0f;

        for (Item item : listaPedido) {
            precoTotal += item.getPriceItem();
        }

        modelAndView.addObject("itensPedido", listaPedido);
        modelAndView.addObject("precoTotal", precoTotal);
    }



    return "confirmacaoPedido";
    }

    @PostMapping("/alterarEndereco")
    public String alterarEndereco(@RequestParam("novoEndereco") String novoEndereco, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        Cliente cliente = clienteService.buscarClientePorIdUsuario(usuario.getIdUsuario());
    
        // Atualize o endereço do cliente usando o metodo atualziar endeteço em ClienteService
        clienteService.atualizarEndereco(cliente, novoEndereco);
    
        // Redirecione de volta para a página de confirmação e atualzia o endereço do cliente
        return "redirect:/confirmacaoPedido";
    }



}

/*
 * package com.pizzaria.sis_pedido.controller;
 * 
 * import java.util.ArrayList;
 * import java.util.LinkedHashMap;
 * import java.util.List;
 * import java.util.Map;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.stereotype.Controller;
 * import org.springframework.web.bind.annotation.GetMapping;
 * import org.springframework.web.bind.annotation.PostMapping;
 * import org.springframework.web.bind.annotation.RequestMapping;
 * import org.springframework.web.bind.annotation.RequestParam;
 * import org.springframework.web.servlet.ModelAndView;
 * 
 * import com.pizzaria.sis_pedido.model.entity.Item;
 * import com.pizzaria.sis_pedido.model.service.ItemService;
 * 
 * @Controller
 * 
 * @RequestMapping("/pedido")
 * public class PedidoController {
 * 
 * @Autowired
 * private ItemService itemService;
 * 
 * private Map<Integer, Item> mapaDeItens = new LinkedHashMap<>();
 * private int contadorDeItens = 1;
 * 
 * @GetMapping
 * public ModelAndView mostrarMenu() {
 * ModelAndView modelAndView = new ModelAndView("menu");
 * 
 * List<Item> registrosP = itemService.buscarTodasPizzas();
 * List<Item> registrosB = itemService.buscarTodasBebidas();
 * 
 * modelAndView.addObject("registrosP", registrosP);
 * modelAndView.addObject("registrosB", registrosB);
 * modelAndView.addObject("pedidos", mapaDeItens.values());
 * 
 * return modelAndView;
 * }
 * 
 * @PostMapping
 * public String adicionarAoPedido(@RequestParam String nomeItem, @RequestParam
 * String descItem, @RequestParam double priceItem) {
 * Item item = new Item();
 * item.setIdItem(contadorDeItens++);
 * item.setNomeItem(nomeItem);
 * item.setDescItem(descItem);
 * item.setPriceItem(priceItem);
 * 
 * mapaDeItens.put(item.getIdItem(), item);
 * 
 * System.out.println("Item adicionado ao pedido: " + item.getNomeItem() + " - "
 * + item.getDescItem() + " - " + item.getPriceItem());
 * System.out.println("Mapa
 */