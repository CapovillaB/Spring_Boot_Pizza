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
import com.pizzaria.sis_pedido.model.entity.Item;
import com.pizzaria.sis_pedido.model.entity.Pedido;
import com.pizzaria.sis_pedido.model.entity.Usuario;
import com.pizzaria.sis_pedido.model.repository.ClienteRepository;
import com.pizzaria.sis_pedido.model.repository.ItemRepository;
import com.pizzaria.sis_pedido.model.repository.PedidoRepository;



@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemRepository itemRepository;


    @GetMapping
    public ModelAndView mostrarMenu() {
        List<Item> registros = itemRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("menu");
        modelAndView.addObject("registros", registros);
        return modelAndView;
    }


   
}
