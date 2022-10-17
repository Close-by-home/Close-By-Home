package closebyhome.closebyhome.controller;

import closebyhome.closebyhome.dto.UsuarioDto;
import closebyhome.closebyhome.listaObj.ListaObj;
import closebyhome.closebyhome.models.Condominio;
import closebyhome.closebyhome.service.CondominioService;
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
    @Autowired
    private CondominioService condominioService;
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listar() {

        List<UsuarioDto> res = this.usuarioService.buscar();

        if (res.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(res);
    }

    @GetMapping("/logar/{codCondominio}/{email}/{senha}")
    public ResponseEntity<UsuarioDto> logar(@PathVariable String codCondominio,
                                         @PathVariable String email,
                                         @PathVariable String senha) {

        UsuarioDto res = this.usuarioService.buscarUsuario(codCondominio, email, senha);

        if (res != null) {
            return ResponseEntity.status(200).body(res);
        } else {
            return ResponseEntity.status(404).build();
        }


    }

    @PostMapping("/cadastrar/{idCondominio}")
    public ResponseEntity<UsuarioDto> cadastrar(
        @RequestBody @Valid UsuarioDto novoUsuario,
        @PathVariable String idCondominio
    ) {
        Condominio codigo = this.condominioService.buscarCondominio(idCondominio);
        UsuarioDto res = new UsuarioDto();
        if(codigo != null){
            res = this.usuarioService.cadastrar(novoUsuario,codigo);
            return ResponseEntity.status(201).body(res);
        }
        else{
            return  ResponseEntity.status(404).body(res);
        }



    }

    @PutMapping("atualizar-senha/{email}/{senhaAtual}/{novaSenha}")
    public ResponseEntity<String> atualizarSenha(
            @PathVariable String email,
            @PathVariable String senhaAtual,
            @PathVariable String novaSenha
    ) {

        Boolean senhaAtualizada = this.usuarioService.atualizarSenha(email, senhaAtual, novaSenha);

        if (senhaAtualizada == true) {
            return ResponseEntity.status(200).body("Senha atualizada com sucesso!");
        } else {
            return ResponseEntity.status(404).body("Email ou senha invalidos");
        }
    }

    @PutMapping("/ativar-perfil-funcionario/{email}")
    public ResponseEntity<Boolean> ativaConta(
            @PathVariable String email) {

        Boolean res = this.usuarioService.ativarContaFuncionario(email);

        if (res == true) {
            return ResponseEntity.status(200).body(res);
        } else {
            return ResponseEntity.status(404).body(res);
        }
    }

    public boolean comparaLetra(String letra1, String letra2) {
        if (letra1.charAt(0) > letra2.charAt(0)) {
            return true;
        }
        return false;
    }

    public ListaObj<UsuarioDto> listaEmObj() {

        List<UsuarioDto> usuariosDto = this.usuarioService.buscar();
        ListaObj<UsuarioDto> listaObjUsuarios = new ListaObj<>(usuariosDto.size());

        if (usuariosDto.isEmpty()) {
            return null;
        }


        //ORDENAÇÃO POR ORDEM ALFABÉTICA
        //Como funciona?
        //Ele roda 3 fors para organizar de na ordem alfabética de A-Z

        //O primeiro for roda 10 vezes para que os próximos vetores ordenem 10 vezes
        //comparando posições de letras diferentes
        //Ex: a primeira ordenação compara os nomes i com j comparando a letra na posição 0,
        //a segunda ordenação compara os nomes i com j com a letra na posição 1, e por ai vai...

        //O segundo e terceiro for rodam para que seja comparados os valores dos nomes nas
        // posições I e J. E se a letra(C) do obj I for maior que a letra(C) do obj J, então
        //os ojetos trocam de posição
        //OBS: C é o valor do primeiro for, a posição a se checar das letras
        //Ex: Leandro(i) e Ana(j), "L"(C) é maior que "A"(C), então o obj Leandro trocaria de
        //lugar com Ana

        UsuarioDto aux = new UsuarioDto();
        for (int c = 0; c < 10; c++) {
            for (int i = 0; i < usuariosDto.size(); i++) {
                for (int j = 0; j < usuariosDto.size(); j++) {

                    //validação para ver qual é o menor nome e guardar a quantidade de caracteres
                    //para evitar que o primeiro for valide uma posição de letra que não existe
                    int menorValorDeChar = 0;
                    if (usuariosDto.get(i).getNome().length() < usuariosDto.get(j).getNome().length()) {
                        menorValorDeChar = usuariosDto.get(i).getNome().length();
                    } else {
                        menorValorDeChar = usuariosDto.get(j).getNome().length();
                    }

                    menorValorDeChar--;//diminuindo o valor para ter o mesmo tamanho de indices
                    if (c <= menorValorDeChar) {
                        if (usuariosDto.get(i).getNome().charAt(c) < usuariosDto.get(j).getNome().charAt(c)) {

                            aux = usuariosDto.get(j);
                            usuariosDto.set(j, usuariosDto.get(i));
                            usuariosDto.set(i, aux);
                        }
                    }

                }
            }
        }

        //adicionando na listaObj a lista Dto após a ordenação
        for (int i = 0; i < usuariosDto.size(); i++) {
            listaObjUsuarios.adiciona(usuariosDto.get(i));
        }

        return listaObjUsuarios;
    }


}

