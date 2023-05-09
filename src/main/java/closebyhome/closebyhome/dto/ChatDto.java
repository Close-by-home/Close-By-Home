package closebyhome.closebyhome.dto;

import java.time.LocalDateTime;

public class ChatDto {
    private Integer id;
    private String funcionario;
    private String usuario;
    private String mensagem;

    private LocalDateTime tempoEnvio;

    public ChatDto() {

    }

    public ChatDto(String funcionario, String usuario, String mensagem, LocalDateTime tempoEnvio) {
        this.funcionario = funcionario;
        this.usuario = usuario;
        this.mensagem = mensagem;
        this.tempoEnvio = tempoEnvio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getTempoEnvio() {
        return tempoEnvio;
    }

    public void setTempoEnvio(LocalDateTime tempoEnvio) {
        this.tempoEnvio = tempoEnvio;
    }
}
