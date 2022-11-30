package closebyhome.closebyhome.controller;

import closebyhome.closebyhome.dto.CondominioDto;
import closebyhome.closebyhome.dto.UsuarioDto;
import closebyhome.closebyhome.dto.UsuarioLogarDto;
import closebyhome.closebyhome.models.Condominio;
import closebyhome.closebyhome.models.Usuario;
import closebyhome.closebyhome.repository.FuncionarioRepository;
import closebyhome.closebyhome.repository.UsuarioRepository;
import closebyhome.closebyhome.service.CondominioService;
import closebyhome.closebyhome.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UsuarioControllerTest {

    @Autowired
    private UsuarioController controller;
    @Autowired
    @MockBean
    private UsuarioService service;
    @Autowired
    private CondominioService serviceC;

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

    @Test
    @DisplayName("Listar por condominio, porém lista vazia deve retornar o status 204")
    void listarPorCondominioVazia(){
        when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<UsuarioDto>> listaUsuarios = controller.listarPorCondominio(1);

        assertEquals(204, listaUsuarios.getStatusCodeValue());
        assertNull(listaUsuarios.getBody());
    }

    @Test
    @DisplayName("Listar por condominio e retornar o status 200")
    void listarPorCondominio(){
        when(repository.findAll()).thenReturn(List.of(
                new Usuario(),
                new Usuario()));

        ResponseEntity<List<UsuarioDto>> listaUsuarios = controller.listarPorCondominio(1);

        assertEquals(200, listaUsuarios.getStatusCodeValue());
        assertTrue(listaUsuarios.getBody().size() > 0);

    }


//    @Test
//    @DisplayName("Verificar se o usuario ja esta cadastrado, se não, retornar o status 404")
//    void logarUsuarioInexistente(){
//        when(repository.findByEmailAndSenhaAndCodigoCondominioCodigoCondominio("ana@hotmail.com", "senha123", "1"))
//                .equals(true);
//
//    assertEquals(200, controller.logar(""));
//
//    }

    @Test
    @DisplayName("Atualizar senha padrão recebida por email, por senha desejada, deve retornar status 204 Usuario não existe")
    void naoAlterarSenhaRecebidaQuandoUsuarioInexistente(){
        when(service.atualizarSenha("leandro@gmail.com", "senha123", "senha321")
        ).thenReturn(true);


        assertEquals(204, controller.atualizarSenha("ana@gmail.com", "senha123", "senha321").getStatusCodeValue());

    }

    @Test
    @DisplayName("Atualizar senha padrão recebida por email, por senha desejada, deve retornar status 201. Usuario existente")
    void alterarSenhaRecebidaQuandoUsuarioExistente(){
        when(service.atualizarSenha("leandro@gmail.com", "senha123", "senha321")
        ).thenReturn(true);

        assertEquals(201, controller.atualizarSenha("leandro@gmail.com", "senha123", "senha321").getStatusCodeValue());

    }

    @Test
    @DisplayName("Ativar perfil de funcionario e retornar status 200")
    void ativarFuncionario(){
        when(service.ativarContaFuncionario("ana@hotmail.com", "manicure", 45.00))
                .thenReturn(true);

        assertEquals(200, controller.ativaConta("ana@hotmail.com", "manicure", 45.00).getStatusCodeValue());
    }

    @Test
    @DisplayName("Não ativar perfil com email invalido e retornar status 404")
    void ativarFuncionarioEmailInvalido(){
        when(service.ativarContaFuncionario("ana@hotmail.com", "manicure", 45.00))
                .thenReturn(true);

        assertEquals(404, controller.ativaConta("laura@hotmail.com", "manicure", 45.00).getStatusCodeValue());
    }

//    @Test
//    @DisplayName("Cadastrar usuario e retonar status 201")
//    void cadastrarUsuario(){
//        when(serviceC.buscarCondominioPeloCodigo("1"))
//                .thenReturn(new Condominio());
//
//    }

}

