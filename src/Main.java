import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scannerOpcaoMenu = new Scanner(System.in);
        Banco banco = new Banco();
        int opcao1;
        int numConta;
        do{
            System.out.println("========= Menu =========");
            System.out.println("1. Abrir nova conta");
            System.out.println("2. Fechar conta");
            System.out.println("3. Acessar conta");
            System.out.println("0. Sair");
            System.out.println("Digite a opcão desejada: ");
            opcao1 = scannerOpcaoMenu.nextInt();

            switch(opcao1){
                case 1:
                    banco.abrirConta();
                    break;
                case 2:
                    banco.fecharConta();
                    break;
                case 3:
                    numConta = scannerOpcaoMenu.nextInt();
                    banco.acessarConta();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opcão inválida. tente novamente.");
            }
        } while (opcao1 !=0);

    }
}
