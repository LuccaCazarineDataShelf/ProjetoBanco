import java.util.Scanner;

public class Main{
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        Banco banco = new Banco();

        System.out.println("Bem Vindo ao Banco!");

        while(true){
            System.out.println("\nEscolha uma opção");
            System.out.println("1 - Abrir conta");
            System.out.println("2 - sair");
            System.out.println("Opcão: ");

            int opcao = scan.nextInt();
            scan.nextInt();

            switch (opcao){
                case 1:
                    banco.abrirConta();
                    break;
                case 2:
                    scan.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("opcão inválida");
                    break;
            }
        }
    }
}