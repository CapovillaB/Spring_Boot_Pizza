package test.java.com.pizzaria.repository;

import com.pizzaria.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void salvarUsuario() {
        // Criando um novo usuário
        Usuario usuario = new Usuario();
        usuario.setNome_usuario("João");
        usuario.setPswd_usuario("senha123");

        // Salvando o usuário no banco de dados
        usuarioRepository.save(usuario);

        // Buscando o usuário do banco de dados
        Usuario usuarioSalvo = usuarioRepository.findById(usuario.getId_usuario()).orElse(null);

        // Verificando se o usuário foi salvo corretamente
        assertThat(usuarioSalvo).isNotNull();
        assertThat(usuarioSalvo.getNome_usuario()).isEqualTo("João");
        assertThat(usuarioSalvo.getPswd_usuario()).isEqualTo("senha123");
    }
}
