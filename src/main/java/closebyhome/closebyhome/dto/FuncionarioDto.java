package closebyhome.closebyhome.dto;

import closebyhome.closebyhome.models.Agenda;
import closebyhome.closebyhome.models.Data;
import closebyhome.closebyhome.models.Descricao;
import closebyhome.closebyhome.models.Usuario;

import java.util.List;

public class FuncionarioDto {

    private String nomeServico;
  //  private Descricao descricao;
    private Double valorMinimo;
    //private List<Agenda> agenda;
   // private List<Data> data;
 //   private Usuario usuario;

 //   public Usuario getUsuario() {
   //     return usuario;
  //  }

//    public void setUsuario(Usuario usuario) {
   //     this.usuario = usuario;
  //  }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    //public Descricao getDescricao() {
  //      return descricao;
  //  }

  //  public void setDescricao(Descricao descricao) {
  //      this.descricao = descricao;
 //   }

    public Double getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(Double valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    //public List<Agenda> getAgenda() {
   //     return agenda;
   // }

   /// public void setAgenda(List<Agenda> agenda) {
   //     this.agenda = agenda;
  //  }

  ///  public List<Data> getData() {
   //     return data;
  //  }

   // public void setData(List<Data> data) {
   //     this.data = data;
   // }
}
