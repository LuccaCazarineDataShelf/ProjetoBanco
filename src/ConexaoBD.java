import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    public static final String URL = "jdbc:sqlserver://localhost:1433;database=master;user=sa;password=Cazarine2003;trustServerCertificate=false; encrypt=false";

    private static final String USUARIO = "sa";
    private static final String SENHA = "Cazarine2003";

    private static Connection conexao;

    public static Connection obterConexao(){
        try{
            if(conexao == null || conexao.isClosed()){
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conexao = DriverManager.getConnection(URL);
            }
            return conexao;
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
