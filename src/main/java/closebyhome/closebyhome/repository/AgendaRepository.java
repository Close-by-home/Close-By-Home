package closebyhome.closebyhome.repository;
import closebyhome.closebyhome.models.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AgendaRepository extends JpaRepository<Agenda, Integer> {

    Agenda findByCodigoServico(int codigoServico);

    List<Agenda> findByUserCpf(String Cpf);

    List<Agenda> findByFuncId(int idFunc);
}
