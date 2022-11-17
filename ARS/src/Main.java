import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static File airportFile;
    private static File scheduleFile;
    private static Scanner scanner;
    private static HashMap<String,String> airports;
    private static ArrayList<Schedule> schedule;
    private static Date date;

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
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        airportFile = new File("C:\\Users\\iangr\\CompSci240-FinalGroupProject\\ARS\\src\\airports.csv");
        scheduleFile = new File("C:\\Users\\iangr\\CompSci240-FinalGroupProject\\ARS\\src\\schedule.txt");

        loadAirports();
        loadSchedule();

        UI gui = new UI();
        gui.createAndShowUI(airports, schedule);
    }
}