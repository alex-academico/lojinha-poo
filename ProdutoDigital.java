import java.time.LocalDate;

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
        this.dataCompra = alugado ? dataCompra : null;
        this.dataExpiracao = alugado ? dataCompra.plusMonths(1) : null;
    }

    public String getDetalhes() {
        String info = "| Digital: " + formato + ", " + tamanhoMB + "MB, ";
        if (alugado) {
            info += "Alugado, válido de " + dataCompra + " até " + dataExpiracao;
        } else {
            info += "Comprado";
        }
        return info;
    }
}
