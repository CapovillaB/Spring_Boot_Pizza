package com.pizzaria.sis_pedido.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public class Item {
    
    @Id
    @Column(name = "id_item", updatable = false)
    private int IdItem;

    @Column(name = "item_nome", updatable = false)
    private String NomeItem;

    @Column(name = "item_desc", updatable = false)
    private String DescItem;

    @Column(name = "item_price", updatable = false)
    private float PriceItem;

    @Column(name = "item_tipo", updatable = false)
    private String TipoItem;


    

}
