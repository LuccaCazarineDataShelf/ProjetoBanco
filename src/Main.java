import java.util.Scanner;

public class Main{
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        Banco banco = new Banco();

        System.out.println("Bem Vindo ao Banco!");

        while(true){
            System.out.println("\nEscolha uma opção");
            System.out.println("1 - Abrir conta");
            System.out.println("2 - Fechar Conta");
            System.out.println("3 - Acessar conta");
            System.out.println("4 - Sacar");
            System.out.println("5 - Depositar");
            System.out.println("0 - sair");
            System.out.println("Opcão: ");

            int opcao = scan.nextInt();
            scan.nextInt();

            switch (opcao){
                case 1:
                    banco.abrirConta();
                    break;
                case 2:
                    banco.fecharConta();
                    break;
                case 3:
                    banco.acessarConta();
                    break;
                case 4:
                   banco.sacar();
                   break;
                case 5:
                    banco.depositar();
                    break;
                case 0:
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