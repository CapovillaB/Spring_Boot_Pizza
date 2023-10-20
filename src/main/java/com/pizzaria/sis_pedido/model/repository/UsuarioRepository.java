package com.pizzaria.sis_pedido.model.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pizzaria.sis_pedido.model.entity.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    List<Usuario> findByNome(String nome);
    List<Usuario> findByNomeAndPswd(String nome, String pswd);
    
}
