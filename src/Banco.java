import java.sql.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Banco {
    private int numConta;
    private float saldo;
    private String tipo;
    private boolean statusConta;
    private ListaLigada listaClientes;

    Scanner scan = new Scanner(System.in);

    public Banco() {
        this.listaClientes = new ListaLigada();
    }

    public void abrirConta() {
        String nome = JOptionPane.showInputDialog("digite seu nome: ");
        if(nome == null || nome.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"Nome inválido");
            return;
        }

        String email = JOptionPane.showInputDialog("digite seu email: ");
        if (verificarEmailCadastrado(email)) {
            JOptionPane.showMessageDialog(null,"Este email ja está cadastrado á uma conta, tente novamnte!");
            return;
        }
        String senha = JOptionPane.showInputDialog("Crie uma senha: ");

        double cpf;
        try{
            String cpfStr = JOptionPane.showInputDialog("Digite o cpf: ");
            cpf = Double.parseDouble(cpfStr);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"cpf inválido");
            return;
        }

        String tipo = JOptionPane.showInputDialog("Digite o tipo de conta: Corrente ou Poupança");

        Random random = new Random();
        numConta = 1000 + random.nextInt(9000);
        JOptionPane.showMessageDialog(null,"parabens! voce criou sua conta " + this.getTipo() + "!"
                + "\nNúmero da conta: " + this.getNumConta());

        Pessoa cliente = new Pessoa(nome, email, senha , cpf, numConta, saldo, tipo);

        listaClientes.adicionarPessoa(cliente);

        try {
            Connection conexao = ConexaoBD.obterConexao();
            String sql = "INSERT INTO TabelaClientes3 (nome, email, senha, cpf, numConta, saldo, tipo)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getSenha());
            stmt.setDouble(4, cliente.getCpf());
            stmt.setInt(5, cliente.getNumConta());
            stmt.setFloat(6, cliente.getSaldo());
            stmt.setString(7, cliente.getTipo());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean verificarEmailCadastrado(String email) {
        for (Pessoa cliente : listaClientes) {
            if (cliente.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public void fecharConta() {
        String numDigitadoStr = JOptionPane.showInputDialog("Digite o número da conta");
        int numDigitado;

        try{
            numDigitado = Integer.parseInt(numDigitadoStr);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Entrada Inválida");
            return;
        }

        Pessoa cliente = buscarConta(numDigitado);
        if (cliente != null) {
            listaClientes.removerPessoa(cliente);
            JOptionPane.showMessageDialog(null,"Conta removida com sucesso!");
            try {
                Connection conexao = ConexaoBD.obterConexao();
                String sql = "DELETE FROM TabelaClientes3 WHERE numConta = ?";
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, numDigitado);
                stmt.executeUpdate();
                stmt.close();
                conexao.close();
                JOptionPane.showMessageDialog(null,"Conta excluída do sistema! ");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"Erro ao excluir!");
            }
        } else {
            JOptionPane.showMessageDialog(null,"Conta não encontrada!");
        }
    }

    private Pessoa buscarConta(int numConta) {
        try {
            Connection conexao = ConexaoBD.obterConexao();
            String sql = "SELECT * FROM TabelaClientes3 WHERE numConta = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, numConta);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Pessoa cliente = new Pessoa();
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setNumConta(rs.getInt("numConta"));
                cliente.setSaldo(rs.getFloat("saldo"));
                cliente.setCpf(rs.getDouble("cpf"));
                cliente.setTipo(rs.getString("tipo"));
                return cliente;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Erro ao buscar!");
            return null;
        }
    }
    public void acessarConta(){
        String numDigitado2Str = JOptionPane.showInputDialog("Digite o número da conta que deseja acessar: ");
        int numDigitado2;

        try{
            numDigitado2 = Integer.parseInt(numDigitado2Str);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Entrada inválida");
            return;
        }

        Pessoa cliente = buscarConta(numDigitado2);
        if(cliente != null){
            JOptionPane.showMessageDialog(null,"\nConta acessada com sucesso! \nDados: "
                        +"\ntipo da conta: " + getTipo()
                        +"\nSaldo: " + cliente.getSaldo()
            );

        }else{
            JOptionPane.showMessageDialog(null,"Conta não encontrada! tente novamente.");
            return;
        }
    }
    public void sacar(){
        String numDigitado3Str = JOptionPane.showInputDialog("Digite o número da sua conta.");
        int numDigitado3;

        try{
            numDigitado3 = Integer.parseInt(numDigitado3Str);
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Entrada inválida");
            return;
        }

        Pessoa cliente = buscarConta(numDigitado3);
        if(cliente != null){
            JOptionPane.showMessageDialog(null,"seu saldo: " + cliente.getSaldo());
            String saqueStr = JOptionPane.showInputDialog("Quanto deseja sacar? ");
            float saque;

            try{
                saque = Float.parseFloat(saqueStr);
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Entrada inválida");
                return;
            }

            if(saque > cliente.getSaldo()){
                JOptionPane.showMessageDialog(null,"Você nao tem dinheiro para realizar esse saque ");
            }else{
                float novoSaldo2 = cliente.getSaldo() - saque;
                try{
                    Connection conexao = ConexaoBD.obterConexao();
                    String sql = "UPDATE TabelaClientes3 SET saldo = ? WHERE numConta = ?";
                    PreparedStatement stmt = conexao.prepareStatement(sql);
                    stmt.setFloat(1, novoSaldo2);
                    stmt.setInt(2, numDigitado3);
                    stmt.executeUpdate();
                    stmt.close();
                    conexao.close();
                }catch (SQLException e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Erro ao atualizar!");
                }
                JOptionPane.showMessageDialog(null,"saque realizado com sucesso!");
            }
        } else{
            JOptionPane.showMessageDialog(null,"Conta não encontrada! Tente novamente.");
        }
    }
    public void depositar() {
        String numDigitado4Str = JOptionPane.showInputDialog("Digite o número da sua conta:");
        int numDigitado4;
        try {
            numDigitado4 = Integer.parseInt(numDigitado4Str);
        } catch (InputMismatchException e) {
            JOptionPane.showMessageDialog(null,"Entrada inválida. O número da conta deve ser um valor inteiro.");
            return;
        }
        Pessoa cliente = buscarConta(numDigitado4);
        if (cliente != null) {
            JOptionPane.showInputDialog(null,"Seu saldo atual: " + cliente.getSaldo());
            String vDepositoStr = JOptionPane.showInputDialog("Digite o valor do depósito:");
            float vDeposito;
            try {
                vDeposito = Float.parseFloat(vDepositoStr);
            } catch (InputMismatchException e) {
                JOptionPane.showMessageDialog(null,"Entrada inválida. O valor do depósito deve ser um número.");
                return;
            }
            if (vDeposito > 0) {
                float novoSaldo = cliente.getSaldo() + vDeposito;
                cliente.setSaldo(novoSaldo);
                JOptionPane.showMessageDialog(null,"Depósito realizado com sucesso! Seu novo saldo é: " + cliente.getSaldo());
                try{
                    Connection conexao = ConexaoBD.obterConexao();
                    String sql = "UPDATE TabelaClientes3 SET saldo = ? WHERE numConta = ?";
                    PreparedStatement stmt = conexao.prepareStatement(sql);
                    stmt.setFloat(1, novoSaldo);
                    stmt.setInt(2, numDigitado4);
                    stmt.executeUpdate();
                    stmt.close();
                    conexao.close();
                }catch (SQLException e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Erro ao atualizar!");
                }
            } else {
                JOptionPane.showMessageDialog(null,"Valor inválido para depósito. O valor deve ser positivo.");
            }
        } else {
            JOptionPane.showMessageDialog(null,"Conta não encontrada! Tente novamente.");
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
