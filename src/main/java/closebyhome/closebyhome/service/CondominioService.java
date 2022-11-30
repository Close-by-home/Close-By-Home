package closebyhome.closebyhome.service;

import closebyhome.closebyhome.dto.CondominioDto;
import closebyhome.closebyhome.dto.CondominioDtoFactory;
import closebyhome.closebyhome.models.Condominio;
import closebyhome.closebyhome.models.Usuario;
import closebyhome.closebyhome.repository.CondominioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CondominioService {
    @Autowired
    private CondominioRepository condominioRepository;

    public CondominioDto cadastrar(CondominioDto res){

        Condominio c = new Condominio(res);

        this.condominioRepository.save(c);

        return res;
    }

    public Condominio buscarCondominioPeloCodigo(String codigoCondominio){

        Condominio listaCondominio = condominioRepository.findByCodigoCondominio(codigoCondominio);
        return listaCondominio;
    }

    public List<CondominioDto> buscarTodos(){
        List<Condominio> ret = condominioRepository.findAll();
        List<CondominioDto> listRes = ret.stream()
                .map(CondominioDtoFactory::toDto)
                .collect(Collectors.toList());

        return listRes;
    }
}
