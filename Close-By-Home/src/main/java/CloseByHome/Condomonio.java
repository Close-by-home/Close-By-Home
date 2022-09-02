package CloseByHome;

import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.ArrayList;
import java.util.List;

public class Condomonio extends Usuario{
    // Atributos
        private String cep;

        // LISTA PARA TESTE
        private List<UsuarioAplicativo>usuariosAplicativo;

    // Construtor
    public Condomonio(String nome, String codCondominio,
                      String email, String senha, boolean logado, String cep) {
        super(nome, codCondominio, email, senha, logado);
        this.cep = cep;
    }

    public Condomonio(String nome, String codCondominio,
                      String email, String senha, boolean logado,
                      List<UsuarioAplicativo> usuariosAplicativo) {
        super(nome, codCondominio, email, senha, logado);
        this.usuariosAplicativo = new ArrayList<>();
    }

    // Métodos
    // CADASTRAR USUARIO
    public void cadastrarUsuario(UsuarioAplicativo u){
        usuariosAplicativo.add(u);
        System.out.println("Usuario adicionado com sucesso!");
    }

    // DELETAR USUARIO
    public void deletarUsuario(String nomeUsuario){
        for (UsuarioAplicativo u : usuariosAplicativo ){
            if(u.getNome().equalsIgnoreCase(nomeUsuario)) {
                usuariosAplicativo.remove(u);
                System.out.println("Usuario removido com sucesso!");
            }
            return;
        }
        System.out.println("Usuario não encontrado");
        }

    }



