package closebyhome.closebyhome.dto;

import closebyhome.closebyhome.models.Condominio;
import closebyhome.closebyhome.models.Funcionario;
import closebyhome.closebyhome.models.Usuario;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

public class UsuarioDto {

    @CPF
    private String cpf;
    private String bloco;
    @Email
    private String email;
    private String telefone;
    private String nome;
    private String senha;
    private String codigoCondominio;
    private String imagem;
    private Boolean funcionario;

    public UsuarioDto() {

    }

    public UsuarioDto(UsuarioDtoCadastro usuarioDtoCadastro, Usuario usuario) {
        this.cpf = usuarioDtoCadastro.getCpf();
        this.bloco = usuarioDtoCadastro.getBloco();
        this.email = usuarioDtoCadastro.getEmail();
        this.telefone = usuarioDtoCadastro.getTelefone();
        this.nome = usuarioDtoCadastro.getNome();
        this.senha = usuarioDtoCadastro.getSenha();
        this.codigoCondominio = usuario.getCodigoCondominio().getCodigoCondominio();
        this.imagem = usuario.getImagem();
        this.funcionario = usuario.getFuncionario();
    }

    public String getCodigoCondominio() {
        return codigoCondominio;
    }

    public void setCodigoCondominio(String codigoCondominio) {
        this.codigoCondominio = codigoCondominio;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Boolean getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Boolean funcionario) {
        this.funcionario = funcionario;
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
