package closebyhome.closebyhome.repository;
import closebyhome.closebyhome.models.Notificacao;
import closebyhome.closebyhome.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Integer> {
    List<Notificacao> findByUsuario(Optional <Usuario> usuario);
}
