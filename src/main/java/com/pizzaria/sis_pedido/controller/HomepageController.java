package com.pizzaria.sis_pedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pizzaria.sis_pedido.model.entity.Item;
import com.pizzaria.sis_pedido.model.service.ItemService;

@RequestMapping("/pizzaria")
@Controller
public class HomepageController {

    @Autowired
    private ItemService itemService;
    
    @GetMapping
    public ModelAndView homepage() {

        ModelAndView modelAndView = new ModelAndView("homepage");
        List<Item> registrosP = itemService.buscarTodasPizzas();
        modelAndView.addObject("registrosP", registrosP);
    
        List<Item> registrosB = itemService.buscarTodasBebidas();
        modelAndView.addObject("registrosB", registrosB);

        return modelAndView;

    }
}
