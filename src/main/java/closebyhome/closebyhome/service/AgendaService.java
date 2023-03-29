package closebyhome.closebyhome.service;

import closebyhome.closebyhome.dto.*;
import closebyhome.closebyhome.models.Agenda;
import closebyhome.closebyhome.models.Funcionario;
import closebyhome.closebyhome.models.Usuario;
import closebyhome.closebyhome.observable.Observable;
import closebyhome.closebyhome.pilhaObj.PilhaObj;
import closebyhome.closebyhome.repository.AgendaRepository;
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

    public List<AgendaDto> getAllAgenda() {
        List<Agenda> ret = agendaRepository.findAll();
        List<AgendaDto> listRes = ret.stream().map(AgendaDtoFactory::toDto).collect(Collectors.toList());

        return listRes;
    }

    public AgendaDto agendarServico(String cpfFuncionario, String user, LocalDateTime date) {

        Usuario resUsuario = usuarioService.buscarUsuarioPorCpf(user);
        Funcionario funcionario = funcionarioService.buscarFuncionario(cpfFuncionario);
        FuncionarioAgendaDto funcionarioAgendaDto = new FuncionarioAgendaDto(funcionario);

        if (funcionario == null || resUsuario == null) {
            return null;
        }

        AgendaDto res = new AgendaDto(funcionarioAgendaDto, date);
        Agenda saveAgenda = new Agenda(funcionario, resUsuario, date, res.getNotaServico(), res.getStatus());
        agendaRepository.save(saveAgenda);
        notificarObservadores(saveAgenda);

//        res.setCodigoServico(saveAgenda.getCodigoServico());
        return res;
    }

    public List<FuncionarioAgendaDto> buscarAgendaUsaurio(String cpf) {
        List<Agenda> agendaList = agendaRepository.findByUserCpf(cpf);
        List<FuncionarioAgendaDto> funcionarioAgendaDtoList = new ArrayList<>();

        for(Agenda ag : agendaList){
            int nota = pegarNota(ag.getFunc().getIdUsuario().getCpf());
            FuncionarioAgendaDto funcionarioAgendaDto = new FuncionarioAgendaDto(ag.getFunc(),ag,nota);
            funcionarioAgendaDtoList.add(funcionarioAgendaDto);
        }

        List<AgendaDto> listRes = agendaList.stream().map(AgendaDtoFactory::toDto).collect(Collectors.toList());

        return funcionarioAgendaDtoList;
    }

    public List<AgendaDtoUsuario> buscarAgendaFuncionario(String idFuncCpf) {
        List<Agenda> agendaList = agendaRepository.findByFuncIdUsuarioCpf(idFuncCpf);
        List<AgendaDtoUsuario> listRes = new ArrayList<>();
        for (Agenda ag : agendaList){
            AgendaDtoUsuario agendaDtoUsuario = new AgendaDtoUsuario(ag);
            listRes.add(agendaDtoUsuario);
        }
        return listRes;
    }

    public boolean mudarStatus(String novoStatus, int idAgenda) {
        Optional<Agenda> agenda = agendaRepository.findById(idAgenda);

        if (agenda.isPresent()) {
            agenda.get().setStatus(novoStatus);
            agendaRepository.save(agenda.get());
            notificarObservadores(agenda.get());
            return true;
        }
        return false;
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

    public List<AgendaDto> buscaOrdenadoPorData(String id, boolean empilhar) {
        List<Agenda> lista = agendaRepository.findAll();

        Agenda aux;
        for (int i = 0; i < lista.size(); i++) {
            for (int j = 0; j < lista.size(); j++) {

                if (lista.get(i).getData().isAfter(lista.get(j).getData())) {

                    aux = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, aux);
                }
            }
        }
        List<AgendaDto> listaDto = lista.stream().map(AgendaDtoFactory::toDto).collect(Collectors.toList());

        if (empilhar) {
            PilhaObj<AgendaDto> pilha = new PilhaObj(lista.size());

            for (int i = 0; !pilha.isFull(); i++) {
                pilha.push(listaDto.get(i));
            }
            for (int i = 0; !pilha.isEmpty(); i++) {
                listaDto.set(i, pilha.pop());
            }
        }

        return listaDto;
    }

    public AgendaDto avaliarAgenda(int nota, String cpfUsuario, Integer idAgenda) {
        Optional<Agenda> agendaAvaliada = agendaRepository.findById(idAgenda);

        if (agendaAvaliada.isPresent() && cpfUsuario.equals(agendaAvaliada.get().getUser().getCpf()) ) {
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

    public Double contarAvaliacoesPorFuncionario(String cpfFuncionario) {
        List<Agenda> agendasUsuario = agendaRepository.findByFuncIdUsuarioCpf(cpfFuncionario);
        Double quantidadeEstrelas = 5.0;
        Integer quantidade = 1;
        for (Agenda ag: agendasUsuario) {
            quantidade++;
            quantidadeEstrelas = quantidadeEstrelas + ag.getNotaServico();
        }
        quantidadeEstrelas = (quantidadeEstrelas / quantidade);


        return quantidadeEstrelas;
    }

    public List<AgendaDto> buscaAgendaPorData(String data) {
        LocalDateTime dataConvertida = LocalDateTime.parse(data);
        List<Agenda> lista = agendaRepository.findByData(dataConvertida);

        if (!lista.isEmpty()) {
            List<AgendaDto> resDto = lista.stream().map(AgendaDtoFactory::toDto).collect(Collectors.toList());
            return resDto;
        }
        return null;
    }

    public int pegarNota(String cpf){
        int nota = 5;
        List<Agenda> funcionario = agendaRepository.findByFuncIdUsuarioCpf(cpf);
        if (!(funcionario == null)){
            for (Agenda ag : funcionario){
                nota = nota + ag.getNotaServico();
            }
            nota = nota/(funcionario.size()+1);
        }
        return nota;
    }
}
