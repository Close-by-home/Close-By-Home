package closebyhome.closebyhome.models;

import closebyhome.closebyhome.dto.CondominioDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class Condominio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String cnpj;
    private String codigoCondominio;
    private String cep;
    private String telefone;
    @OneToMany
    private List<Usuario> usuarios;
    private int numero;
    private int quatidadeDeBlocos;
    private String sindico;
    private String emailSindico;

    public Condominio(CondominioDto condominioDto) {
        Random gerador = new Random();
        int numeroAleatororio = gerador.nextInt();
        if(numeroAleatororio < 0){
            numeroAleatororio = numeroAleatororio * -1;
        }
        this.cnpj = condominioDto.getCnpj();
        this.codigoCondominio = String.valueOf(numeroAleatororio);
        this.cep = condominioDto.getCep();
        this.telefone = condominioDto.getTelefone();
        this.usuarios =  new ArrayList<Usuario>();
        this.numero = condominioDto.getNumero();
        this.quatidadeDeBlocos = condominioDto.getQuatidadeDeBlocos();
        this.sindico = condominioDto.getSindico();
        this.emailSindico = condominioDto.getEmailSindico();

    }

    public Condominio() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
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


}
