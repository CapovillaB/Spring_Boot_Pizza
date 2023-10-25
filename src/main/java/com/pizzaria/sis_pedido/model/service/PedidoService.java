package com.pizzaria.sis_pedido.model.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzaria.sis_pedido.model.entity.Pedido;
import com.pizzaria.sis_pedido.model.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido salvarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido buscarPedidoPorId(int id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido acharPedidoTS (Date instante) {
        return pedidoRepository.findByPedidoTimestamp(instante);
    }
}
