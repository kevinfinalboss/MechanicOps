package mechanicops.view;

import java.util.Scanner;

public class Menu {

    public void exibir() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("===== MechanicOps =====");
            System.out.println("1) Novo orçamento");
            System.out.println("2) Ver orçamentos");
            System.out.println("3) Fechar orçamento");
            System.out.println("4) Vender produto");
            System.out.println("5) Verificar produto");
            System.out.println("6) Adicionar produto");
            System.out.println("7) Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

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
                    System.out.println("6) Adicionar produto");
                    break;
                case 7:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 7);
        scanner.close();
    }
}
