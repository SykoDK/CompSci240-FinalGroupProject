/*import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class StartPanel implements ItemListener {
    static JButton GO;
    static JLabel choices, chosen;
    static JComboBox comboBox;
    static JPanel startPanel;

    public StartPanel() {
        startPanel = new JPanel();
        GO = new JButton();
        choices = new JLabel();
        chosen = new JLabel();
        comboBox = new JComboBox();
    }

    public StartPanel(JPanel startPanel, JButton GO, JLabel choices, JLabel chosen, JComboBox comboBox) {
        startPanel = new JPanel();
        String[] startSelection = {"Flight Lookup", "Purchase Ticket", "Departure Time"};

        StartPanel s = new StartPanel();

        comboBox = new JComboBox(startSelection);
        comboBox.addItemListener(s);
        GO = new JButton("GO!");
        GO.addActionListener(new ButtonListener());


        choices = new JLabel("What would you like to do?");
        chosen = new JLabel("Flight Lookup selected");

        startPanel.add(choices);
        startPanel.add(comboBox);
        startPanel.add(chosen);
        startPanel.add(comboBox);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == StartPanel.comboBox) {
            chosen.setText(comboBox.getSelectedItem() + " selected");
        }
    }


    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

}

*/