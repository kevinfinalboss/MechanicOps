package mechanicops.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import mechanicops.model.Produto;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorProdutos {
    private List<Produto> produtos;
    private Gson gson;
    private File arquivoProdutos;

    public GerenciadorProdutos() {
        gson = new Gson();
        produtos = new ArrayList<>();
        arquivoProdutos = new File("src/main/java/mechanicops/database/produtos.json");
        if (arquivoProdutos.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(arquivoProdutos))) {
                Type type = new TypeToken<List<Produto>>(){}.getType();
                produtos = gson.fromJson(br, type);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                // Cria o diretório se não existir
                arquivoProdutos.getParentFile().mkdirs();
                // Cria o arquivo
                arquivoProdutos.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        salvarProdutos();
    }

    public void removerProduto(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                produtos.remove(produto);
                salvarProdutos();
                break;
            }
        }
    }

    public Produto buscarProduto(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    public List<Produto> listarProdutos() {
        List<Produto> copiaProdutos = new ArrayList<>(produtos);
        return copiaProdutos;
    }

    public int proximoId(List<Produto> produtos) {
        int ultimoId = 0;
        for (Produto produto : this.produtos) {
            if (produto.getId() > ultimoId) {
                ultimoId = produto.getId();
            }
        }
        return ultimoId + 1;
    }

    private void salvarProdutos() {
        try (FileWriter fw = new FileWriter(arquivoProdutos);
             BufferedWriter bw = new BufferedWriter(fw)) {
            gson.toJson(produtos, bw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
