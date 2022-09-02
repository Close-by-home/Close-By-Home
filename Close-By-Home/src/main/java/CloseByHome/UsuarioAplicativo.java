package CloseByHome;

public abstract class UsuarioAplicativo extends Usuario {
    // Atributos
    private String dataNascimento;
    private int cpfTitular;
    private int cpfUtilitario;
    private int telefone;

    // Construtor

    public UsuarioAplicativo(String nome, String codCondominio, String email,
                             String senha, boolean logado, String dataNascimento,
                             int cpfTitular, int cpfUtilitario, int telefone) {
        super(nome, codCondominio, email, senha, logado);
        this.dataNascimento = dataNascimento;
        this.cpfTitular = cpfTitular;
        this.cpfUtilitario = cpfUtilitario;
        this.telefone = telefone;
    }


    // Métodos

    // PESQUISAR
    // CANCELAR SERVIÇO
    // AVALIAR
    // COMENTAR

}
