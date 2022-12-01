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
@CrossOrigin(origins = "http://10.18.6.31:3000", maxAge = 3600)
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

    @GetMapping("{codigoCondominio}")
    public ResponseEntity<List<FuncionarioDto>> listarPorCondominio(@PathVariable String codigoCondominio) {
        List<FuncionarioDto> res = this.funcionarioService.buscarPorCondominio(codigoCondominio);

        if (res.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(res);
    }

    @GetMapping("buscaPorNome/{nome}/{codigoCondominio}")
    public ResponseEntity<List<FuncionarioDto>> listarPorNome(@PathVariable String nome, @PathVariable String codigoCondominio) {
        List<FuncionarioDto> res = this.funcionarioService.buscarFuncionarioPeloNome(nome,codigoCondominio);
        if (res.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(res);
    }


    @GetMapping("buscaPorServico/{servico}/{codigoCondominio}")
    public ResponseEntity<List<FuncionarioDto>> listarPorServico(@PathVariable String servico, @PathVariable String codigoCondominio) {
        List<FuncionarioDto> res = this.funcionarioService.buscarFuncionarioPeloServico(servico, codigoCondominio);

        if (res.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(res);
    }


    @GetMapping("buscaPorNomeEServico/{nome}/{servico}/{codigoCondominio}")
    public ResponseEntity<List<FuncionarioDto>> listarPorNomeEServico(@PathVariable String nome,@PathVariable String servico,@PathVariable String codigoCondominio) {
        List<FuncionarioDto> res = this.funcionarioService.buscarFuncionarioPeloServicoEnome(nome, servico,codigoCondominio);

        if (res.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(res);
    }

}
