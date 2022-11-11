package closebyhome.closebyhome.dto;


import closebyhome.closebyhome.models.Agenda;


public class AgendaDtoFactory {

    public static AgendaDto toDto(Agenda agendaDomain){
        AgendaDto res = new AgendaDto();

        res.setData(agendaDomain.getData());
        res.setFunc(agendaDomain.getFunc());
        res.setStatus(agendaDomain.getStatus());
        res.setNotaServico(agendaDomain.getNotaServico());
        res.setNomeFuncionario(agendaDomain.getFunc().getIdUsuario().getNome());
        res.setUsuario(agendaDomain.getFunc().getIdUsuario());
        return res;
    }

}
