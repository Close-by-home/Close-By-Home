package closebyhome.closebyhome.controller;

import closebyhome.closebyhome.dto.CondominioDto;
import closebyhome.closebyhome.dto.FuncionarioDto;
import closebyhome.closebyhome.dto.UsuarioDto;
import closebyhome.closebyhome.dto.UsuarioLogarDto;
import closebyhome.closebyhome.models.Usuario;
import closebyhome.closebyhome.repository.CondominioRepository;
import closebyhome.closebyhome.service.CondominioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CondominioControllerTest {

    @Autowired
    private CondominioController controller;

    @Autowired
    @MockBean
    private CondominioRepository repository;

    @MockBean
    private CondominioService service;

    @Test
    @DisplayName("Lista de condominios vazia deve retornar status 204")
    void listaDeCondominiosVazia() {
        when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<CondominioDto>> listaCondominios = controller.exibirTodos();

        assertEquals(204, listaCondominios.getStatusCodeValue());
        assertNull(listaCondominios.getBody());
    }

    @Test
    @DisplayName("Lista de condominios e retornar status 200")
    void listaDeCondominios() {
        when(service.buscarTodos()).thenReturn(List.of(new CondominioDto()));

        ResponseEntity<List<CondominioDto>> listaCondominios = controller.exibirTodos();

        assertEquals(200, listaCondominios.getStatusCodeValue());
        assertTrue(listaCondominios.getBody().size() > 0);
    }

    @Test
    @DisplayName("Cadastrar condominio e retornar status 201")
    void cadastrarCondominio(){
        CondominioDto cond = new CondominioDto();
        when(service.cadastrar(cond)).thenReturn(new CondominioDto());

        assertEquals(201, controller.cadastrar(cond).getStatusCodeValue());
    }

    }



