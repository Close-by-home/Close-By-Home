package closebyhome.closebyhome.controller;

import closebyhome.closebyhome.dto.FuncionarioDto;
import closebyhome.closebyhome.dto.UsuarioDto;
import closebyhome.closebyhome.service.FuncionarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Funcionario", description = "Requesição dos Funcionarios.")
@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;
    @GetMapping
    public ResponseEntity<List<FuncionarioDto>> listar() {
        List<FuncionarioDto> res = this.funcionarioService.buscarTodosFuncionarios();
        if (res.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(res);
    }

    @GetMapping("{idCondominio}")
    public ResponseEntity<List<FuncionarioDto>> listarPorCondominio(@PathVariable Integer idCondominio) {
        List<FuncionarioDto> res = this.funcionarioService.buscarPorCondominio(idCondominio);

        if (res.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(res);
    }

    @GetMapping("buscaPorNome/{nome}")
    public ResponseEntity<List<FuncionarioDto>> listarPorNome(@PathVariable String nome) {
        List<FuncionarioDto> res = this.funcionarioService.buscarFuncionarioPeloNome(nome);
        if (res.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(res);
    }

    @GetMapping("buscaPorServico/{servico}")
    public ResponseEntity<List<FuncionarioDto>> listarPorServico(@PathVariable String servico) {
        List<FuncionarioDto> res = this.funcionarioService.buscarFuncionarioPeloNome(servico);
        if (res.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(res);
    }

    @GetMapping("buscaPorNomeEServico/{nome}/{servico}")
    public ResponseEntity<List<FuncionarioDto>> listarPorNomeEServico(@PathVariable String nome,@PathVariable String servico) {
        List<FuncionarioDto> res = this.funcionarioService.buscarFuncionarioPeloServicoEnome(nome, servico);
        if (res.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(res);
    }

}
