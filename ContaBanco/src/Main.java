import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scannerOpcaoMenu = new Scanner(System.in);
        Scanner scannerOpcaoConta = new Scanner(System.in);
        Banco banco;
        int opcao1, opcao2;
        String cc, cp;
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
                    do{
                        System.out.println("1. Abrir conta poupança");
                        System.out.println("2. Abrir conta corrente");
                        System.out.println("0. Cancelar");
                        opcao2 = scannerOpcaoConta.nextInt();

                        switch(opcao2){
                            case 1:
                                System.out.println("Você abriu uma conta poupança");
                                break;
                            case 2:
                                System.out.println("Você abriu uma conta corrente");
                                break;
                            case 0:
                                System.out.println("Cancelando Ação... ");
                                break;
                            default:
                                System.out.println("Opção inválida. tente novamente");
                        }
                    }while (opcao2 != 0);

                    if(opcao2 == 1){
                        System.out.println("Você ganhou um saldo de 150 reais!!");
                            
                    }

                    break;
                case 2:

                    break;
                case 3:

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
