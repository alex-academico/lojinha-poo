package model.produto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProdutoDigital extends Produto {

    private String formato;
    private double tamanhoMB;
    private boolean alugado;
    private LocalDate dataCompra;
    private LocalDate dataExpiracao;

    public ProdutoDigital(String codigo, String nome, BigDecimal preco, int estoque,
                          String formato, double tamanhoMB, boolean alugado, LocalDate dataCompra) {
        
        super(codigo, nome, preco, estoque);
        this.formato = formato;
        this.tamanhoMB = tamanhoMB;
        this.alugado = alugado;
        this.dataCompra = dataCompra; 
        
        if (this.alugado) {
            this.dataExpiracao = LocalDate.now().plusMonths(1);
        } else {
            this.dataExpiracao = null;
        }
    }

    public Produto.TipoProduto getTipoProduto() {
        return Produto.TipoProduto.DIGITAL;
    }
    
    public String toString() {
        String detalhesPai = super.toString().substring(0, super.toString().length() - 1);
        
        StringBuilder detalhes = new StringBuilder();
        detalhes.append(detalhesPai)
                .append(", Tipo: ").append(getTipoProduto().getDescricao())
                .append(", Formato: ").append(formato)
                .append(", Tamanho: ").append(tamanhoMB).append("MB");

        if (alugado) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            detalhes.append(", Status: Alugado")
                    .append(", Válido até: ").append(dataExpiracao.format(formatter));
        } else {
            detalhes.append(", Status: Comprado");
        }

        detalhes.append("]");
        return detalhes.toString();
    }
}
