package closebyhome.closebyhome.models;

import javax.persistence.*;

@Entity
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "fk_codigo_funcionario")
    private Funcionario func;

    private Integer dataNaoTrabalhada;

    public Integer getDataNaoTrabalhada() {
        return dataNaoTrabalhada;
    }

    public void setDataNaoTrabalhada(Integer dataNaoTrabalhada) {
        this.dataNaoTrabalhada = dataNaoTrabalhada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Funcionario getFunc() {
        return func;
    }

    public void setFunc(Funcionario func) {
        this.func = func;
    }
}
