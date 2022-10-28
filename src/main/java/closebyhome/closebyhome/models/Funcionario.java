package closebyhome.closebyhome.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String nomeServico;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_descricao", referencedColumnName = "id")
    private Descricao descricao;
    private Double valorMinimo;
    @OneToMany
    private List<Agenda> agenda;

    @OneToMany
    private List<Data> data;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_usuario", referencedColumnName = "id")
    private Usuario idUsuario;

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

    public Descricao getDescricao() {
        return descricao;
    }

    public void setDescricao(Descricao descricao) {
        this.descricao = descricao;
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
