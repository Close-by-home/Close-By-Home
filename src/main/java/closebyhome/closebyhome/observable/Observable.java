package closebyhome.closebyhome.observable;

import closebyhome.closebyhome.models.Agenda;
import closebyhome.closebyhome.service.AgendaService;

public interface Observable {

    void notificar(Agenda agenda);
}
