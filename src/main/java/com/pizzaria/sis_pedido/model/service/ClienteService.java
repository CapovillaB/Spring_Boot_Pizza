package com.pizzaria.sis_pedido.model.service;


import org.springframework.beans.factory.annotation.Autowired;
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

    public Cliente buscarClientePorCPF( Long clienteCPF) {
        return clienteRepository.findByClienteCPF(clienteCPF);
    }

    public Cliente buscarClientePorIdUsuario( int idUsuario) {
        Cliente cliente = clienteRepository.findByUsuarioId(idUsuario);
        return cliente;
    }


    //metodo usado no botao "alterar endereço na pagina de confirmação de pedido"
    public void atualizarEndereco(Cliente cliente, String novoEndereco) {
        cliente.setClienteEnd(novoEndereco);
        clienteRepository.save(cliente); // Isso atualiza o cliente no banco de dados
    }


}
