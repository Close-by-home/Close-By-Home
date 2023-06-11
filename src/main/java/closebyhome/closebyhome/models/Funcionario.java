package closebyhome.closebyhome.models;

import closebyhome.closebyhome.dto.FuncionarioDto;
import closebyhome.closebyhome.dto.UsuarioDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(length = 45)
    private String nomeServico;

    private Double valorMinimo;
    @OneToMany
    private List<Agenda> agenda;

    @OneToMany
    private List<Data> data;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_usuario", referencedColumnName = "id")
    private Usuario idUsuario;

    public Funcionario(
            String servico,
            Double valor,
            Usuario idUsuario
    ) {

        this.nomeServico = servico;
        this.valorMinimo = valor;
        this.agenda = new ArrayList<Agenda>();
        this.data = new ArrayList<Data>();
        this.idUsuario = idUsuario;
    }
    public Funcionario(){
        this.agenda = new ArrayList<Agenda>();
        this.data = new ArrayList<Data>();
    };
    public String getNomeServico() {
        return nomeServico;
    }

    public int getId() {
        return id;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Agenda> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Agenda> agenda) {
        this.agenda = agenda;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }


}
