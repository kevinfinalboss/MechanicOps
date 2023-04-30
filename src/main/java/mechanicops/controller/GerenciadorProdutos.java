package mechanicops.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import mechanicops.model.Produto;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorProdutos {
    private static final String ARQUIVO_PRODUTOS = "src/main/java/mechanicops/database/produtos.json";

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();

        try {
            FileReader reader = new FileReader(ARQUIVO_PRODUTOS);
            BufferedReader bufferedReader = new BufferedReader(reader);

            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Produto>>(){}.getType();
            produtos = gson.fromJson(bufferedReader, type);

            if (produtos == null) {
                produtos = new ArrayList<>();
            }

            bufferedReader.close();
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de produtos não encontrado. Um novo arquivo será criado.");
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de produtos.");
        }

        return produtos;
    }

    public int proximoId(List<Produto> produtos) {
        int maxId = 0;
        for (Produto produto : produtos) {
            if (produto.getId() > maxId) {
                maxId = produto.getId();
            }
        }
        return maxId + 1;
    }

    public void adicionarProduto(Produto produto) {
        List<Produto> produtos = listarProdutos();
        produtos.add(produto);
        salvarProdutos(produtos);
    }

    public Produto buscarProdutoPorId(int id) {
        List<Produto> produtos = listarProdutos();
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    public boolean venderProduto(int id) {
        List<Produto> produtos = listarProdutos();
        boolean produtoVendido = false;

        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                if (produto.getQuantidade() > 0) {
                    produto.setQuantidade(produto.getQuantidade() - 1);
                    salvarProdutos(produtos);
                    produtoVendido = true;
                } else {
                    System.out.println("Produto esgotado.");
                }
                break;
            }
        }

        return produtoVendido;
    }

    private void salvarProdutos(List<Produto> produtos) {
        try {
            FileWriter writer = new FileWriter(ARQUIVO_PRODUTOS);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            Gson gson = new Gson();
            gson.toJson(produtos, bufferedWriter);

            bufferedWriter.close();
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo de produtos.");
        }
    }
}
