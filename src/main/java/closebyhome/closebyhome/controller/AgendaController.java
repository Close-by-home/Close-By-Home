package closebyhome.closebyhome.controller;

import closebyhome.closebyhome.dto.AgendaDto;
import closebyhome.closebyhome.models.Notificacao;
import closebyhome.closebyhome.service.AgendaService;
import closebyhome.closebyhome.service.NotificacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Agenda", description = "Requesição dos Usuario.")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    AgendaService agendaService;
    @Autowired
    NotificacaoService notificacaoService;


    @GetMapping
    public ResponseEntity<List<AgendaDto>> listar() {

        List<AgendaDto> res = agendaService.getAllAgenda();

        if (res.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(res);
    }

    @PostMapping("{idFuncionario}/{cpfUsuario}")
    public ResponseEntity<AgendaDto> setAgenda(
            @PathVariable int idFuncionario,
            @PathVariable String cpfUsuario,
            @RequestParam String data
    ) {
        AgendaDto res = agendaService.agendarServico(idFuncionario, cpfUsuario, LocalDateTime.parse(data));
        agendaService.adicionarObservador(notificacaoService);

        return ResponseEntity.status(201).body(res);
    }

    @GetMapping("/buscarPorCpf/{cpfUsuario}")
    public ResponseEntity<List<AgendaDto>> getAgendaPorCpf(
            @PathVariable String cpfUsuario
    ) {
        List<AgendaDto> res = agendaService.buscarAgendaUsaurio(cpfUsuario);

        if (res.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(res);
    }

    @GetMapping("/{idFuncionario}")
    public ResponseEntity<List<AgendaDto>> getAgendaPorId(
            @PathVariable int idFuncionario
    ) {
        List<AgendaDto> res = agendaService.buscarAgendaFuncionario(idFuncionario);

        if (res.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(res);
    }

    @PutMapping("atualizar-status/{novoStatus}/{idAgenda}")
    public ResponseEntity<Boolean> atualizarStatus(
            @PathVariable String novoStatus,
            @PathVariable int idAgenda
    ) {
        Boolean res = agendaService.mudarStatus(novoStatus, idAgenda);

        if(res){
            return ResponseEntity.status(201).body(res);
        }
        return ResponseEntity.status(201).build();

    }

    //Retorna os serviços agendados por data em ordem decrescente, caso o empilhar seja true,
    //retorna em ordem crescente(mais antigo pro mais recente)
    @GetMapping("nota/{idFuncionario}/{empilhar}")
    public ResponseEntity<List<AgendaDto>> buscarPorData(
            @PathVariable int idFuncionario,
            @PathVariable boolean empilhar
    ) {
        List<AgendaDto> res = agendaService.buscaOrdenadoPorData(idFuncionario, empilhar);

        if (res.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(res);
    }

    //Atualiza a avaliação do serviço onde id for = {idAgenda}
    @PutMapping("avaliarAgenda/{nota}/{idUsuario}/{idAgenda}")
    public ResponseEntity<List<AgendaDto>> AvaliarAgenda(
            @PathVariable int nota,
            @PathVariable Integer idUsuario,
            @PathVariable Integer idAgenda
    ) {
        AgendaDto res = null;
        res = agendaService.avaliarAgenda(nota, idUsuario, idAgenda);

        if (res != null) {
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(204).build();

    }
    @GetMapping("contarAvaliacoes/{id}")
    public ResponseEntity<Long> contarAvaliacoes(@PathVariable Integer id){
        Long res = null;
        res = agendaService.contarAvaliacoesPorFuncionario(id);

        if (res != null) {
            return ResponseEntity.status(200).body(res);
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("buscaAvaliacaoPorData/{data}")
    public ResponseEntity<List<AgendaDto>> buscaAvaliacaoPorData(@PathVariable String data){
        List<AgendaDto> res = agendaService.buscaAgendaPorData(data);

        if (res != null) {
            return ResponseEntity.status(200).body(res);
        }
        return ResponseEntity.status(204).build();
    }

}