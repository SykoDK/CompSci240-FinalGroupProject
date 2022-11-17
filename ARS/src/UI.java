import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UI extends JFrame implements ActionListener {
    // instance variables - replace the example below with your own
    private JFrame frame;

    private JButton exitButton;
    private JButton searchButton;
    private JButton resetButton;
    private JButton cancelButton;

    private JPanel buttonPanel;
    private JPanel reservationPanel;
    private JPanel textPanel;

    private JLabel cityDepartureLabel;
    private JLabel cityArrivalLabel;
    private JLabel departureDateLabel;
    private JLabel returnDateLabel;
    private JLabel numPassengerLabel;

    private JTextField cityDepartureField;
    private JTextField cityArrivalField;
    private JTextField departureDateField;
    private JTextField returnDateField;
    private JTextField numPassengerField;

    private JComboBox airportBox;

    private HashMap<String, String> airports;
    private ArrayList<Schedule> schedule;

    /**
     * Constructor for objects of class UI
     */
    public UI()
    {
        // initialise instance variables
        setResizable(false);
    }

    public void search() {
        for (Map.Entry<String, String> element : airports.entrySet()) {
            System.out.println(element);
        }

        for (Schedule x : schedule) {
            x.print();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "Search": System.out.println("Search button pressed");
                search();
                break;
            case "Reset": System.out.println("Reset button pressed");
                break;
            case "Cancel": System.out.println("Cancel button pressed");
                break;
            case "Exit": System.out.println("Exit button pressed");
                System.exit(0);
                break;
        }

        String data = "Departure Airport selected: " + airportBox.getItemAt(airportBox.getSelectedIndex());
        System.out.println(data);
    }

    public void createReservationPanel() {
        reservationPanel = new JPanel();
        reservationPanel.setLayout(new GridLayout(1,2,250,500));
        reservationPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Book a Flight", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("TimeRoman", Font.BOLD, 18)));
    }

    public void createButtonPanel() {
        buttonPanel = new JPanel(new FlowLayout());

        exitButton = new JButton("Exit");
        searchButton = new JButton("Search");
        resetButton = new JButton("Reset");
        cancelButton = new JButton("Cancel");

        exitButton.addActionListener(this);
        searchButton.addActionListener(this);
        resetButton.addActionListener(this);
        cancelButton.addActionListener(this);

        buttonPanel.add(searchButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(exitButton);
    }

    public void createTextPanel() {
        textPanel = new JPanel(new FlowLayout());

        String[] test = { "asdfda", "rttyr rytr", "uiyoyu", "zcvccvzx"};

        cityDepartureLabel = new JLabel("Departure City:");
        cityArrivalLabel = new JLabel("Destination City:");
        departureDateLabel = new JLabel("Departure Date:");
        returnDateLabel = new JLabel("Return Date:");
        numPassengerLabel = new JLabel("Number of Tickets");

        cityDepartureField = new JTextField(15);
        cityArrivalField = new JTextField(15);
        departureDateField = new JTextField(15);
        returnDateField = new JTextField(15);
        numPassengerField = new JTextField(15);

        airportBox = new JComboBox(test);
        airportBox.setEditable(true);
        airportBox.addActionListener(this);

        textPanel.add(cityDepartureLabel);
        textPanel.add(airportBox);
        textPanel.add(cityDepartureField);
        textPanel.add(cityArrivalLabel);
        textPanel.add(cityArrivalField);
        textPanel.add(departureDateLabel);
        textPanel.add(departureDateField);
        textPanel.add(returnDateLabel);
        textPanel.add(returnDateField);
        textPanel.add(numPassengerLabel);
        textPanel.add(numPassengerField);



    }

    public void createAndShowUI(HashMap<String, String> airPorts, ArrayList<Schedule> sched) {
        frame = new JFrame("EVIL Airline Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setLayout(new BorderLayout());


        airports = airPorts;
        schedule = sched;

        createReservationPanel();
        createButtonPanel();
        createTextPanel();

        frame.add(reservationPanel, BorderLayout.NORTH);
        frame.add(textPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
