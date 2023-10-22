package com.pizzaria.sis_pedido.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pizzaria.sis_pedido.model.entity.Item;
import com.pizzaria.sis_pedido.model.service.ItemService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    private List<Item> listaPedidos = new ArrayList<>();

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ModelAndView mostrarMenu() {
        ModelAndView modelAndView = new ModelAndView("menu");
        List<Item> registrosP = itemService.buscarTodasPizzas();
        modelAndView.addObject("registrosP", registrosP);

        List<Item> registrosB = itemService.buscarTodasBebidas();
        modelAndView.addObject("registrosB", registrosB);

        modelAndView.addObject("pedidos", listaPedidos); // Adicione a lista de pedidos ao modelo

        return modelAndView;
    }

    @PostMapping
    public String adicionarAoPedido(@RequestParam String nomeItem, @RequestParam String descItem, @RequestParam double priceItem) {
        Item item = new Item(nomeItem, descItem, priceItem);
        listaPedidos.add(item);
        return "redirect:/pedido";
    }
}

/* criar metodos de lista de pedido com adição de itens
 * criar metodo de salvar pedido
 * criar método de cancelar pedido
 */
