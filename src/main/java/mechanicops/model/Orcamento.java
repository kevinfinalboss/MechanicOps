package mechanicops.model;

public class Orcamento {
    private int id;
    private String nomeCliente;
    private String telefoneCliente;
    private String data;
    private String carroModeloAno;
    private float valorConserto;

    public Orcamento(int id, String nomeCliente, String telefoneCliente, String data, String carroModeloAno, float valorConserto) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.telefoneCliente = telefoneCliente;
        this.data = data;
        this.carroModeloAno = carroModeloAno;
        this.valorConserto = valorConserto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCarroModeloAno() {
        return carroModeloAno;
    }

    public void setCarroModeloAno(String carroModeloAno) {
        this.carroModeloAno = carroModeloAno;
    }

    public float getValorConserto() {
        return valorConserto;
    }

    public void setValorConserto(float valorConserto) {
        this.valorConserto = valorConserto;
    }

    @Override
    public String toString() {
        return "Orçamentos em lista por ID:\n" +
                "ID: " + id + "\n" +
                "Nome do Cliente: " + nomeCliente + "\n" +
                "Telefone do Cliente: " + telefoneCliente + "\n" +
                "Data: " + data + "\n" +
                "Carro, Modelo e Ano: " + carroModeloAno + "\n" +
                "Valor do Conserto: R$ " + String.format("%.2f", valorConserto) + "\n";
    }
}

