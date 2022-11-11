package closebyhome.closebyhome.dto;

import javax.validation.constraints.Email;

public class UsuarioLogarDto {

    private String codigoCondominio;
    @Email
    private String email;
    private String senha;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCodigoCondominio() {
        return codigoCondominio;
    }

    public void setCodigoCondominio(String codigoCondominio) {
        this.codigoCondominio = codigoCondominio;
    }
}
