package model.nota;

import java.math.BigDecimal;
import model.produto.Produto;

public abstract class ItemNota {
    
    private Produto produto;
    private int quantidade;
    public ItemNota(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }
   
    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getSubtotal() {
        return produto.getPrecoBase().multiply(BigDecimal.valueOf(quantidade));
    }
   
    public String toString() {
        return produto.getNome() 
            + " \n-Quantidade: " + quantidade 
            + " \n-Subtotal: R$ " + String.format("%.2f", getSubtotal());
    }
}
