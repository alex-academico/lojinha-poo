package model.cliente;
public class PessoaJuridica extends Cliente {
    private String cnpj;

    public PessoaJuridica(String id, String nome, String endereco, String telefone, String cnpj) {
        super(id, nome, endereco, telefone);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String toString() {
        return super.toString() + ", CNPJ: " + cnpj;
    }
}
