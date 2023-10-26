package com.pizzaria.sis_pedido.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzaria.sis_pedido.model.entity.Item;
import com.pizzaria.sis_pedido.model.entity.Pedido;
import com.pizzaria.sis_pedido.model.entity.PedidoItem;
import com.pizzaria.sis_pedido.model.repository.PedidoItemRepository;

@Service
public class PedidoItemService {

    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    public List<PedidoItem> listarPedidoItem(Pedido pedido) {
        List<PedidoItem> listaPI = new ArrayList<PedidoItem>();
        List<Item> listaP = pedido.getItem();

        for (Item item : listaP) {
            Integer idPedido = pedido.getIdPedido();
            Integer idItem = item.getIdItem();
            Integer piQuantidade = item.getQtd();
            Float piValor = item.getValorQtd();
            String nomeItem = item.getNomeItem();
            String descItem =item.getDescItem();
            PedidoItem pedidoItem = new PedidoItem(idPedido, piValor, piQuantidade, idItem, descItem, nomeItem);
            
            if(!listaPI.contains(pedidoItem)){
                listaPI.add(pedidoItem);
            }                  
            
        }

        return listaPI;

    }

    public void salvarListaPI(List<PedidoItem> listaPI) {
        
        for(PedidoItem i: listaPI){
            pedidoItemRepository.save(i);
        }

    }
}
