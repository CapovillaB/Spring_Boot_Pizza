package com.pizzaria.sis_pedido.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pizzaria.sis_pedido.model.entity.Cliente;
import com.pizzaria.sis_pedido.model.entity.Pedido;

import java.time.LocalDateTime;



@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

    Pedido findByIdPedido(Integer idPedido);
    
   List<Pedido> findByPedidoTimestamp(LocalDateTime pedidoTimestamp);
}