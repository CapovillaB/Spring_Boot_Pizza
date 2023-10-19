package com.pizzaria.sis_pedido.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.pizzaria.sis_pedido.model.Usuario;
import com.pizzaria.sis_pedido.repository.UsuarioRepository;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ModelAndView paginaUsuarios() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("usuario", new Usuario());
        return modelAndView;
    }


    @PostMapping
    public String logarUsuario(@ModelAttribute Usuario usuario) {
        List<Usuario> usuarios = usuarioRepository.findByNomeAndPswd(usuario.getNome(), usuario.getPswd());
        if (!usuarios.isEmpty()) {
            Usuario usuarioLogado = usuarios.get(0);
            usuarioLogado.setLogged(true);
            return "redirect:/pedidos";
        } else {
            return "redirect:/usuarios";
        }
    }

}

    
   

