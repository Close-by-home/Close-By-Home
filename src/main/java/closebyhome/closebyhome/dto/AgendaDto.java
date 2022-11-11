package closebyhome.closebyhome.dto;

import closebyhome.closebyhome.models.Funcionario;
import closebyhome.closebyhome.models.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AgendaDto {
    private Usuario usuario;
    private Funcionario func;

    private String NomeFuncionario;

    private LocalDate data;

    private int notaServico;
    private String status;

    public AgendaDto(Usuario usuario, Funcionario func,LocalDate data) {
        this.usuario = usuario;
        this.func = func;
        NomeFuncionario = func.getIdUsuario().getNome();
        this.data = data;
        this.notaServico = 0;
        this.status = "Agendado";
    }

    public AgendaDto() {

    }

    public Funcionario getFunc() {
        return func;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setFunc(Funcionario func) {
        this.func = func;
    }

    public String getNomeFuncionario() {
        return NomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        NomeFuncionario = nomeFuncionario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getNotaServico() {
        return notaServico;
    }

    public void setNotaServico(int notaServico) {
        this.notaServico = notaServico;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
