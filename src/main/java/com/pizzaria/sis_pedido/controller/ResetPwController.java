package com.pizzaria.sis_pedido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pizzaria.sis_pedido.model.entity.Cliente;
import com.pizzaria.sis_pedido.model.entity.Usuario;
import com.pizzaria.sis_pedido.model.service.ClienteService;
import com.pizzaria.sis_pedido.model.service.UsuarioService;

@RequestMapping("/reset_pw")
@Controller
public class ResetPwController {
    
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String resetPassword(@RequestBody Cliente cliente) {

        Cliente oldCliente = clienteService.buscarClientePorCPF(Integer.parseInt(cliente.getClienteCPF()));
        Usuario oldUsuario = usuarioService.buscarUsuarioPorNome(cliente.getUsuario().getNome());
        
        if ((cliente.getClienteEmail().equals(oldCliente.getClienteEmail()))&&(oldCliente.getUsuarioId() == oldUsuario.getId())) {
            usuarioService.mudarSenha(cliente.getUsuario().getPswd(), oldUsuario.getId());
            return "redirect:/logar";
        } else {
            return "redirect:/reset_pw";   
        }
        
    }

}
