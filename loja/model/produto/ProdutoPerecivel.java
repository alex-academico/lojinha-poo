package model.produto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public Produto.TipoProduto getTipoProduto() {
        return Produto.TipoProduto.PERECIVEL;
    }

    public String getDetalhes() {
        String detalhesPai = super.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String detalhesFilho = "| Perecível: validade até " + validade.format(formatter) + ", armazenado em " + formaArmazenamento;
        return detalhesPai + " " + detalhesFilho;
    }
}
