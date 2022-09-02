package CloseByHome;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados {
    private List<Usuario> usuarios ;

    public BancoDeDados() {
        this.usuarios =   new ArrayList<Usuario>();
    }

    public  void  adiconarUsuario(Usuario user ){
        usuarios.add(user);

        System.out.println("cadastrado com sucesso");
    }

    public void atulizarBanco(List<Usuario> listaAtualizada){
        usuarios = listaAtualizada;
    }
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
