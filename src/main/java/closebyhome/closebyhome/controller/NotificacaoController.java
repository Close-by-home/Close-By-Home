package closebyhome.closebyhome.controller;

import closebyhome.closebyhome.dto.AgendaDto;
import closebyhome.closebyhome.dto.NotificacaoDto;
import closebyhome.closebyhome.dto.UsuarioDto;
import closebyhome.closebyhome.models.Agenda;
import closebyhome.closebyhome.repository.AgendaRepository;
import closebyhome.closebyhome.repository.NotificacaoRepository;
import closebyhome.closebyhome.service.AgendaService;
import closebyhome.closebyhome.service.NotificacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Notificacao", description = "Requesição das notificações dos serviços.")
@CrossOrigin(origins = "http://10.18.6.31:3000", maxAge = 3600)
@RestController
@RequestMapping("/notificacao")
public class NotificacaoController {

    @Autowired
    NotificacaoService notificacaoService;

    @GetMapping
    public ResponseEntity<List<NotificacaoDto>> listar() {
        List<NotificacaoDto> res = notificacaoService.getAllNotificacao();

        if (res.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(res);
    }

    @GetMapping("{idUsuario}")
    public ResponseEntity<List<NotificacaoDto>> listarPorAgenda(@PathVariable Integer idUsuario) {
        List<NotificacaoDto> res = this.notificacaoService.getAllByUsuario(idUsuario);

        if (res.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(res);
    }

    @DeleteMapping("{idNotificacao}")
    public ResponseEntity<List<NotificacaoDto>> deletarPorId(@PathVariable Integer idNotificacao) {
        Boolean res = this.notificacaoService.deleteById(idNotificacao);

        if (res) {
            return ResponseEntity.status(201).build();
        }

        return ResponseEntity.status(204).build();
    }



}