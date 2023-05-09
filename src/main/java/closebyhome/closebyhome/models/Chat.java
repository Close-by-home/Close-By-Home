package closebyhome.closebyhome.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "fk_id_funcionario")
    private Funcionario func;

    @ManyToOne
    @JoinColumn(name = "fk_id_usuario")
    private Usuario user;

    private String mensagem;
    private LocalDateTime dataDoEnvio;

    public Chat (Funcionario func, Usuario user, String mensagem) {

        this.func = func;
        this.user = user;
        this.mensagem = mensagem;
        this.dataDoEnvio = LocalDateTime.now();
    }

    public Chat() {
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

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getDataDoEnvio() {
        return dataDoEnvio;
    }

    public void setDataDoEnvio(LocalDateTime dataDoEnvio) {
        this.dataDoEnvio = dataDoEnvio;
    }
}
