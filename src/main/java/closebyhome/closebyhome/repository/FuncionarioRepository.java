package closebyhome.closebyhome.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import closebyhome.closebyhome.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
}
