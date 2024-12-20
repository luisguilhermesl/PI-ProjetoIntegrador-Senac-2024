package dao;

import Controller.Conexao;
import java.sql.Connection;

/**
 *
 * @author Luis
 */
public class BD {
    private static Connection connection = null;

    public static void main(String[] args) {
        try {
            connection = Conexao.getConn().abrirConexao();
            System.out.println("Base criada com sucesso");
            Conexao.getConn().fecharConexao();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}
