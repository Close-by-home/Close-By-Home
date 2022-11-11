package closebyhome.closebyhome.repository;
import closebyhome.closebyhome.dto.UsuarioLogarDto;
import closebyhome.closebyhome.models.Condominio;
import org.springframework.data.jpa.repository.JpaRepository;
import closebyhome.closebyhome.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findById(int id);
    Usuario findByCpf(String cpf);


    Usuario findByEmailAndSenhaAndCodigoCondominioCodigoCondominio(String email,String Senha,String cod);
}
