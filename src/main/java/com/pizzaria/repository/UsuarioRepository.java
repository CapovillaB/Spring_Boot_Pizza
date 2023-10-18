package main.java.com.pizzaria.repository;

import com.pizzaria.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Você pode adicionar métodos de consulta personalizados aqui, se necessário
}
