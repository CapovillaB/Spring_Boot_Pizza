package com.pizzaria.sis_pedido.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido_item")
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pi")
    private int idPi;

    @Column(name = "pi_valor")
    private Float piValor;

    @Column(name = "pi_qtd")
    private Integer piQuantidade;

    
    @Column(name = "id_pedido")
    private int idPedido;

    
    @Column(name = "id_item")
    private int idItem;

    @Transient
    private String descItem;

    @Transient
    private String nomeItem;



    public PedidoItem(Integer idPedido, Float piValor, Integer piQuantidade, Integer idItem, String descItem, String nomeItem) {
        this.piValor = piValor;
        this.piQuantidade = piQuantidade;
        this.idPedido = idPedido;
        this.idItem = idItem;
        this.descItem = descItem;
        this.nomeItem = nomeItem;
    }



        

}
