package closebyhome.closebyhome.repository;

import closebyhome.closebyhome.models.Agenda;
import closebyhome.closebyhome.models.Chat;
import closebyhome.closebyhome.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {


    List<Chat> fndByFuncIdUsuarioCpfAndUserCpf(String cpfFunc, String cpfUser);
}
