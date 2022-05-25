package graph;

import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

class GUI extends JFrame {
    private Connection connection;
    private UserData userData;
    private JScrollPane pane;
    private JButton delete;
    private JButton add;
    public GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        initConnection();
        buildUI();
        loadDataFromDB();
        add.addActionListener(new AddingListener());
        delete.addActionListener(new DeleteListener());
        setVisible(true);
    }

    private void initConnection() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String url = "jdbc:ucanaccess://C:\\Users\\Zver\\Documents\\BanketHoll1.accdb";
            connection = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buildUI() {
        setSize(800,300);
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        JPanel buttons = new JPanel();

        userData = new UserData();
        JTable userTable = new JTable(userData);

        pane = new JScrollPane(userTable);
        userTable.setFillsViewportHeight(true);

        delete = new JButton("Видалити запис");
        add = new JButton("Додати запис");

        buttons.add(delete);
        buttons.add(add);

        add(pane);
        add(buttons);
    }

    private void loadDataFromDB() {

        try {
            userData.alData.clear();
            initConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Таблица1");
            while (resultSet.next()) {
                int cod = resultSet.getInt("Код_клієнта");
                String firstName = resultSet.getString("Ім_я");
                String secondName = resultSet.getString("Прізвище");
                String date = resultSet.getString("Дата_та_час");
                int peopleAmount = resultSet.getInt("Кількість_осіб");
                String phone = resultSet.getString("Номер_телефону");
                Record r = new Record(cod, firstName,secondName, date,peopleAmount,phone);
                userData.alData.add(r);
                userData.fireTableDataChanged();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteRecord(int i){
        try {
            initConnection();

            PreparedStatement delst = connection.prepareStatement("DELETE FROM Таблица1 WHERE Код_клієнта =?");
            delst.setInt(1,i);

            delst.executeUpdate();
            userData.fireTableDataChanged();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public void addRecord(Record order){
        try {
            initConnection();
            PreparedStatement inst = connection.prepareStatement("INSERT INTO Таблица1 VALUES (?,?,?,?,?,?)");

            inst.setInt(1,userData.getRowCount() + 1);
            inst.setString(2,order.getFirstName());
            inst.setString(3,order.getSecondName());
            inst.setString(4,order.getDate());
            inst.setInt(5,order.getPeopleAmount());
            inst.setString(6,order.getPhone());

            inst.executeUpdate();
            userData.fireTableDataChanged();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    class DeleteListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evt){
            try {
                int numb = Integer.parseInt(JOptionPane.showInputDialog("Введіть номер запису"));
                deleteRecord(numb);
                loadDataFromDB();
            }
            catch (NumberFormatException e){}
        }
    }
    class AddingListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evt){
            InputFrame dial = new InputFrame(null,true);
            addRecord(dial.getRecord());
            loadDataFromDB();
        }
    }
}
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUI app = new GUI();
            }
        });
    }
}
