package mechanicops.view;

import mechanicops.controller.*;
import mechanicops.model.Orcamento;
import mechanicops.model.Produto;
import mechanicops.model.Venda;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private GerenciadorProdutos gerenciadorProdutos;
    private GerenciadorOrcamentos gerenciadorOrcamentos;
    private AtualizadorOrcamento atualizadorOrcamento;
    private RegistroVendas registroVendas;

    public Menu() {
        scanner = new Scanner(System.in);
        gerenciadorProdutos = new GerenciadorProdutos();
        gerenciadorOrcamentos = new GerenciadorOrcamentos();
        atualizadorOrcamento = new AtualizadorOrcamento();
        registroVendas = new RegistroVendas();
    }

    public void exibir() {
        exibirTelaCarregamento();
        verificarEstoqueBaixo();

        int opcao = -1;
        while (opcao != 4) {
            System.out.println("\nMenu de Opções:");
            System.out.println("1) Orçamentos");
            System.out.println("2) Produtos");
            System.out.println("3) Vendas");
            System.out.println("4) Sair");
            System.out.print("Digite a opção desejada: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuOrcamentos();
                    break;
                case 2:
                    menuProdutos();
                    break;
                case 3:
                    menuVendas();
                    break;
                case 4:
                    encerrarPrograma();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void menuOrcamentos() {
        int opcao = -1;
        while (opcao != 4) {
            System.out.println("\nMenu Orçamentos:");
            System.out.println("1) Novo orçamento");
            System.out.println("2) Ver orçamentos");
            System.out.println("3) Fechar orçamentos");
            System.out.println("4) Voltar");
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
                    fecharOrcamento();
                    break;
                case 4:
                    System.out.println("Voltando ao menu principal");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void menuProdutos() {
        int opcao = -1;
        while (opcao != 4) {
            System.out.println("\nMenu Produtos:");
            System.out.println("1) Adicionar produto");
            System.out.println("2) Ver produtos");
            System.out.println("3) Vender produto");
            System.out.println("4) Voltar");
            System.out.print("Digite a opção desejada: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarProduto();
                    break;
                case 2:
                    VerificadorProdutos.verificarProdutos(gerenciadorProdutos.listarProdutos());
                    break;
                case 3:
                    venderProduto();
                    break;
                case 4:
                    System.out.println("Voltando ao menu principal");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void menuVendas() {
        int opcao = -1;
        while (opcao != 2) {
            System.out.println("\nMenu Vendas:");
            System.out.println("1) Ver logs de vendas");
            System.out.println("2) Voltar");
            System.out.print("Digite a opção desejada: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    listarVendas();
                    break;
                case 2:
                    System.out.println("Voltando ao menu principal");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void exibirTelaCarregamento() {
        System.out.println("Carregando MechanicOps...");
        aguardar(500);

        System.out.println("Carregando componentes...");
        aguardar(500);

        System.out.println("Checando conectividade com a internet...");
        boolean conectado = testarConexaoInternet();
        if (conectado) {
            System.out.println("Conectado à internet!");
        } else {
            System.out.println("Sem conexão com a internet. A aplicação continuará offline.");
            System.out.println("Atenção: Algumas funcionalidades podem não funcionar corretamente sem conexão com a internet.");
        }
        aguardar(500);

        System.out.println("\nBem-vindo ao MechanicOps!");
        aguardar(500);
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

    private void verificarEstoqueBaixo() {
        List<Produto> produtos = gerenciadorProdutos.listarProdutos();
        for (Produto produto : produtos) {
            if (produto.getQuantidade() < 5) {
                System.out.println("\nAtenção! Produto com estoque baixo:");
                System.out.println(produto.toString());
            }
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

    private void fecharOrcamento() {
        System.out.print("Digite o ID do orçamento que deseja fechar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite a nova situação do orçamento: ");
        String novaSituacao = scanner.nextLine();

        if (atualizadorOrcamento.atualizarSituacaoOrcamento(id, novaSituacao)) {
            System.out.println("Orçamento atualizado com sucesso!");
        } else {
            System.out.println("Orçamento não encontrado.");
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

    private void venderProduto() {
        System.out.print("Digite o ID do produto que foi vendido: ");
        int idProduto = scanner.nextInt();
        scanner.nextLine();

        Produto produto = gerenciadorProdutos.buscarProdutoPorId(idProduto);
        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Nome do cliente: ");
        String nomeCliente = scanner.nextLine();

        System.out.print("Forma de pagamento: ");
        String formaPagamento = scanner.nextLine();

        System.out.print("Nome do vendedor: ");
        String nomeVendedor = scanner.nextLine();

        float valorPago = produto.getValor();
        if (gerenciadorProdutos.venderProduto(idProduto)) {
            LocalDateTime dataHoraVenda = LocalDateTime.now();
            Produto produtoVendido = new Produto(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getValor(), 1);
            int idVenda = registroVendas.proximoId();
            Venda venda = new Venda(idVenda, nomeCliente, nomeVendedor, dataHoraVenda, produtoVendido, formaPagamento);
            registroVendas.adicionarVenda(venda);
            System.out.println("Venda realizada com sucesso!");
        } else {
            System.out.println("Não foi possível realizar a venda.");
        }
    }
    private void listarVendas() {
        List<Venda> vendas = RegistroVendas.carregarVendas();
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda foi registrada.");
        } else {
            System.out.println("\n========== Vendas Registradas ==========");
            for (Venda venda : vendas) {
                System.out.println(venda);
            }
        }
    }

    private void encerrarPrograma() {
        System.out.println("Salvando orçamentos...");
        aguardar(500);

        System.out.println("Salvando produtos...");
        aguardar(500);

        System.out.println("Salvando logs...");
        aguardar(500);

        System.out.println("Encerrando conexão com o banco de dados...");
        aguardar(500);

        System.out.println("Encerrando programa...");
        aguardar(500);
    }
}
