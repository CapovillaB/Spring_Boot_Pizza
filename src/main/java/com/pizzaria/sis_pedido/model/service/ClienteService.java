package com.pizzaria.sis_pedido.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pizzaria.sis_pedido.model.entity.Cliente;
import com.pizzaria.sis_pedido.model.repository.ClienteRepository;

@Service
public class ClienteService {

      @Autowired
    private ClienteRepository clienteRepository;

    public String criarCliente(Cliente cliente) {
        try {
            clienteRepository.save(cliente);
            return "redirect:/logar";

        } catch (Exception e) {

            return "redirect:/logar";
            
        }
        
    }

    public Cliente buscarClientePorCPF( int clienteCPF) {
        return clienteRepository.findByClienteCPF(clienteCPF);
    }
}
