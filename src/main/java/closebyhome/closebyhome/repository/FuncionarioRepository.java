package closebyhome.closebyhome.repository;
import closebyhome.closebyhome.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import closebyhome.closebyhome.models.Funcionario;

import java.util.List;
import java.util.Optional;


public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
      Funcionario findById(int id);
      List<Funcionario> findByNomeServico(String nome);

      Funcionario findByIdUsuarioCpf(String cpf);
//    Funcionario findByUsuarioId(Integer id);
}
