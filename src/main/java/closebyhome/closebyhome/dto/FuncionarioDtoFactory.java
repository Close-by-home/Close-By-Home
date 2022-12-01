package closebyhome.closebyhome.dto;

import closebyhome.closebyhome.models.Funcionario;

public class FuncionarioDtoFactory {

     public  static FuncionarioDto toDto(Funcionario funcionarioDomain){
        FuncionarioDto funcionarioDto = new FuncionarioDto();

        funcionarioDto.setNomeServico(funcionarioDomain.getNomeServico());
        funcionarioDto.setNomeServico(funcionarioDomain.getNomeServico());
        funcionarioDto.setValorMinimo(funcionarioDomain.getValorMinimo());
        funcionarioDto.setUsuario(funcionarioDomain.getIdUsuario());
        funcionarioDto.setNomeUsuario(funcionarioDomain.getIdUsuario().getNome());
        funcionarioDto.setEmailUsuario(funcionarioDomain.getIdUsuario().getEmail());
        funcionarioDto.setCpf(funcionarioDomain.getIdUsuario().getCpf());
//        funcionarioDto.setNota(0);
        return funcionarioDto;
    }
//    Pra que isso serve???
//    public  Funcionario toDomain(FuncionarioDto funcionarioDto){
//        Funcionario funcionario = new Funcionario();
//
//        funcionario.setNomeServico(funcionarioDto.getNomeServico());
//        funcionario.setNomeServico(funcionarioDto.getNomeServico());
//        funcionario.setValorMinimo(funcionarioDto.getValorMinimo());
//        funcionario.setIdUsuario(funcionarioDto.pegaUsuario());
//        return funcionario;
//    }
//    public  FuncionarioDto retornaFuncionarioDto(Funcionario funcionarioDomain){
//        FuncionarioDto funcionarioDto = new FuncionarioDto();
//
//        funcionarioDto.setAgenda(funcionarioDomain.getAgenda());
//        funcionarioDto.setData(funcionarioDomain.getData());
//        funcionarioDto.setDescricao(funcionarioDomain.getDescricao());
//        funcionarioDto.setNomeServico(funcionarioDomain.getNomeServico());
//        funcionarioDto.setValorMinimo(funcionarioDomain.getValorMinimo());
//        funcionarioDto.setUsuario(funcionarioDto.getUsuario());
//        return funcionarioDto;
//    }
}
