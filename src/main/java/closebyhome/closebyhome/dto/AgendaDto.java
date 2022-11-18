package closebyhome.closebyhome.dto;

import closebyhome.closebyhome.models.Funcionario;
import closebyhome.closebyhome.models.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AgendaDto {
    private FuncionarioAgendaDto func;

    private int codigoServico;
    private LocalDateTime data;

    private int notaServico;
    private String status;

    public AgendaDto( FuncionarioAgendaDto func,LocalDateTime data) {
        this.func = func;
        this.data = data;
        this.notaServico = 0;
        this.status = "Agendado";
        this.codigoServico = 0;
    }

    public AgendaDto() {

    }

    public int getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(int codigoServico) {
        this.codigoServico = codigoServico;
    }

    public FuncionarioAgendaDto getFunc() {
        return func;
    }

    public void setFunc(FuncionarioAgendaDto func) {
        this.func = func;
    }


    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
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
