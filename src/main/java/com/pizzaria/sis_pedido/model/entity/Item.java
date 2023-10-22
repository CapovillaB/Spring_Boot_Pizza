package com.pizzaria.sis_pedido.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public class Item {
    
    public Item(String nomeItem2, String descItem2, double priceItem2) {
    }

    @Id
    @Column(name = "id_item", updatable = false)
    private int idItem;

    @Column(name = "item_nome", updatable = false)
    private String nomeItem;

    @Column(name = "item_desc", updatable = false)
    private String descItem;

    @Column(name = "item_price", updatable = false)
    private double priceItem;

    @Column(name = "item_tipo", updatable = false)
    private String tipoItem;



    

}
