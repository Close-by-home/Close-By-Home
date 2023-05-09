package closebyhome.closebyhome.dto;

import closebyhome.closebyhome.models.Agenda;
import closebyhome.closebyhome.models.Chat;

public class ChatDtoFactory {
    public static ChatDto toDto(Chat chatDomain){
        ChatDto chatDto = new ChatDto();

        chatDto.setId(chatDomain.getId());
        chatDto.setMensagem(chatDomain.getMensagem());
        chatDto.setFuncionario(chatDomain.getFunc().getIdUsuario().getNome());
        chatDto.setUsuario(chatDomain.getUser().getNome());
        chatDto.setTempoEnvio(chatDomain.getDataDoEnvio());

        return chatDto;
    }
}
