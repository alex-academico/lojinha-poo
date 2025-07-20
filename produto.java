import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Scanner;

public class Produtos {
              private String codigo;
              private String nome;
              private BigDecimal precoBase;
              private int estoque;
              
              public Produto(String codigo, String nome, BigDecimal precoBase, Int estoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.precoBase = precoBase;
        this.estoque = 0;
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

    private void cadastrarProduto() {
        System.out.print("Código do produto: ");
        String codigo = scanner.nextLine();
      
        for (Produto valorCodigo : produtos) {
            if (valorCodigo.getCodigo().equals(codigo)) {
                System.out.println("Código já associado a produto existente.");
                return;
            }
        }

        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Preço do produto: ");
        BigDecimal preco = new BigDecimal(scanner.nextLine());

        Produto novoProduto = new Produto(codigo, nome, preco);
        produtos.add(novoProduto);
        System.out.println("Produto cadastrado com sucesso.");
    }

    private void alterarProduto() {
        System.out.print("Código do produto que deseja alterar: ");
        String codigo = scanner.nextLine();

        Produto encontrado = null;
        for (Produto valorCodigo : produtos) {
            if (valorCodigo.getCodigo().equals(codigo)) {
                encontrado = valorCodigo;
                break;
            }
        }

        if (encontrado == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Novo nome: ");
        String novoNome = scanner.nextLine();
        if (!novoNome.isEmpty()) {
            encontrado.setNome(novoNome);
        }

        System.out.print("Novo preço: ");
        String novoPreco = scanner.nextLine();
        if (!novoPreco.isEmpty()) {
            encontrado.setPreco(new BigDecimal(novoPreco));
        }

        System.out.println("Produto atualizado.");
    }

    private void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        for (Produto valorCodigo : produtos) {
            System.out.println(valorCodigo);
        }
    }
}
