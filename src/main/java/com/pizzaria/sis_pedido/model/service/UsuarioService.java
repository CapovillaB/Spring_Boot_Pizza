package com.pizzaria.sis_pedido.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzaria.sis_pedido.model.entity.Usuario;
import com.pizzaria.sis_pedido.model.repository.UsuarioRepository;

@Service
public class UsuarioService {

      @Autowired
    private UsuarioRepository usuarioRepository;

    
    public String Logar(Usuario usuario) {
        List<Usuario> usuarios = usuarioRepository.findByNomeUsuarioAndPswdUsuario(usuario.getNomeUsuario(), usuario.getPswdUsuario());
        if (!usuarios.isEmpty()) {
            Usuario usuarioLogado = usuarios.get(0);
            usuarioLogado.setLogged(true);
            return "redirect:/pedido";
        } else {
            return "redirect:/logar";
        }


    }

    public String criarUsuario(Usuario usuario) {
        try {
            usuarioRepository.save(usuario);
            return "redirect:/logar";

        } catch (Exception e) {

            return "redirect:/logar";
            
        }
        
    }

    public Usuario buscarUsuarioPorNome(String nomeUsusario) {

    return usuarioRepository.findByNomeUsuario(nomeUsusario).get(0);
    }

    public void mudarSenha(String pswd, int id) {
       usuarioRepository.setPswdUsuarioByIdUsuario(pswd, id);
      
    }
}
