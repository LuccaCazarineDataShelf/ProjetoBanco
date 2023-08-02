import java.util.InputMismatchException;
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

        Pessoa cliente = new Pessoa(nome, email, senha, cpf, numConta, saldo);

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
        int numDigitado = scan.nextInt();
        scan.nextLine();

        Pessoa cliente  = buscarConta(numDigitado);
        if(cliente != null){
            listaClientes.removerPessoa(cliente);
            System.out.println("Conta removida com sucesso!");
        }else{
            System.out.println("Conta não encontrada!");
        }
    }

    private Pessoa buscarConta(int numDigitado){
        for(Pessoa cliente : listaClientes){
            if(cliente.getNumConta() == numDigitado){
                return cliente;
            }
        }
        return null;
    }
    public void acessarConta(){
        System.out.println("Digite o número da conta que deseja acessar: ");
        int numDigitado2 = scan.nextInt();
        scan.nextLine();

        Pessoa cliente = buscarConta(numDigitado2);
        if(cliente != null){
            System.out.println("Conta acessada com sucesso! Dados: ");
            System.out.println("tipo da conta: " + getTipo());
            System.out.println("Saldo: " + cliente.getSaldo());
        }else{
            System.out.println("Conta não encontrada! tente novamente.");
            return;
        }
    }
    public void sacar(){
        System.out.println("Digite o número da sua conta.");
        int numDigitado3 = scan.nextInt();
        scan.nextLine();

        Pessoa cliente = buscarConta(numDigitado3);
        if(cliente != null){
            System.out.println("seu saldo: " + cliente.getSaldo());
            System.out.println("Quanto deseja sacar? ");
            float saque = scan.nextFloat();
            if(saque > cliente.getSaldo()){
                System.out.println("Você nao tem dinheiro para realizar esse saque ");
            }else{
                this.setSaldo(cliente.getSaldo() - saque);
                System.out.println("saque realizado com sucesso!");
            }
        } else{
            System.out.println("Conta não encontrada! Tente novamente.");
        }
    }
    public void depositar() {
        System.out.println("Digite o número da sua conta:");
        int numDigitado4;
        try {
            numDigitado4 = scan.nextInt();
            scan.nextLine(); // Limpar o buffer do scanner após a leitura do inteiro
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. O número da conta deve ser um valor inteiro.");
            scan.nextLine(); // Limpar o buffer do scanner após o erro
            return;
        }

        Pessoa cliente = buscarConta(numDigitado4);
        if (cliente != null) {
            System.out.println("Seu saldo atual: " + cliente.getSaldo());
            System.out.println("Digite o valor do depósito:");
            float vDeposito;
            try {
                vDeposito = scan.nextFloat();
                scan.nextLine(); // Limpar o buffer do scanner após a leitura do float
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. O valor do depósito deve ser um número.");
                scan.nextLine(); // Limpar o buffer do scanner após o erro
                return;
            }
            if (vDeposito > 0) {
                float novoSaldo = cliente.getSaldo() + vDeposito;
                cliente.setSaldo(novoSaldo);
                System.out.println("Depósito realizado com sucesso! Seu novo saldo é: " + cliente.getSaldo());
            } else {
                System.out.println("Valor inválido para depósito. O valor deve ser positivo.");
            }
        } else {
            System.out.println("Conta não encontrada! Tente novamente.");
        }
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
