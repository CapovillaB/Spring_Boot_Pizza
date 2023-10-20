package com.pizzaria.sis_pedido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pizzaria.sis_pedido.model.entity.Cliente;
import com.pizzaria.sis_pedido.model.entity.Usuario;
import com.pizzaria.sis_pedido.model.repository.ClienteRepository;
import com.pizzaria.sis_pedido.model.repository.UsuarioRepository;
import com.pizzaria.sis_pedido.model.service.ClienteService;
import com.pizzaria.sis_pedido.model.service.UsuarioService;


@Controller
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ClienteService clienteService;


    @GetMapping
    public ModelAndView paginaCadastro() {
        ModelAndView modelAndView = new ModelAndView("cadastro");
        return modelAndView;
    }


    @PostMapping
    public String processarCadastro(@RequestBody Cliente newCliente) {

        try {
            usuarioService.criarUsuario(newCliente.getUsuario());
            clienteService.criarCliente(newCliente);

            return "redirect:/logar";

            
        } catch (Exception e) {
            return ("Erro Interno ao salvar Usuario: " + e.getMessage());
        }

    }

    
}
