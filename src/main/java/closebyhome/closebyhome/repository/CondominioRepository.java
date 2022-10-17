package closebyhome.closebyhome.repository;

import closebyhome.closebyhome.models.Condominio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CondominioRepository extends JpaRepository<Condominio, Integer> {
    Condominio findByCodigoCondominio(String codigoCondominio);
}
