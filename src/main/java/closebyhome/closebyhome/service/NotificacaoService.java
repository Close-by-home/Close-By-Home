package closebyhome.closebyhome.service;

import closebyhome.closebyhome.dto.*;
import closebyhome.closebyhome.models.*;
import closebyhome.closebyhome.observable.Observable;
import closebyhome.closebyhome.pilhaObj.PilhaObj;
import closebyhome.closebyhome.repository.AgendaRepository;
import closebyhome.closebyhome.repository.NotificacaoRepository;
import closebyhome.closebyhome.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificacaoService implements Observable {

    @Autowired
    NotificacaoRepository notificacaoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    public Boolean deleteById(Integer id) {
        Optional<Notificacao> ret = notificacaoRepository.findById(id);
        if(ret.isPresent()){
            notificacaoRepository.deleteById(id);
            return true;
        }

        return false;
    }
    public List<NotificacaoDto> getAllNotificacao() {
        List<Notificacao> ret = notificacaoRepository.findAll();
        List<NotificacaoDto> listRes = ret.stream().map(NotificacaoDtoFactory::toDto).collect(Collectors.toList());

        return listRes;
    }

    public List<NotificacaoDto> getAllByUsuario(Integer idUsuario) {

        Optional<Usuario> u = usuarioRepository.findById(idUsuario);
        List<Notificacao> listaNotificacao = notificacaoRepository.findByUsuario(u);

        List<NotificacaoDto> listRes = listaNotificacao.stream().map(NotificacaoDtoFactory::toDto).collect(Collectors.toList());
        return listRes;
    }

    @Override
    public void notificar(Agenda agenda) {

        String status = agenda.getStatus().toLowerCase();
        Notificacao novaNotificacao = new Notificacao("", "", agenda);

        //isto estar programado para trazer o primerio o primeiro nome apenas
        String nomeUser = agenda.getUser().getNome().split(" ")[0];
        String nomeFunc = agenda.getFunc().getIdUsuario().getNome().split(" ")[0];

        LocalDateTime data = agenda.getData();
        System.out.println("Chegou na classe notificacaoService" + status);
        switch (status) {
            case "agendado":
                System.out.println("DEVERIA TER IDO AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                novaNotificacao.setTitulo("Serviço agendado");
                novaNotificacao.setDescricao("Olá " + nomeUser + ", você agendou um novo serviço" +
                        " para a data " + data + " e o mesmo já pode ser visualizado através da sua agenda." +
                        "Agora aguarde um pouco, em breve " + nomeFunc + " entrará em contato para iniciar o serviço!");
                notificacaoRepository.save(novaNotificacao);
                break;
            case "iniciado":
                novaNotificacao.setTitulo("Serviço iniciado");
                novaNotificacao.setDescricao("Olá " + nomeUser + ", apenas passando pra te avisar que " +
                        nomeFunc + " já esta atuando no serviço agendado e em breve te avisaram quando" +
                        " o serviço estiver concluído.");
                notificacaoRepository.save(novaNotificacao);
                break;
            case "finalizado":
                novaNotificacao.setTitulo("Serviço concluído");
                novaNotificacao.setDescricao("Olá " + nomeUser + ", parece que o serviço agendado para a data " + data +
                        " foi concluido pelo " + nomeFunc + ". Agora que tal avaliar o serviço prestado?");
                notificacaoRepository.save(novaNotificacao);
                break;
            case "cancelado":
                novaNotificacao.setTitulo("Serviço cancelado");
                novaNotificacao.setDescricao("Olá " + nomeUser + ", lamentamos em informar que ocorreu um emprevisto no serviço" +
                        " agendado para a data " + data + ",prestado por" + nomeFunc + ", e ele foi cancelado :(");
                notificacaoRepository.save(novaNotificacao);
                break;
        }
    }
}
