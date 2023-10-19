package com.pizzaria.sis_pedido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pizzaria.sis_pedido.model.Cliente;
import com.pizzaria.sis_pedido.model.Usuario;
import com.pizzaria.sis_pedido.model.Pedido;
import com.pizzaria.sis_pedido.repository.ClienteRepository;
import com.pizzaria.sis_pedido.repository.PedidoRepository;



@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    @GetMapping
    public ModelAndView paginaUsuarios() {
        ModelAndView modelAndView = new ModelAndView("menu");
        return modelAndView;
    }


   
}
