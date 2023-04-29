package mechanicops.controller;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import mechanicops.model.Produto;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;


public class GerenciadorProdutos {

    private static final String ARQUIVO_PRODUTOS = "src/main/java/mechanicops/database/produtos.json";

    public GerenciadorProdutos() {
    }

    public void adicionarProduto(Produto produto) {
        try {
            Gson gson = new Gson();
            String produtoJson = gson.toJson(produto);

            FileWriter fileWriter = new FileWriter(ARQUIVO_PRODUTOS, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(produtoJson);
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Erro ao adicionar produto: " + e.getMessage());
        }
    }

    public int proximoId() {
        int maxId = 0;
        List<Produto> produtos = listarProdutos();
        for (Produto produto : produtos) {
            if (produto.getId() > maxId) {
                maxId = produto.getId();
            }
        }
        return maxId + 1;
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(ARQUIVO_PRODUTOS);
            JsonReader jsonReader = new JsonReader(fileReader);
            Gson gson = new Gson();

            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                Produto produto = gson.fromJson(jsonReader, Produto.class);
                produtos.add(produto);
            }
            jsonReader.endArray();

            jsonReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.err.println("Erro ao ler produtos: " + e.getMessage());
        }
        return produtos;
    }

    public String listarProdutosFormatado() {
        StringBuilder sb = new StringBuilder();
        List<Produto> produtos = listarProdutos();
        for (Produto produto : produtos) {
            sb.append(produto.toString()).append("\n");
        }
        return sb.toString();
    }
}
