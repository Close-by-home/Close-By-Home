package closebyhome.closebyhome.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import closebyhome.closebyhome.models.Funcionario;

import java.util.List;


public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
      Funcionario findById(int id);
      List<Funcionario> findByNomeServico(String nome);

//    Funcionario findByUsuarioId(Integer id);
}
