package com.pizzaria.sis_pedido.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzaria.sis_pedido.model.entity.Item;
import com.pizzaria.sis_pedido.model.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> buscarTodasPizzas() {
       return itemRepository.findByTipoItem("PIZZA");
    }
    
    public List<Item> buscarTodasBebidas() {
        return itemRepository.findByTipoItem("BEBIDA");
    }

    public Item buscarItemPorId(int itemId) {
       return itemRepository.findByIdItem(itemId);
    }
}
