package mechanicops.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Venda {
    private int id;
    private String nomeVendedor;
    private String nomeCliente;
    private String dataHoraVenda; // Altere o tipo para String
    private Produto produto;
    private String formaPagamento;

    public Venda(int id, String nomeVendedor, String nomeCliente, LocalDateTime dataHoraVenda, Produto produto, String formaPagamento) {
        this.id = id;
        this.nomeVendedor = nomeVendedor;
        this.nomeCliente = nomeCliente;
        this.dataHoraVenda = dataHoraVenda.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME); // Converta LocalDateTime para String
        this.produto = produto;
        this.formaPagamento = formaPagamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public LocalDateTime getDataHoraVenda() {
        return LocalDateTime.parse(dataHoraVenda, DateTimeFormatter.ISO_LOCAL_DATE_TIME); // Converta String para LocalDateTime
    }

    public void setDataHoraVenda(LocalDateTime dataHoraVenda) {
        this.dataHoraVenda = dataHoraVenda.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME); // Converta LocalDateTime para String
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        return id == venda.id &&
                Objects.equals(nomeVendedor, venda.nomeVendedor) &&
                Objects.equals(nomeCliente, venda.nomeCliente) &&
                Objects.equals(dataHoraVenda, venda.dataHoraVenda) &&
                Objects.equals(produto, venda.produto) &&
                Objects.equals(formaPagamento, venda.formaPagamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeVendedor, nomeCliente, dataHoraVenda, produto, formaPagamento);
    }

    @Override
    public String toString() {
        return "Venda {\n" +
                "\tID: " + id + ",\n" +
                "\tNome Vendedor: '" + nomeVendedor + "',\n" +
                "\tNome Cliente: '" + nomeCliente + "',\n" +
                "\tData e Hora da Venda: " + dataHoraVenda + ",\n" +
                "\tProduto: \n" + produto.toString().replaceAll("(?m)^", "\t\t") + ",\n" +
                "\tForma de Pagamento: '" + formaPagamento + "'\n" +
                "}";
    }
}
