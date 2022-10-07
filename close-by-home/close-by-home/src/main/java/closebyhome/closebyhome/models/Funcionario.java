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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_agenda", referencedColumnName = "id")
    private Agenda agenda;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_data", referencedColumnName = "id")
    private Data data;

    public String getNomeServico() {
        return nomeServico;
    }

    public int getId() {
        return id;
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

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}