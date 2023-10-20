package com.pizzaria.sis_pedido.model.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//   @Table(name = "nome_tabela_no_banco")    //caso a classe tenha um nome diferente da tabela no banco de dados
public class Bebida extends Produto{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nome da coluna na tabela")
    private int id;
    @Column(name = "nome da coluna na tabela")
    private String nome;
    @Column(name = "nome da coluna na tabela")
    private String descricao;
    @Column(name = "nome da coluna na tabela")
    private String tamanho; // os tamanhos de bebida devem ser exibidos para o cliente 3.1.1
    @Column(name = "nome da coluna na tabela")
    private float valor;
    
}

