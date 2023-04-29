package mechanicops.view;

import mechanicops.controller.GerenciadorProdutos;
import mechanicops.model.Produto;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private GerenciadorProdutos gerenciadorProdutos;

    public Menu() {
        scanner = new Scanner(System.in);
        gerenciadorProdutos = new GerenciadorProdutos();
    }

    public void exibir() {
        int opcao = -1;
        while (opcao != 7) {
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
                    System.out.println("1) Novo orçamento");
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
                    System.out.println("5) Verificar produto");
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

        int id = gerenciadorProdutos.proximoId();
        Produto produto = new Produto(id, nome, descricao, valor, quantidade);
        gerenciadorProdutos.adicionarProduto(produto);

        System.out.println("Produto adicionado com sucesso!");
    }
}
