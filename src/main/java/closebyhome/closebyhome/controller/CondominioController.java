package closebyhome.closebyhome.controller;


import closebyhome.closebyhome.dto.CondominioDto;
import closebyhome.closebyhome.dto.UsuarioDto;
import closebyhome.closebyhome.dto.UsuarioDtoCadastro;
import closebyhome.closebyhome.listaObj.ListaObj;
import closebyhome.closebyhome.models.Condominio;
import closebyhome.closebyhome.service.CondominioService;
import closebyhome.closebyhome.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Condominio", description = "Requesição dos Condominios.")
@RestController
@RequestMapping("/condominio")
public class CondominioController {
    @Autowired
    private CondominioService condominioService;
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping
    public ResponseEntity<List<CondominioDto>> exibirTodos() {

        List<CondominioDto> res = this.condominioService.buscarTodos();

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


    @PostMapping(value = "/import-usuarios/{idCondominio}", consumes = "multipart/form-data")
    public ResponseEntity cadastroUsuarioAutomatico(
            @RequestBody MultipartFile novosUsuarios,
            @PathVariable String idCondominio
    ) throws IOException {
        String nomeArq = novosUsuarios.getResource().getFilename();
        System.out.println(nomeArq);

        byte[] teste = novosUsuarios.getBytes();
        String teste2 = new String(teste);

        String[] split = teste2.split("\n");

        List<UsuarioDtoCadastro> usuarios = new ArrayList<>();
        boolean header = true;
        Condominio condominio = condominioService.buscarCondominioPeloCodigo(idCondominio);
        for (String x : split) {
            if(header){
                header = false;
            }
            else{

                System.out.println("-".repeat(10));

                System.out.println(x);

                usuarios.add(new UsuarioDtoCadastro(x));

                usuarioService.cadastrar(new UsuarioDtoCadastro(x),condominio);
            }

        }

        usuarios.forEach(user -> {

            System.out.println("Nome: " + user.getNome());
        });
        return ResponseEntity.status(200).build();
    }

    @GetMapping(value = "/arquivo-exemplo",produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity<byte[]> download(){
        String teste = "NOME;CPF;TELEFONE;BLOCO;EMAIL;SENHA" +"\n" +
                "usuario;9999999999;11999999999;B9;usuario@gmail.com;senha123";
        byte[] arquivo = teste.getBytes();

        return ResponseEntity.status(200).header("content-disposition", "attachment; filename=\"arquivo_exemplo.csv\"").body(arquivo);
    }
}
