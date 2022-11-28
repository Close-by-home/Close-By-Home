package closebyhome.closebyhome.controller;

import closebyhome.closebyhome.dto.FuncionarioDto;
import closebyhome.closebyhome.repository.FuncionarioRepository;
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

    @MockBean
    private FuncionarioRepository repository;

//@Test
//    @DisplayName("Cadastra usuario e retorna o status 200")
//    void cadastrarUsuariocomsucesso(){
//    when(repository.findById(anyInt())).thenReturn(true)
}
//    @PostMapping("{idUsario}")
//    public ResponseEntity<FuncionarioDto> cadastrarFuncionario(
//            @RequestBody @Valid FuncionarioDto novoFuncionario,
//            @PathVariable int idUsario
//    ){
//
//        FuncionarioDto res = funcionarioService.cadastrarFuncionario(novoFuncionario,idUsario);
//
//        if (res != null) {
//            return ResponseEntity.status(201).body(res);
//        } else {
//            return ResponseEntity.status(404).body(null);
//        }
//    }

//@Test
//@DisplayName("Não retorna lista de motoristas e retorna o status 204")
//void retornaComFalha(){
//
//    when(repository.findAll()).thenReturn(new ArrayList<>());
//
//    ResponseEntity<List<Motorista>> listaMotoristas = controller.get();
//
//    assertEquals(204, listaMotoristas.getStatusCodeValue());
//    assertNull(listaMotoristas.getBody());
//}
//
//    @Test
//    @DisplayName("Retorna lista de motoristas e retorna o status 200")
//    void retornaComSucesso(){
//
//        when(repository.findAll()).thenReturn(List.of(
//                new Motorista(),
//                new Motorista()
//        ));
//
//        ResponseEntity<List<Motorista>> listaMotoristas = controller.get();
//
//        assertEquals(200, listaMotoristas.getStatusCodeValue());
//        assertTrue(listaMotoristas.getBody().size() > 0);
//    }

