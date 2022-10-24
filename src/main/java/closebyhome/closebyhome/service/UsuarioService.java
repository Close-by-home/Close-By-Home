package closebyhome.closebyhome.service;

import closebyhome.closebyhome.dto.UsuarioDto;
import closebyhome.closebyhome.dto.UsuarioDtoFactory;
import closebyhome.closebyhome.listaObj.ListaObj;
import closebyhome.closebyhome.models.Condominio;
import closebyhome.closebyhome.models.Usuario;
import closebyhome.closebyhome.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    //region Cadastrar/Ativar
    public UsuarioDto cadastrar(UsuarioDto res, Condominio condominio){
        Usuario user = new Usuario() ;

        user.setFuncionario(false);
        user.setBloco(res.getBloco());
        user.setCpf(res.getCpf());
        user.setEmail(res.getEmail());
        user.setCodigoCondominio(condominio);
        user.setNome(res.getNome());
        user.setSenha(res.getSenha());
        user.setTelefone(res.getTelefone());
        this.usuarioRepository.save(user);

        return res;
    }
    public Boolean ativarContaFuncionario(String email){
        Usuario usuario = buscarIdLogado(email);
        if (usuario != null) {
            usuario.setFuncionario(true);
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }
    //endregion

    //region Senhas
    public Boolean atualizarSenha(String email, String senhaAtual, String novaSenha) {

        Usuario usuario = buscarId(email,senhaAtual);
        if (usuario != null) {
            usuario.setSenha(novaSenha);
            usuarioRepository.save(usuario);

            return true;
        }
        return false;
    }
    public Boolean atualizarSenhaEsquecida(String email, String codCondominio,
                                           String novaSenha, String repSenha) {

        Usuario usuario = buscarId(email, codCondominio);
        if (usuario != null && novaSenha.equalsIgnoreCase(repSenha)) {
            usuario.setSenha(novaSenha);
            usuarioRepository.save(usuario);

            return true;
        }
        return false;
    }
    //endregion

    //region Buscar
    public UsuarioDto buscarUsuario(String codCondominio,String email,String senha)
    {
        List<Usuario> listaUsuario = usuarioRepository.findAll();
        for ( Usuario user : listaUsuario) {
            if(codCondominio.equals(user.getCodigoCondominio().getCodigoCondominio()) &&
                    email.equals(user.getEmail())&&
                    senha.equals(user.getSenha())){

                UsuarioDto usuarioDto = new UsuarioDto();

                usuarioDto.setBloco(user.getBloco());
                usuarioDto.setCpf(user.getCpf());
                usuarioDto.setEmail(user.getEmail());
                usuarioDto.setNome(user.getNome());
                usuarioDto.setSenha(user.getSenha());
                usuarioDto.setTelefone(user.getTelefone());

                return  usuarioDto;
            }
        }
        return null;
    }

    private Usuario buscarId(String email, String senha){
        List<Usuario> listaUsuario = usuarioRepository.findAll();
        for (Usuario user : listaUsuario) {
            if (email.equals(user.getEmail()) && senha.equals(user.getSenha())){

                return user;
            }
        }
        return null;

    }
    private Usuario buscarIdLogado(String email){
        List<Usuario> listaUsuario = usuarioRepository.findAll();
        for (Usuario user : listaUsuario) {
            if (email.equals(user.getEmail())){
                return user;
            }
        }
        return null;

    }
    public List<UsuarioDto> buscar(){

        List<Usuario> listaUsuario = usuarioRepository.findAll();
        UsuarioDto res = new UsuarioDto();
        List<UsuarioDto> listRes = listaUsuario.stream().map(UsuarioDtoFactory::toDto).collect(Collectors.toList());


        return listRes;
    }
    //endregion

    //region Arquivo Csv
    public static void gravaArquivoCsv(ListaObj<UsuarioDto> listaUsuario, String nomeArq) {

        FileWriter arq = null;     // objeto que representa o arquivo de gravação
        Formatter saida = null;    // objeto usado para escrever no arquivo
        Boolean deuRuim = false;
        nomeArq += ".csv";          // acrescenta a extensão ".csv" ao arquivo

        // Bloco que abre o arquivo
        try {
            arq = new FileWriter(nomeArq);   // abre o arquivo
            saida = new Formatter(arq);     // cria o objeto saida associando ao arquivo
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        // Bloco que grava o arquivo
        try {
            for (int i = 0; i < listaUsuario.getTamanho();i++) {
                UsuarioDto user = listaUsuario.getElemento(i);
                saida.format("%s;%s;%s;%s;%s;%s\n", user.getNome(), user.getCpf(), user.getTelefone(), user.getBloco(), user.getEmail(), user.getSenha());
            }
        }
        catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar arquivo");
            deuRuim = true;
        }
        finally {
            saida.close();
            try {
                arq.close();
            }
            catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

    public static void leExibeArquivoCsv(String nomeArq) {
        FileReader arq = null;      // objeto que representa o arquivo de leitura
        Scanner entrada = null;     // objeto usado para ler do arquivo
        Boolean deuRuim = false;
        nomeArq += ".csv";

        // Bloco para abrir o arquivo
        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        }
        catch (FileNotFoundException erro) {
            System.out.println("Arquivo não encontrado");
            System.exit(1);
        }

    }
    //endregion

    //region ListaObj
    public ListaObj<UsuarioDto> listaEmObj() {

        List<UsuarioDto> usuariosDto = this.buscar();
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

    //endregion
}

