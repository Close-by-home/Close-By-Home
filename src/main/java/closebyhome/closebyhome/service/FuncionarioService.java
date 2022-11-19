package closebyhome.closebyhome.service;

import closebyhome.closebyhome.dto.*;
import closebyhome.closebyhome.models.Funcionario;
import closebyhome.closebyhome.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;


    public List<FuncionarioDto> buscarTodosFuncionarios()
    {
        List<Funcionario> listaFuncionario = funcionarioRepository.findAll();
        List<FuncionarioDto> listRes = listaFuncionario.stream().map(FuncionarioDtoFactory::toDto).collect(Collectors.toList());
        return listRes;
    }
    public  Funcionario buscarFuncionario(int idFunc){
        Funcionario res = funcionarioRepository.findById(idFunc);
        return res;
    }

}
