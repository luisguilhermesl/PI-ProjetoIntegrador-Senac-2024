package Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author admin
 */
public class ModeloTabela extends AbstractTableModel {
    
    private static final String[] colunas = {"ID","CPF/CNPJ","Nome","E-mail","Telefone","Endereço"};
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
    public String getColumnName(int column){
        return colunas[column];
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = clientes.get(rowIndex);
        if(columnIndex == 0){
            return cliente.getId();
        }else if(columnIndex == 1){
            return cliente.getCpfCnpj();
        }else if(columnIndex == 2){
            return cliente.getNome();
        }else if(columnIndex == 3){
            return cliente.getEmail();
        }else if(columnIndex == 4){
            return cliente.getTelefone();
        }else if(columnIndex == 5){
            return cliente.getEndereco();
        }else{
            return null;
        }
    }

    public void removerClientePorId(String id) {
        for (int i = 0; i < clientes.size(); i++) {
            if(clientes.get(i).getId().equals(id)){
                clientes.remove(i);
                fireTableRowsDeleted(i, i);
                break;
            }
        }
    }

    public void atualizarCliente(Cliente clienteAtualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            if(clientes.get(i).getId().equals(clienteAtualizado.getId())){
                clientes.set(i, clienteAtualizado);//substitui o objeto
                fireTableRowsDeleted(i, i); //atualiza só a linha alterada
                break;
            }
        }
    }
}