package closebyhome.closebyhome.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "fk_codigo_funcionario")
    private Funcionario func;

    @ManyToOne
    @JoinColumn(name = "fk_id_funcionario")
    private Usuario user;

    private LocalDateTime data;
    private int notaServico;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigoServico;
    private String status;

    public Agenda( Funcionario func, Usuario user, LocalDateTime data, int notaServico, String status) {
        this.func = func;
        this.user = user;
        this.data = data;
        this.notaServico = notaServico;
        this.status = status;
    }

    public Agenda() {
    }

    public int getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(int codigoServico) {
        this.codigoServico = codigoServico;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public int getNotaServico() {
        return notaServico;
    }

    public void setNotaServico(int notaServico) {
        this.notaServico = notaServico;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
