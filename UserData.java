package graph;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class UserData extends AbstractTableModel {
    String [] columns ;
    public List alData ;
    public UserData(){
        this.alData  = new ArrayList();
        this.columns = new String[]{"№","Ім'я","Прізвище","Дата та час", "Кількість осіб", "Номер телефону"};
    }

    public int getColumnCount() {
        return columns.length;
    }

    public Object getValueAt(int nRow, int nCol) {
        if (nRow < 0 || nRow > this.alData.size()) {
            return null;
        }
        Record rowAddress = (Record) this.alData.get(nRow);
        switch (nCol) {
            case 0:
                return rowAddress.getCod();
            case 1:
                return rowAddress.getFirstName();
            case 2:
                return rowAddress.getSecondName();
            case 3:
                return rowAddress.getDate();
            case 4:
                return rowAddress.getPeopleAmount();
            case 5:
                return rowAddress.getPhone();
        }
        return "";
    }

    public int getRowCount() {
        return alData == null ? 0 : alData.size();
    }

    public String getColumnName(int column) {
        return columns[column];
    }

    public boolean isCellEditable(int nRow, int nCol) {
        return false;
    }
}
