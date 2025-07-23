package model.produto;

import java.math.BigDecimal;

public abstract class Produto {
    
    public enum TipoProduto {
        FISICO("Físico"),
        DIGITAL("Digital"),
        PERECIVEL("Perecível");

        private final String descricao;

        TipoProduto(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }

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

    public abstract TipoProduto getTipoProduto();

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
    
    public int getEstoque() {
        return estoque;
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
