package closebyhome.closebyhome.controller;

import closebyhome.closebyhome.dto.UsuarioDto;
import closebyhome.closebyhome.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Usuario", description = "Requesição dos Usuario.")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listar(){

        List<UsuarioDto> res  = this.usuarioService.buscar();

        if (res.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(res);
    }

    @GetMapping("/logar/{codCondominio}/{email}/{senha}")
    public ResponseEntity<Boolean> logar(@PathVariable String codCondominio,
                                         @PathVariable String email,
                                         @PathVariable String senha){

        Boolean res = this.usuarioService.buscarUsuario(codCondominio,email,senha);

        if(res == true){
            return ResponseEntity.status(200).body(res);
        }
        else{
            return ResponseEntity.status(404).build();
        }


    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioDto novoUsuario) {

        UsuarioDto res = this.usuarioService.cadastrar(novoUsuario);

        return ResponseEntity.status(201).body(res);
    }

    @PutMapping("atualizar-senha/{email}/{senhaAtual}/{novaSenha}")
    public ResponseEntity<String> atualizarSenha(
            @PathVariable String email,
            @PathVariable String senhaAtual,
            @PathVariable String novaSenha
            ){

        Boolean senhaAtualizada = this.usuarioService.atualizarSenha(email,senhaAtual,novaSenha);

        if(senhaAtualizada == true){
            return ResponseEntity.status(200).body("Senha atualizada com sucesso!");
        }
        else{
            return ResponseEntity.status(404).body("Email ou senha invalidos");
        }
    }
    @PutMapping("/ativar-perfil-funcionario/{email}")
    public ResponseEntity<Boolean> ativaConta(
            @PathVariable String email){

        Boolean res =  this.usuarioService.ativarContaFuncionario(email);

        if(res == true){
            return ResponseEntity.status(200).body(res);
        }
        else{
            return ResponseEntity.status(404).body(res);
        }
    }
}

