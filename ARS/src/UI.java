import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.*;

public class UI extends JFrame implements ActionListener {
    private JFrame frame;

    private JButton exitButton;
    private JButton searchButton;
    private JButton resetButton;
    private JButton cancelButton;

    private JPanel buttonPanel;
    private JPanel reservationPanel;
    private JPanel textPanel;
    private JPanel searchPanel;
    private JPanel availableFlightsPanel;
    private JPanel searchDepartResultsPanel;
    private JPanel searchReturnResultsPanel;
    private JPanel searchResultPanel;
    private JPanel searchResultsButtonPanel;

    private JLabel cityDepartureLabel;
    private JLabel cityArrivalLabel;
    private JLabel departureDateLabel;
    private JLabel returnDateLabel;
    private JLabel numPassengerLabel;

    private JComboBox cityDepartureCBox;
    private JComboBox cityArrivalCBox;
    private JComboBox numPassengerCBox;

    private DateTextField  departureDateTextField;
    private DateTextField returnDateTextField;

    private HashMap<String, String> airports;
    private ArrayList<Schedule> schedule;
    private ArrayList<String> Airports;
    private ArrayList<String> departFlights = new ArrayList<String>();
    private ArrayList<String> departFlightDetails = new ArrayList<String>();
    private ArrayList<String> returnFlights = new ArrayList<String>();
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

    //private static String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";

    private Color lineColor = new Color(3,155,216);

    private LocalDate date;
    /**
     * Constructor for objects of class UI
     */
    public UI() {
        // initialise instance variables
        setResizable(false);
    }

    public void searchDepartFlights() {
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

        System.out.println(chosenDepartureDate);
        System.out.println(chosenReturnDate);
        chosenNumPassenger = (String) numPassengerCBox.getSelectedItem();

        String searchString1 = (chosenDepartureDate);
        String searchString2 = (chosenDepartureAirport + "," + chosenArrivalAirport);
        try {
            File file = new File(schedulePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(searchString1) && line.contains(searchString2)) {
                    System.out.println(line);
                    departFlights.add(line);

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
                searchDepartFlights();
                reservationPanel.setVisible(false);
                textPanel.setVisible(false);
                createAvailableFLights();
                createSearchResultsPanel();
                searchDepartResultsPanel.setVisible(true);
                searchDepartResultsPanel.repaint();
                createButtonPanel();
                break;
            case "Reset":
                cityDepartureCBox.setSelectedIndex(0);
                cityArrivalCBox.setSelectedIndex(0);
                departureDateTextField.setDate(DateTextField.getToday());
                returnDateTextField.setDate(DateTextField.getToday());
                numPassengerCBox.setSelectedIndex(0);
                break;
            case "Book":
                System.out.println("Flight Booked");
                break;
            case "Cancel":
                //searchPanel.setVisible(false);
                reservationPanel.setVisible(true);
                buttonPanel.setVisible(true);
                textPanel.setVisible(true);
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

    public void createAvailableFLights(){
        availableFlightsPanel = new JPanel();
        availableFlightsPanel.setLayout(new GridLayout(1, 2, 250, 500));
        availableFlightsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Available Flights", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("TimeRoman", Font.BOLD, 18)));
    }
    public void createSearchResultsPanel() {
        JButton button = new JButton("Book");


        int departureTime = 0;
        int arrivalTime = 0;
        int flightTime = 0;
        int flightTimeHours = 0;
        int flightTimeMinutes = 0;

        searchResultPanel = new JPanel();
        searchResultPanel.setLayout(new GridLayout(2, 2, 100, 100));
        searchResultPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Available Flights", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("TimeRoman", Font.BOLD, 18)));

        searchDepartResultsPanel = new JPanel();
        searchDepartResultsPanel.setLayout(new FlowLayout());
        searchDepartResultsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                " Depart From " + chosenDepartureAirport + " to " + chosenArrivalAirport + " ", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("TimeRoman", Font.BOLD, 18)));

        searchReturnResultsPanel = new JPanel();
        searchReturnResultsPanel.setLayout(new FlowLayout());
        searchReturnResultsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                " Return From " + chosenArrivalAirport + " to " + chosenDepartureAirport + " ", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("TimeRoman", Font.BOLD, 18)));

        for (int i = 0; i < departFlights.size(); i++) {
            departureTime = Integer.parseInt(departFlights.get(i).split(",")[8]);
            arrivalTime = Integer.parseInt(departFlights.get(i).split(",")[10]);
            int departureDay = Integer.parseInt(departFlights.get(i).split(",")[2]);
            int departureMonth = Integer.parseInt(departFlights.get(i).split(",")[1]);
            int departureYear = Integer.parseInt(departFlights.get(i).split(",")[0]);
            System.out.println(departureDay + " " + departureMonth + " " + departureYear);
            String departureTimeStr = String.format("%02d:%02d", departureTime / 100, departureTime % 100);
            System.out.println(departureTimeStr);
            String arrivalTimeStr = String.format("%02d:%02d", arrivalTime / 100, arrivalTime % 100);
            System.out.println(arrivalTimeStr);
            flightTime = arrivalTime - departureTime;
            flightTimeHours = flightTime / 100;
            flightTimeMinutes = flightTime % 100;
            String flightTimeStr = String.format("%2dh %02dm", flightTimeHours, flightTimeMinutes);
            System.out.println(flightTimeStr);
            System.out.println("Flight Number: " + departFlights.get(i).split(",")[5] + "\n" + " Departure Time: " + departureTimeStr + "\n" + " Arrival Time: " + arrivalTimeStr + "\n" + "Flight Time: " + flightTimeStr + " Departure Date: " + departureMonth + "/" + departureDay + "/" + departureYear);
            departFlightDetails.add("Flight Number: " + departFlights.get(i).split(",")[5] + " Departure Time: " + departureTimeStr + " Arrival Time: " + arrivalTimeStr + " Flight Time: " + flightTimeStr + " DepartureDate: " + departureMonth + "/" + departureDay + "/" + departureYear);
            System.out.println(departFlightDetails);
        }
        searchDepartResultsPanel.add(button);
        button.addActionListener(this);

        String[] flightList = new String[departFlightDetails.size()];
        flightList = departFlightDetails.toArray(flightList);

        for (int i = 0; i < departFlightDetails.size(); i++) {
            JLabel label = new JLabel(flightList[i]);
        }
        JList<String> flightList1 = new JList<>(flightList);
        flightList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        flightList1.setLayoutOrientation(JList.VERTICAL);
        flightList1.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(flightList1);
        listScroller.setPreferredSize(new Dimension(800, 200));
        searchDepartResultsPanel.add(listScroller);
        searchDepartResultsPanel.add(button);
        button.addActionListener(this);
        button.setActionCommand("Book");
        searchResultPanel.add(searchDepartResultsPanel);
        searchResultPanel.add(searchReturnResultsPanel);
        frame.add(searchResultPanel);
        frame.setVisible(true);

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
        textPanel.setBackground(Color.WHITE);
        String[] numPassenger = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        cityDepartureLabel = new JLabel("Departure City:");
        cityArrivalLabel = new JLabel("Destination City:");
        departureDateLabel = new JLabel("Departure Date:");
        returnDateLabel = new JLabel("Return Date:");
        numPassengerLabel = new JLabel("Number of Tickets");

        cityDepartureCBox = new JComboBox(Airports.toArray());
        cityDepartureCBox.setEditable(false);
        cityDepartureCBox.addActionListener(this);

        cityArrivalCBox = new JComboBox(Airports.toArray());
        cityArrivalCBox.setEditable(false);
        cityArrivalCBox.addActionListener(this);

        numPassengerCBox= new JComboBox(numPassenger);
        numPassengerCBox.setEditable(true);
        numPassengerCBox.addActionListener(this);

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

    }



    public void fillAirportCBoxFromTxtFile() {
        String filePath = "C:\\Users\\iangr\\IdeaProjects\\CompSci240-FinalGroupProject\\ARS\\src\\airports.csv";

        try {
            Scanner reader = new Scanner(new File(filePath));
            Airports = new ArrayList<>();
            Airports.add("Select an Airport");
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
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.WHITE);
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
