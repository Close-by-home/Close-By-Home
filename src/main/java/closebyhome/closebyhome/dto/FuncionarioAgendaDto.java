package closebyhome.closebyhome.dto;

import closebyhome.closebyhome.models.Agenda;
import closebyhome.closebyhome.models.Funcionario;

import java.time.LocalDateTime;

public class FuncionarioAgendaDto {

    private String cpf;
    private String nomeFuncionario;
    private String contato;
    private String nomeServico;
    private Double valorMinimo;
    private String imagem;
    private String telefone;
    private LocalDateTime dateTime;
    private int nota;
    private String status;

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FuncionarioAgendaDto(Funcionario funcionario, Agenda agenda, int nota) {

        this.cpf = funcionario.getIdUsuario().getCpf();
        this.nomeFuncionario = funcionario.getIdUsuario().getNome();
        this.contato = funcionario.getIdUsuario().getTelefone();
        this.nomeServico = funcionario.getNomeServico();
        this.valorMinimo = funcionario.getValorMinimo();
        this.imagem = funcionario.getIdUsuario().getImagem();
        this.telefone = funcionario.getIdUsuario().getTelefone();
        this.dateTime = agenda.getData();
        this.status = agenda.getStatus();

        this.nota = nota;
    }
    public FuncionarioAgendaDto(Funcionario funcionario) {

        this.cpf = funcionario.getIdUsuario().getCpf();
        this.nomeFuncionario = funcionario.getIdUsuario().getNome();
        this.contato = funcionario.getIdUsuario().getTelefone();
        this.nomeServico = funcionario.getNomeServico();
        this.valorMinimo = funcionario.getValorMinimo();
        this.imagem = funcionario.getIdUsuario().getImagem();
        this.telefone = funcionario.getIdUsuario().getTelefone();
        this.nota = 0;
    }
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
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
