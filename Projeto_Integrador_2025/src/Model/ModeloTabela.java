package Model;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.Vector;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis
 */

public class ModeloTabela extends AbstractTableModel  {
    
    private static final String[] colunas = {"ID", "CPF/CNPJ", "Nome", "E-mail", "Telefone", "Endereço"};
    private ArrayList<Cliente> clientes;

    
    public ModeloTabela(ArrayList<Cliente> clientes) {
    super();
    this.clientes = clientes;
    }
    
   public void adicionarCliente(Cliente cliente){
       this.clientes.add(cliente);
       fireTableDataChanged();

   }

    @Override
    public Object getValueAt(int row, int column) {
        Cliente cliente = clientes.get(row);
        if(column==0){
            return cliente.getId();
        }else if(column==1){
            return cliente.getCpfCnpj();
        }else if(column==2){
            return cliente.getNome();
        }else if(column==3){
            return cliente.getEmail();
        }else if(column==4){
            return cliente.getTelefone();
        }else if(column==5){
            return cliente.getEndereco();
        }else{
            return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
    return colunas[column]; 
    }

    @Override
    public int getColumnCount() {
        return colunas.length; 
    }

    @Override
    public int getRowCount() {
        return clientes.size(); 
    }
    
}
