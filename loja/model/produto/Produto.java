package model.produto;

import java.math.BigDecimal;

public class Produto {
    private String codigo;
    private String nome;
    private BigDecimal precoBase;
    private int estoque;

    public Produto(String codigo, String nome, BigDecimal precoBase, int estoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.precoBase = precoBase;
        this.estoque = estoque;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getEstoque() {
        return estoque;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPrecoBase(BigDecimal preco) {
        this.precoBase = preco;
    }

    public void adicionarEstoque(int quantidade) {
        this.estoque += quantidade;
    }

    public boolean removerEstoque(int quantidade) {
        if (this.estoque >= quantidade) {
            this.estoque -= quantidade;
            return true;
        }
        return false;
    }

    public String toString() {
        return "Produto [Código:" + codigo + ", Nome:" + nome + ", Preço: R$" + precoBase + ", Estoque:" + estoque + "]";
    }
}
