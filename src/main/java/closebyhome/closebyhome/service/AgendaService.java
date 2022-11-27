package closebyhome.closebyhome.service;

import closebyhome.closebyhome.dto.*;
import closebyhome.closebyhome.models.Agenda;
import closebyhome.closebyhome.models.Funcionario;
import closebyhome.closebyhome.models.Usuario;
import closebyhome.closebyhome.observable.Observable;
import closebyhome.closebyhome.pilhaObj.PilhaObj;
import closebyhome.closebyhome.repository.AgendaRepository;
import closebyhome.closebyhome.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Agenda  agenda = agendaRepository.findByCodigoServico(codigoAgenda);
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
    public List<AgendaDto> buscaOrdenadoPorData(int id,boolean empilhar){
        List<Agenda> lista = agendaRepository.findAll();

        Agenda aux;
        for (int i = 0; i < lista.size(); i++) {
            for (int j = 0; j < lista.size(); j++) {

                if (lista.get(i).getData().isAfter(lista.get(j).getData())){

                    aux = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, aux);
                }
            }
        }
        List<AgendaDto> listaDto = lista.stream().map(AgendaDtoFactory::toDto).collect(Collectors.toList());

        if(empilhar){
            PilhaObj<AgendaDto> pilha = new PilhaObj(lista.size());

            for (int i = 0; !pilha.isFull(); i++) {
                pilha.push(listaDto.get(i));
            }
            for (int i = 0; !pilha.isEmpty(); i++) {
                listaDto.set(i,pilha.pop());
            }
        }

        return listaDto;
    }

    public AgendaDto avaliarAgenda(int nota,Integer idUsuario,Integer idAgenda){
        Optional<Agenda> agendaAvaliada = agendaRepository.findById(idAgenda);

        if(agendaAvaliada.isPresent() && idUsuario == agendaAvaliada.get().getUser().getId()){
            agendaAvaliada.get().setNotaServico(nota);
            agendaRepository.save(agendaAvaliada.get());

            //retornando a agenda atualizada em formato DTO
            List<Agenda> lista = new ArrayList<>();
            lista.add(agendaAvaliada.get());
            List<AgendaDto> resDto = lista.stream().map(AgendaDtoFactory::toDto).collect(Collectors.toList());

            return resDto.get(0);
        }
        return null;
    }

    public Long contarAvaliacoesPorFuncionario(Integer idFuncionario){
        Long quantidadeAvaliacoes = agendaRepository.countById(idFuncionario);

        if(quantidadeAvaliacoes >= 0){
            return quantidadeAvaliacoes;
        }
        return null;
    }

    public List<AgendaDto> buscaAgendaPorData(LocalDateTime data){
        List<Agenda> lista = agendaRepository.findByData(data);

        if(!lista.isEmpty()){
            List<AgendaDto> resDto = lista.stream().map(AgendaDtoFactory::toDto).collect(Collectors.toList());
            return resDto;
        }
        return null;
    }
}
