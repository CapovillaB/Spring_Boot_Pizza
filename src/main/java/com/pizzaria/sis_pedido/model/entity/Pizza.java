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
//   @Table(name = "nome da tabela no banco")    //caso a classe tenha um nome diferente da tabela no banco de dados
public class Pizza extends Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza")
    private int id;
    @Column(name = "pz_type")
    private String tipo; //adicionei pois estava anteriormente mas nao sei qual função em pizzas
    @Column(name = "pz_size")
    private String tamanho;
    @Column(name = "pz_flavor")
    private String sabor;
    @Column(name = "pz_desc")
    private String descricao;
    @Column(name = "pz_price")
    private float valor;

    
}
