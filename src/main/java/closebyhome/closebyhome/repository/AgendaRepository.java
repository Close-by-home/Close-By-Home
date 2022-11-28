package closebyhome.closebyhome.repository;
import closebyhome.closebyhome.models.Agenda;
import closebyhome.closebyhome.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AgendaRepository extends JpaRepository<Agenda, Integer> {

    //    Agenda findByCodigoServico(int codigoServico);
    Agenda findByUser(Optional<Usuario> user);

    List<Agenda> findByUserCpf(String Cpf);

    List<Agenda> findByFuncId(int idFunc);

    List<Agenda> findByData(LocalDateTime data);

    Long countById(int idFunc);
}
