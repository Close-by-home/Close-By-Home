package CloseByHome;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
    // Atributos
    private String nome;
    private String codCondominio;
    private String email;
    private String senha;
    private boolean logado;
    // Construtor

    private  List<Usuario> listaDeUsuarios ;


    public Usuario(String nome, String codCondominio, String email, String senha, boolean logado) {
        this.nome = nome;
        this.codCondominio = codCondominio;
        this.email = email;
        this.senha = senha;
        this.logado = logado;
        this.listaDeUsuarios = new ArrayList<Usuario>();
    }

    public List<Usuario> getListaDeUsuarios() {
        return listaDeUsuarios;
    }

    public void setListaDeUsuarios(List<Usuario> listaDeUsuarios) {
        this.listaDeUsuarios = listaDeUsuarios;
    }

    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodCondominio() {
        return codCondominio;
    }

    public void setCodCondominio(String codCondominio) {
        this.codCondominio = codCondominio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

// Métodos

    // LOGAR
    public  String logar(String email,String senha, String codCondominio){

        System.out.println(listaDeUsuarios);
        for (Usuario  us: listaDeUsuarios
             ) {
            if (
                    us.email.equalsIgnoreCase(email)
                            && us.senha.equals(senha)
                            && us.codCondominio.equalsIgnoreCase(codCondominio)
            ) {
                us.setLogado(true);

                return "Foi Logado";

            }

        }

            return "login incorreto";

    }
    // DESLOGAR
    public String deslogar(String email){
        BancoDeDados banco = new BancoDeDados();
        List<Usuario> listaDeUsuarios = banco.getUsuarios();
        for (Usuario  us: listaDeUsuarios
        ){
            if (us.email.equalsIgnoreCase(email)){
                us.setLogado(false);
                banco.atulizarBanco(listaDeUsuarios);
                return "Deslogado com sucesso";
            }
        }

        return null;
    }
    // TROCAR USUARIO

}
