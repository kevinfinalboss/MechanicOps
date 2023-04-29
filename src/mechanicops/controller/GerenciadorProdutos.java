package mechanicops.controller;

import mechanicops.model.Produto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorProdutos {

    private static final String ARQUIVO_PRODUTOS = "src/mechanicops/database/produtos.txt";

    public GerenciadorProdutos() {
        File file = new File(ARQUIVO_PRODUTOS);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Erro ao criar arquivo de produtos: " + e.getMessage());
            }
        }
    }

    public void adicionarProduto(Produto produto) {
        try {
            FileWriter fileWriter = new FileWriter(ARQUIVO_PRODUTOS, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(produto.toString());
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
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linha;

            while ((linha = bufferedReader.readLine()) != null) {
                String[] partes = linha.split(",");
                int id = Integer.parseInt(partes[0]);
                String nome = partes[1];
                String descricao = partes[2];
                float valor = Float.parseFloat(partes[3]);
                int quantidade = Integer.parseInt(partes[4]);

                Produto produto = new Produto(id, nome, descricao, valor, quantidade);
                produtos.add(produto);
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.err.println("Erro ao ler produtos: " + e.getMessage());
        }
        return produtos;
    }
}
