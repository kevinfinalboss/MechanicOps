package mechanicops.model;

public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private float valor;
    private int quantidade;

    public Produto(int id, String nome, String descricao, float valor, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id + "," +
                "\"nome\": \"" + nome + "\"," +
                "\"descricao\": \"" + descricao + "\"," +
                "\"valor\": " + valor + "," +
                "\"quantidade\": " + quantidade +
                "}";
    }
}
