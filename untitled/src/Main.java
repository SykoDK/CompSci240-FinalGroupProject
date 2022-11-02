import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class Main extends JFrame implements ItemListener {
    static JFrame frame;
    static JButton GO;
    static JLabel choices, chosen;
    static JComboBox c1;

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == c1) {
            chosen.setText(c1.getSelectedItem() + " selected");
        }
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            PurchaseTicket;
        }
    }

    public static void main(String args[]) {
        frame = new JFrame("Airline Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JPanel startPanel = new JPanel();
        Main s = new Main();
        String startSelection[] = {"Flight Lookup", "Purchase Ticket", "Departure Time"};

        c1 = new JComboBox(startSelection);
        c1.addItemListener(s);
        GO = new JButton("GO!");
        GO.addActionListener(new ButtonListener());


        choices = new JLabel("What would you like to do?");
        chosen = new JLabel("Flight Lookup selected");

        startPanel.add(choices);
        startPanel.add(c1);

        startPanel.add(chosen);


       /* JLabel firstName = new JLabel("Please enter your first name: ");
        JLabel lastName = new JLabel("Please enter your last name: ");

        JTextField fnTextField = new JTextField(25);
        frame.getContentPane().add(fnTextField);
        JTextField lnTextField = new JTextField(25);
        frame.getContentPane().add(lnTextField);

        JButton search = new JButton("Search");
        search.addActionListener(new ButtonListener(fnTextField, lnTextField));


        startPanel.add(firstName);
        startPanel.add(lastName);
        startPanel.add(fnTextField);
        startPanel.add(lnTextField);
        startPanel.add(search);*/
        startPanel.add(c1);
        frame.pack();
        frame.getContentPane().add(BorderLayout.SOUTH, startPanel);
        frame.setSize(1700, 825);
        frame.setVisible(true);
    }
}
