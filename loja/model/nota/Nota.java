package model.nota;

import model.cliente.Cliente;
import java.time.LocalDateTime;
import java.math.BigDecimal;

public class Nota {
    private static int contadorNotas = 1;

    private final int numNota;
    private final Cliente cliente;
    private final LocalDateTime dataHora;
    private final ItemNota[] itens;
    private int quantidadeItens;

    private static final int limiteItens = 100;

    public Nota(Cliente cliente) {
        this.numNota = contadorNotas++;
        this.cliente = cliente;
        this.dataHora = LocalDateTime.now();
        this.itens = new ItemNota[limiteItens];
        this.quantidadeItens = 0;
    }

    public void adicionarItem(ItemNota item) {
        try {
            if (item == null) {
                throw new IllegalArgumentException("Invalido :( ");
            }
            if (quantidadeItens >= limiteItens) {
                throw new IllegalStateException("Limite de itens atingido :(");
            }
            itens[quantidadeItens++] = item;
        } catch (Exception e) {
            System.out.println(" =( Erro ao adicionar item: " + e.getMessage());
        }
    }

    public BigDecimal calcularTotal() {
        BigDecimal total = BigDecimal.ZERO;
        try {
            for (int i = 0; i < quantidadeItens; i++) {
                total = total.add(itens[i].getSubtotal());
            }
        } catch (Exception e) {
            System.out.println(" =( Erro ao calcular total " + e.getMessage());
        }
        return total;
    }

    public void exibirResumo() {
        try {
            System.out.println("\n  *  NOTA FISCAL  *  ");
            System.out.println("Nota nº: " + numNota);
            System.out.println("Cliente: " + cliente.getNome());
            System.out.println("Data: " + dataHora);

            if (quantidadeItens == 0) {
                System.out.println("Nenhum item adicionado");
            } else {
                for (int i = 0; i < quantidadeItens; i++) {
                    System.out.println(itens[i]);
                }
                System.out.printf("\n TOTAL: R$ %.2f\n", calcularTotal());
            }

        } catch (Exception e) {
            System.out.println(" =( Erro na exibição da nota:" + e.getMessage());
        }
    }

    public int getNumeroNota() {
        return numNota;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ItemNota[] getItens() {
        ItemNota[] copia = new ItemNota[quantidadeItens];
        for (int i = 0; i < quantidadeItens; i++) {
            copia[i] = itens[i];
        }
        return copia;
    }

    public int getQuantidadeItens() {
        return quantidadeItens;
    }
}
