package closebyhome.closebyhome.service;

import closebyhome.closebyhome.dto.UsuarioDto;
import closebyhome.closebyhome.dto.UsuarioDtoCadastro;
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

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.*;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private CondominioRepository condominioRepository;
    @Autowired
    private EmailService emailService;


    //region Cadastrar/Ativar
    public UsuarioDto cadastrar(UsuarioDtoCadastro res, Condominio condominio) {
        Usuario user = new Usuario();

        user.setFuncionario(false);
        user.setBloco(res.getBloco());
        user.setCpf(res.getCpf());
        user.setEmail(res.getEmail());
        user.setCodigoCondominio(condominio);
        user.setNome(res.getNome());
        user.setSenha(res.getSenha());
        user.setTelefone(res.getTelefone());
        user.setImagem(res.getImagem());
        user.setSexo("B");
        this.usuarioRepository.save(user);

        UsuarioDto resFinal = new UsuarioDto(res,user);
        emailService.EnviarEmail(user.getEmail(),user.getSenha(),resFinal.getCodigoCondominio(), user.getNome());

        return resFinal;
    }

    public  String recuperarSenha(String email, String cpf){
        Usuario valida = this.buscarUsuarioPorCpf(cpf);
        if(valida != null){
            this.atualizarSenha(email,valida.getSenha(),"AZ9682SP" );
            this.emailService.RecuperarSenhaEmail(email,"AZ9682SP");

            return "Senha Temporaria enviada no e-mail";
        }
        return "Cpf ou E-mail Inválidos";
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

        Usuario usuario = buscarUsuarioPorEmailESenha(email, senhaAtual);
        if (usuario != null) {
            System.out.println("Usuario encontrado");
            usuario.setSenha(novaSenha);
            usuarioRepository.save(usuario);

            return true;
        }

        return false;
    }
    
    public Boolean atualizarEmail(String cpf,String email,String novoEmail){
        Usuario user = usuarioRepository.findByCpfAndEmail(cpf,email);
        if(user != null){
            user.setEmail(novoEmail);
            usuarioRepository.save(user);
            return true;
        }
        return false;
    }

    public Boolean atualizarImagem(String cpf,String email,String novaImagem){
        Usuario user = usuarioRepository.findByCpfAndEmail(cpf,email);
        if(user != null){
            user.setImagem(novaImagem);
            usuarioRepository.save(user);
            return true;
        }
        return false;
    }
    
    public Boolean atualizarNumero(String cpf,String email,String novoNumero){
        Usuario user = usuarioRepository.findByCpfAndEmail(cpf,email);
        if(user != null){
            user.setTelefone(novoNumero);
            usuarioRepository.save(user);
            return true;
        }
        return false;
    }


//    public Boolean atualizarSenhaEsquecida(String email, String codCondominio,
//                                           String novaSenha) {
//
//        Usuario usuario = buscarUsuarioPorEmailESenha(email, senha);
//        if (usuario != null ) {
//            usuario.setSenha(novaSenha);
//            usuarioRepository.save(usuario);
//
//            return true;
//        }
//        return false;
//    }
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
        usuarioDto.setImagem(user.getImagem());
        usuarioDto.setFuncionario(user.getFuncionario());
        usuarioDto.setCodigoCondominio(user.getCodigoCondominio().getCodigoCondominio());

        List<Usuario> usuarioList = usuarioRepository.findAll();
        List<Funcionario> funcionarioList = funcionarioRepository.findAll();
        gravaArquivoTxt(usuarioList,funcionarioList,"arquivo_txt");

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

    private Usuario buscarUsuarioPorEmailESenha(String email, String senha) {
        List<Usuario> listaUsuario = usuarioRepository.findAll();
        System.out.println("Chegou na busca por email e senha");
        System.out.println(listaUsuario);
        for (Usuario user : listaUsuario) {
            System.out.println("Encontrou um item");
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
            corpo += String.format("%-15.15s", user.getCodigoCondominio().getCodigoCondominio());
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
            corpo += String.format("%04d", func.getIdUsuario().getId());

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

