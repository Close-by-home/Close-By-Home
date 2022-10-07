package closebyhome.closebyhome.service;

import closebyhome.closebyhome.dto.FuncionarioDto;
import closebyhome.closebyhome.models.Agenda;
import closebyhome.closebyhome.models.Data;
import closebyhome.closebyhome.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Boolean logar(){

        return true;
    }

    public FuncionarioDto cadastrarFuncionario(FuncionarioDto funcionarioNovo){


        return null;
    }

    public List<Agenda> buscarAgenda(){


        return null;
    }

    public List<Data> buscarDatas(){


        return null;
    }


}
