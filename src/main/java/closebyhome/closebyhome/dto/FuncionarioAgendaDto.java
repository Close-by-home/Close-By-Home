package closebyhome.closebyhome.dto;

import closebyhome.closebyhome.models.Funcionario;

public class FuncionarioAgendaDto {

    private String cpf;
    private String nomeFuncionario;
    private String contato;
    private String nomeServico;
    private Double valorMinimo;

    public FuncionarioAgendaDto(Funcionario funcionario) {
        this.cpf = funcionario.getIdUsuario().getCpf();
        this.nomeFuncionario = funcionario.getIdUsuario().getNome();
        this.contato = funcionario.getIdUsuario().getTelefone();
        this.nomeServico = funcionario.getNomeServico();
        this.valorMinimo = funcionario.getValorMinimo();
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
