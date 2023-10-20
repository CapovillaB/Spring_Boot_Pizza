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

    public ResponseEntity<?> criarCliente(Cliente cliente) {
        try {
            clienteRepository.save(cliente);
            return new ResponseEntity<String>("redirect:/logar",HttpStatus.OK);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("redirect:/logar");
            
        }
        
    }
}
