package com.pizzaria.sis_pedido.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private int idCliente;

    @Column(name = "id_usuario")
    private int usuarioId;

    @Column(name = "cliente_nome")
    private String clienteNome;

    @Column(name = "cliente_cpf")
    private int clienteCPF;

    @Column(name = "cliente_end")
    private String clienteEnd;

    @Column(name = "cliente_email")
    private String clienteEmail;

    @Column(name = "cliente_tel")
    private String clienteTel;

    @Transient
    private Usuario usuario;


    public Cliente(String clienteNome, int clienteCPF, String clienteEnd, String clienteEmail, String clienteTel) {
        this.clienteNome = clienteNome;
        this.clienteCPF = clienteCPF;
        this.clienteEnd = clienteEnd;
        this.clienteEmail = clienteEmail;
        this.clienteTel = clienteTel;
    }


    
}
