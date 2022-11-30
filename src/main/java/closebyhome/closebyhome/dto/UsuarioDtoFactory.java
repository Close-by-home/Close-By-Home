package closebyhome.closebyhome.dto;

import closebyhome.closebyhome.models.Usuario;

public class UsuarioDtoFactory {


    public static UsuarioDto toDto(Usuario usuarioDomain){

        UsuarioDto usuarioDto = new UsuarioDto();


        usuarioDto.setBloco(usuarioDomain.getBloco());
        usuarioDto.setCpf(usuarioDomain.getCpf());
        usuarioDto.setEmail(usuarioDomain.getEmail());
        usuarioDto.setNome(usuarioDomain.getNome());
        usuarioDto.setSenha(usuarioDomain.getSenha());
        usuarioDto.setTelefone(usuarioDomain.getTelefone());
        usuarioDto.setCodigoCondominio(usuarioDomain.getCodigoCondominio().getCodigoCondominio());
        usuarioDto.setImagem(usuarioDomain.getImagem());
        usuarioDto.setFuncionario(usuarioDomain.getFuncionario());
        return usuarioDto;
    }

    public static Usuario toDomain(UsuarioDto usuarioDto){

        Usuario usuarioDomain = new Usuario();

        usuarioDomain.setFuncionario(false);
        usuarioDomain.setBloco(usuarioDto.getBloco());
        usuarioDomain.setCpf(usuarioDto.getCpf());
        usuarioDomain.setEmail(usuarioDto.getEmail());
        usuarioDomain.setNome(usuarioDto.getNome());
        usuarioDomain.setSenha(usuarioDto.getSenha());
        usuarioDomain.setTelefone(usuarioDto.getTelefone());

        return usuarioDomain;
    }

}
