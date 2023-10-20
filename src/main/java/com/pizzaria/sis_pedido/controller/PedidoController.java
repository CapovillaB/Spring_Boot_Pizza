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
import com.pizzaria.sis_pedido.model.service.ItemService;



@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemService itemService;


    @GetMapping
    public ModelAndView mostrarMenu() {
    
        ModelAndView modelAndView = new ModelAndView("menu");
        List<Item> registrosP = itemService.buscarTodasPizzas();
        modelAndView.addObject("registrosP", registrosP);
    
        List<Item> registrosB = itemService.buscarTodasBebidas();
        modelAndView.addObject("registrosB", registrosB);

        return modelAndView;
    }


   
}

/* criar metodos de lista de pedido com adição de itens
 * criar metodo de salvar pedido
 * criar método de cancelar pedido
 */
