package model.produto;

import java.math.BigDecimal;

public class ProdutoFisico extends Produto {

    public enum TamanhoProduto {
        PEQUENO, MEDIO, GRANDE
    }

    public enum LocalArmazenamento {
        FABRICA, DEPOSITO, MOSTRUARIO
    }

    private TamanhoProduto tamanho;
    private LocalArmazenamento localArmazenamento;

    public ProdutoFisico(String codigo, String nome, BigDecimal preco, int estoque,
                         TamanhoProduto tamanho, LocalArmazenamento local) {
        super(codigo, nome, preco, estoque);
        this.tamanho = tamanho;
        this.localArmazenamento = local;
    }

    public Produto.TipoProduto getTipoProduto() {
        return Produto.TipoProduto.FISICO;
    }
    
    public String getTipo() {
        return getTipoProduto().getDescricao();
    }

    public String getDetalhes() {
        return toString();
    }

    public String toString() {
        String detalhesPai = super.toString().substring(0, super.toString().length() - 1);
        return detalhesPai + ", Tipo: " + getTipoProduto().getDescricao() + 
               ", Tamanho: " + tamanho + ", Armazenamento: " + localArmazenamento + "]";
    }
}
