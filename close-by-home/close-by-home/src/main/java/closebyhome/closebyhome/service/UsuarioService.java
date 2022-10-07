package closebyhome.closebyhome.service;

import closebyhome.closebyhome.dto.UsuarioDto;
import closebyhome.closebyhome.dto.UsuarioDtoFactory;
import closebyhome.closebyhome.models.Usuario;
import closebyhome.closebyhome.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDto> buscar(){

        List<Usuario> listaUsuario = usuarioRepository.findAll();
        UsuarioDto res = new UsuarioDto();
        List<UsuarioDto> listRes = listaUsuario.stream().map(UsuarioDtoFactory::toDto).collect(Collectors.toList());


        return listRes;
    }

    public UsuarioDto cadastrar(UsuarioDto res){
        Usuario user = new Usuario() ;

        user.setFuncionario(false);
        user.setBloco(res.getBloco());
        user.setCpf(res.getCpf());
        user.setEmail(res.getEmail());
        user.setCodigoCondominio(res.getCodigoCondominio());
        user.setNome(res.getNome());
        user.setSenha(res.getSenha());
        user.setTelefone(res.getTelefone());
        this.usuarioRepository.save(user);

        return res;
    }

    public Boolean atualizarSenha(String email, String senhaAtual, String novaSenha) {

        Usuario usuario = buscarId(email,senhaAtual);
        if (usuario != null) {
            usuario.setSenha(novaSenha);
            usuarioRepository.save(usuario);

            return true;
        }
        return false;
    }
    public Boolean ativarContaFuncionario(String email){
        Usuario usuario = buscarIdLogado(email);
        if (usuario != null) {
            usuario.setFuncionario(true);
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }
    public Boolean buscarUsuario(String codCondominio,String email,String senha)
    {
        List<Usuario> listaUsuario = usuarioRepository.findAll();
        for ( Usuario user : listaUsuario) {
            if(codCondominio.equals(user.getCodigoCondominio()) &&
                    email.equals(user.getEmail())&&
                    senha.equals(user.getSenha())){
                return  true;
            }
        }
        return false;
    }

    private Usuario buscarId(String email, String senha){
        List<Usuario> listaUsuario = usuarioRepository.findAll();
        for (Usuario user : listaUsuario) {
            if (email.equals(user.getEmail()) && senha.equals(user.getSenha())){

                return user;
            }
        }
        return null;

    }
    private Usuario buscarIdLogado(String email){
        List<Usuario> listaUsuario = usuarioRepository.findAll();
        for (Usuario user : listaUsuario) {
            if (email.equals(user.getEmail())){
                return user;
            }
        }
        return null;

    }
}

