package closebyhome.closebyhome.service;

import closebyhome.closebyhome.dto.UsuarioDto;
import closebyhome.closebyhome.dto.UsuarioDtoFactory;
import closebyhome.closebyhome.dto.UsuarioLogarDto;
import closebyhome.closebyhome.listaObj.ListaObj;
import closebyhome.closebyhome.models.Condominio;
import closebyhome.closebyhome.models.Funcionario;
import closebyhome.closebyhome.models.Usuario;
import closebyhome.closebyhome.repository.CondominioRepository;
import closebyhome.closebyhome.repository.FuncionarioRepository;
import closebyhome.closebyhome.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private CondominioRepository condominioRepository;


    //region Cadastrar/Ativar
    public UsuarioDto cadastrar(UsuarioDto res, Condominio condominio) {
        Usuario user = new Usuario();

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

    public Boolean ativarContaFuncionario(String email, String servico, Double valor) {
        Usuario usuario = buscarIdLogado(email);
        if (usuario != null) {
            usuario.setFuncionario(true);
            usuarioRepository.save(usuario);
            funcionarioRepository.save(new Funcionario(servico, valor, usuario));
            return true;
        }
        return false;
    }
    //endregion

    //region Senhas
    public Boolean atualizarSenha(String email, String senhaAtual, String novaSenha) {

        Usuario usuario = buscarId(email, senhaAtual);
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
    public UsuarioDto buscarUsuario(UsuarioLogarDto usuarioLogarDto) {
        Usuario user = usuarioRepository.findByEmailAndSenhaAndCodigoCondominioCodigoCondominio(
                usuarioLogarDto.getEmail(),
                usuarioLogarDto.getSenha(),
                usuarioLogarDto.getCodigoCondominio()
        );

        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setBloco(user.getBloco());
        usuarioDto.setCpf(user.getCpf());
        usuarioDto.setEmail(user.getEmail());
        usuarioDto.setNome(user.getNome());
        usuarioDto.setSenha(user.getSenha());
        usuarioDto.setTelefone(user.getTelefone());

        return usuarioDto;
    }

    public List<UsuarioDto> buscarPorCondominio(Integer idCondominio) {

        Optional<Condominio> condominio = condominioRepository.findById(idCondominio);
        List<Usuario> listaUsuario = usuarioRepository.findAllByCodigoCondominio(condominio);

        if (!listaUsuario.isEmpty()) {
            List<UsuarioDto> listRes = listaUsuario.stream().map(UsuarioDtoFactory::toDto).collect(Collectors.toList());
            return listRes;
        }

        return null;
    }

    private Usuario buscarId(String email, String senha) {
        List<Usuario> listaUsuario = usuarioRepository.findAll();
        for (Usuario user : listaUsuario) {
            if (email.equals(user.getEmail()) && senha.equals(user.getSenha())) {

                return user;
            }
        }
        return null;

    }

    private Usuario buscarIdLogado(String email) {
        List<Usuario> listaUsuario = usuarioRepository.findAll();
        for (Usuario user : listaUsuario) {
            if (email.equals(user.getEmail())) {
                return user;
            }
        }
        return null;

    }

    public List<UsuarioDto> buscar() {

        List<Usuario> listaUsuario = usuarioRepository.findAll();
        List<UsuarioDto> listRes = listaUsuario.stream().map(UsuarioDtoFactory::toDto).collect(Collectors.toList());

        return listRes;
    }

    //endregion
    public Usuario buscarUsuarioPorCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf);
    }

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
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        // Bloco que grava o arquivo
        try {
            for (int i = 0; i < listaUsuario.getTamanho(); i++) {
                UsuarioDto user = listaUsuario.getElemento(i);
                saida.format("%s;%s;%s;%s;%s;%s\n", user.getNome(), user.getCpf(), user.getTelefone(), user.getBloco(), user.getEmail(), user.getSenha());
            }
        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar arquivo");
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
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
        } catch (FileNotFoundException erro) {
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
        //Ele roda 2 fors usando o selection sort para organizar na ordem alfabética de A-Z

        //Existe uma condicional que utiliza o compareTo, o compareTo verifica a distancia de caracteres
        // entre os nomes i e j, se a diferença for negativa, signifca que o nome i é menor do que o nome j
        // (na ordem alfabética) e inverte a posição de i por j

        UsuarioDto aux = new UsuarioDto();
        for (int i = 0; i < usuariosDto.size(); i++) {
            for (int j = 0; j < usuariosDto.size(); j++) {

                if (usuariosDto.get(i).getNome().compareTo(usuariosDto.get(j).getNome()) < 0) {

                    aux = usuariosDto.get(i);
                    usuariosDto.set(i, usuariosDto.get(j));
                    usuariosDto.set(j, aux);
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

