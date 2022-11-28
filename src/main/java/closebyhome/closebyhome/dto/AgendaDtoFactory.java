package closebyhome.closebyhome.dto;


import closebyhome.closebyhome.models.Agenda;


public class AgendaDtoFactory {

    public static AgendaDto toDto(Agenda agendaDomain){
        AgendaDto res = new AgendaDto();

        FuncionarioAgendaDto funcionarioAgendaDto = new FuncionarioAgendaDto(agendaDomain.getFunc());
        res.setData(agendaDomain.getData());
        res.setFunc(funcionarioAgendaDto);
        res.setStatus(agendaDomain.getStatus());
        res.setNotaServico(agendaDomain.getNotaServico());
        res.setCodigoServico(agendaDomain.getCodigoServico());

        return res;
    }

}
