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
    private Integer idPi;

    @Column(name = "pi_valor")
    private Float piValor;

    @Column(name = "pi_qtd")
    private Integer piQuantidade;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Integer idPedido;

    @ManyToOne
    @JoinColumn(name = "id_item")
    private Integer idItem;


    
    public PedidoItem(Float piValor, Integer piQuantidade, Integer idPedido, Integer idItem) {
        this.piValor = piValor;
        this.piQuantidade = piQuantidade;
        this.idPedido = idPedido;
        this.idItem = idItem;
    }



        

}
