package closebyhome.closebyhome.controller;

import closebyhome.closebyhome.dto.AgendaDto;
import closebyhome.closebyhome.service.AgendaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Agenda", description = "Requesição dos Usuario.")
@CrossOrigin(origins =  "http://localhost:3000",maxAge = 3600)
@RestController
@RequestMapping("/agenda")
public class ControllerAgenda {

    @Autowired
    AgendaService agendaService;

    @GetMapping
    public ResponseEntity<List<AgendaDto>> getTodasAgendas(){

        List<AgendaDto> res = agendaService.getAllAgenda();

        if (res.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return  ResponseEntity.status(200).body(res);
    }
    @PostMapping("{idFuncionario}/{cpfUsuario}")
    public ResponseEntity<AgendaDto> setAgendar(
            @PathVariable int idFuncionario,
            @PathVariable String cpfUsuario,
            @RequestParam String data
    ){
        AgendaDto res = agendaService.agendarServico(idFuncionario,cpfUsuario,LocalDateTime.parse(data));

        return ResponseEntity.status(201).body(res);
    }

    @GetMapping("/{cpfUsuario}")
    public ResponseEntity<List<AgendaDto>> getAgendaUsuario(
            @PathVariable String cpfUsuario
    ){
        List<AgendaDto> res = agendaService.buscarAgendaUsaurio(cpfUsuario);


        if (res.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return  ResponseEntity.status(200).body(res);
    }

    @GetMapping("/{idFuncionario}")
    public ResponseEntity<List<AgendaDto>> getAgendaFuncionario(
            @PathVariable int idFuncionario
    ){
        List<AgendaDto> res = agendaService.buscarAgendaFuncionario(idFuncionario);


        if (res.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return  ResponseEntity.status(200).body(res);
    }

    @PutMapping("atualizar-status/{novoStauts}/{codigoServico}")
    public ResponseEntity<String> atualizarStatus(
            @PathVariable String novoStauts,
            @PathVariable int codigoServico
    ){
        agendaService.mudarStatus(novoStauts,codigoServico);
        return ResponseEntity.status(200).body("sucesso");
    }
}
