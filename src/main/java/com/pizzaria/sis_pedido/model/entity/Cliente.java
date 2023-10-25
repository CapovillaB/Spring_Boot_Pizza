package com.pizzaria.sis_pedido.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "id_usuario")
    private Integer usuarioId;

    @Column(name = "cliente_nome")
    private String clienteNome;

    @Column(name = "cliente_cpf")
    private Long clienteCPF;

    @Column(name = "cliente_end")
    private String clienteEnd;

    @Column(name = "cliente_email")
    private String clienteEmail;

    @Column(name = "cliente_tel")
    private Long clienteTel;

    @Transient
    private Usuario usuario;


    public Cliente(Usuario usuario, String clienteNome, Long clienteCPF, String clienteEnd, String clienteEmail, Long clienteTel) {
        this.clienteNome = clienteNome;
        this.clienteCPF = clienteCPF;
        this.clienteEnd = clienteEnd;
        this.clienteEmail = clienteEmail;
        this.clienteTel = clienteTel;
        this.usuario = usuario;
    }


    public Cliente(Long clienteCPF, String clienteEmail, Usuario usuario) {
        this.clienteCPF = clienteCPF;
        this.clienteEmail = clienteEmail;
        this.usuario = usuario;
    }

    


    
}
