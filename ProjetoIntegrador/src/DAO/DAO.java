package DAO;

import Controller.Conexao;
import Model.Cliente;
import Model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author admin
 */
public class DAO {

    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    

    private static String CADASTRAR_CLIENTE = "INSERT INTO CLIENTE (ID,NOME,CPFCNPJ,EMAIL,TELEFONE,ENDERECO) VALUES (NULL, ?,?,?,?,?);";
    private static String CONSULTAR_CLIENTE = "SELECT * FROM CLIENTE WHERE ID = ?;";
    private static String ALTERAR_CLIENTE = "UPDATE CLIENTE SET NOME = ?, CPFCNPJ  = ?,EMAIL = ?,TELEFONE = ?,ENDERECO = ? WHERE ID = ?;";
    private static String EXCLUIR_CLIENTE = "DELETE FROM CLIENTE WHERE ID = ?;";
    private static String LISTAR_CLIENTE = "SELECT * FROM CLIENTE WHERE 1 = 1;";
    private static String CONSULTAR_USUARIO = "SELECT USUARIO, SENHA FROM USUARIO WHERE USUARIO = ? AND SENHA = ?;";
    
    //public DAO(){}
    
    public void cadastrarCliente(Cliente cliente) throws SQLException{
        Connection connection = Conexao.getConn().abrirConexao();
        connection.setAutoCommit(false);
        try{
            ps = connection.prepareStatement(CADASTRAR_CLIENTE);
            int i = 1;
            ps.setString(i++, cliente.getNome());
            ps.setString(i++, cliente.getCpfCnpj());
            ps.setString(i++, cliente.getEmail());
            ps.setString(i++, cliente.getTelefone());
            ps.setString(i++, cliente.getEndereco());
            
            ps.execute();
            connection.commit();
            
            JOptionPane.showMessageDialog(null, "Cliente incluido com sucesso");
        }catch(SQLException e){
            e.printStackTrace();
    }
    }
    
    public Cliente consultarCliente (String id) throws SQLException{
        Connection connection = Conexao.getConn().abrirConexao();
        Cliente cliente = null;
        connection.setAutoCommit(false);
        try{
            ps = connection.prepareStatement(CONSULTAR_CLIENTE);
            ps.setInt(1, Integer.parseInt(id)); // mantém o ID no banco como numérico
            rs = ps.executeQuery();
            while(rs.next()){
                cliente = new Cliente(rs.getString("ID"),rs.getString("NOME"), 
                        rs.getString("CPFCNPJ"),rs.getString("EMAIL"),
                        rs.getString("TELEFONE"),rs.getString("ENDERECO"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        if(cliente == null){
            JOptionPane.showMessageDialog(null, "Não foi possivel localizar o "
                    + "cliente selecionado","", JOptionPane.WARNING_MESSAGE);
            //
        }
        return cliente;
    }
    
    public void alterarCliente(String id, Cliente cliente) throws SQLException{
        Connection connection = Conexao.getConn().abrirConexao();
        connection.setAutoCommit(false);
        try{
            ps = connection.prepareStatement(ALTERAR_CLIENTE);
            int i = 1;
            ps.setString(i++, cliente.getNome());
            ps.setString(i++, cliente.getCpfCnpj());
            ps.setString(i++, cliente.getEmail());
            ps.setString(i++, cliente.getTelefone());
            ps.setString(i++, cliente.getEndereco());
            ps.setString(i++, cliente.getId());
            
            ps.execute();
            connection.commit();
            JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso");
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void excluirCliente(String id) throws SQLException{
        Connection connection = Conexao.getConn().abrirConexao();
        connection.setAutoCommit(false);
        try{
            ps = connection.prepareStatement(EXCLUIR_CLIENTE);
            ps.setString(1,id);
            
            ps.execute();
            connection.commit();
            JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso");
        }catch(SQLException e){
            e.printStackTrace();
    }
    }
    
    public ArrayList<Cliente> listarClientes() throws SQLException{
        Connection connection = Conexao.getConn().abrirConexao();
        ArrayList<Cliente> clientes = new ArrayList<>();
        connection.setAutoCommit(false);
        try{
            ps = connection.prepareStatement(LISTAR_CLIENTE);
            rs = ps.executeQuery();
            while(rs.next()){
                clientes.add(new Cliente(rs.getString("ID"),rs.getString("NOME"), 
                        rs.getString("CPFCNPJ"),rs.getString("EMAIL"),
                        rs.getString("TELEFONE"),rs.getString("ENDERECO")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        if(clientes.isEmpty()){
            JOptionPane.showMessageDialog(null, "Não há clientes cadastrados ","", JOptionPane.WARNING_MESSAGE);
            //
        }
        return clientes;
    }
    
    public Usuario consultarUsuario (String nomeUsuario, String senhaCriptografada) throws SQLException{
        Connection connection = Conexao.getConn().abrirConexao();
        Usuario usuario = null;
        connection.setAutoCommit(false);
        try{
            ps = connection.prepareStatement(CONSULTAR_USUARIO);
            ps.setString(1, nomeUsuario);
            ps.setString(2, senhaCriptografada);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                usuario = new Usuario(rs.getInt("ID"),rs.getString("USUARIO"),
                        rs.getString("SENHA"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        if(usuario == null){
            JOptionPane.showMessageDialog(null, "Não foi possivel localizar o "
                    + "usuário","", JOptionPane.WARNING_MESSAGE);
            //
        }
        return usuario;
    }
    
    //private void fecharConexao(){}
}