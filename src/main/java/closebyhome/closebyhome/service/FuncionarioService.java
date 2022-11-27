package closebyhome.closebyhome.service;

import closebyhome.closebyhome.dto.*;
import closebyhome.closebyhome.models.Funcionario;
import closebyhome.closebyhome.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<FuncionarioDto> buscarFuncionarioPeloNome(String nome){
        List<Funcionario> lista = funcionarioRepository.findAll();
        List<Funcionario> listaEncontrados = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getIdUsuario().getNome().equals(nome)){
                listaEncontrados.add(lista.get(i));
            }
        }

        List<FuncionarioDto> listRes = lista.stream().map(FuncionarioDtoFactory::toDto).collect(Collectors.toList());
        return listRes;
    }

    public List<FuncionarioDto> buscarFuncionarioPeloServico(String servico){
        List<Funcionario> res = funcionarioRepository.findByNomeServico(servico);

        List<FuncionarioDto> listRes = res.stream().map(FuncionarioDtoFactory::toDto).collect(Collectors.toList());
        return listRes;
    }

    public List<FuncionarioDto> buscarFuncionarioPeloServicoEnome(String servico, String nome){
        List<Funcionario> lista = funcionarioRepository.findAll();
        List<Funcionario> listaEncontrados = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getNomeServico().equals(servico) && lista.get(i).getIdUsuario().getNome().equals(nome)){
                listaEncontrados.add(lista.get(i));
            }
        }

        List<FuncionarioDto> listRes = lista.stream().map(FuncionarioDtoFactory::toDto).collect(Collectors.toList());
        return listRes;
    }

}
