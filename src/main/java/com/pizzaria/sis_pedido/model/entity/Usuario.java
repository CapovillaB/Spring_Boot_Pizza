package com.pizzaria.sis_pedido.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario", schema = "pizzaria" )
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nome_usuario")
    private String nomeUsuario;

    @Column(name = "pswd_usuario")
    private String pswdUsuario;

    @Transient
    private boolean logged;


    public Usuario(String nome_usuario, String pswd_usuario) {
        this.nomeUsuario = nome_usuario;
        this.pswdUsuario = pswd_usuario;

    }
    
}
