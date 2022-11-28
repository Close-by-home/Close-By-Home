package closebyhome.closebyhome.controller;

import closebyhome.closebyhome.dto.UsuarioDto;
import closebyhome.closebyhome.dto.UsuarioLogarDto;
import closebyhome.closebyhome.listaObj.ListaObj;
import closebyhome.closebyhome.models.Condominio;
import closebyhome.closebyhome.models.Funcionario;
import closebyhome.closebyhome.models.Usuario;
import closebyhome.closebyhome.repository.UsuarioRepository;
import closebyhome.closebyhome.service.CondominioService;
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
@CrossOrigin(origins =  "http://localhost:3000",maxAge = 3600)
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
    public ResponseEntity<UsuarioDto> logar(@RequestBody @Valid UsuarioLogarDto novoUsuario) {

        UsuarioDto res = this.usuarioService.buscarUsuario(novoUsuario);

        if (res != null) {
            return ResponseEntity.status(200).body(res);
        } else {
            return ResponseEntity.status(404).build();
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
            return ResponseEntity.status(200).body("Senha atualizada com sucesso!");
        } else {
            return ResponseEntity.status(404).body("Email ou senha invalidos");
        }
    }

    @PutMapping("atualizar-senha-esquecida/{codCondominio}/{email}/{novaSenha}/{repSenha}")
    public ResponseEntity<String> atualizarSenhaEsquicida(
            @PathVariable String codCondominio,
            @PathVariable String email,
            @PathVariable String novaSenha,
            @PathVariable String repSenha

    ) {

        Boolean senhaAtualizada = this.usuarioService.atualizarSenhaEsquecida(codCondominio, email,
                novaSenha, repSenha);

        if (senhaAtualizada == true) {
            return ResponseEntity.status(200).body("Senha atualizada com sucesso!");
        } else {
            return ResponseEntity.status(404).body("Email ou senha invalidos");
        }
    }
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

    //region TXT
    public static void gravaRegistro(String registro, String nomeArq) {
        BufferedWriter saida = null;

        // try-catch para abrir o arquivo
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            erro.printStackTrace();
        }

        // try-catch para gravar e fechar o arquivo
        try {
            saida.append(registro + "\n");
            saida.close();
        }
        catch (IOException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
        }
    }

    public static void gravaArquivoTxt(List<Usuario> listaUsuario, List<Funcionario> listaFunc, String nomeArq) {
        int contaRegDados = 0;

        // Monta o registro de header
        String header = "00USUARIO";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";
        // Grava o registro de header
        gravaRegistro(header, nomeArq);

        // Monta e grava os registros de corpo
        String corpo;
        for (Usuario user : listaUsuario) {
            corpo = "02";
            corpo += String.format("%04d", user.getId());
            corpo += String.format("%-45.45s", user.getNome());
            corpo += String.format("%-25.25s", user.getSenha());
            corpo += String.format("%-15.15s", user.getCpf());
            corpo += String.format("%-45.45s", user.getEmail());
            corpo += String.format("%-15.15s", user.getTelefone());
            corpo += String.format("%-15.15s", user.getCodigoCondominio());
            corpo += String.format("%-2.2s", user.getBloco());
            corpo += String.format("%-5.5b", user.getFuncionario());
            corpo += String.format("%1.1s", user.getSexo());

            contaRegDados++;
            gravaRegistro(corpo, nomeArq);
        }


        for (Funcionario func : listaFunc){
            corpo = "03";
            corpo += String.format("%04d", func.getId());
            corpo += String.format("%-45.45s", func.getNomeServico());
            corpo += String.format("%06.2f", func.getValorMinimo());
            corpo += String.format("%04d", func.getIdUsuario());

            contaRegDados++;
            gravaRegistro(corpo, nomeArq);
        }

        // Monta e grava o registro de trailer
        String trailer = "01";
        trailer += String.format("%010d", contaRegDados);
        gravaRegistro(trailer, nomeArq);
    }

    public static void leArquivoTxt(String nomeArq) {
        BufferedReader entrada = null;
        String registro, tipoRegistro;
        String nome, senha, cpf, email, telefone, codigoCondominio, bloco, sexo;
        Integer id;
        Boolean funcionario;
        Integer contaRegDadoLido = 0;
        Integer qtdRegDadoGravadoTrailer;

        // Cria uma lista com os dados lidos do arquivo
        List<Usuario> listaLida = new ArrayList();

        // try-catch para abrir o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo!");
            erro.printStackTrace();
        }

        // try-catch para ler e fechar o arquivo
        try {
            registro = entrada.readLine();       // Lê o 1o registro

            while (registro != null) {
                    tipoRegistro = registro.substring(0,2);
                if (tipoRegistro.equals("00")) {
                    System.out.println("Registro de header");
                    System.out.println("Tipo de arquivo: " + registro.substring(2,11));
                    System.out.println("Data e hora de gravação: " + registro.substring(11,31));
                    System.out.println("Versão do documento: " + registro.substring(31,34));

                }
                else if (tipoRegistro.equals("01")) {
                    System.out.println("Registro de trailer");
                    qtdRegDadoGravadoTrailer = Integer.valueOf(registro.substring(2, 12));
                    if (contaRegDadoLido == qtdRegDadoGravadoTrailer) {
                        System.out.println("Quantidade de registros lidos compatível com " +
                                "quantidade de registros gravados");
                    }
                    else {
                        System.out.println("Quantidade de registros lidos incompatível com " +
                                "quantidade de registros gravados");
                    }
                }
                else if (tipoRegistro.equals("02")) {
                    System.out.println("Registro de corpo");
                    id = Integer.valueOf(registro.substring(2, 6));
                    nome = registro.substring(6, 52).trim();
                    senha = registro.substring(52, 78).trim();
                    cpf = registro.substring(78, 94).trim();
                    email = registro.substring(94, 139).trim();
                    telefone = registro.substring(139, 155).trim();
                    codigoCondominio = registro.substring(155, 171).trim();
                    bloco = registro.substring(171, 172).trim();
                    funcionario = Boolean.valueOf(registro.substring(172, 178).trim());
                    sexo = registro.substring(178, 179).trim();

                    // Incrementa o contador de registros lidos
                    contaRegDadoLido++;

//                    Usuario user = new Usuario(id, nome, senha, cpf, email, telefone, codigoCondominio, bloco, funcionario, sexo);
//
//                    // No Projeto de PI
//                    // repository.save(a);
//                    usuarioRepository.save(user);
                }
                
                else {
                    System.out.println("Tipo de registro inválido!");
                }
                // Lê o próximo registro
                registro = entrada.readLine();
            }
            entrada.close();
        }
        catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo");
            erro.printStackTrace();
        }

        // Exibe o conteúdo da lista lida
        System.out.println("Conteúdo da lista lida do arquivo");
        for (Usuario user : listaLida) {
            System.out.println(user);
        }
    }


}

