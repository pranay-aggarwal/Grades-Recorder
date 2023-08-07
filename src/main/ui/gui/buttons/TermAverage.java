package ui.gui.buttons;

import model.Term;
import ui.gui.StartApplication;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * The TermAverage class represents the graphical user interface (GUI) for displaying the average marks
 * for each term in the Transcript Record application.
 * It extends the StartApplication class.
 * The class calculates the average marks for each term and generates a message with the results.
 */

public class TermAverage extends StartApplication {
    String message = "";

    // EFFECTS: calculates the average marks for each term and generates a message with the results
    public TermAverage() throws IOException {
        ImageIcon icon = new ImageIcon("images/gpa.jpeg");
        Image scaledImage = icon.getImage().getScaledInstance(130, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        for (Term t : termList) {
            double result = t.averageSub();
            int year = t.getYear();
            int termNum = t.getTermNum();
            message += "For year: " + year + " Term: " + termNum + "\n" + "Your average is: " + result + "\n" + "\n";
        }

        JFrame frame = new JFrame("Transcript Averages");
        JOptionPane.showMessageDialog(frame, message, "Averages", JOptionPane.PLAIN_MESSAGE, scaledIcon);
    }

}
