package com.pizzaria.sis_pedido.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.pizzaria.sis_pedido.model.entity.Usuario;
import com.pizzaria.sis_pedido.model.service.UsuarioService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/logar")
public class LogarController {

    @Autowired
    UsuarioService usuarioService;

    public Usuario usuario = new Usuario();

    @GetMapping
    public ModelAndView paginaUsuarios() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("usuario", usuario);
        return modelAndView;
    }


    @PostMapping
    public String logarUsuario(@ModelAttribute Usuario usuario, HttpSession session) {
        try {
            usuarioService.Logar(usuario, session);

            return "redirect:/pedido";
            
        } catch (Exception e) {
            return "redirect:/logar";
        }
    }

    @GetMapping("/deslogar")
    public String deslogar(HttpSession session) {

        session.invalidate();
        return "redirect:/pizzaria";
    }

}

    
   

