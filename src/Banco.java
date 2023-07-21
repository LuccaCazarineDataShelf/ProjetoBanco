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
    public void setSaldo(float s){this.saldo = s;}

    public boolean getStatus(){
        return this.status;
    }
    public void setStatus(boolean st){
        this.status = st;
    }


    //Métodos
  public void estadoAtual(){
      System.out.println("Conta: " + this.getNumConta() );
      System.out.println("Tipo: " + this.getTipo());
      System.out.println("Dono: " + this.getDono());
      System.out.println("Saldo: " + this.getSaldo());
      System.out.println("Status: " + this.getStatus());
  }

    public void abrirConta(String t){
            this.setTipo(t);
            this.setStatus(true);
            if(t == "CC"){
                this.setSaldo(50.00f);
                System.out.println("conta corrente aberta com sucesso!");
            }else if(t == "CP"){
                this.setSaldo(150.00f);
                System.out.println("conta poupança aberta com sucesso!");
            }else {
                System.out.println("Opcão inválida ");
            }
        }
    public void fecharConta(){
        if(this.getSaldo() > 0){
            System.out.println("Conta não pode ser fechada pois tem dinheiro");
        } else if (this.getSaldo() < 0) {
            System.out.println("Conta não pode ser fechada pois está negativada");
        } else{
            this.setStatus(false);
            System.out.println("conta fechada com sucesso");
        }
    }
    public void depositar(float v) {
        if (this.getStatus()) {
            this.setSaldo(this.getSaldo() + v);
            System.out.println("Deposito realizado com sucesso na conta de" + this.getDono());
        } else{
            System.out.println("impossivel depositar em uma conta fechada");
        }
    }
    public void sacar(float v) {
        if (this.getStatus()) {
            if (this.getSaldo() < v) {
                System.out.println("Saldo insuficiente");
            }
            else if (this.getSaldo() >= v){
                this.setSaldo(this.getSaldo() - v);
                System.out.println("Saque realizado com sucesso");
               }
            }else {
            System.out.println("impossivel depositar em uma conta fechada");
          }
        }
    public void pagarMensal(){
        int v = 0;
        if(this.getTipo() == "CC"){
            v = 12;
        }else if (this.getTipo() == "CP"){
            v = 20;
        }
        if (this.getStatus()){
            this.setSaldo(this.getSaldo() - v);
            System.out.println("Mensalidade paga valor: " + v);
        } else{
            System.out.println("Impossivel pagar uma conta fechada");
        }
    }
    // método construtor
    public void Banco(){
        this.setSaldo(0);
        this.setStatus(false);
    }
}
