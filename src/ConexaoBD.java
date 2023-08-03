import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static final String URL = "jdbc:sqlserver://localhost:1443;databaseName=master";
    private static final String USUARIO = "sa";
    private static final String SENHA = "Cazarine2003";

    private static Connection conexao;

    public static Connection obterConexao(){
        try{
            if(conexao == null || conexao.isClosed()){
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            }
            return conexao;
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
