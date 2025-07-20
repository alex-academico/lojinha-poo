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

    public TamanhoProduto getTamanho() {
        return tamanho;
    }

    public LocalArmazenamento getLocalArmazenamento() {
        return localArmazenamento;
    }
    
    public String toString() {
        String detalhesPai = super.toString().substring(0, super.toString().length() - 1);
        return detalhesPai + ", Tamanho:" + tamanho + ", Armazenamento:" + localArmazenamento + "]";
    }
}
