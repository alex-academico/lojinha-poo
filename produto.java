import java.math.BigDecimal;

public class Produtos {
              private String codigo;
              private String nome;
              private BigDecimal precoBase;
              private int estoque;
              
              public Produto(String codigo, String nome, BigDecimal precoBase, Int estoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.precoBase = precoBase;
        this.estoque = estoque;
    }

    public String getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }
    public BigDecimal getPrecoBase() {
        return precoBase;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setPrecoBase(BigDecimal precoBase) {
        this.precoBase = precoBase;
    }

    public String toString() {
        return "Produto [Código: " + codigo + ", Nome: " + nome + ", Preço: R$" + String.format("%.2f", precoBase) + "]";
    }
}
