package mechanicops.controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import mechanicops.model.Orcamento;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorOrcamentos {
    private static final String ARQUIVO_ORCAMENTOS = "src/main/java/mechanicops/database/orcamentos.json";
    private Gson gson;

    public GerenciadorOrcamentos() {
        gson = new Gson();
    }

    public int proximoId() {
        List<Orcamento> orcamentos = listarOrcamentos();
        return orcamentos.size() > 0 ? orcamentos.get(orcamentos.size() - 1).getId() + 1 : 1;
    }

    public List<Orcamento> listarOrcamentos() {
        List<Orcamento> orcamentos = new ArrayList<>();
        try {
            Type type = new TypeToken<List<Orcamento>>(){}.getType();
            orcamentos = gson.fromJson(new FileReader(ARQUIVO_ORCAMENTOS), type);
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo de orçamentos não encontrado.");
        } catch (JsonSyntaxException e) {
            System.err.println("Erro na sintaxe do arquivo de orçamentos.");
        }
        return orcamentos != null ? orcamentos : new ArrayList<>();
    }

    public void adicionarOrcamento(Orcamento orcamento) {
        List<Orcamento> orcamentos = listarOrcamentos();
        orcamentos.add(orcamento);
        salvarOrcamentos(orcamentos);
    }

    void salvarOrcamentos(List<Orcamento> orcamentos) {
        try (FileWriter writer = new FileWriter(ARQUIVO_ORCAMENTOS)) {
            gson.toJson(orcamentos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}