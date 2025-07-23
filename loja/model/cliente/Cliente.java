package model.cliente;

// uso de classe abstrata para generalizar o conceito que pode ser usado nos subtipos
public abstract class Cliente implements Identificavel {
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
