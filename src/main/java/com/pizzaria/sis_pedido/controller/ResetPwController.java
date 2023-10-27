package com.pizzaria.sis_pedido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView mostrarPagina() {
        ModelAndView modelAndView = new ModelAndView("reset_pw");
        modelAndView.addObject("cliente", new Cliente());
        return modelAndView;
    }

    @PostMapping
    public String resetPassword(@ModelAttribute Cliente cliente) {

        Cliente oldCliente = clienteService.buscarClientePorCPF(cliente.getClienteCPF());
        Usuario oldUsuario = usuarioService.buscarUsuarioPorNome(cliente.getUsuario().getNomeUsuario());
        
        if ((cliente.getClienteEmail().equals(oldCliente.getClienteEmail()))&&(oldCliente.getUsuario() == oldUsuario)) {
            usuarioService.mudarSenha(cliente.getUsuario().getPswdUsuario(), oldUsuario.getIdUsuario());
            return "redirect:/logar";
        } else {
            return "redirect:/reset_pw";   
        }
        
    }

}
