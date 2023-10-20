package com.pizzaria.sis_pedido.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pizzaria.sis_pedido.model.entity.Usuario;
import com.pizzaria.sis_pedido.model.repository.UsuarioRepository;

@Service
public class UsuarioService {

      @Autowired
    private UsuarioRepository usuarioRepository;

    
    public ResponseEntity<?> Logar(Usuario usuario) {
        List<Usuario> usuarios = usuarioRepository.findByNomeAndPswd(usuario.getNome(), usuario.getPswd());
        if (!usuarios.isEmpty()) {
            Usuario usuarioLogado = usuarios.get(0);
            usuarioLogado.setLogged(true);
            return ResponseEntity.ok("redirect:/pedido");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("redirect:/logar");
        }


    }

    public ResponseEntity<?> criarUsuario(Usuario usuario) {
        try {
            usuarioRepository.save(usuario);
            return new ResponseEntity<String>("redirect:/logar",HttpStatus.OK);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("redirect:/logar");
            
        }
        
    }
}
