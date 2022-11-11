package closebyhome.closebyhome.service;

import closebyhome.closebyhome.dto.AgendaDto;
import closebyhome.closebyhome.dto.AgendaDtoFactory;
import closebyhome.closebyhome.dto.UsuarioDto;
import closebyhome.closebyhome.dto.UsuarioDtoFactory;
import closebyhome.closebyhome.models.Agenda;
import closebyhome.closebyhome.models.Funcionario;
import closebyhome.closebyhome.models.Usuario;
import closebyhome.closebyhome.repository.AgendaRepository;
import closebyhome.closebyhome.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendaService {

    @Autowired
    AgendaRepository agendaRepository;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    FuncionarioService funcionarioService;
    public List<AgendaDto> getAllAgenda(){
        List<Agenda> ret = agendaRepository.findAll();
        List<AgendaDto> listRes = ret.stream().map(AgendaDtoFactory::toDto).collect(Collectors.toList());

        return  listRes;
    }

    public AgendaDto agendarServico(int func,
                                    String user,
                                    LocalDate date){

        Usuario resUsuario = usuarioService.buscarUsuarioPorCpf(user);
        Funcionario resFunc = funcionarioService.buscarFuncionario(func);
        if(resFunc == null || resUsuario == null){
            return null;
        }
        AgendaDto res = new AgendaDto(resUsuario,resFunc,date);
        Agenda saveAgenda = new Agenda(resFunc,resUsuario,date, res.getNotaServico(), res.getStatus());
        agendaRepository.save(saveAgenda);
        return  res;
    }
}
