import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Reservation extends JFrame implements ActionListener {
    JLabel l1,l2,l3,l4,l5;
    JTextField tf1,tf2,tf3,tf4,tf5;
    JComboBox c1;
    JButton jbut1, jbut2;
    JLabel result;
    private String filename;
    private static File airportFile;
    private static File scheduleFile;
    private static Scanner scanner;
    private static HashMap<String,String> airports;
    private static ArrayList<Schedule> schedule;
    private static Date date;
    String currentPattern;

    public static void loadAirports() {
        airports = new HashMap();
        String line;
        String[] details;

        try {
            scanner = new Scanner(airportFile);
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                details = line.split(",");
                airports.put(details[0], line.substring(line.indexOf(',')+1));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Reservation() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        String line;
        airports = new HashMap();
        try {
            scanner = new Scanner(airportFile);
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        JComboBox airportList = new JComboBox((ComboBoxModel) airports);
        //Set up the UI for selecting a pattern.
        JLabel patternLabel1 = new JLabel("Enter the pattern string or");
        JLabel patternLabel2 = new JLabel("select one from the list:");


        airportList.setEditable(true);
        airportList.addActionListener(this);

        //Create the UI for displaying result.
        JLabel resultLabel = new JLabel("Current Date/Time",
                JLabel.LEADING); //== LEFT
        result = new JLabel(" ");
        result.setForeground(Color.black);
        result.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.black),
                BorderFactory.createEmptyBorder(5,5,5,5)
        ));

        //Lay out everything.
        JPanel patternPanel = new JPanel();
        patternPanel.setLayout(new BoxLayout(patternPanel,
                BoxLayout.PAGE_AXIS));
        patternPanel.add(patternLabel1);
        patternPanel.add(patternLabel2);
        airportList.setAlignmentX(Component.LEFT_ALIGNMENT);
        patternPanel.add(airportList);

        JPanel resultPanel = new JPanel(new GridLayout(0, 1));
        resultPanel.add(resultLabel);
        resultPanel.add(result);

        patternPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        resultPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(patternPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(resultPanel);


    }

    public void reformat() {
        Date today = new Date();
        SimpleDateFormat formatter =
                new SimpleDateFormat(currentPattern);
        try {
            String dateString = formatter.format(today);
            result.setForeground(Color.black);
            result.setText(dateString);
        } catch (IllegalArgumentException iae) {
            result.setForeground(Color.red);
            result.setText("Error: " + iae.getMessage());
        }
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Reservation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new Reservation();
      //  newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


    public static void loadSchedule() {
        String[] line;
        schedule = new ArrayList<Schedule>();

        try {
            scanner = new Scanner(scheduleFile);
            while (scanner.hasNextLine()) {
                line = scanner.nextLine().split(",");
                LocalDate date = LocalDate.of(Integer.parseInt(line[0]),
                        Integer.parseInt(line[1]),
                        Integer.parseInt(line[2]));
                schedule.add(new Schedule(date, line[4], Integer.parseInt(line[5]), line[6],
                        line[7], Integer.parseInt(line[8]),
                        Integer.parseInt(line[9]),
                        Integer.parseInt(line[10])));
                for (Schedule x : schedule) {
                    x.print();
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String newSelection = (String)cb.getSelectedItem();
        currentPattern = newSelection;
    }
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
