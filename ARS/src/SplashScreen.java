import java.awt.Image;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * Write a description of class SplashScreen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SplashScreen extends Thread {
    private static JProgressBar progressBar = new JProgressBar();
    private JLabel image = new JLabel(new ImageIcon("C:\\Users\\iangr\\IdeaProjects\\CompSci240-FinalGroupProject\\ARS\\src\\images.jpeg"));
    private JFrame frame;
    private JLabel message = new JLabel();

    public SplashScreen() {
        JPanel panel = new JPanel();
        panel.setBorder(new javax.swing.border.EtchedBorder());
        image.setSize(600,200);
        frame = new JFrame();
        frame.setSize(600,200);
        frame.add(image);
        frame.setVisible(true);
        progressBar.setBounds(250,320,200,40);
        progressBar.setBorderPainted(true);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(Color.BLACK);
        progressBar.setValue(0);
        frame.add(progressBar);
        frame.add(message);
    }

    public void run() {
        int i = 0;

        while (i <= 100) {
            try {
                Thread.sleep(120);
                progressBar.setValue(i);
                message.setText("LOADING " + Integer.toString(i) + "%");
                i++;
                if (i == 100) {
                    frame.dispose();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}