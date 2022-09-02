package CloseByHome;

public class UsuarioFuncionario extends UsuarioAplicativo{
    // Atributos
    private Servico servicoPrestado;

    // Construtor

    public UsuarioFuncionario(String nome, String codCondominio, String email,
                              String senha, boolean logado, String dataNascimento,
                              int cpfTitular, int cpfUtilitario, int telefone, Servico servicoPrestado) {
        super(nome, codCondominio, email, senha, logado, dataNascimento,
                cpfTitular, cpfUtilitario, telefone);
        this.servicoPrestado = servicoPrestado;
    }


    // Métodos

    // CADASTRARSERVICO
    // ADICIONARFOLGA
    // EDITARSTATUSSERVICO
}
