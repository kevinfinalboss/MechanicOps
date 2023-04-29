package mechanicops.controller;

import mechanicops.model.Orcamento;

import java.util.List;
import java.util.Optional;

public class AtualizadorOrcamento {
    private GerenciadorOrcamentos gerenciadorOrcamentos;

    public AtualizadorOrcamento() {
        gerenciadorOrcamentos = new GerenciadorOrcamentos();
    }

    public boolean atualizarSituacaoOrcamento(int id, String novaSituacao) {
        List<Orcamento> orcamentos = gerenciadorOrcamentos.listarOrcamentos();
        Optional<Orcamento> orcamentoOptional = orcamentos.stream().filter(orcamento -> orcamento.getId() == id).findFirst();

        if (orcamentoOptional.isPresent()) {
            Orcamento orcamento = orcamentoOptional.get();
            orcamento.setSituacao(novaSituacao);
            gerenciadorOrcamentos.salvarOrcamentos(orcamentos);
            return true;
        }
        return false;
    }
}
