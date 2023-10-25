package com.pizzaria.sis_pedido.controller;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pizzaria.sis_pedido.model.entity.Item;
import com.pizzaria.sis_pedido.model.entity.Pedido;


import jakarta.servlet.http.HttpSession;

@RequestMapping("/confirmacaoPedido")
@Controller
public class DetalhesPedidoController {

    
    @GetMapping
    public ModelAndView MostrarDetalhes(HttpSession session) {

        Pedido pedido = (Pedido)session.getAttribute("pedido");
        

        ModelAndView modelAndView = new ModelAndView("detalhes_pedido");
       
        modelAndView.addObject("pedido", pedido);

        return modelAndView;

    }
}