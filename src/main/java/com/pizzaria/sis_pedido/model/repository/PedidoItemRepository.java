package com.pizzaria.sis_pedido.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pizzaria.sis_pedido.model.entity.PedidoItem;

import java.util.List;


@Repository
public interface PedidoItemRepository extends JpaRepository <PedidoItem, Integer> {
    
    PedidoItem findByIdPi(Integer idPi);

    List<PedidoItem> findByIdPedido(Integer idPedido);

    List<PedidoItem> findByIdItem(Integer idItem);

    
}
