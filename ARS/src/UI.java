import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

    private JComboBox cityDepartureCBox;

    private HashMap<String, String> airports;
    private ArrayList<Schedule> schedule;

    private String chosenDepartureAirport;

    private Color lineColor = new Color(3,155,216);

    /**
     * Constructor for objects of class UI
     */
    public UI() {
        // initialise instance variables
        setResizable(false);
    }

    public void search() {
        for (Map.Entry<String, String> elements : airports.entrySet()) {
            System.out.println(elements);
        }

        for (Schedule x : schedule) {
            x.print();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "Search":
                System.out.println("Search button pressed");
                search();
                break;
            case "Reset":
                System.out.println("Reset button pressed");
                break;
            case "Cancel":
                System.out.println("Cancel button pressed");
                break;
            case "Exit":
                System.out.println("Exit button pressed");
                System.exit(0);
                break;
        }

        chosenDepartureAirport = String.valueOf(cityDepartureCBox.getItemAt(cityDepartureCBox.getSelectedIndex()));
        System.out.println(chosenDepartureAirport);
    }

    public void createReservationPanel() {
        reservationPanel = new JPanel();
        reservationPanel.setLayout(new GridLayout(1, 2, 250, 500));
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
        //fillAirportCBoxFromTxtFile(Airport);
        String[] test = {"JAX", "ALB", "uiyoyu", "zcvccvzx"};

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

        cityDepartureCBox = new JComboBox(test);
        cityDepartureCBox.setEditable(true);
        cityDepartureCBox.addActionListener(this);

        textPanel.add(cityDepartureLabel);
        textPanel.add(cityDepartureCBox);
        textPanel.add(cityArrivalLabel);
        textPanel.add(cityArrivalField);
        textPanel.add(departureDateLabel);
        textPanel.add(departureDateField);
        textPanel.add(returnDateLabel);
        textPanel.add(returnDateField);
        textPanel.add(numPassengerLabel);
        textPanel.add(numPassengerField);

    }



    public void fillAirportCBoxFromTxtFile() {
        String filePath = "C:\\Users\\iangr\\CompSci240-FinalGroupProject\\ARS\\src\\airports.csv";
        File file = new File(filePath);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            Object[] Airports = br.lines().toArray();

            for (Object airport : Airports) {
                String Airport = airport.toString();
                cityDepartureCBox.addItem(Airport);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
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
