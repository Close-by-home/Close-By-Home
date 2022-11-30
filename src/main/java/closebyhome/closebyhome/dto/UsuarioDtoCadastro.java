package closebyhome.closebyhome.dto;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;

public class UsuarioDtoCadastro {

    @CPF
    private String cpf;
    private String bloco;
    @Email
    private String email;
    private String telefone;
    private String nome;
    private String senha;
    private String imagem;

    public UsuarioDtoCadastro(String linha) {
        String[] values = linha.split(";");

        this.nome = values[0];
        this.cpf = values[1];
        this.telefone = values[2];
        this.bloco = values[3];
        this.email = values[4];
        this.senha = values[5];
    }

    public UsuarioDtoCadastro(){}

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
