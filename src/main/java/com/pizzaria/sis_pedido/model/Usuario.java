package com.pizzaria.sis_pedido.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int id;

    @Column(name = "nome_usuario")
    private String nome;

    @Column(name = "pswd_usuario")
    private String pswd;

    @Transient
    private boolean logged;


    public Usuario(String nome_usuario, String pswd_usuario) {
        this.nome = nome_usuario;
        this.pswd = pswd_usuario;

    }
    
}
