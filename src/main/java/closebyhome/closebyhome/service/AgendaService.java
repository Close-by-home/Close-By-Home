package closebyhome.closebyhome.service;

import closebyhome.closebyhome.dto.*;
import closebyhome.closebyhome.models.Agenda;
import closebyhome.closebyhome.models.Funcionario;
import closebyhome.closebyhome.models.Usuario;
import closebyhome.closebyhome.observable.Observable;
import closebyhome.closebyhome.repository.AgendaRepository;
import closebyhome.closebyhome.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendaService {

    @Autowired
    AgendaRepository agendaRepository;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    FuncionarioService funcionarioService;

    private final List<Observable> observadores = new ArrayList();

    public List<AgendaDto> getAllAgenda(){
        List<Agenda> ret = agendaRepository.findAll();
        List<AgendaDto> listRes = ret.stream().map(AgendaDtoFactory::toDto).collect(Collectors.toList());

        return  listRes;
    }

    public AgendaDto agendarServico(int func,
                                    String user,
                                    LocalDateTime date){

        Usuario resUsuario = usuarioService.buscarUsuarioPorCpf(user);
        Funcionario funcionario = funcionarioService.buscarFuncionario(func);
        FuncionarioAgendaDto funcionarioAgendaDto = new FuncionarioAgendaDto(
                funcionario
        );
        if(funcionario == null || resUsuario == null){
            return null;
        }
        AgendaDto res = new AgendaDto(funcionarioAgendaDto,date);
        Agenda saveAgenda = new Agenda(funcionario,resUsuario,date, res.getNotaServico(), res.getStatus());
        agendaRepository.save(saveAgenda);
        res.setCodigoServico(saveAgenda.getCodigoServico());
        return  res;
    }

    public List<AgendaDto> buscarAgendaUsaurio(String cpf){

        List<Agenda> agendaList = agendaRepository.findByUserCpf(cpf);

        List<AgendaDto> listRes = agendaList.stream().map(AgendaDtoFactory::toDto).collect(Collectors.toList());

        return listRes;
    }
    public List<AgendaDto> buscarAgendaFuncionario(int idFuncCpf) {

        List<Agenda> agendaList = agendaRepository.findByFuncId(idFuncCpf);

        List<AgendaDto> listRes = agendaList.stream().map(AgendaDtoFactory::toDto).collect(Collectors.toList());

        return listRes;
    }
    // PENSAR NA LOGICA
    public void mudarStatus(String novoStatus, int codigoAgenda){
        Agenda  agenda = new Agenda();
        agenda = agendaRepository.findByCodigoServico(codigoAgenda);
        agenda.setStatus(novoStatus);


        agendaRepository.save(agenda);

        notificarObservadores(agenda);
    }

    private void notificarObservadores(Agenda agenda) {
        for (Observable observador : observadores) {
            observador.notificar(agenda);
        }
    }

    public void adicionarObservador(Observable obs) {
        observadores.add(obs);
    }

    public void removerObservador(Observable obs) {
        observadores.remove(obs);
    }
}
