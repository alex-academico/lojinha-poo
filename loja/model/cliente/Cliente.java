package model.cliente;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
        private String id;
        private String nome;
        private String endereco;
        private String telefone;

        public Cliente(String id, String nome, String endereco, String telefone) {
            this.id = id;
            this.nome = nome;
            this.endereco = endereco;
            this.telefone = telefone;
        }
        public Main() {
        this.clientes = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Main sistema = new Main();
        sistema.menu();
    }

    public void menu() {
        while (true) {
            System.out.println("\nMenu");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Alterar cliente");
            System.out.println("3 - Listar clientes");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    cadastrarCliente();
                    break;
                case "2":
                    alterarCliente();
                    break;
                case "3":
                    listarClientes();
                    break;
                case "0":
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }


        public String getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public String getEndereco() {
            return endereco;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public String toString() {
            return "Cliente [ID: " + id + ", Nome: " + nome + ", Endereço: " + endereco + ", Telefone: " + telefone + "]";
        }
    }

    private void cadastrarCliente() {
        System.out.print("ID do cliente: ");
        String id = scanner.nextLine();

        for (Cliente c : clientes) {
            if (c.getId().equals(id)) {
                System.out.println("ID já cadastrado.");
                return;
            }
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        Cliente novo = new Cliente(id, nome, endereco, telefone);
        clientes.add(novo);
        System.out.println("Cliente cadastrado com sucesso.");
    }

    private void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    private void alterarCliente() {
        System.out.print("ID do cliente que deseja alterar: ");
        String id = scanner.nextLine();

        Cliente encontrado = null;
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                encontrado = cliente;
                break;
            }
        }

        if (encontrado == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Novo nome (deixe vazio para manter): ");
        String novoNome = scanner.nextLine();
        if (!novoNome.isEmpty()) {
            encontrado.setNome(novoNome);
        }

        System.out.print("Novo endereço (deixe vazio para manter): ");
        String novoEndereco = scanner.nextLine();
        if (!novoEndereco.isEmpty()) {
            encontrado.setEndereco(novoEndereco);
        }

        System.out.print("Novo telefone (deixe vazio para manter): ");
        String novoTelefone = scanner.nextLine();
        if (!novoTelefone.isEmpty()) {
            encontrado.setTelefone(novoTelefone);
        }

        System.out.println("Cliente atualizado.");
    }
}

