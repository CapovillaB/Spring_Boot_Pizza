package com.pizzaria.sis_pedido.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pizzaria.sis_pedido.model.Item;

@Repository
public interface ItemRepository extends JpaRepository <Item, Integer> {
    
    
}
