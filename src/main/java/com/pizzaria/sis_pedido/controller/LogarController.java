package com.pizzaria.sis_pedido.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.pizzaria.sis_pedido.model.entity.Usuario;
import com.pizzaria.sis_pedido.model.service.UsuarioService;


@Controller
@RequestMapping("/logar")
public class LogarController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ModelAndView paginaUsuarios() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("usuario", new Usuario());
        return modelAndView;
    }


    @PostMapping
    public String logarUsuario(@ModelAttribute Usuario usuario) {
        try { 
            return (usuarioService.Logar(usuario));
            
        } catch (Exception e) {
            return "redirect:/logar";
        }
    }

}

    
   

