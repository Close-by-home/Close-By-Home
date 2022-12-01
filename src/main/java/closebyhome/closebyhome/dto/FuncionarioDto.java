package closebyhome.closebyhome.dto;

import closebyhome.closebyhome.models.Usuario;

import java.time.LocalDateTime;


public class FuncionarioDto {

    private String nomeServico;
    private String nomeUsuario;
    private String emailUsuario;
    private Double valorMinimo;
    private Usuario usuario;
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
//NÂO DESCOMENTA ESSE GET POR FAVOR SE VOCE TEM AMOR A SUA VIDA NAO FACA ISSO
//    public Usuario getUsuario() {
//        return usuario;
//    }

//    public int getNota() {
//        return nota;
//    }
//
//    public void setNota(int nota) {
//        this.nota = nota;
//    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario pegaUsuario() {
        return usuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public Double getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(Double valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

}
