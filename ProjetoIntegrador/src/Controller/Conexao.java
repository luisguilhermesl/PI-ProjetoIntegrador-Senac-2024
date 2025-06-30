package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class Conexao {

    private static final String url = "jdbc:mysql://localhost:3306/bdclientes";
    private static final String user = "root";
    private static final String password = "root";
    
    private static Connection connection;
    private static Conexao conn;

    //private Conexao(){}
    
    public static Conexao getConn() {
        if (conn == null) {
            conn = new Conexao();
        }
        return conn;
    }

    public Connection abrirConexao() {
        try {
            //
            System.out.println("Conectando ao banco de dados...");
            connection = DriverManager.getConnection(url, user, password);
            //
            System.out.println("Conexão estabelecida com sucesso!");
            return connection;

        }catch(SQLException e) { //ClassNotFoundException e
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return connection;
    }
    
    //public void fecharConexao()

}