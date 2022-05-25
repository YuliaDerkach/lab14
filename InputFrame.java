package graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputFrame extends JDialog {
    JTextField firstName;
    JTextField secondName;
    JTextField date;
    JTextField time;
    JTextField peopleAmount;
    JTextField phoneNumber;
    JButton confirm;
    Record record;
    InputFrame(JFrame parent,boolean modal){
        super(parent,modal);

        setSize(400,400);

        createFrame();
        setResizable(false);

        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        confirm.addActionListener(new DataListener());

        setVisible(true);
    }
    public void createFrame(){
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT,40,0);
        JPanel labelPn = new JPanel();
        JPanel firstPn = new JPanel(layout);
        JPanel secondPn = new JPanel(layout);
        JPanel datePn = new JPanel(layout);
        JPanel peoplePn = new JPanel(layout);
        JPanel phonePn = new JPanel(layout);
        JPanel buttonPn = new JPanel();

        firstName = new JTextField(20);
        secondName = new JTextField(15);
        date= new JTextField(7);
        time = new JTextField(5);
        peopleAmount = new JTextField(4);
        phoneNumber = new JTextField(15);

        firstPn.add(new JLabel("Ім'я"));
        firstPn.add(firstName);

        secondPn.add(new JLabel("Прізвище"));
        secondPn.add(secondName);

        datePn.add(new JLabel("Дата"));
        datePn.add(date);
        datePn.add(new JLabel("Час"));
        datePn.add(time);

        peoplePn.add(new JLabel("Кількість осіб"));
        peoplePn.add(peopleAmount);

        phonePn.add(new JLabel("Номер телефону"));
        phonePn.add(phoneNumber);

        confirm = new JButton("Підтвердити");
        buttonPn.add(confirm);

        add(labelPn);
        add(firstPn);
        add(secondPn);
        add(datePn);
        add(peoplePn);
        add(phonePn);
        add(buttonPn);
    }
    public Record getRecord(){return record;}
    class DataListener implements ActionListener {
        private String dateTemp;
        private String name;
        private String timeTemp;
        private String secondNameTemp;
        private String phone;
        private int amount;
        @Override
        public void actionPerformed(ActionEvent evt){

            try {
                name = firstName.getText();
                secondNameTemp = secondName.getText();
                dateTemp = date.getText();
                timeTemp = time.getText();
                phone = phoneNumber.getText();
                amount = Integer.parseInt(peopleAmount.getText());
                if(amount <= 0){throw new NumberFormatException();}
                dateTemp = dateTemp + " " + timeTemp;

                record = new Record(name, secondNameTemp,dateTemp,amount,phone);
                dispose();
            }
            catch(NumberFormatException e){System.out.println(e);}
            catch (NullPointerException e){System.out.println(e);}
        }
    }

}
