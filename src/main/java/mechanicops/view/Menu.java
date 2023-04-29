package mechanicops.view;

import mechanicops.controller.GerenciadorOrcamentos;
import mechanicops.controller.GerenciadorProdutos;
import mechanicops.controller.VerificadorProdutos;
import mechanicops.model.Orcamento;
import mechanicops.model.Produto;

import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private GerenciadorProdutos gerenciadorProdutos;
    private GerenciadorOrcamentos gerenciadorOrcamentos;

    public Menu() {
        scanner = new Scanner(System.in);
        gerenciadorProdutos = new GerenciadorProdutos();
        gerenciadorOrcamentos = new GerenciadorOrcamentos();
    }

    public void exibir() {
        int opcao = -1;
        while (opcao != 8) {
            System.out.println("Menu de Opções:");
            System.out.println("1) Novo orçamento");
            System.out.println("2) Ver orçamentos");
            System.out.println("3) Fechar orçamento");
            System.out.println("4) Vender produto");
            System.out.println("5) Verificar produto");
            System.out.println("6) Adicionar Produto");
            System.out.println("7) Sair");
            System.out.print("Digite a opção desejada: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    criarOrcamento();
                    break;
                case 2:
                    System.out.println("2) Ver orçamentos");
                    break;
                case 3:
                    System.out.println("3) Fechar orçamento");
                    break;
                case 4:
                    System.out.println("4) Vender produto");
                    break;
                case 5:
                    VerificadorProdutos.verificarProdutos(gerenciadorProdutos.listarProdutos());
                    break;
                case 6:
                    adicionarProduto();
                    break;
                case 7:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void criarOrcamento() {
        System.out.print("Nome do cliente: ");
        String nomeCliente = scanner.nextLine();

        System.out.print("Telefone do cliente: ");
        String telefoneCliente = scanner.nextLine();

        LocalDate data = LocalDate.now();

        System.out.print("Carro, Modelo e Ano: ");
        String carroModeloAno = scanner.nextLine();

        System.out.print("Valor do conserto: ");
        float valorConserto = scanner.nextFloat();
        scanner.nextLine();

        int id = gerenciadorOrcamentos.proximoId();
        Orcamento orcamento = new Orcamento(id, nomeCliente, telefoneCliente, data, carroModeloAno, valorConserto);
        gerenciadorOrcamentos.adicionarOrcamento(orcamento);

        System.out.println("Orçamento criado com sucesso!");
    }

    private void adicionarProduto() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Descrição do produto: ");
        String descricao = scanner.nextLine();

        System.out.print("Valor do produto: ");
        float valor = scanner.nextFloat();
        scanner.nextLine();

        System.out.print("Quantidade disponível: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        int id = gerenciadorProdutos.proximoId(gerenciadorProdutos.listarProdutos());
        Produto produto = new Produto(id, nome, descricao, valor, quantidade);
        gerenciadorProdutos.adicionarProduto(produto);

        System.out.println("Produto adicionado com sucesso!");
    }
}
