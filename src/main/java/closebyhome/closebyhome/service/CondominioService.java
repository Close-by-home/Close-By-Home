package closebyhome.closebyhome.service;

import closebyhome.closebyhome.dto.CondominioDto;
import closebyhome.closebyhome.dto.CondominioDtoFactory;
import closebyhome.closebyhome.dto.UsuarioDto;
import closebyhome.closebyhome.dto.UsuarioDtoFactory;
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

    public CondominioDto cadastrar(CondominioDto condominioNovo){

        Condominio c = new Condominio(condominioNovo);

        condominioRepository.save(c);
        List<Condominio> listaTemp= new ArrayList<>();
        listaTemp.add(c);
        List<CondominioDto> listRes = listaTemp.stream().map(CondominioDtoFactory::toDto).collect(Collectors.toList());

        return listRes.get(0);
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
