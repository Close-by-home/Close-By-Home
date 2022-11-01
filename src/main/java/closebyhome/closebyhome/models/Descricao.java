package closebyhome.closebyhome.models;

import javax.persistence.*;

@Entity
public class Descricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_funcionario", referencedColumnName = "id")
    private Funcionario idFuncionario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Funcionario getFunc() {
        return idFuncionario;
    }

    public void setFunc(Funcionario func) {
        this.idFuncionario = func;
    }
}
