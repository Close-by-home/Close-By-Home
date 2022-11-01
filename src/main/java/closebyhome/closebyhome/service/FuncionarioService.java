package closebyhome.closebyhome.service;

import closebyhome.closebyhome.dto.FuncionarioDto;
import closebyhome.closebyhome.dto.FuncionarioDtoFactory;
import closebyhome.closebyhome.dto.UsuarioDto;
import closebyhome.closebyhome.dto.UsuarioDtoFactory;
import closebyhome.closebyhome.models.Agenda;
import closebyhome.closebyhome.models.Data;
import closebyhome.closebyhome.models.Funcionario;
import closebyhome.closebyhome.models.Usuario;
import closebyhome.closebyhome.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Boolean logar(){

        return true;
    }

    public FuncionarioDto cadastrarFuncionario(FuncionarioDto funcionarioNovo,int id){
        Usuario user = usuarioRepository.findById(id);
        Funcionario resFun = new Funcionario(funcionarioNovo,user);



        //funcionarioRepository.findByUsuarioId(id);

        if(resFun != null) {
            funcionarioRepository.save(resFun);
        }
       // FuncionarioDto res = factoryFuncionario.retornaFuncionarioDto(resFun);

        return funcionarioNovo;
    }

    public List<Agenda> buscarAgenda(){


        return null;
    }

    public List<Data> buscarDatas(){


        return null;
    }


}
