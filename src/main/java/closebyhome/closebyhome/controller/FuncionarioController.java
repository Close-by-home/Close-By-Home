package closebyhome.closebyhome.controller;

import closebyhome.closebyhome.service.FuncionarioService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Funcionario", description = "Requesição dos Funcionarios.")
@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public Boolean entrar(){

        Boolean res =true ;

        return res;
    }
}
