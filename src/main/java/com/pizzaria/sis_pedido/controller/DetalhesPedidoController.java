package com.pizzaria.sis_pedido.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pizzaria.sis_pedido.model.entity.Cliente;

import com.pizzaria.sis_pedido.model.entity.Pedido;
import com.pizzaria.sis_pedido.model.entity.PedidoItem;
import com.pizzaria.sis_pedido.model.entity.Usuario;
import com.pizzaria.sis_pedido.model.service.ClienteService;
import com.pizzaria.sis_pedido.model.service.PedidoItemService;
import com.pizzaria.sis_pedido.model.service.PedidoService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/confirmacaoPedido")
@Controller
public class DetalhesPedidoController {

    
    @Autowired
    private PedidoItemService pedidoItemService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

        private List<PedidoItem> lista;
    
    @GetMapping
    public ModelAndView MostrarDetalhes(HttpSession session) {

        Pedido pedido = (Pedido)session.getAttribute("pedido");

        lista = pedidoItemService.listarPedidoItem(pedido);
        Cliente cliente = pedido.getCliente();
        Float precoTotal = pedido.getPedidoValor();
        String nomeCliente = cliente.getClienteNome();
        String enderecoCliente = cliente.getClienteEnd();
        Long telefoneCliente = cliente.getClienteTel();
        Long cpfCliente = cliente.getClienteCPF();




        ModelAndView modelAndView = new ModelAndView("confirmacaoPedido");
       
        modelAndView.addObject("nomeCliente", nomeCliente);
        modelAndView.addObject("enderecoCliente", enderecoCliente);
        modelAndView.addObject("telefoneCliente", telefoneCliente);
        modelAndView.addObject("cpfCliente", cpfCliente);
        modelAndView.addObject("listaPedido", lista);
        modelAndView.addObject("precoTotal", precoTotal);
        

        return modelAndView;

    }


    @PostMapping("/alterarEndereco")
    public String alterarEndereco(@RequestParam("novoEndereco") String novoEndereco, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        Pedido pedido = (Pedido) session.getAttribute("pedido");
        Cliente cliente = clienteService.buscarClientePorIdUsuario(usuario);
        pedido.getCliente().setClienteEnd(novoEndereco);
        session.setAttribute("pedido", pedido);
        // Atualize o endereço do cliente usando o metodo atualziar endeteço em ClienteService
        clienteService.atualizarEndereco(cliente, novoEndereco);
    
        // Redirecione de volta para a página de confirmação e atualzia o endereço do cliente
        return "redirect:/confirmacaoPedido";
    }

    @PostMapping("/pedir")
    public String enviarPedido(HttpSession session, @RequestParam("payment") String payment) {

        Pedido pedido = (Pedido)session.getAttribute("pedido");
        pedido.setPedidoPagamento(payment);
        pedido.setPedidoStatus("Realizado");

        pedidoService.salvarPedido(pedido);
        pedidoItemService.salvarListaPI(lista);





        return "redirect:/pizzaria";
    }

}