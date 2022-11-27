package closebyhome.closebyhome.dto;

import closebyhome.closebyhome.models.Funcionario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NotificacaoDto {

    @NotBlank
    @NotNull
    @NotEmpty
    private String titulo;

    @NotBlank
    @NotNull
    @NotEmpty
    private String descricao;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
