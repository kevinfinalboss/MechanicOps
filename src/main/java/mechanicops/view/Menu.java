package mechanicops.view;

import mechanicops.controller.GerenciadorOrcamentos;
import mechanicops.controller.GerenciadorProdutos;
import mechanicops.controller.VerificadorProdutos;
import mechanicops.model.Orcamento;
import mechanicops.model.Produto;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
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
        exibirTelaCarregamento();

        int opcao = -1;
        while (opcao != 7) {
            System.out.println("\nMenu de Opções:");
            System.out.println("1) Novo orçamento");
            System.out.println("2) Ver orçamentos");
            System.out.println("3) Fechar orçamento");
            System.out.println("4) Vender produto");
            System.out.println("5) Ver produtos");
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
                    listarOrcamentos();
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

    private void exibirTelaCarregamento() {
        System.out.println("Carregando MechanicOps...");
        aguardar(1000);

        System.out.println("Carregando componentes...");
        aguardar(1000);

        System.out.println("Checando conectividade com a internet...");
        boolean conectado = testarConexaoInternet();
        if (conectado) {
            System.out.println("Conectado à internet!");
        } else {
            System.out.println("Sem conexão com a internet. A aplicação continuará offline.");
            System.out.println("Atenção: Algumas funcionalidades podem não funcionar corretamente sem conexão com a internet.");
        }
        aguardar(1000);

        System.out.println("\nBem-vindo ao MechanicOps!");
        aguardar(1000);
    }

    private boolean testarConexaoInternet() {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("www.google.com", 80), 3000);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private void aguardar(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void criarOrcamento() {
        System.out.print("Nome do cliente: ");
        String nomeCliente = scanner.nextLine();

        System.out.print("Telefone do cliente: ");
        String telefoneCliente = scanner.nextLine();

        LocalDate localDate = LocalDate.now();
        String data = localDate.toString();

        System.out.print("Carro, Modelo e Ano: ");
        String carroModeloAno = scanner.nextLine();

        System.out.print("Valor do conserto: ");
        float valorConserto = scanner.nextFloat();
        scanner.nextLine();

        System.out.print("Situação (aberto, andamento, fechado): ");
        String situacao = scanner.nextLine();

        int id = gerenciadorOrcamentos.proximoId();
        Orcamento orcamento = new Orcamento(id, nomeCliente, telefoneCliente, data, carroModeloAno, valorConserto, situacao);
        gerenciadorOrcamentos.adicionarOrcamento(orcamento);

        System.out.println("Orçamento criado com sucesso!");
    }

    private void listarOrcamentos() {
        System.out.println("\n==========Orçamentos================");
        for (Orcamento orcamento : gerenciadorOrcamentos.listarOrcamentos()) {
            System.out.println(orcamento);
        }
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
