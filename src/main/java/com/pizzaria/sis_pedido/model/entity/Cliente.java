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
