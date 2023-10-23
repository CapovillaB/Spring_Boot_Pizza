package com.pizzaria.sis_pedido.model.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pizzaria.sis_pedido.model.entity.Usuario;

import jakarta.transaction.Transactional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    List<Usuario> findByNomeUsuario(String nomeUsuario);
    List<Usuario> findByNomeUsuarioAndPswdUsuario(String nomeUsuario, String pswdUsuario);
    
    @Transactional
    @Modifying
    @Query("update Usuario u set u.pswdUsuario = :pswdUsuario where u.idUsuario = :idUsuario")
    void setPswdUsuarioByIdUsuario(@Param(value = "pswdUsuario") String pswdUsuario, @Param(value = "idUsuario") int idUsuario);
    
}
