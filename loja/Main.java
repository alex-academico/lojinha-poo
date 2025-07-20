import java.math.BigDecimal;
import java.util.Scanner;
import model.produto.Produto;

public class Main {
    private Produto[] produtos;
    private int totalDeProdutos;
    private Scanner scanner = new Scanner(System.in);

    public Main(int capacidade) {
        this.produtos = new Produto[capacidade];
        this.totalDeProdutos = 0;
    }

    public static void main(String[] args) {
        Scanner setupScanner = new Scanner(System.in);
        int capacidade = 0;
        
        System.out.print("Quantos produtos deseja cadastrar: ");

        while (true) {
            
            if (setupScanner.hasNextInt()) {
                capacidade = setupScanner.nextInt();
                
                if (capacidade > 0) {
                    break; 
                } else {
                    System.out.println("Erro: O número deve ser positivo.");
                    System.out.print("Tente novamente: ");
                }
            } else {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número inteiro.");
                setupScanner.next(); 
                
                System.out.print("Tente novamente: ");
            }
        }

        Main app = new Main(capacidade);
        System.out.println("\nSistema iniciado com capacidade para " + capacidade + " produtos.");
        app.menu();
        
    }

    public void menu() {
        while (true) {
            System.out.println("\n--- Menu do Sistema das Musas ---");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Alterar produto");
            System.out.println("3 - Adicionar estoque");
            System.out.println("4 - Remover estoque");
            System.out.println("x - Listar produtos");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine();
            
            if (opcao.isEmpty()) {
                opcao = scanner.nextLine();
            }

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
        if (totalDeProdutos >= produtos.length) {
            System.out.println("\nErro: Limite máximo de " + produtos.length + " produtos atingido.");
            return;
        }

        System.out.print("Código do produto: ");
        String codigo = scanner.nextLine();

        if (buscarProdutoPorCodigo(codigo) != null) {
            System.out.println("Erro: Código já associado a um produto existente.");
            return;
        }

        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();

        try {
            System.out.print("Preço do produto: ");
            BigDecimal preco = new BigDecimal(scanner.nextLine());
            Produto novo = new Produto(codigo, nome, preco, 0);
            this.produtos[totalDeProdutos] = novo;
            this.totalDeProdutos++;
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
        if (totalDeProdutos == 0) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        System.out.println("\n--- LISTA DE PRODUTOS ---");
        for (int i = 0; i < totalDeProdutos; i++) {
            System.out.println(produtos[i]);
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
        for (int i = 0; i < totalDeProdutos; i++) {
            if (produtos[i] != null && produtos[i].getCodigo().equals(codigo)) {
                return produtos[i];
            }
        }
        return null;
    }
}
