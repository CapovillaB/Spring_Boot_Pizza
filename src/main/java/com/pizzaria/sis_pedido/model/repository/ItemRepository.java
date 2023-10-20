package com.pizzaria.sis_pedido.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pizzaria.sis_pedido.model.entity.Item;
import java.util.List;


@Repository
public interface ItemRepository extends JpaRepository <Item, Integer> {
    
    public List<Item> findByTipoItem(String tipoItem);
}
