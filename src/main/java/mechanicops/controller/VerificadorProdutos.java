package mechanicops.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import mechanicops.model.Produto;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class VerificadorProdutos {

    public VerificadorProdutos() {
        Gson gson = new Gson();
        List<Produto> produtos = new ArrayList<>();
        File arquivoProdutos = new File("src/mechanicops/database/produtos.json");
        if (arquivoProdutos.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(arquivoProdutos))) {
                Type type = new TypeToken<List<Produto>>(){}.getType();
                produtos = gson.fromJson(br, type);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void verificarProdutos(List<Produto> produtos) {
        System.out.println("Produtos cadastrados:\n");
        for (Produto produto : produtos) {
            System.out.printf("ID: %d | Nome: %s | Descrição: %s | Valor: R$%.2f | Quantidade: %d\n",
                    produto.getId(), produto.getNome(), produto.getDescricao(),
                    produto.getValor(), produto.getQuantidade());
        }
    }
}
