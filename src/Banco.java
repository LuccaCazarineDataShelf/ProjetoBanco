import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
public class Banco {
    private ArrayList<Banco> contas = new ArrayList<>();
    //Atributos
    public int numConta;
    protected String tipo;
    private String dono;
    private float saldo;
    private boolean status;

    //getters and setters
    public int getNumConta(){
        return this.numConta;
    }
    public void setNumConta(int nC){
        this.numConta = nC;
    }
    public String getTipo(){
        return this.tipo;
    }
    public void setTipo(String t){
        this.tipo = t;
    }
    public String getDono(){
        return this.dono;
    }
    public void setDono(String d){
        this.dono = d;
    }
    public float getSaldo(){
        return this.saldo;
    }
    public void setSaldo(float s){

        this.saldo = s;
    }

    public boolean getStatus(){
        return this.status;
    }
    public void setStatus(boolean st){
        this.status = st;
    }


    //Métodos
    public void acessarConta(){
        int numDigitado = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite seu nome: ");
        this.dono = scanner.next();
        System.out.println("Digite o tipo de conta (corrente ou poupança");
        this.tipo = scanner.next();
        System.out.println("Digite o número da conta: ");
        numDigitado = scanner.nextInt();
        if(numDigitado == this.numConta){
            System.out.println("Conta acessada com sucesso!");
            System.out.println("Seu saldo: " + this.saldo);
        }else{
            System.out.println("Não existe conta com esse número!");
        }
    }

    public void statusConta() {
        if (this.status == true) {
            abrirConta();
        } if(this.status == false) {
            fecharConta();
        }
    }
    public void abrirConta(){
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Banco novaConta = new Banco();

        System.out.println("Digite seu nome");
        this.dono = scanner.next();
        System.out.println("Digite o tipo de conta (corrente ou poupança)");
        this.tipo = scanner.next();
        setNumConta((random.nextInt(9000) + 1000));

        /*if(this.tipo != "corrente" || this.tipo != "poupança") {
            System.out.println("Você digitou uma opcão inválida, tente novamente");
            return;*/
        if (this.tipo.equalsIgnoreCase("corrente")) {
            System.out.println("Você abriu uma conta corrente e ganhou um saldo de 50 reais");
            this.saldo = 50.00f;
            this.setStatus(true);
        } else if(this.tipo.equalsIgnoreCase("poupança")) {
            System.out.println("você abriu uma conta poupança e ganhou 150 reais de saldo");
            this.saldo = 150f;
            this.setStatus(false);
        }else{
            System.out.println("Tipo de conta inválido. Tipos válidos: 'corrente' ou 'popança'");
            return;

        /*System.out.println("Digite seu nome: ");
        this.dono = scanner.next();
        System.out.println("Digite o tipo de conta que deseja abrir: Corrente ou poupança");
        this.tipo = scanner.next();

        setNumConta(random.nextInt(9000) + 1000);

        if(this.tipo.equalsIgnoreCase("corrente")){
            System.out.println("Você abriu uma conta corrente e ganhou 50 reais de saldo");
            this.saldo = 50.00f;
            this.setStatus(true);
        }else if(this.tipo.equalsIgnoreCase("poupança")){
            System.out.println("você abriu uma conta poupança e ganhou 150 reais de saldo");
            this.saldo = 150.00f;
            this.setStatus(true);
        }else{
            System.out.println("Tipo de conta inválido. Tipos válidos: 'corrente' ou 'popança'");
            return;*/
        }

        System.out.println("Conta criada com sucesso!");
        System.out.println("Número da conta: " + this.getNumConta());
        System.out.println("tipo de conta: " + this.getTipo());
        System.out.println("Dono da conta " + this.getDono());
        System.out.println("Saldo: " + this.getSaldo());
        System.out.println("Status: " + this.getStatus());
        return;
    }
    public void fecharConta(){
        int numDigitado2 = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o número da conta que deseja excluir: ");
        numDigitado2 = scanner.nextInt();
        if(numDigitado2 == numConta){
            this.setStatus(false);
            System.out.println("A conta número " + numConta + "foi excluída com sucesso!");
        }else{
            System.out.println("Conta número " + numDigitado2 + "não encontrada, tente novamente");
            return;
        }

    }
    public void depositar(){

    }
    public void sacar(){

    }
    public void pagarMensal(){

    }

}
