package com.pizzaria.sis_pedido.model.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pizzaria.sis_pedido.model.entity.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    List<Usuario> findByNome(String nome);
    List<Usuario> findByNomeAndPswd(String nome, String pswd);
    
    @Modifying
    @Query("update usuario set usuario.pswd_usuario = ?1 where usuario.id_usuario = ?2")
    Void updateUsuarioPswd(String pswd, int id);
    
}
