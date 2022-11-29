package closebyhome.closebyhome.controller;

import closebyhome.closebyhome.dto.UsuarioDto;
import closebyhome.closebyhome.dto.UsuarioLogarDto;
import closebyhome.closebyhome.models.Usuario;
import closebyhome.closebyhome.repository.FuncionarioRepository;
import closebyhome.closebyhome.repository.UsuarioRepository;
import closebyhome.closebyhome.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@SpringBootTest
class UsuarioControllerTest {

    @Autowired
    private UsuarioController controller;

    @MockBean
    private UsuarioRepository repository;

    @Test
    @DisplayName("Lista de usuarios vazia deve retornar status 204")
    void listaDeUsuariosVazia(){
        when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<UsuarioDto>> listaUsuarios = controller.listar();

        assertEquals(204, listaUsuarios.getStatusCodeValue());
        assertNull(listaUsuarios.getBody());
    }

    @Test
    @DisplayName("Retorna lista de usuarios e retorna o status 200")
    void retornaListaDeUsuarios(){
        when(repository.findAll()).thenReturn(List.of(
               new Usuario()));

        ResponseEntity<List<UsuarioDto>> listaUsuarios = controller.listar();

        assertEquals(200, listaUsuarios.getStatusCodeValue());
        assertTrue(listaUsuarios.getBody().size() > 0);

    }

//    @Test
//    @DisplayName("Verificar se o usuario ja esta cadastrado, se não, retornar o status 404")
//    void logarUsuarioInexistente(){
//        when(repository.existsById(anyInt())).thenReturn(false);
//
//       UsuarioLogarDto user = new UsuarioLogarDto();
//
//        assertThrows(ResponseStatusException.class, () ->{controller.logar(user);});
//
//
//    }

    @Test
    @DisplayName("Atualizar senha padrão recebida por email, por senha desejada, deve retornar status 404 Usuario não existe")
    void naoAlterarSenhaRecebidaQuandoUsuarioInexistente(){
        Usuario user = new Usuario();
        when(repository.existsById(anyInt())).thenReturn(false);

//        when(repository.findById(anyInt())).thenReturn(user);

        assertEquals(404, controller.atualizarSenha(user.getEmail(), user.getSenha(), "ana123").getStatusCodeValue());
        // assertNotNull(controller.atualizarSenha.getBody());

    }


}

