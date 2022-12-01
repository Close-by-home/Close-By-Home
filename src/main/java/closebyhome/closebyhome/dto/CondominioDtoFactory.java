package closebyhome.closebyhome.dto;


import closebyhome.closebyhome.models.Condominio;

public class CondominioDtoFactory {
    public  static CondominioDto toDto(Condominio condominioDomain){
        CondominioDto condominioDto = new CondominioDto();

        condominioDto.setCodigoCondominio(condominioDomain.getCodigoCondominio());
        condominioDto.setCep(condominioDomain.getCep());
        condominioDto.setCnpj(condominioDomain.getCnpj());
        condominioDto.setSindico(condominioDomain.getSindico());
        condominioDto.setNumero(condominioDomain.getNumero());
        condominioDto.setTelefone(condominioDomain.getTelefone());
        condominioDto.setEmailSindico(condominioDomain.getEmailSindico());
        condominioDto.setQuatidadeDeBlocos(condominioDomain.getQuatidadeDeBlocos());

        return condominioDto;
    }
}
