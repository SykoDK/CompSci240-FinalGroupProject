/*import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PurchaseTicket {

        class ButtonListener implements ActionListener {

               private JTextField fnTextField;
               private JTextField lnTextField;

               public ButtonListener(JTextField  fnTextField, JTextField lnTextField) {
                   this.fnTextField = fnTextField;
                   this.lnTextField = lnTextField;
               }

               @Override
               public void actionPerformed (ActionEvent e){
                   String firstName = fnTextField.getText();
                   String lastName = lnTextField.getText();
                   JOptionPane.showMessageDialog(null, "First name is: " + firstName + " Last name is: " + lastName);

               }

        }
        JPanel PTPanel = new JPanel();
        JLabel firstName = new JLabel("Please enter your first name: ");
        JLabel lastName = new JLabel("Please enter your last name: ");

        JTextField fnTextField = new JTextField(25);
        PTPanel.
        JTextField lnTextField = new JTextField(25);
        PTFrame.getContentPane().add(lnTextField);

        JButton search = new JButton("Search");
        search.addActionListener(new ButtonListener(fnTextField, lnTextField));


        startPanel.add(firstName);
        startPanel.add(lastName);
        startPanel.add(fnTextField);
        startPanel.add(lnTextField);
        startPanel.add(search);*/
