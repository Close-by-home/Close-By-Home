package closebyhome.closebyhome.models;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @NotNull
    @NotEmpty
    @Column(length = 45)
    private String titulo;

    @NotBlank
    @NotNull
    @NotEmpty
    @Column(length = 45)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "fk_agenda")
    private Agenda agenda;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;
    public Notificacao() {
    }

    public Notificacao(String titulo, String descricao, Agenda agenda) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.agenda = agenda;
        this.usuario = agenda.getUser();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
