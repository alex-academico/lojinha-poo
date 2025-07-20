package model.produto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProdutoPerecivel extends Produto {

    public enum FormaArmazenamentoPerecivel {
        PRATELEIRA, REFRIGERADOR, CONGELADOR, FRESCO
    }

    private LocalDate validade;
    private FormaArmazenamentoPerecivel formaArmazenamento;

    public ProdutoPerecivel(String codigo, String nome, BigDecimal preco, int estoque,
                            LocalDate validade, FormaArmazenamentoPerecivel forma) {
        super(codigo, nome, preco, estoque);
        this.validade = validade;
        this.formaArmazenamento = forma;
    }

    public String getDetalhes() {
        return "| Perecível: validade até " + validade + ", armazenado em " + formaArmazenamento;
    }
}
