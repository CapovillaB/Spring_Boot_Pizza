package com.pizzaria.sis_pedido.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizzaria.sis_pedido.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    List<Usuario> findByNome(String nome);
    List<Usuario> findByNomeAndPswd(String nome, String pswd);
    // Você pode adicionar métodos de consulta personalizados aqui, se necessário
}
