package closebyhome.closebyhome.service;

import closebyhome.closebyhome.dto.*;
import closebyhome.closebyhome.models.Condominio;
import closebyhome.closebyhome.models.Funcionario;
import closebyhome.closebyhome.models.Usuario;
import closebyhome.closebyhome.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CondominioRepository condominioRepository;

    public List<FuncionarioDto> buscarTodosFuncionarios() {
        List<Funcionario> listaFuncionario = funcionarioRepository.findAll();
        List<FuncionarioDto> listRes = listaFuncionario.stream().map(FuncionarioDtoFactory::toDto).collect(Collectors.toList());
        return listRes;
    }

    public Funcionario buscarFuncionario(int idFunc) {
        Funcionario res = funcionarioRepository.findById(idFunc);
        return res;
    }

    public List<FuncionarioDto> buscarPorCondominio(String codigoCondominio) {
        List<Funcionario> listaFuncionario = funcionarioRepository.findAll();

        if (!listaFuncionario.isEmpty()) {

            List<Funcionario> listRes = new ArrayList<>();
            for (int i = 0; i < listaFuncionario.size(); i++) {
                String codigoAtual =listaFuncionario.get(i).getIdUsuario().getCodigoCondominio().getCodigoCondominio().toLowerCase();
                if(codigoAtual.equals(codigoCondominio.toLowerCase())){
                    listRes.add(listaFuncionario.get(i));
                }
            }
            List<FuncionarioDto> listDto = listRes.stream().map(FuncionarioDtoFactory::toDto).collect(Collectors.toList());
            return listDto;
        }

        return null;
    }

    public List<FuncionarioDto> buscarFuncionarioPeloNome(String nome,String codigoCondominio) {
        List<Funcionario> lista = funcionarioRepository.findAll();
        List<Funcionario> listaEncontrados = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            String codigoAtual =lista.get(i).getIdUsuario().getCodigoCondominio().getCodigoCondominio().toLowerCase();
            String nomeAtual = lista.get(i).getIdUsuario().getNome().toLowerCase();

            if (codigoAtual.equals(codigoCondominio.toLowerCase()) && nomeAtual.equals(nome.toLowerCase())) {
                listaEncontrados.add(lista.get(i));
            }
        }

        List<FuncionarioDto> listRes = listaEncontrados.stream().map(FuncionarioDtoFactory::toDto).collect(Collectors.toList());
        return listRes;
    }

    public List<FuncionarioDto> buscarFuncionarioPeloServico(String servico,String codigoCondominio) {
        List<Funcionario> lista = funcionarioRepository.findByNomeServico(servico);
        List<Funcionario> listaEncontrados = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            String codigoAtual =lista.get(i).getIdUsuario().getCodigoCondominio().getCodigoCondominio().toLowerCase();
            String servicoAtual = lista.get(i).getNomeServico().toLowerCase();
            if (codigoAtual.equals(codigoCondominio.toLowerCase())&&servicoAtual.equals(servico.toLowerCase())) {
                listaEncontrados.add(lista.get(i));
            }
        }
        List<FuncionarioDto> listRes = listaEncontrados.stream().map(FuncionarioDtoFactory::toDto).collect(Collectors.toList());
        return listRes;
    }

    public List<FuncionarioDto> buscarFuncionarioPeloServicoEnome(String nome, String servico,String codigoCondominio) {
        List<Funcionario> lista = funcionarioRepository.findAll();
        List<Funcionario> listaEncontrados = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            String codigoAtual =lista.get(i).getIdUsuario().getCodigoCondominio().getCodigoCondominio().toLowerCase();
            String nomeAtual = lista.get(i).getIdUsuario().getNome().toLowerCase();
            String servicoAtual = lista.get(i).getNomeServico().toLowerCase();
            if (
                    servicoAtual.equals(servico.toLowerCase()) &&
                    nomeAtual.equals(nome.toLowerCase()) &&
                    codigoAtual.equals(codigoCondominio.toLowerCase())
            ) {
                listaEncontrados.add(lista.get(i));
            }
        }

        List<FuncionarioDto> listRes = listaEncontrados.stream().map(FuncionarioDtoFactory::toDto).collect(Collectors.toList());
        return listRes;
    }

}
