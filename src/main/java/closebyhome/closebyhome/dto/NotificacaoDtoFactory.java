package closebyhome.closebyhome.dto;


import closebyhome.closebyhome.models.Notificacao;

public class NotificacaoDtoFactory {
     public  static NotificacaoDto toDto(Notificacao notificacaoDomain){
        NotificacaoDto notificacaoDto = new NotificacaoDto();

         notificacaoDto.setTitulo(notificacaoDomain.getTitulo());
         notificacaoDto.setDescricao(notificacaoDomain.getDescricao());
        return notificacaoDto;
    }
}
