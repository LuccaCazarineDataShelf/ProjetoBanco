import java.util.Scanner;
import javax.swing.JOptionPane;
public class Main{
    public static void main(String args[]){

        Scanner scan = new Scanner(System.in);
        Banco banco = new Banco();

        JOptionPane.showMessageDialog(null,"Bem Vindo ao Banco!");

        while(true){
            String opcaoStr = JOptionPane.showInputDialog("\nEscolha uma opção"
                    + "\n1 - Abrir conta"
                    + "\n2 - Fechar Conta"
                    + "\n3 - Acessar conta"
                    + "\n4 - Sacar"
                    + "\n5 - Depositar"
                    + "\n0 - sair"
                    + "\nOpcão: ");

            int opcao;

            try{
                opcao = Integer.parseInt(opcaoStr);
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Opção inválida");
                continue;
            }

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
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"opcão inválida");
                    break;
            }
        }
    }
}