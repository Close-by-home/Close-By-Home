package closebyhome.closebyhome.service;

import closebyhome.closebyhome.dto.AgendaDto;
import closebyhome.closebyhome.dto.AgendaDtoFactory;
import closebyhome.closebyhome.dto.ChatDto;
import closebyhome.closebyhome.dto.ChatDtoFactory;
import closebyhome.closebyhome.models.Agenda;
import closebyhome.closebyhome.models.Chat;
import closebyhome.closebyhome.models.Funcionario;
import closebyhome.closebyhome.models.Usuario;
import closebyhome.closebyhome.repository.AgendaRepository;
import closebyhome.closebyhome.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {
    @Autowired
    ChatRepository chatRepository ;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    FuncionarioService funcionarioService;

        public List<ChatDto> getConversa(String user, String func) {
        List<Chat> ret = chatRepository.
                fndByFuncIdUsuarioCpfAndUserCpf(func,user);

        List<ChatDto> listRes = ret.stream().map(ChatDtoFactory::toDto).collect(Collectors.toList());

            return listRes;
    }

        public ChatDto chatEnviarMensagemFuncionario(
                String cpfFuncionario,
                String cpfUsuario,
                String mensagem){
            ChatDto ret = new ChatDto();

            Usuario resUsuario = usuarioService.buscarUsuarioPorCpf(cpfUsuario);
            Funcionario funcionario = funcionarioService.buscarFuncionario(cpfFuncionario);

            Chat chat = new Chat(
                    funcionario,resUsuario,mensagem
            );
            chatRepository.save(chat);
            return ret;
        }
        public ChatDto chatEnviarMensagemUsuario(String cpfFuncionario,
                                                 String cpfUsuario,
                                                 String mensagem){
            ChatDto ret = new ChatDto();
            Usuario resUsuario = usuarioService.buscarUsuarioPorCpf(cpfUsuario);
            Funcionario funcionario = funcionarioService.buscarFuncionario(cpfFuncionario);

            Chat chat = new Chat(
                    funcionario,resUsuario,mensagem
            );
            chatRepository.save(chat);
            return  ret;
        }
}
