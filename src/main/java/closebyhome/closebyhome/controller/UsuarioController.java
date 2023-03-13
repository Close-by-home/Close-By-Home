package closebyhome.closebyhome.controller;

import closebyhome.closebyhome.dto.UsuarioDto;
import closebyhome.closebyhome.dto.UsuarioDtoCadastro;
import closebyhome.closebyhome.dto.UsuarioLogarDto;
import closebyhome.closebyhome.listaObj.ListaObj;
import closebyhome.closebyhome.models.Condominio;
import closebyhome.closebyhome.models.Funcionario;
import closebyhome.closebyhome.models.Usuario;
import closebyhome.closebyhome.repository.UsuarioRepository;
import closebyhome.closebyhome.service.CondominioService;
import closebyhome.closebyhome.service.FuncionarioService;
import closebyhome.closebyhome.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Usuario", description = "Requesição dos Usuario.")
@CrossOrigin(origins = "http://3.221.81.114:8080", maxAge = 3600)
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private CondominioService condominioService;

    //region Listar
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listar() {

        List<UsuarioDto> res = this.usuarioService.buscar();

        if (res.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(res);
    }
    @GetMapping("{idCondominio}")
    public ResponseEntity<List<UsuarioDto>> listarPorCondominio(@PathVariable Integer idCondominio) {
        List<UsuarioDto> res = this.usuarioService.buscarPorCondominio(idCondominio);

        if (res.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(res);
    }
    //endregion

    //region Logar
    @PostMapping("/logar")
    public ResponseEntity<UsuarioDto> logar(@RequestBody @Valid UsuarioLogarDto usuario) {

        UsuarioDto res = this.usuarioService.buscarUsuario(usuario);

        if (res != null) {
            return ResponseEntity.status(200).body(res);
        } else {
            return ResponseEntity.status(204).build();
        }
    }
    //endregion

    //region Senhas
    @PutMapping("atualizar-senha/{email}/{senhaAtual}/{novaSenha}")
    public ResponseEntity<String> atualizarSenha(
            @PathVariable String email,
            @PathVariable String senhaAtual,
            @PathVariable String novaSenha
    ) {

        Boolean senhaAtualizada = this.usuarioService.atualizarSenha(email, senhaAtual, novaSenha);

        if (senhaAtualizada == true) {
            return ResponseEntity.status(201).body("Senha atualizada com sucesso!");
        } else {
            return ResponseEntity.status(204).body("Email ou senha invalidos");
        }
    }

//    @PutMapping("atualizar-senha-esquecida/{codCondominio}/{email}/{novaSenha}")
//    public ResponseEntity<String> atualizarSenhaEsquicida(
//            @PathVariable String codCondominio,
//            @PathVariable String email,
//            @PathVariable String novaSenha
//
//    ) {
//
//        Boolean senhaAtualizada = this.usuarioService.atualizarSenhaEsquecida(codCondominio, email,
//                novaSenha);
//
//        if (senhaAtualizada == true) {
//            return ResponseEntity.status(200).body("Senha atualizada com sucesso!");
//        } else {
//            return ResponseEntity.status(404).body("Email ou senha invalidos");
//        }
//    }
    //endregion

    //region Cadastrar e ativar perfil
    @PutMapping("/ativar-perfil-funcionario/{email}/{servico}/{valorMin}")
    public ResponseEntity<Boolean> ativaConta(
            @PathVariable String email,
            @PathVariable String servico,
            @PathVariable Double valorMin) {

        Boolean res = this.usuarioService.ativarContaFuncionario(email,servico,valorMin);

        if (res == true) {
            return ResponseEntity.status(200).body(res);
        } else {
            return ResponseEntity.status(404).body(res);
        }
    }

    @PostMapping("/cadastrar/{codigoCondominio}")
    public ResponseEntity<UsuarioDto> cadastrar(
            @RequestBody @Valid UsuarioDtoCadastro novoUsuario,
            @PathVariable String codigoCondominio
    ) {
        Condominio condominio = this.condominioService.buscarCondominioPeloCodigo(codigoCondominio);
        UsuarioDto res = new UsuarioDto();
        if(condominio != null){
            res = this.usuarioService.cadastrar(novoUsuario,condominio);

            return ResponseEntity.status(201).body(res);
        }
        else{
            return  ResponseEntity.status(204).body(res);
        }



    }
    //endregion


    //region CSV
    @GetMapping("/grava-arquivo-csv")
    public ResponseEntity< List<UsuarioDto>> grava(){

        List<UsuarioDto> res = this.usuarioService.buscar();
//
//        ListaObj<UsuarioDto> listaObjUsuarios = new ListaObj<UsuarioDto>(res.size());
        ListaObj<UsuarioDto> listaObjUsuarios = this.usuarioService.listaEmObj();
//        for (UsuarioDto user : res){
//            listaObjUsuarios.adiciona(user);
//        }
        this.usuarioService.gravaArquivoCsv( listaObjUsuarios, "ListaDeUsuario");

        if (res != null) {
            return ResponseEntity.status(200).body(res);
        } else {
            return ResponseEntity.status(404).build();
        }

    }
    //endregion

    @PutMapping("atualizar/email/{cpf}/{email}/{novoEmail}")
    public ResponseEntity<String> atualizarEmail(
            @PathVariable String cpf,
            @PathVariable String email,
            @PathVariable String novoEmail
    ){
        Boolean res = usuarioService.atualizarEmail(cpf, email, novoEmail);
        if (res) {
            return ResponseEntity.status(200).body("Email atualizado com sucesso!");
        } else {
            return ResponseEntity.status(404).body("Email ou cpf invalidos");
        }
    }

    @PutMapping("atualizar/imagem/{cpf}/{email}")
    public ResponseEntity<String> atualizarImagem(
            @PathVariable String cpf,
            @PathVariable String email,
            @RequestBody String novaImagem
    ){
        Boolean res = usuarioService.atualizarImagem(cpf, email, novaImagem);
        if (res) {
            return ResponseEntity.status(200).body("Email atualizado com sucesso!");
        } else {
            return ResponseEntity.status(404).body("Email ou cpf invalidos");
        }
    }
    @PutMapping("atualizar/telefone/{cpf}/{email}/{novoNumero}")
    public ResponseEntity<String> atualizarNumero(
            @PathVariable String cpf,
            @PathVariable String email,
            @PathVariable String novoNumero
    ){
        Boolean res = usuarioService.atualizarNumero(cpf, email, novoNumero);
        if (res) {
            return ResponseEntity.status(200).body("Numero atualizado com sucesso!");
        } else {
            return ResponseEntity.status(404).body("Email ou cpf invalidos");
        }
    }
}

