package closebyhome.closebyhome.service;

import closebyhome.closebyhome.dto.*;
import closebyhome.closebyhome.models.Agenda;
import closebyhome.closebyhome.models.Condominio;
import closebyhome.closebyhome.models.Funcionario;
import closebyhome.closebyhome.models.Usuario;
import closebyhome.closebyhome.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private AgendaRepository agendaRepository;
    @Autowired
    private CondominioRepository condominioRepository;

    public List<FuncionarioDto> buscarTodosFuncionarios() {
        List<Funcionario> listaFuncionario = funcionarioRepository.findAll();
        List<FuncionarioDto> listRes = listaFuncionario.stream().map(FuncionarioDtoFactory::toDto).collect(Collectors.toList());
        return listRes;
    }

    public Funcionario buscarFuncionario(String cpfFuncionario) {
        Funcionario res = funcionarioRepository.findByIdUsuarioCpf(cpfFuncionario);
        return res;
    }

    public List<FuncionarioDto> buscarPorCondominio(String codigoCondominio) {
        Condominio condominio = condominioRepository.findByCodigoCondominio(codigoCondominio);
        List<Funcionario> listaFuncionario = funcionarioRepository.findAll();
        System.out.println(condominio.getId());

        if (!listaFuncionario.isEmpty()) {

            List<Funcionario> listRes = new ArrayList<>();
            for (int i = 0; i < listaFuncionario.size(); i++) {
                if(listaFuncionario.get(i).getIdUsuario().getCodigoCondominio().getId()== condominio.getId()){
                    listRes.add(listaFuncionario.get(i));
                }
            }
            List<FuncionarioDto> listDto = listRes.stream().map(FuncionarioDtoFactory::toDto).collect(Collectors.toList());
            return listDto;
        }

        return null;
    }

    public List<FuncionarioDto> buscarFuncionarioPeloNome(String nome) {
        List<Funcionario> lista = funcionarioRepository.findAll();
        List<Funcionario> listaEncontrados = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdUsuario().getNome().equals(nome)) {
                listaEncontrados.add(lista.get(i));
            }
        }

        List<FuncionarioDto> listRes = lista.stream().map(FuncionarioDtoFactory::toDto).collect(Collectors.toList());
        return listRes;
    }

    public List<FuncionarioDto> buscarFuncionarioPeloServico(String servico) {
        List<Funcionario> res = funcionarioRepository.findByNomeServico(servico);

        List<FuncionarioDto> listRes = res.stream().map(FuncionarioDtoFactory::toDto).collect(Collectors.toList());
        return listRes;
    }

    public List<FuncionarioDto> buscarFuncionarioPeloServicoEnome(String servico, String nome) {
        List<Funcionario> lista = funcionarioRepository.findAll();
        List<Funcionario> listaEncontrados = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNomeServico().equals(servico) && lista.get(i).getIdUsuario().getNome().equals(nome)) {
                listaEncontrados.add(lista.get(i));
            }
        }

        List<FuncionarioDto> listRes = lista.stream().map(FuncionarioDtoFactory::toDto).collect(Collectors.toList());
        return listRes;
    }


}
