package closebyhome.closebyhome.controller;

import closebyhome.closebyhome.dto.CondominioDto;
import closebyhome.closebyhome.dto.FuncionarioDto;
import closebyhome.closebyhome.models.Funcionario;
import closebyhome.closebyhome.service.FuncionarioService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Funcionario", description = "Requesição dos Funcionarios.")
@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public Boolean entrar(){

        Boolean res = true ;

        return res;
    }

    @PostMapping("{idUsario}")
    public ResponseEntity<FuncionarioDto> cadastrarFuncionario(
            @RequestBody @Valid FuncionarioDto novoFuncionario,
            @PathVariable int idUsario
    ){

        FuncionarioDto res = funcionarioService.cadastrarFuncionario(novoFuncionario,idUsario);

        if (res != null) {
            return ResponseEntity.status(201).body(res);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }



}
