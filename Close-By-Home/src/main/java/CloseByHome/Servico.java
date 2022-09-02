package CloseByHome;

import java.util.ArrayList;
import java.util.List;

public class Servico {
    // Atributos
    private String nomeServico;
    private String descricao;
    private Double previsaoDeOrcamento;
    private String categoria;
    private List<Agenda>agendas;

    // Construtor
    public Servico(String nomeServico, String descricao,
                   Double previsaoDeOrcamento, String categoria,
                   List<Agenda> agendas) {
        this.nomeServico = nomeServico;
        this.descricao = descricao;
        this.previsaoDeOrcamento = previsaoDeOrcamento;
        this.categoria = categoria;
        this.agendas = new ArrayList<>();
    }

    // Métodos


}
