import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class UI extends JFrame implements ActionListener {
    private JFrame frame;

    private JButton exitButton;
    private JButton searchButton;
    private JButton resetButton;
    private JButton cancelButton;
    //private JButton s1;

    private JPanel buttonPanel;
    private JPanel reservationPanel;
    private JPanel textPanel;

    private JLabel cityDepartureLabel;
    private JLabel cityArrivalLabel;
    private JLabel departureDateLabel;
    private JLabel returnDateLabel;
    private JLabel numPassengerLabel;
    private JLabel seatSelectLabel;

    private JComboBox cityDepartureCBox;
    private JComboBox cityArrivalCBox;
    private JComboBox numPassengerCBox;
    private JComboBox seatSelectCBox;

    private DateTextField  departureDateTextField;
    private DateTextField returnDateTextField;

    private HashMap<String, String> airports;
    private ArrayList<Schedule> schedule;
    private ArrayList<String> Airports;

    private String chosenDepartureAirport;
    private String chosenArrivalAirport;
    private String chosenDepartureDate;
    private String chosenDepartureMonth;
    private String chosenDepartureYear;
    private String chosenDepartureDay;
    private String chosenReturnMonth;
    private String chosenReturnYear;
    private String chosenReturnDay;
    private String chosenReturnDate;
    private String chosenNumPassenger;

    private Color lineColor = new Color(3,155,216);

    /**
     * Constructor for objects of class UI
     */
    public UI() {
        // initialise instance variables
        setResizable(false);
    }

    public void search() {
        String schedulePath = "C:\\Users\\iangr\\IdeaProjects\\CompSci240-FinalGroupProject\\ARS\\src\\schedule.txt";
        chosenDepartureAirport = (String) ((cityDepartureCBox.getSelectedItem().toString()).split(",")[0]);
        System.out.println(chosenDepartureAirport);
        chosenArrivalAirport = (String) ((cityArrivalCBox.getSelectedItem().toString()).split(",")[0]);
        System.out.println(chosenArrivalAirport);
        chosenDepartureMonth = departureDateTextField.getText().split("/")[0];
        chosenDepartureDay = departureDateTextField.getText().split("/")[1] + ",";
        chosenDepartureYear = departureDateTextField.getText().split("/")[2];
        chosenReturnMonth = returnDateTextField.getText().split("/")[0] ;
        chosenReturnDay = returnDateTextField.getText().split("/")[1];
        chosenReturnYear = returnDateTextField.getText().split("/")[2];
        chosenDepartureDate = chosenDepartureYear +"," + chosenDepartureMonth + "," + chosenDepartureDay;

        chosenReturnDate = chosenReturnYear + "," + chosenReturnMonth + "," + chosenReturnDay;

        System.out.println(chosenReturnDate);
        chosenNumPassenger = (String) numPassengerCBox.getSelectedItem();

        String searchString1 = (chosenDepartureDate);
        String searchString2 = (chosenArrivalAirport + "," + chosenDepartureAirport);
        try {
            File file = new File(schedulePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(searchString1) && line.contains(searchString2)) {
                    System.out.println(line);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
        }
       /* for (Map.Entry<String, String> elements : airports.entrySet()) {
            System.out.println(elements);
        }

        for (Schedule x : schedule) {
           // x.print();
        }*/
    }

    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "Search":
               // System.out.println("Search button pressed");
                search();
                break;
            case "Reset":
                cityDepartureCBox.setSelectedIndex(0);
                cityArrivalCBox.setSelectedIndex(0);
                departureDateTextField.setText("");
                returnDateTextField.setText("");
                numPassengerCBox.setSelectedIndex(0);
                seatSelectCBox.setSelectedIndex(0);
                break;
            case "Cancel":
                break;
            case "Exit":
                System.out.println("Exit button pressed");
                System.exit(0);
                break;
        }

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

        String[] numPassenger = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] seatNum = {"A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4"};

        cityDepartureLabel = new JLabel("Departure City:");
        cityArrivalLabel = new JLabel("Destination City:");
        departureDateLabel = new JLabel("Departure Date:");
        returnDateLabel = new JLabel("Return Date:");
        numPassengerLabel = new JLabel("Number of Tickets");
        seatSelectLabel = new JLabel("Which seat(s) do you want?");

        cityDepartureCBox = new JComboBox(Airports.toArray());
        cityDepartureCBox.setEditable(false);
        cityDepartureCBox.addActionListener(this);

        cityArrivalCBox = new JComboBox(Airports.toArray());
        cityArrivalCBox.setEditable(false);
        cityArrivalCBox.addActionListener(this);

        numPassengerCBox= new JComboBox(numPassenger);
        numPassengerCBox.setEditable(true);
        numPassengerCBox.addActionListener(this);

        seatSelectCBox = new JComboBox(seatNum);
        seatSelectCBox.setEditable(true);
        seatSelectCBox.addActionListener(this);

        departureDateTextField = new DateTextField();
        returnDateTextField = new DateTextField();

        textPanel.add(cityDepartureLabel);
        textPanel.add(cityDepartureCBox);
        textPanel.add(cityArrivalLabel);
        textPanel.add(cityArrivalCBox);
        textPanel.add(departureDateLabel);
        textPanel.add(departureDateTextField);
        textPanel.add(returnDateLabel);
        textPanel.add(returnDateTextField);
        textPanel.add(numPassengerLabel);
        textPanel.add(numPassengerCBox);
        textPanel.add(seatSelectLabel);
        textPanel.add(seatSelectCBox);

    }



    public void fillAirportCBoxFromTxtFile() {
        String filePath = "C:\\Users\\vader\\IdeaProjects\\CompSci240-FinalGroupProject1\\ARS\\src\\airports.csv";

        try {
            Scanner reader = new Scanner(new File(filePath));
            Airports = new ArrayList<>();

            while (reader.hasNextLine()){
                Airports.add(reader.nextLine());
            }
                 reader.close();


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

        fillAirportCBoxFromTxtFile();
        createReservationPanel();
        createButtonPanel();
        createTextPanel();

        frame.add(reservationPanel, BorderLayout.NORTH);
        frame.add(textPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
