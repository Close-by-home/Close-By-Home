package closebyhome.closebyhome.repository;
import closebyhome.closebyhome.dto.UsuarioLogarDto;
import closebyhome.closebyhome.models.Condominio;
import org.springframework.data.jpa.repository.JpaRepository;
import closebyhome.closebyhome.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findById(int id);
    List<Usuario> findAllByCodigoCondominio(Optional condominio);

    Usuario findByCpf(String cpf);

    Usuario findByCpfAndEmail(String cpf,String email);
    Usuario findByCodigoCondominioCodigoCondominioAndEmail(String cod,String email);
    Usuario findByEmailAndSenhaAndCodigoCondominioCodigoCondominio(String email,String Senha,String cod);

    List<Usuario> findByCodigoCondominioCodigoCondominio(String cod);
}
