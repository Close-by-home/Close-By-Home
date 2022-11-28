package closebyhome.closebyhome.controller;


import closebyhome.closebyhome.dto.CondominioDto;
import closebyhome.closebyhome.dto.UsuarioDto;
import closebyhome.closebyhome.models.Condominio;
import closebyhome.closebyhome.service.CondominioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Condominio", description = "Requesição dos Condominios.")
@RestController
@RequestMapping("/condominio")
public class CondominioController {
    @Autowired
    private CondominioService condominioService;

    @GetMapping
    public ResponseEntity<List<CondominioDto>> exibirTodos(){

        List<CondominioDto> res= this.condominioService.buscarTodos();

        if (res.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(res);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<CondominioDto> cadastrar(@RequestBody @Valid CondominioDto condominioNovo) {

        CondominioDto res = this.condominioService.cadastrar(condominioNovo);

        return ResponseEntity.status(201).body(res);
    }


}
