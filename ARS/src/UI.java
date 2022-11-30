import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

public class UI extends JFrame implements ActionListener {
    private JFrame frame;

    private JButton exitButton;
    private JButton searchButton;
    private JButton resetButton;
    private JButton cancelButton;
    private JButton confirmButton;

    //private JButton s1;

    private JPanel buttonPanel;
    private JPanel reservationPanel;
    private JPanel textPanel;
    private JPanel searchPanel;
    private JPanel availableFlightsPanel;
    private JPanel searchDepartResultsPanel;
    private JPanel searchReturnResultsPanel;
    private JPanel searchResultPanel;
    private JPanel searchResultsButtonPanel;
    private JPanel flightConfirmationPanel;
    private JPanel flightConfirmationButtonPanel;
    private JPanel confirmationPanel;

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
    private ArrayList<String> departFlights = new ArrayList<String>();
    private ArrayList<String> departFlightDetails = new ArrayList<String>();
    private ArrayList<String> departFlight = new ArrayList<>();
    private ArrayList<String> returnFlight = new ArrayList<>();
    private ArrayList<String> returnFlightDetails = new ArrayList<String>();
    private ArrayList<String> returnFlights = new ArrayList<String>();
    private ArrayList<Reservation> reservationList = new ArrayList<>();
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
    private String departureTimeStr;
    private String arrivalTimeStr;
    private String  rdepartureTimeStr;
    private String  rarrivalTimeStr;
    private String departFlightNumber;
    private String returnFlightNumber;
    private String departDetails;
    JList<String> departList1;
    JScrollPane listScroller;

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
        chosenDepartureAirport = ((cityDepartureCBox.getSelectedItem().toString()).split(",")[0]);
        System.out.println(chosenDepartureAirport);
        chosenArrivalAirport = ((cityArrivalCBox.getSelectedItem().toString()).split(",")[0]);
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

    public void searchReturnFlights() {
        String schedulePath = "C:\\Users\\iangr\\IdeaProjects\\CompSci240-FinalGroupProject\\ARS\\src\\schedule.txt";

        System.out.println(chosenReturnDate);
        chosenNumPassenger = (String) numPassengerCBox.getSelectedItem();

        String searchString1 = (chosenReturnDate);
        String searchString2 = (chosenArrivalAirport + "," + chosenDepartureAirport);
        try {
            File file = new File(schedulePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(searchString1) && line.contains(searchString2)) {
                    System.out.println(line);
                    returnFlights.add(line);

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
            case "Search Flights":
                searchDepartFlights();
                searchReturnFlights();
                reservationPanel.setVisible(false);
                textPanel.setVisible(false);
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
                //searchPanel.setVisible(false);
                reservationPanel.setVisible(true);
                buttonPanel.setVisible(true);
                textPanel.setVisible(true);
                break;
            case "Exit":
                System.out.println("Exit button pressed");
                System.exit(0);
                break;
            case " Back to Search":
                break;
            case "Book Flight":
                System.out.println("Flight Booked");
                searchResultPanel.setVisible(false);
                createFlightConfirmationPanel();
                createFlightConfirmationButtonPanel();
                flightConfirmationPanel.setVisible(true);
                flightConfirmationPanel.repaint();
                flightConfirmationPanel.revalidate();
                flightConfirmationButtonPanel.setVisible(true);
                flightConfirmationButtonPanel.repaint();
                flightConfirmationButtonPanel.revalidate();

                Reservation reservation = new Reservation();
                reservation.setDepartureFlight(departFlight.get(0));
                reservation.setReturnFlight(returnFlight.get(0));
                reservation.setNumPassengers(chosenNumPassenger);
                reservation.setSeatType(seatSelectCBox.getSelectedItem().toString());
                reservation.setDepartureDate(departureDateTextField.getText());
                reservation.setReturnDate(returnDateTextField.getText());
                reservation.setDepartureAirport(chosenDepartureAirport);
                reservation.setArrivalAirport(chosenArrivalAirport);
                reservation.setDepartureTime(departFlight.get(1));
                reservation.setArrivalTime(departFlight.get(2));
                reservation.setReturnDepartureTime(returnFlight.get(1), returnDateTextField);
                reservation.setReturnArrivalTime(returnFlight.get(2), returnDateTextField);
                reservation.setDepartureFlightNumber(departFlight.get(0));
                reservation.setReturnFlightNumber(returnFlight.get(0));
                reservation.getDepartureFlightNumber();
                System.out.println(reservation.getDepartureFlight());
                System.out.println(reservation.getReturnFlight());
                System.out.println(reservation.getNumPassengers());
                System.out.println(reservation.getSeatType());
                System.out.println(reservation.getDepartureDate());
                System.out.println(reservation.getReturnDate());
                reservationList.add(reservation);
                break;

            case "Back to Search Results":
                flightConfirmationPanel.setVisible(false);
                flightConfirmationButtonPanel.setVisible(false);
                searchResultPanel.setVisible(true);
                searchResultPanel.repaint();
                searchResultPanel.revalidate();
                break;

            case "Confirm":
                System.out.println("Flight Confirmed");
                flightConfirmationPanel.setVisible(false);
                createFlightConfirmationPanel();
                flightConfirmationPanel.setVisible(true);
                flightConfirmationPanel.repaint();
                flightConfirmationPanel.revalidate();
                flightConfirmationButtonPanel.setVisible(true);
                break;
            case "Continue":
                break;
        }


    }

    public void createFlightConfirmationPanel() {
        flightConfirmationPanel = new JPanel();
        flightConfirmationPanel.setLayout(new BoxLayout(flightConfirmationPanel, BoxLayout.Y_AXIS));
        flightConfirmationPanel.setBackground(Color.WHITE);
        departList1.getListSelectionListeners();
        System.out.println(departList1.getSelectedValue());
      //  departDetails = departDetails.substring(1, departDetails.length() - 1);
        departFlightNumber = Arrays.toString(departList1.getSelectedValue().split(",")[1].split(" "));;
        System.out.println(departDetails);
        System.out.println(departFlightNumber);
        flightConfirmationPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        flightConfirmationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        flightConfirmationPanel.add(new JLabel("Flight Confirmation"));

        flightConfirmationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        flightConfirmationPanel.add(new JLabel("Flight Number: " + departFlightNumber));
        flightConfirmationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        flightConfirmationPanel.add(new JLabel("Departure Date: " + chosenDepartureDate));
        flightConfirmationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        flightConfirmationPanel.add(new JLabel("Departure Time: " + departureTimeStr));
        flightConfirmationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        flightConfirmationPanel.add(new JLabel("Arrival Time: " + arrivalTimeStr));
        flightConfirmationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        flightConfirmationPanel.add(new JLabel("Arrival Date: " + chosenReturnDate));
        flightConfirmationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        flightConfirmationPanel.add(new JLabel("Departure Airport: " + chosenDepartureAirport));
        flightConfirmationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        flightConfirmationPanel.add(new JLabel("Arrival Airport: " + chosenArrivalAirport));
        flightConfirmationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        flightConfirmationPanel.add(new JLabel("Number of Passengers: " + chosenNumPassenger));
        flightConfirmationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
       // flightConfirmationPanel.add(new JLabel("Seat Type: " + chosenSeatType));
        //flightConfirmationPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        flightConfirmationPanel.setVisible(true);

    }

    public void createFlightConfirmationButtonPanel() {
        flightConfirmationButtonPanel = new JPanel();
        flightConfirmationButtonPanel.setLayout(new BoxLayout(flightConfirmationButtonPanel, BoxLayout.X_AXIS));
        flightConfirmationButtonPanel.setBackground(Color.WHITE);
        flightConfirmationButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(this);
        confirmButton.setActionCommand("Confirm");
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        cancelButton.setActionCommand("Cancel");
        flightConfirmationButtonPanel.add(confirmButton);
        flightConfirmationButtonPanel.add(cancelButton);
        add(flightConfirmationButtonPanel);
    }

    public void createConfirmationScreen() {
        confirmationPanel = new JPanel();
        confirmationPanel.setLayout(new GridLayout(2, 1));
        confirmationPanel.setBackground(Color.WHITE);
        confirmationPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel confirmationTextPanel = new JPanel();
        confirmationTextPanel.setLayout(new GridLayout(2, 1));
        confirmationTextPanel.setBackground(Color.WHITE);
        confirmationTextPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel confirmationLabel = new JLabel("Thank you for booking with us!");
        confirmationLabel.setFont(new Font("Arial", Font.BOLD, 20));
        confirmationLabel.setForeground(Color.BLACK);
        confirmationLabel.setHorizontalAlignment(JLabel.CENTER);
        confirmationLabel.setVerticalAlignment(JLabel.CENTER);
        confirmationTextPanel.add(confirmationLabel);

        JLabel confirmationLabel2 = new JLabel("Your reservation number is: ");
        confirmationLabel2.setFont(new Font("Arial", Font.BOLD, 20));
        confirmationLabel2.setForeground(Color.BLACK);
        confirmationLabel2.setHorizontalAlignment(JLabel.CENTER);
        confirmationLabel2.setVerticalAlignment(JLabel.CENTER);
        confirmationTextPanel.add(confirmationLabel2);

        JPanel confirmationButtonPanel = new JPanel();
        confirmationButtonPanel.setLayout(new GridLayout(1, 1));
        confirmationButtonPanel.setBackground(Color.WHITE);
        confirmationButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.setForeground(Color.BLACK);
        exitButton.setBackground(Color.WHITE);
        exitButton.addActionListener(this);
        exitButton.setActionCommand("Exit");
        confirmationButtonPanel.add(exitButton);

        confirmationPanel.add(confirmationTextPanel);
        confirmationPanel.add(confirmationButtonPanel);
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

        JButton book = new JButton("Book Flight");
        book.addActionListener(this);
        book.setActionCommand("Book Flight");

        int departureTime = 0;
        int arrivalTime = 0;
        int flightTime = 0;
        int flightTimeHours = 0;
        int flightTimeMinutes = 0;

        for (int i = 0; i < departFlights.size(); i++) {
            departureTime = Integer.parseInt(departFlights.get(i).split(",")[8]);
            arrivalTime = Integer.parseInt(departFlights.get(i).split(",")[10]);
            int departureDay = Integer.parseInt(departFlights.get(i).split(",")[2]);
            int departureMonth = Integer.parseInt(departFlights.get(i).split(",")[1]);
            int departureYear = Integer.parseInt(departFlights.get(i).split(",")[0]);
            System.out.println(departureDay + " " + departureMonth + " " + departureYear);
            String departureTimeStr = String.format("%2d:%02d", departureTime / 100, departureTime % 100);
            System.out.println(departureTimeStr);
            String arrivalTimeStr = String.format("%2d:%02d", arrivalTime / 100, arrivalTime % 100);
            System.out.println(arrivalTimeStr);
            flightTime = arrivalTime - departureTime;
            flightTimeHours = flightTime / 100;
            flightTimeMinutes = flightTime % 100;
            String flightTimeStr = String.format("%2dh %02dm", flightTimeHours, flightTimeMinutes);
            System.out.println(flightTimeStr);
            departFlightNumber = departFlights.get(i).split(",")[5];
            System.out.println("Flight Number: " + departFlights.get(i).split(",")[5] + "," + "\n" + " Departure Time: " + departureTimeStr+ "," + "\n" + " Arrival Time: " + arrivalTimeStr + "," + "\n" + "Flight Time: " + flightTimeStr + "," + " Departure Date: " + departureMonth + "/" + departureDay + "/" + departureYear+ ",");
            departFlightDetails.add("Flight Number: " + departFlights.get(i).split(",")[5] + "," + " Departure Time: " + departureTimeStr + "," + " Arrival Time: " + arrivalTimeStr + "," + " Flight Time: " + flightTimeStr + "," + " DepartureDate: " + departureMonth + "/" + departureDay + "/" + departureYear+ ",");
            departFlight.add(departFlights.get(i).split(",")[5] + "," + departureTimeStr + "," + arrivalTimeStr + "," +  flightTimeStr + "," + departureMonth + "/" + departureDay + "/" + departureYear+ ",");
            System.out.println(departFlightDetails);
        }
        searchDepartResultsPanel.add(book);
        book.addActionListener(this);

        String[] departFlightList = new String[departFlightDetails.size()];
        departFlightList = departFlightDetails.toArray(departFlightList);

        for (int i = 0; i < departFlightDetails.size(); i++) {
            JLabel label = new JLabel(departFlightList[i]);
        }
        departList1 = new JList<>(departFlightList);
        departList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        departList1.setLayoutOrientation(JList.VERTICAL);
        departList1.setVisibleRowCount(-1);
        listScroller = new JScrollPane(departList1);
        listScroller.setPreferredSize(new Dimension(800, 200));
        searchDepartResultsPanel.add(listScroller);
        searchDepartResultsPanel.add(book);
        book.addActionListener(this);
        book.setActionCommand("Book Flight");
        searchResultPanel.add(searchDepartResultsPanel);
        searchResultPanel.add(searchReturnResultsPanel);
        frame.add(searchResultPanel);
        frame.setVisible(true);
    }


        public void createReturnPanel () {

            int rdepartureTime = 0;
            int rarrivalTime = 0;
            int rflightTime = 0;
            int rflightTimeHours = 0;
            int rflightTimeMinutes = 0;

            JButton book1 = new JButton("Book Flight");
            book1.addActionListener(this);
            book1.setActionCommand("Book Flight");

            for (int i = 0; i < returnFlights.size(); i++) {
                rdepartureTime = Integer.parseInt(returnFlights.get(i).split(",")[8]);
                rarrivalTime = Integer.parseInt(returnFlights.get(i).split(",")[10]);
                int rdepartureDay = Integer.parseInt(returnFlights.get(i).split(",")[2]);
                int rdepartureMonth = Integer.parseInt(returnFlights.get(i).split(",")[1]);
                int rdepartureYear = Integer.parseInt(returnFlights.get(i).split(",")[0]);
                System.out.println(rdepartureDay + " " + rdepartureMonth + " " + rdepartureYear);
                String rdepartureTimeStr = String.format("%2d:%02d", rdepartureTime / 100, rdepartureTime % 100);
                System.out.println(rdepartureTimeStr);
                String rarrivalTimeStr = String.format("%2d:%02d", rarrivalTime / 100, rarrivalTime % 100);
                System.out.println(rarrivalTimeStr);
                rflightTime = rarrivalTime - rdepartureTime;
                rflightTimeHours = rflightTime / 100;
                rflightTimeMinutes = rflightTime % 100;
                String rflightTimeStr = String.format("%2dh %02dm", rflightTimeHours, rflightTimeMinutes);
                System.out.println(rflightTimeStr);
                returnFlightNumber = returnFlights.get(i).split(",")[5];
                System.out.println("Flight Number: " + returnFlights.get(i).split(",")[5] + "\n" + " Departure Time: " + rdepartureTimeStr + "\n" + " Arrival Time: " + rarrivalTimeStr + "\n" + "Flight Time: " + rflightTimeStr + " Departure Date: " + rdepartureMonth + "/" + rdepartureDay + "/" + rdepartureYear);
                returnFlightDetails.add("Flight Number: " + returnFlights.get(i).split(",")[5] + " Departure Time: " + rdepartureTimeStr + " Arrival Time: " + rarrivalTimeStr + " Flight Time: " + rflightTimeStr + " DepartureDate: " + rdepartureMonth + "/" + rdepartureDay + "/" + rdepartureYear);
                returnFlight.add(departFlights.get(i).split(",")[5] + "," + rdepartureTimeStr + "," + rarrivalTimeStr + "," +  rflightTimeStr + "," + rdepartureMonth + "/" + rdepartureDay + "/" + rdepartureYear+ ",");
                System.out.println(returnFlightDetails);
            }
            searchReturnResultsPanel.add(book1);
            book1.addActionListener(this);

            String[] returnFlightList = new String[returnFlightDetails.size()];
            returnFlightList = returnFlightDetails.toArray(returnFlightList);

            for (int i = 0; i < returnFlightDetails.size(); i++) {
                JLabel label = new JLabel(returnFlightList[i]);
            }
            JList<String> returnList1 = new JList<>(returnFlightList);
            returnList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            returnList1.setLayoutOrientation(JList.VERTICAL);
            returnList1.setVisibleRowCount(-1);
            JScrollPane rlistScroller = new JScrollPane(returnList1);
            rlistScroller.setPreferredSize(new Dimension(800, 200));
            searchReturnResultsPanel.add(rlistScroller);
            searchReturnResultsPanel.add(book1);
            book1.addActionListener(this);
            book1.setActionCommand("Book Flights");
            searchResultPanel.add(searchReturnResultsPanel);
            searchResultPanel.add(searchReturnResultsPanel);
            frame.add(searchResultPanel);
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
