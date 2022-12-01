package closebyhome.closebyhome.dto;

import closebyhome.closebyhome.models.Agenda;

import java.time.LocalDateTime;

public class AgendaDtoUsuario {
    private String nomeServico;
    private String nomeUsuario;
    private String emailUsuario;
    private String telefone;
    private LocalDateTime data;

    public AgendaDtoUsuario(Agenda agenda) {
        this.nomeServico = agenda.getFunc().getNomeServico();
        this.nomeUsuario = agenda.getUser().getNome();
        this.emailUsuario = agenda.getUser().getEmail();
        this.telefone = agenda.getUser().getTelefone();
        this.data = agenda.getData();
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
