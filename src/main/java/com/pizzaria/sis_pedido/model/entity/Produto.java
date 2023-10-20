package com.pizzaria.sis_pedido.model.entity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Produto {

    private float valor; //essa variavel tbm deve estar delcarada em pizza e bebida para o metodo "atualizaValor" funciar com as classes pizza e bebida.

    public boolean atualizarValor(float novoValor) {
        if (novoValor > 0) {
            this.valor = novoValor;
            return true;
        } 
        else {
            return false;

        }
    }
}

