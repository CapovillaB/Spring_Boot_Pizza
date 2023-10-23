package com.pizzaria.sis_pedido.model.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private int idPedido;

    @Column(name = "pedido_ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pedidoTimestamp;

    @Column(name = "pedido_valor")
    private float pedidoValor;

    @Column(name = "pedido_pag")
    private String pedidoPagamento;

    @Column(name = "pedido_status")
    private String pedidoStatus;

    
    @ManyToMany
    @JoinColumn(name = "id_item")
    List<Item> item;

    //Pedido possui um campo cliente que armazena a associação entre o pedido e o cliente
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    //Agora, na classe PedidoController, você pode chamar o método setCliente para associar o cliente ao pedido:
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public Pedido(float pedidoValor, String pedidoPagamento, String pedidoStatus, List<Item> item, Cliente cliente) {
        this.pedidoValor = pedidoValor;
        this.pedidoPagamento = pedidoPagamento;
        this.pedidoStatus = pedidoStatus;
        this.item = item;
        this.cliente = cliente;
    }
}   
