import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;
import model.produto.Produto;

public class Main {
    private ArrayList<Produto> produtos = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Produtos app = new Produtos();
        app.menu();
    }

    public void menu() {
        while (true) {
            System.out.println("\n1 - Cadastrar produto");
            System.out.println("2 - Alterar produto");
            System.out.println("3 - Adicionar estoque");
            System.out.println("4 - Remover estoque");
            System.out.println("x - Listar produtos"); // ta x pq não sei quantas opções vao entrar e eahco que seria interresante as listagens por ultimo
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1": cadastrarProduto(); break;
                case "2": alterarProduto(); break;
                case "3": adicionarEstoqueProduto(); break;
                case "4": removerEstoqueProduto(); break;
                case "x": listarProdutos(); break;
                case "0":
                    System.out.println("Programa Encerrado...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrarProduto() {
        System.out.print("Código do produto: ");
        String codigo = scanner.nextLine();

        if (buscarProdutoPorCodigo(codigo) != null) {
            System.out.println("Código já associado a produto existente.");
            return;
        }

        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();

        try {
            System.out.print("Preço do produto: ");
            BigDecimal preco = new BigDecimal(scanner.nextLine());

            Produto novo = new Produto(codigo, nome, preco, 0);
            produtos.add(novo);
            System.out.println("Produto cadastrado com sucesso.");
        } catch (NumberFormatException e) {
            System.out.println("Erro: Preço inválido. O cadastro foi cancelado.");
        }
    }

    private void alterarProduto() {
        System.out.print("Código do produto que deseja alterar: ");
        String codigo = scanner.nextLine();
        Produto p = buscarProdutoPorCodigo(codigo);

        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) {
            p.setNome(nome);
        }

        System.out.print("Novo preço: ");
        String precoStr = scanner.nextLine();
        if (!precoStr.isEmpty()) {
            try {
                p.setPrecoBase(new BigDecimal(precoStr));
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. O preço não foi alterado.");
            }
        }
        System.out.println("Produto atualizado.");
    }

    private void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        System.out.println("\n--- LISTA DE PRODUTOS ---");
        for (Produto p : produtos) {
            System.out.println(p);
        }
    }

    private void adicionarEstoqueProduto() {
        System.out.print("Código do produto: ");
        String codigo = scanner.nextLine();
        Produto p = buscarProdutoPorCodigo(codigo);

        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        try {
            System.out.print("Quantidade a adicionar: ");
            int qtd = Integer.parseInt(scanner.nextLine());
            
            if (qtd > 0) {
                p.adicionarEstoque(qtd);
                System.out.println("Estoque atualizado.");
            } else {
                System.out.println("A quantidade deve ser um número positivo.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: Quantidade inválida. Digite apenas números.");
        }
    }

    private void removerEstoqueProduto() {
        System.out.print("Código do produto: ");
        String codigo = scanner.nextLine();
        Produto p = buscarProdutoPorCodigo(codigo);

        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }
        
        try {
            System.out.print("Quantidade a remover: ");
            int qtd = Integer.parseInt(scanner.nextLine());
            
            if (qtd <= 0) {
                 System.out.println("A quantidade deve ser um número positivo.");
                 return;
            }

            if (p.removerEstoque(qtd)) {
                System.out.println("Estoque atualizado.");
            } else {
                System.out.println("Quantidade insuficiente em estoque. Estoque atual: " + p.getEstoque());
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: Quantidade inválida. Digite apenas números.");
        }
    }

    private Produto buscarProdutoPorCodigo(String codigo) {
        for (Produto p : produtos) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null;
    }
}
