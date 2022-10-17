package closebyhome.closebyhome.dto;

import closebyhome.closebyhome.models.Usuario;

import javax.persistence.*;
import java.util.List;

public class CondominioDto {


    private String cnpj;
    private String codigoCondominio;
    private String cep;
    private String telefone;
    private int numero;
    private int quatidadeDeBlocos;
    private String sindico;
    private String emailSindico;
    private String telefoneSindico;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCodigoCondominio() {
        return codigoCondominio;
    }

    public void setCodigoCondominio(String codigoCondominio) {
        this.codigoCondominio = codigoCondominio;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getQuatidadeDeBlocos() {
        return quatidadeDeBlocos;
    }

    public void setQuatidadeDeBlocos(int quatidadeDeBlocos) {
        this.quatidadeDeBlocos = quatidadeDeBlocos;
    }

    public String getSindico() {
        return sindico;
    }

    public void setSindico(String sindico) {
        this.sindico = sindico;
    }

    public String getEmailSindico() {
        return emailSindico;
    }

    public void setEmailSindico(String emailSindico) {
        this.emailSindico = emailSindico;
    }

    public String getTelefoneSindico() {
        return telefoneSindico;
    }

    public void setTelefoneSindico(String telefoneSindico) {
        this.telefoneSindico = telefoneSindico;
    }
}
