package closebyhome.closebyhome.service;

import closebyhome.closebyhome.dto.CondominioDto;
import closebyhome.closebyhome.models.Condominio;
import closebyhome.closebyhome.models.Usuario;
import closebyhome.closebyhome.repository.CondominioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CondominioService {
    @Autowired
    private CondominioRepository condominioRepository;

    public CondominioDto cadastrar(CondominioDto res){

        Condominio c = new Condominio(res);

        this.condominioRepository.save(c);

        return res;
    }

    public Condominio buscarCondominio(String codigoCondominio){

        Condominio listaCondominio = condominioRepository.findByCodigoCondominio(codigoCondominio);
        return listaCondominio;
    }
}
