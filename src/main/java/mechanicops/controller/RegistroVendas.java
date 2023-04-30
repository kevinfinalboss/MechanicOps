package mechanicops.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import mechanicops.model.Venda;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RegistroVendas {


    private static final String PASTA_LOGS = "src/main/java/mechanicops/logs";
    private static final String ARQUIVO_VENDAS = PASTA_LOGS + "/vendas.json";
    private List<Venda> vendas;

    public RegistroVendas() {
        this.vendas = carregarVendas();
    }

    public int proximoId() {
        int maxId = 0;
        for (Venda venda : vendas) {
            if (venda.getId() > maxId) {
                maxId = venda.getId();
            }
        }
        return maxId + 1;
    }
    public List<Venda> getVendas() {
        return vendas;
    }

    public void adicionarVenda(Venda venda) {
        vendas.add(venda);
        salvarVendas();
    }

    private static void criarPastaLogs() {
        File pastaLogs = new File(PASTA_LOGS);
        if (!pastaLogs.exists()) {
            pastaLogs.mkdir();
        }
    }

    public static List<Venda> carregarVendas() {
        criarPastaLogs();
        File arquivoVendas = new File(ARQUIVO_VENDAS);
        if (!arquivoVendas.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(ARQUIVO_VENDAS)) {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
            Type type = new TypeToken<List<Venda>>() {
            }.getType();
            List<Venda> vendas = gson.fromJson(reader, type);
            if (vendas == null) {
                vendas = new ArrayList<>();
            }
            return vendas;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void salvarVendas() {
        criarPastaLogs();

        try (Writer writer = new FileWriter(ARQUIVO_VENDAS)) {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").setPrettyPrinting().create();
            gson.toJson(vendas, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
