package com.pizzaria.sis_pedido.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private int idPedido;

    @Column(name = "id_cliente")
    private int idCliente;

    @Column(name = "pedido_ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pedidoTimestamp;

    @Column(name = "pedido_valor")
    private float pedidoValor;

    @Column(name = "pedido_pag")
    private String pedidoPagamento;

    @Column(name = "pedido_status")
    private String pedidoStatus;

    public Pedido( float pedidoValor, String pedidoPagamento, String pedidoStatus) {
        this.pedidoValor = pedidoValor;
        this.pedidoPagamento = pedidoPagamento;
        this.pedidoStatus = pedidoStatus;
    }


}
