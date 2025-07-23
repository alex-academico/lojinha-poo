import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.produto.*;
import model.produto.ProdutoFisico.LocalArmazenamento;
import model.produto.ProdutoFisico.TamanhoProduto;
import model.produto.ProdutoPerecivel.FormaArmazenamentoPerecivel;


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
            try {
                capacidade = setupScanner.nextInt();
                if (capacidade > 0) break;
                else System.out.print("Erro: O número deve ser positivo. Tente novamente: ");
            } catch (InputMismatchException e) {
                System.out.print("Erro: Entrada inválida. Digite um número inteiro. Tente novamente: ");
                setupScanner.next();
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

        System.out.println("\n--- Cadastro de Novo Produto ---");
        System.out.println("Qual o tipo de produto?");
        System.out.println("1 - Físico (ex: móveis)");
        System.out.println("2 - Perecível (ex: alimentos)");
        System.out.println("3 - Digital (ex: e-book, filme/series)");
        System.out.print("Escolha o tipo: ");
        String tipo = scanner.nextLine();

        System.out.print("Código do produto: ");
        String codigo = scanner.nextLine();
        if (buscarProdutoPorCodigo(codigo) != null) {
            System.out.println("Erro: Código já associado a um produto existente.");
            return;
        }
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Preço do produto: ");
        BigDecimal preco;
        try {
            preco = new BigDecimal(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Erro: Preço inválido. O cadastro foi cancelado.");
            return;
        }

        Produto novoProduto = null;

        switch (tipo) {
            case "1": // Produto Físico
                System.out.print("Tamanho (PEQUENO, MEDIO, GRANDE): ");
                TamanhoProduto tamanho = TamanhoProduto.valueOf(scanner.nextLine().toUpperCase());
                System.out.print("Local de Armazenamento (FABRICA, DEPOSITO, MOSTRUARIO): ");
                LocalArmazenamento local = LocalArmazenamento.valueOf(scanner.nextLine().toUpperCase());
                novoProduto = new ProdutoFisico(codigo, nome, preco, 0, tamanho, local);
                break;

            case "2": // Produto Perecível
                System.out.print("Tamanho (PEQUENO, MEDIO, GRANDE): ");
                ProdutoPerecivel.TamanhoProduto tamanhoP = ProdutoPerecivel.TamanhoProduto.valueOf(scanner.nextLine().toUpperCase());
                System.out.print("Local de Armazenamento (DEPOSITO, MOSTRUARIO, REFRIGERADOR): ");
                ProdutoPerecivel.LocalArmazenamento localP = ProdutoPerecivel.LocalArmazenamento.valueOf(scanner.nextLine().toUpperCase());
                System.out.print("Data de Validade (dd/MM/yyyy): ");
                LocalDate validade;
                 try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    validade = LocalDate.parse(scanner.nextLine(), formatter);
                } catch (DateTimeParseException ex) {
                    System.out.println("Formato de data inválido! Usando a data de hoje como padrão.");
                    validade = LocalDate.now();
                }
                System.out.print("Forma de Armazenamento Específica (PRATELEIRA, REFRIGERADOR, CONGELADOR): ");
                FormaArmazenamentoPerecivel forma = FormaArmazenamentoPerecivel.valueOf(scanner.nextLine().toUpperCase());
                novoProduto = new ProdutoPerecivel(codigo, nome, preco, 0, tamanhoP, localP, validade, forma);
                break;

            case "3": // Produto Digital
                 System.out.print("Formato (PDF, MP4, etc): ");
                 String formato = scanner.nextLine();
                 System.out.print("Tamanho em MB: ");
                 double tamanhoMB = Double.parseDouble(scanner.nextLine());
                 novoProduto = new ProdutoDigital(codigo, nome, preco, 0, formato, tamanhoMB, false, LocalDate.now());
                break;
                
            default:
                System.out.println("Tipo de produto inválido. Cadastro cancelado.");
                return;
        }

        this.produtos[totalDeProdutos] = novoProduto;
        this.totalDeProdutos++;
        System.out.println("Produto cadastrado com sucesso.");
    }

    private void listarProdutos() {
        if (totalDeProdutos == 0) {
            System.out.println("\nNenhum produto cadastrado.");
            return;
        }
        System.out.println("\n--- LISTA DE PRODUTOS ---");
        for (int i = 0; i < totalDeProdutos; i++) {
            System.out.println(produtos[i].getDetalhes());
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

    private void adicionarEstoqueProduto() {
        System.out.print("Código do produto: ");
        String codigo = scanner.nextLine();
        Produto p = buscarProdutoPorCodigo(codigo);

        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Quantidade a adicionar: ");
        try {
            int qtd = Integer.parseInt(scanner.nextLine());
            if (qtd > 0) {
                p.adicionarEstoque(qtd);
                System.out.println("Estoque atualizado.");
            } else {
                System.out.println("A quantidade deve ser um número positivo.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: Quantidade inválida.");
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
        
        System.out.print("Quantidade a remover: ");
        try {
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
            System.out.println("Erro: Quantidade inválida.");
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
