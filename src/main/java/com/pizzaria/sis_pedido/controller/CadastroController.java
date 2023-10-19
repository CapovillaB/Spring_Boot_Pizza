package com.pizzaria.sis_pedido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pizzaria.sis_pedido.model.Cliente;
import com.pizzaria.sis_pedido.model.Usuario;
import com.pizzaria.sis_pedido.repository.ClienteRepository;
import com.pizzaria.sis_pedido.repository.UsuarioRepository;


@Controller
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    @GetMapping
    public ModelAndView paginaUsuarios() {
        ModelAndView modelAndView = new ModelAndView("cadastro");
        return modelAndView;
    }


    @PostMapping
    public String processarFormulario(
            @RequestParam String nomeUsuario,
            @RequestParam String pswdUsuario,
            @RequestParam String clienteNome,
            @RequestParam String clienteCPF,
            @RequestParam String clienteEnd,
            @RequestParam String clienteEmail,
            @RequestParam String clienteTel) {

        // Criar e salvar instância de Usuario
        Usuario usuario = new Usuario(nomeUsuario, pswdUsuario);
        usuarioRepository.save(usuario);

        // Criar e salvar instância de Cliente
        Cliente cliente = new Cliente(clienteNome, clienteCPF, clienteEnd, clienteEmail, clienteTel);
        cliente.setUsuarioId(usuario.getId()); // Associar o cliente ao usuário
        clienteRepository.save(cliente);

        // Mais ações, redirecionamentos, etc., se necessário.

        return "sucesso"; // Página de sucesso
    }
}
