package com.pizzaria.sis_pedido.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private ItemService itemService;

    private Map<Integer, Item> mapaDeItens = new LinkedHashMap<>();
    private int contadorDeItens = 1;

    @GetMapping
    public ModelAndView mostrarMenu() {
        ModelAndView modelAndView = new ModelAndView("menu");

        List<Item> registrosP = itemService.buscarTodasPizzas();
        List<Item> registrosB = itemService.buscarTodasBebidas();

        modelAndView.addObject("registrosP", registrosP);
        modelAndView.addObject("registrosB", registrosB);
        modelAndView.addObject("pedidos", mapaDeItens.values());

        return modelAndView;
    }

    @PostMapping
    public String adicionarAoPedido(@RequestParam String nomeItem, @RequestParam String descItem, @RequestParam double priceItem) {
        Item item = new Item();
        item.setIdItem(contadorDeItens++);
        item.setNomeItem(nomeItem);
        item.setDescItem(descItem);
        item.setPriceItem(priceItem);

        mapaDeItens.put(item.getIdItem(), item);

        System.out.println("Item adicionado ao pedido: " + item.getNomeItem() + " - " + item.getDescItem() + " - " + item.getPriceItem());
        System.out.println("Mapa de Itens:");
        for (Item i : mapaDeItens.values()) {
            System.out.println(i.getIdItem() + " - " + i.getNomeItem() + " - " + i.getDescItem() + " - " + i.getPriceItem());
        }

        return "redirect:/pedido";
    }
}



/*package com.pizzaria.sis_pedido.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private ItemService itemService;

    private Map<Integer, Item> mapaDeItens = new LinkedHashMap<>();
    private int contadorDeItens = 1;

    @GetMapping
    public ModelAndView mostrarMenu() {
        ModelAndView modelAndView = new ModelAndView("menu");

        List<Item> registrosP = itemService.buscarTodasPizzas();
        List<Item> registrosB = itemService.buscarTodasBebidas();

        modelAndView.addObject("registrosP", registrosP);
        modelAndView.addObject("registrosB", registrosB);
        modelAndView.addObject("pedidos", mapaDeItens.values());

        return modelAndView;
    }

    @PostMapping
    public String adicionarAoPedido(@RequestParam String nomeItem, @RequestParam String descItem, @RequestParam double priceItem) {
        Item item = new Item();
        item.setIdItem(contadorDeItens++);
        item.setNomeItem(nomeItem);
        item.setDescItem(descItem);
        item.setPriceItem(priceItem);

        mapaDeItens.put(item.getIdItem(), item);

        System.out.println("Item adicionado ao pedido: " + item.getNomeItem() + " - " + item.getDescItem() + " - " + item.getPriceItem());
        System.out.println("Mapa */