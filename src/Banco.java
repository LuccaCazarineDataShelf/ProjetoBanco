import java.util.Random;
import java.util.Scanner;

public class Banco{
    private int numConta;
    private float saldo;
    private String tipo;
    private boolean statusConta;
    private ListaLigada listaClientes;

    Scanner scan = new Scanner(System.in);

    public Banco(){
        this.listaClientes = new ListaLigada();
    }

    public void abrirConta() {
        System.out.println("digite seu nome: ");
        String nome = scan.nextLine();
        System.out.println("digite seu email: ");
        String email = scan.nextLine();
        if (verificarEmailCadastrado(email)) {
            System.out.println("Este email ja está cadastrado á uma conta, tente novamnte!");
            return;
        }
        System.out.println("Crie uma senha: ");
        String senha  = scan.nextLine();
        System.out.println("Digite o cpf: ");
        double cpf = scan.nextDouble();
        scan.nextLine();
        System.out.println("Digite o tipo de conta: Corrente ou Poupança");
        tipo = scan.nextLine();

        Random random = new Random();
        numConta = 1000 + random.nextInt(9000);
        System.out.println("parabens! voce criou sua conta " + this.getTipo() + "!"
                + "Número da conta: " + this.getNumConta());

        Pessoa cliente = new Pessoa(nome, email, senha, cpf);

        listaClientes.adicionarPessoa(cliente);
    }

    private boolean verificarEmailCadastrado(String email){
        for (Pessoa cliente : listaClientes){
            if(cliente.getEmail().equalsIgnoreCase(email)){
                return true;
            }
        }
        return false;
    }
    public void fecharConta() {
        System.out.println("Digite o número da conta");
        numConta = scan.nextInt();
    }
    public void acessarConta(){

    }
    public void sacar(){

    }
    public void depositar(){

    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isStatusConta() {
        return statusConta;
    }

    public void setStatusConta(boolean statusConta) {
        this.statusConta = statusConta;
    }
}
