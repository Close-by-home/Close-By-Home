package closebyhome.closebyhome.controller;

import closebyhome.closebyhome.dto.FuncionarioDto;
import closebyhome.closebyhome.dto.UsuarioDto;
import closebyhome.closebyhome.models.Funcionario;
import closebyhome.closebyhome.models.Usuario;
import closebyhome.closebyhome.repository.FuncionarioRepository;
import closebyhome.closebyhome.repository.UsuarioRepository;
import closebyhome.closebyhome.service.FuncionarioService;
import closebyhome.closebyhome.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class FuncionarioControllerTest {

    @Autowired
    private FuncionarioController controller;

    @Autowired
    @MockBean
    private FuncionarioRepository repository;

    @MockBean
    private FuncionarioService service;

    @Test
    @DisplayName("Lista de funcionarios vazia deve retornar status 204")
    void listaDeUsuariosVazia() {
        when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<FuncionarioDto>> listaUsuarios = controller.listar();

        assertEquals(204, listaUsuarios.getStatusCodeValue());
        assertNull(listaUsuarios.getBody());
    }

    @Test
    @DisplayName("Lista de funcionarios e retorna o status 200")
    void retornaListaDeUsuarios() {
        when(service.buscarTodosFuncionarios()).thenReturn(List.of(new FuncionarioDto()));

        ResponseEntity<List<FuncionarioDto>> listaUsuarios = controller.listar();

        assertEquals(200, listaUsuarios.getStatusCodeValue());
        assertTrue(listaUsuarios.getBody().size() > 0);
    }

    @Test
    @DisplayName("Lista de funcionarios vazia atraves da busca por condominio e deve retornar status 204")
    void listarFuncionariosPorCondominioVazia() {
        when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<FuncionarioDto>> listaUsuarios = controller.listarPorCondominio(String.valueOf(1));

        assertEquals(204, listaUsuarios.getStatusCodeValue());
        assertNull(listaUsuarios.getBody());
    }

    @Test
    @DisplayName("\"Lista de funcionarios atraves da busca por condominio e deve retornar status 200")
    void listarFuncionariosPorCondominio() {
        when(service.buscarPorCondominio(String.valueOf(1))).thenReturn(List.of(new FuncionarioDto()));

        ResponseEntity<List<FuncionarioDto>> listaUsuarios = controller.listarPorCondominio(String.valueOf(1));

        assertEquals(200, listaUsuarios.getStatusCodeValue());
        assertTrue(listaUsuarios.getBody().size() > 0);
    }

    @Test
    @DisplayName("Listar funcionarios atraves de busca pelo nome e retonar status 200")
    void buscarFuncionarioPeloNome() {
        when(service.buscarFuncionarioPeloNome("Augusto")).thenReturn(List.of(new FuncionarioDto()));

        ResponseEntity<List<FuncionarioDto>> listaUsuarios = controller.listarPorNome("Augusto");

        assertEquals(200, listaUsuarios.getStatusCodeValue());
        assertTrue(listaUsuarios.getBody().size() > 0);
    }

    @Test
    @DisplayName("Listar funcionarios atraves de busca pelo nome e retonar status 204")
    void buscarFuncionarioPeloNomeErrado() {
        when(service.buscarFuncionarioPeloNome("Augusto")).thenReturn(List.of(new FuncionarioDto()));

        ResponseEntity<List<FuncionarioDto>> listaUsuarios = controller.listarPorNome("Agusto");

        assertEquals(204, listaUsuarios.getStatusCodeValue());
        assertNull(listaUsuarios.getBody());
    }

    @Test
    @DisplayName("Listar funcionarios atraves de busca pelo nome e serviço e retonar status 200")
    void buscarFuncionarioPeloNomeServico() {
        when(service.buscarFuncionarioPeloServicoEnome("manicure", "Joyce")).thenReturn(List.of(new FuncionarioDto()));

        ResponseEntity<List<FuncionarioDto>> listaUsuarios = controller.listarPorNomeEServico("manicure", "Joyce");

        assertEquals(200, listaUsuarios.getStatusCodeValue());
        assertTrue(listaUsuarios.getBody().size() > 0);
    }

    @Test
    @DisplayName("Listar funcionarios atraves de busca pelo nome e serviço, porém com o nome invalido deve retonar status 204")
    void buscarFuncionarioPeloNomeServicoComNomeInvalido() {
        when(service.buscarFuncionarioPeloServicoEnome("manicure", "Joyce")).thenReturn(List.of(new FuncionarioDto()));

        ResponseEntity<List<FuncionarioDto>> listaUsuarios = controller.listarPorNomeEServico("manicure", "Carla");

        assertEquals(204, listaUsuarios.getStatusCodeValue());
        assertNull(listaUsuarios.getBody());
    }

    @Test
    @DisplayName("Listar funcionarios atraves de busca pelo nome e serviço, porém com o serviço invalido deve retonar status 204")
    void buscarFuncionarioPeloNomeServicoComServicoInvalido() {
        when(service.buscarFuncionarioPeloServicoEnome("manicure", "Joyce")).thenReturn(List.of(new FuncionarioDto()));

        ResponseEntity<List<FuncionarioDto>> listaUsuarios = controller.listarPorNomeEServico("jardineira", "Carla");

        assertEquals(204, listaUsuarios.getStatusCodeValue());
        assertNull(listaUsuarios.getBody());
    }

    @Test
    @DisplayName("Listar funcionarios atraves de busca pelo serviço e retonar status 200")
    void buscarFuncionarioPeloServico() {
        when(service.buscarFuncionarioPeloServico("porteiro")).thenReturn(List.of(new FuncionarioDto()));

        ResponseEntity<List<FuncionarioDto>> listaUsuarios = controller.listarPorServico("porteiro");

        assertEquals(200, listaUsuarios.getStatusCodeValue());
        assertTrue(listaUsuarios.getBody().size() > 0);
    }

    @Test
    @DisplayName("Listar funcionarios atraves de busca pelo serviço errado e retonar status 204")
    void buscarFuncionarioPeloServicoErrado() {
        when(service.buscarFuncionarioPeloServico("porteiro")).thenReturn(List.of(new FuncionarioDto()));

        ResponseEntity<List<FuncionarioDto>> listaUsuarios = controller.listarPorServico("jardineiro");

        assertEquals(204, listaUsuarios.getStatusCodeValue());
        assertNull(listaUsuarios.getBody());
    }
}
