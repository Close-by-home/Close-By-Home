package closebyhome.closebyhome.controller;

import closebyhome.closebyhome.dto.ChatDto;
import closebyhome.closebyhome.service.ChatService;
import closebyhome.closebyhome.service.NotificacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Chat", description = "Requesição dos CHat.")
@CrossOrigin(origins = "http://3.221.81.114:3000", maxAge = 3600)
@RestController
@RequestMapping("/chat")
public class ChatComtroller {

    @Autowired
    ChatService chatService;
    @Autowired
    NotificacaoService notificacaoService;

    @GetMapping("{cpfUsuario}/{cpfFUncionario}")
    public ResponseEntity<List<ChatDto>> buscarConversa(
            @PathVariable String cpfUsuario,
            @PathVariable String cpfFUncionario
    ) {

          List<ChatDto> res = chatService.getConversa(cpfUsuario,cpfFUncionario);

        if (res.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(res);
    }

    @PostMapping("{cpfFuncionario}/{cpfUsuario}/{mensagem}")
    public ResponseEntity<ChatDto> enviarMensagemFuncionario(
            @PathVariable String cpfFuncionario,
            @PathVariable String cpfUsuario,
            @PathVariable String mensagem
    ) {
        ChatDto res = chatService.chatEnviarMensagemFuncionario(cpfFuncionario, cpfUsuario,mensagem);
        //chatService.adicionarObservador(notificacaoService);

        return ResponseEntity.status(201).body(res);
    }

    @PostMapping("{cpfUsuario}/{cpfFuncionario}/{mensagem}")
    public ResponseEntity<ChatDto> enviarMensagemUsuario(
            @PathVariable String cpfUsuario,
            @PathVariable String cpfFuncionario,
            @PathVariable String mensagem
    ) {
        ChatDto res = chatService.chatEnviarMensagemUsuario(cpfUsuario,cpfFuncionario,mensagem);
        //chatService.adicionarObservador(notificacaoService);

        return ResponseEntity.status(201).body(res);
    }
}
