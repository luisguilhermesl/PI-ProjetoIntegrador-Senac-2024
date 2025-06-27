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
public class ModeloTabela extends AbstractTableModel {

    private static final String[] colunas = { "Nº", "ID", "CPF/CNPJ", "Nome", "E-mail", "Telefone", "Endereço"};
    private ArrayList<Cliente> clientes;

    public ModeloTabela(ArrayList<Cliente> clientes) {
        super();
        this.clientes = clientes;
    }

    public void adicionarCliente(Cliente cliente) {
        this.clientes.add(cliente);
        fireTableDataChanged();

    }

    @Override
    public Object getValueAt(int row, int column) {
        Cliente cliente = clientes.get(row);
        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return cliente.getId();
            case 2:
                return cliente.getCpfCnpj();
            case 3:
                return cliente.getNome();
            case 4:
                return cliente.getEmail();
            case 5:
                return cliente.getTelefone();
            case 6:
                return cliente.getEndereco();
            default:
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

    public void removerClientePorId(String id) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId().equals(id)) {
                clientes.remove(i);
                fireTableRowsDeleted(i, i);
                break;
            }
        }
    }
    
    public void atualizarCliente(Cliente clienteAtualizado) {
    for (int i = 0; i < clientes.size(); i++) {
        if (clientes.get(i).getId().equals(clienteAtualizado.getId())) {
            clientes.set(i, clienteAtualizado); // substitui o objeto
            fireTableRowsUpdated(i, i); // atualiza só a linha alterada
            break;
        }
    }
}

}
