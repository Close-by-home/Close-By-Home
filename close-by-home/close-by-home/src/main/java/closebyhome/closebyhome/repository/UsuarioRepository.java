package closebyhome.closebyhome.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import closebyhome.closebyhome.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
