package closebyhome.closebyhome.models;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CPF
    private String cpf;
    @ManyToOne
    @JoinColumn(name = "fk_codigo_condominio")
    private Condominio codigoCondominio;
//    @OneToMany
//    private List<Agenda> agenda;
    @Size(max = 2)
    private String bloco;
    @Email
    private String email;
    private String telefone;
    @NotBlank
    @NotNull
    @NotEmpty
    private String nome;
    @NotBlank
    @NotNull
    @NotEmpty
    @Size(min = 6, max = 14)
    private String senha;
    private Boolean funcionario;

    @Size(max = 1)
    private String sexo;

    @OneToOne(mappedBy = "idUsuario")
    private Funcionario func;

    public Funcionario getFunc() {
        return func;
    }

    public void setFunc(Funcionario func) {
        this.func = func;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Condominio getCodigoCondominio() {
        return codigoCondominio;
    }

    public void setCodigoCondominio(Condominio codigoCondominio) {
        this.codigoCondominio = codigoCondominio;
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

    public Boolean getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Boolean funcionario) {
        this.funcionario = funcionario;
    }
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
