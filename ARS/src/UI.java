import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

import static java.lang.System.out;

public class UI extends JFrame implements ActionListener {
    private JFrame frame;
    private JFrame personalInfoFrame;
    private JButton exitButton;
    private JButton searchButton;
    private JButton resetButton;
    private JButton cancelButton;


    private JPanel buttonPanel;
    private JPanel reservationPanel;
    private JPanel textPanel;
    private JPanel searchDepartResultsPanel;
    private JPanel searchReturnResultsPanel;
    private JPanel searchResultPanel;
    private JPanel personalInfoPanel;


    private JPanel flightConfirmation;

    private JPanel returnFlightConfirmation;

    private JLabel cityDepartureLabel;
    private JLabel cityArrivalLabel;
    private JLabel departureDateLabel;
    private JLabel returnDateLabel;
    private JLabel numPassengerLabel;
    private JLabel seatSelectLabel;


    private JPanel personalInfoTextPanel;
    private JLabel firstName;
    private JTextField fname;
    private JLabel lastName;
    private JTextField lname;
    private JLabel Email;
    private JTextField email;
    private JPanel personalInfoButtonPanel;
    private JButton Save;
    private JButton Cancel;

    private JComboBox cityDepartureCBox;
    private JComboBox cityArrivalCBox;
    private JComboBox numPassengerCBox;
    private JComboBox seatSelectCBox;

    private DateTextField  departureDateTextField;
    private DateTextField returnDateTextField;

    private HashMap<String, String> airports;
    private ArrayList<Schedule> schedule;
    private ArrayList<String> Airports;
    private ArrayList<String> departFlights = new ArrayList<String>();
    private ArrayList<String> departFlightDetails = new ArrayList<String>();
    private ArrayList<String> departFlight = new ArrayList<>();
    private ArrayList<String> returnFlight = new ArrayList<>();
    //private String returnFlight = new String();
    private ArrayList<String> returnFlightDetails = new ArrayList<String>();
    private ArrayList<String> returnFlights = new ArrayList<String>();
    private ArrayList<Reservation> reservationList = new ArrayList<Reservation>();

    private Reservation reservation;
    private String chosenDepartureAirport;
    private String chosenArrivalAirport;
    private String chosenDepartureDate;
    private String chosenDepartureMonth;
    private String chosenDepartureYear;
    private String chosenDepartureDay;
    private String chosenReturnMonth;
    private String chosenReturnYear;
    private String chosenReturnDay;
    private String chosenSeat;
    private String chosenReturnDate;
    private String chosenNumPassenger;
    private String departureTimeStr;
    private String arrivalTimeStr;
    private String  rdepartureTimeStr;
    private String  rarrivalTimeStr;
    private String departFlightNumber;
    private String returnFlightNumber;
    private String departDetails;
    private String departArrivalTimeStr;
    private String departDepartureTimeStr;
    private String returnDepartureTimeStr;
    private String returnArrivalTimeStr;
    String[] selectedDepartureFlight;
    String[] selectedReturnFlight;
    JList<String> departList1;
    JList<String> returnList1;
    JScrollPane listScroller;

    File outputReservationFile = new File("C:\\Users\\iangr\\IdeaProjects\\CompSci240-FinalGroupProject\\ARS\\src\\reservation.txt");

    private LocalDate date;

    public UI() {
        setResizable(false);
    }

    public void searchDepartFlights() {
        String schedulePath = "ARS\\src\\southwest.txt";
        chosenDepartureAirport = ((cityDepartureCBox.getSelectedItem().toString()).split(",")[0]);
        chosenSeat = seatSelectCBox.getSelectedItem().toString();
        out.println(chosenDepartureAirport);
        chosenArrivalAirport = ((cityArrivalCBox.getSelectedItem().toString()).split(",")[0]);
        out.println(chosenArrivalAirport);
        chosenDepartureMonth = departureDateTextField.getText().split("/")[0];
        chosenDepartureDay = departureDateTextField.getText().split("/")[1] + ",";
        chosenDepartureYear = departureDateTextField.getText().split("/")[2];
        chosenReturnMonth = returnDateTextField.getText().split("/")[0] ;
        chosenReturnDay = returnDateTextField.getText().split("/")[1];
        chosenReturnYear = returnDateTextField.getText().split("/")[2];
        chosenDepartureDate = chosenDepartureYear +"," + chosenDepartureMonth + "," + chosenDepartureDay;
        chosenReturnDate = chosenReturnYear + "," + chosenReturnMonth + "," + chosenReturnDay;

        out.println(chosenDepartureDate);
        out.println(chosenReturnDate);
        chosenNumPassenger = (String) numPassengerCBox.getSelectedItem();

        String searchString1 = (chosenDepartureDate);
        String searchString2 = (chosenDepartureAirport + "," + chosenArrivalAirport);
        try {
            File file = new File(schedulePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(searchString1) && line.contains(searchString2)) {
                    out.println(line);
                    departFlights.add(line);

                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            out.println("File not found");
        }
    }

    public void searchReturnFlights() {
        String schedulePath = "ARS\\src\\southwest.txt";

        returnFlights = new ArrayList<String>();

        out.println(chosenReturnDate);
        chosenNumPassenger = (String) numPassengerCBox.getSelectedItem();

        String searchString1 = (chosenReturnDate);
        String searchString2 = (chosenArrivalAirport + "," + chosenDepartureAirport);
        try {
            File file = new File(schedulePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(searchString1) && line.contains(searchString2)) {
                    out.println(line);
                    out.println("**** " + returnFlights.size());
                    returnFlights.add(line);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            out.println("File not found");
        }

    }

    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "Search Flights":
                searchDepartFlights();
                searchReturnFlights();
                reservationPanel.setVisible(false);
                textPanel.setVisible(false);
                buttonPanel.setVisible(false);
                createAvailableFLights();
                createDeparturePanel();
                createReturnPanel();
                searchResultPanel.setVisible(true);
                searchResultPanel.repaint();
                searchResultPanel.revalidate();
                createButtonPanel();
                break;
            case "Reset":
                cityDepartureCBox.setSelectedIndex(0);
                cityArrivalCBox.setSelectedIndex(0);
                departureDateTextField.setDate(DateTextField.getToday());
                returnDateTextField.setDate(DateTextField.getToday());
                numPassengerCBox.setSelectedIndex(0);
                seatSelectCBox.setSelectedIndex(0);
                break;
            case "Cancel":
                reservationPanel.setVisible(true);
                buttonPanel.setVisible(true);
                textPanel.setVisible(true);
                personalInfoFrame.setVisible(false);
                break;
            case "Exit":
                out.println("Exit button pressed");
                for(Reservation r : reservationList) {
                    r.print();
                }
                System.exit(0);
                break;

            case "Book Flight":
                try {
                    reservation = new Reservation();
                    selectedDepartureFlight = departList1.getSelectedValue().split(" ");
                    selectedReturnFlight = returnList1.getSelectedValue().split(" ");
                    out.println("***** " + departList1.getSelectedValue());
                    out.println("***** " + returnList1.getSelectedValue());
                    out.println("Flight Booked");
                    out.println("***** " + selectedDepartureFlight.length);
                    out.println("***** " + selectedDepartureFlight[2]);
                    reservation.setDepartureDate(selectedDepartureFlight[15]);
                    reservation.setReturnDate(selectedReturnFlight[15]);
                    reservation.setDepartureAirport(chosenDepartureAirport);
                    reservation.setArrivalAirport(chosenArrivalAirport);
                    reservation.setDepartureFlightNumber(selectedDepartureFlight[2]);
                    reservation.setReturnFlightNumber(selectedReturnFlight[2]);
                    reservation.setDepartDepartureTime(selectedDepartureFlight[5]);
                    reservation.setDepartArrivalTime(selectedDepartureFlight[8]);
                    reservation.setReturnDepartureTime(selectedReturnFlight[5]);
                    reservation.setReturnArrivalTime(selectedReturnFlight[8]);

                    reservation.setSeatType(seatSelectCBox.getSelectedItem().toString());
                    reservation.print();
                    reservationList.add(reservation);
                } catch (Exception e) {
                    out.println("No flight selected");
                    JOptionPane.showMessageDialog(null, "Please select a flight!");
                }

                createPersonalInfoPanel();
                createPersonalInfoTextPanel();
                createPersonalInfoButtonPanel();
                createPersonalInfoFrame();
                personalInfoFrame.setVisible(true);
                personalInfoPanel.setVisible(true);
                personalInfoFrame.add(personalInfoPanel);
                personalInfoFrame.add(personalInfoTextPanel);
                personalInfoFrame.add(personalInfoButtonPanel);
                personalInfoFrame.setVisible(true);
                personalInfoFrame.repaint();
                personalInfoFrame.revalidate();
                break;


            case "Save":
                try {
                    reservation.setFirstName(fname.getText());
                    reservation.setLastName(lname.getText());
                    reservation.setEmail(email.getText());
                    personalInfoFrame.setVisible(false);
                    reservationPanel.setVisible(true);
                    reservationPanel.repaint();
                    reservationPanel.revalidate();
                    frame.repaint();
                    frame.revalidate();
                    buttonPanel.setVisible(true);
                    buttonPanel.repaint();
                    buttonPanel.revalidate();
                    textPanel.setVisible(true);
                    textPanel.repaint();
                    textPanel.revalidate();
                    save();

                    System.exit(0);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error saving file");
                }
                System.out.println("***********************************************************************************************************");
                for (Reservation r : reservationList) {
                    System.out.println("First Name: " + r.getFirstName() + "\n" + "Last Name: " + r.getLastName() + "\n" + "Email: " + r.getEmail() + "\n" +
                            "Origin City: " + r.getOrigin() + "\n" + "Desitnation City: " + r.getDestination() + "\n" + "Departure Date: " + r.getDepartureDate() + "\n" + "Return Date: " + r.getReturnDate() + "\n" +
                            "Departure Flight Number: " + r.getDepartureFlightNumber() + "\n" + "Departure Flight Departure Time : " + r.getDepartDepartureTime() + "\n" + "Departure Flight Arrival Time: " + r.getDepartArrivalTime() + "\n" +
                            "Return Flight Number: " + r.getReturnFlightNumber() + "\n" + "Return Flight Departure Time: " + r.getReturnDepartureTime() + "\n" + "Return Flight Arrival Time: " + r.getReturnArrivalTime() + "\n" + "Seat Type: " + r.getSeatType() + "\n");
                    System.out.print(r);
                }

                break;
        }
    }


    public void save() throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(this.outputReservationFile, true));

        for (Reservation r : reservationList) {
            out.println("First Name: "+ r.getFirstName() + "\n" + "Last Name: " + r.getLastName() + "\n" + "Email: " + r.getEmail() + "\n" +
                    "Origin City: " + r.getOrigin() + "\n" + "Desitnation City: " + r.getDestination() + "\n" + "Departure Date: " + r.getDepartureDate() + "\n" + "Return Date: " + r.getReturnDate() + "\n" +
                    "Departure Flight Number: " + r.getDepartureFlightNumber() + "\n" + "Departure Flight Departure Time : " + r.getDepartDepartureTime() + "\n" + "Departure Flight Arrival Time: " + r.getDepartArrivalTime() + "\n" +
                    "Return Flight Number: " + r.getReturnFlightNumber() + "\n" + "Return Flight Departure Time: " + r.getReturnDepartureTime() + "\n" + "Return Flight Arrival Time: " + r.getReturnArrivalTime() + "\n");

        }
        out.close();
    }

    public void createPersonalInfoFrame() {
        personalInfoFrame = new JFrame("Personal Information");
        personalInfoFrame.setLayout(new GridLayout(3, 1));
        personalInfoFrame.setSize(800, 500);
        personalInfoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        personalInfoFrame.setLocationRelativeTo(null);
        personalInfoFrame.setVisible(true);
        personalInfoFrame.add(personalInfoPanel);
        personalInfoFrame.add(personalInfoTextPanel);
        personalInfoFrame.add(personalInfoButtonPanel);
       // personalInfoFrame.setContentPane(personalInfoPanel);
    }

    public void createPersonalInfoPanel() {
        personalInfoPanel = new JPanel();
        personalInfoPanel.setLayout(new GridLayout(2, 1, 5, 5));
        personalInfoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Please fill in the following information to complete your reservation.", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("TimeRoman", Font.BOLD, 18)));
    }


    public void createPersonalInfoTextPanel() {
        personalInfoTextPanel = new JPanel();
        personalInfoTextPanel.setLayout(new GridLayout(5, 2, 10, 10));
        firstName = new JLabel("First Name: ");
        fname = new JTextField();
        lastName = new JLabel("Last Name: ");
        lname = new JTextField();
        Email = new JLabel("Email: ");
        email = new JTextField();

        personalInfoTextPanel.add(firstName);
        personalInfoTextPanel.add(fname);
        personalInfoTextPanel.add(lastName);
        personalInfoTextPanel.add(lname);
        personalInfoTextPanel.add(Email);
        personalInfoTextPanel.add(email);

        personalInfoPanel.add(personalInfoTextPanel);
        personalInfoTextPanel.setVisible(true);
        }

        public void createPersonalInfoButtonPanel() {
        personalInfoButtonPanel = new JPanel();
        personalInfoButtonPanel.setLayout(new FlowLayout());
        Save = new JButton("Save");
        Cancel = new JButton("Cancel");
        Save.addActionListener(this);
        Cancel.addActionListener(this);
        Save.setActionCommand("Save");
        Cancel.setActionCommand("Cancel");

        personalInfoButtonPanel.add(Save);
        personalInfoButtonPanel.add(Cancel);
        personalInfoPanel.add(personalInfoButtonPanel);

        personalInfoPanel.setVisible(true);
        personalInfoTextPanel.setVisible(true);
        personalInfoButtonPanel.setVisible(true);
    }



    public void createReservationPanel() {
        reservationPanel = new JPanel();
        reservationPanel.setLayout(new GridLayout(1, 2, 250, 500));
        reservationPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Book a Flight", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("TimeRoman", Font.BOLD, 18)));
    }

    public void createAvailableFLights() {
        searchResultPanel = new JPanel();
        searchResultPanel.setLayout(new GridLayout(2, 1, 250, 50));
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
    }

    public void createDeparturePanel() {

        int departureTime = 0;
        int arrivalTime = 0;
        int flightTime = 0;
        int flightTimeHours = 0;
        int flightTimeMinutes = 0;

        departFlight = new ArrayList<String>();
        departFlightDetails = new ArrayList<String>();

        for (int i = 0; i < departFlights.size(); i++) {
            departureTime = Integer.parseInt(departFlights.get(i).split(",")[8]);
            arrivalTime = Integer.parseInt(departFlights.get(i).split(",")[10]);
            int departureDay = Integer.parseInt(departFlights.get(i).split(",")[2]);
            int departureMonth = Integer.parseInt(departFlights.get(i).split(",")[1]);
            int departureYear = Integer.parseInt(departFlights.get(i).split(",")[0]);
            out.println(departureDay + " " + departureMonth + " " + departureYear);
            departDepartureTimeStr = String.format("%2d:%02d", departureTime / 100, departureTime % 100);
            out.println(departDepartureTimeStr);
            departArrivalTimeStr = String.format("%2d:%02d", arrivalTime / 100, arrivalTime % 100);
            out.println(departArrivalTimeStr);
            flightTime = arrivalTime - departureTime;
            flightTimeHours = flightTime / 100;
            flightTimeMinutes = flightTime % 100;
            String flightTimeStr = String.format("%2dh %02dm", flightTimeHours, flightTimeMinutes);
            out.println(flightTimeStr);
            departFlightNumber = departFlights.get(i).split(",")[5];
            out.println("Flight Number: " + departFlights.get(i).split(",")[5] + "," + "\n" + " Departure Time: " + departDepartureTimeStr + "," + "\n" + " Arrival Time: " + departArrivalTimeStr + "," + "\n" + "Flight Time: " + flightTimeStr + "," + " Departure Date: " + departureMonth + "/" + departureDay + "/" + departureYear+ ",");
            departFlightDetails.add("Flight Number: " + departFlights.get(i).split(",")[5] + "," + " Departure Time: " + departDepartureTimeStr + "," + " Arrival Time: " + departArrivalTimeStr + "," + " Flight Time: " + flightTimeStr + "," + " DepartureDate: " + departureMonth + "/" + departureDay + "/" + departureYear+ ",");
            departFlight.add(departFlights.get(i).split(",")[5] + "," + departDepartureTimeStr + "," + departArrivalTimeStr + "," +  flightTimeStr + "," + departureMonth + "/" + departureDay + "/" + departureYear+ ",");
            out.println(departFlightDetails);
        }

        String[] departFlightList = new String[departFlightDetails.size()];
        departFlightList = departFlightDetails.toArray(departFlightList);

        for (int i = 0; i < departFlightDetails.size(); i++) {
            JLabel label = new JLabel(departFlightList[i]);
        }
        departList1 = new JList<>(departFlightList);
        departList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        departList1.setLayoutOrientation(JList.VERTICAL);
        //departList1.setVisibleRowCount(-1);
        listScroller = new JScrollPane(departList1);
        listScroller.setPreferredSize(new Dimension(800, 200));
        searchDepartResultsPanel.add(listScroller);

        searchResultPanel.add(searchDepartResultsPanel);
        frame.add(searchResultPanel);
        frame.setVisible(true);
    }


    public void createReturnPanel () {

        int rdepartureTime = 0;
        int rarrivalTime = 0;
        int rflightTime = 0;
        int rflightTimeHours = 0;
        int rflightTimeMinutes = 0;

        returnFlight = new ArrayList<String>();
        returnFlightDetails = new ArrayList<String>();

        JButton book1 = new JButton("Book Flight");
        book1.addActionListener(this);

        for (int i = 0; i < returnFlights.size(); i++) {
            rdepartureTime = Integer.parseInt(returnFlights.get(i).split(",")[8]);
            rarrivalTime = Integer.parseInt(returnFlights.get(i).split(",")[10]);
            int rdepartureDay = Integer.parseInt(returnFlights.get(i).split(",")[2]);
            int rdepartureMonth = Integer.parseInt(returnFlights.get(i).split(",")[1]);
            int rdepartureYear = Integer.parseInt(returnFlights.get(i).split(",")[0]);
            out.println(rdepartureDay + " " + rdepartureMonth + " " + rdepartureYear);
            returnDepartureTimeStr = String.format("%2d:%02d", rdepartureTime / 100, rdepartureTime % 100);
            out.println(rdepartureTimeStr);
            returnArrivalTimeStr = String.format("%2d:%02d", rarrivalTime / 100, rarrivalTime % 100);
            out.println(returnArrivalTimeStr);
            rflightTime = rarrivalTime - rdepartureTime;
            rflightTimeHours = rflightTime / 100;
            rflightTimeMinutes = rflightTime % 100;
            String rflightTimeStr = String.format("%2dh %02dm", rflightTimeHours, rflightTimeMinutes);
            out.println(rflightTimeStr);
            returnFlightNumber = returnFlights.get(i).split(",")[5];
            out.println("Flight Number: " + returnFlights.get(i).split(",")[5] + "\n" + " Departure Time: " + returnDepartureTimeStr + "\n" + " Arrival Time: " + returnArrivalTimeStr + "\n" + "Flight Time: " + rflightTimeStr + " Departure Date: " + rdepartureMonth + "/" + rdepartureDay + "/" + rdepartureYear);
            returnFlightDetails.add("Flight Number: " + returnFlights.get(i).split(",")[5] + " Departure Time: " + returnDepartureTimeStr + " Arrival Time: " + returnArrivalTimeStr + " Flight Time: " + rflightTimeStr + " DepartureDate: " + rdepartureMonth + "/" + rdepartureDay + "/" + rdepartureYear);
            out.println(returnFlightDetails);
        }
        searchReturnResultsPanel.add(book1);

        String[] returnFlightList = new String[returnFlightDetails.size()];
        returnFlightList = returnFlightDetails.toArray(returnFlightList);

        for (int i = 0; i < returnFlightDetails.size(); i++) {
            JLabel label = new JLabel(returnFlightList[i]);
        }
        returnList1 = new JList<>(returnFlightList);
        returnList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        returnList1.setLayoutOrientation(JList.VERTICAL);
        //returnList1.setVisibleRowCount(-1);
        JScrollPane rlistScroller = new JScrollPane(returnList1);
        rlistScroller.setPreferredSize(new Dimension(800, 200));
        searchReturnResultsPanel.add(rlistScroller);

        searchResultPanel.add(searchReturnResultsPanel);

        frame.add(searchResultPanel);
        searchReturnResultsPanel.add(book1);
        frame.setVisible(true);
    }

    public void createButtonPanel() {
        buttonPanel = new JPanel(new FlowLayout());

        exitButton = new JButton("Exit");
        searchButton = new JButton("Search Flights");
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
        String[] seatNum = {"A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4"};

        cityDepartureLabel = new JLabel("Departure City:");
        cityArrivalLabel = new JLabel("Destination City:");
        departureDateLabel = new JLabel("Departure Date:");
        returnDateLabel = new JLabel("Return Date:");
        seatSelectLabel = new JLabel("Which seat would you like?");

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
        textPanel.add(seatSelectLabel);
        textPanel.add(seatSelectCBox);

    }

    public void fillAirportCBoxFromTxtFile() {
        String filePath = "ARS\\src\\airports.csv";

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
        frame.setSize(900,800);
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