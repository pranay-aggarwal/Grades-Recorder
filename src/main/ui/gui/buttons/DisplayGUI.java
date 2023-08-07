package ui.gui.buttons;

import model.Term;
import ui.gui.StartApplication;

import javax.swing.*;
import java.io.IOException;

/**
 * The DisplayGUI class represents the graphical user interface (GUI) for displaying the transcript of all terms
 * in the Transcript Record application. It extends the StartApplication class to access term data and GUI components.
 * The class generates a string representation of all terms and their associated subjects and graded components and
 * displays it.
 * */
public class DisplayGUI extends StartApplication {


    // EFFECTS: Displays the result of the transcript data in a pop-up message box
    public DisplayGUI() throws IOException {
        ImageIcon icon = new ImageIcon("images/img1.jpg");

        String result = "";
        for (Term term : termList) {
            result += term.toString();
        }

        JFrame frame = new JFrame("Your Transcript");

        JOptionPane.showMessageDialog(frame, result,
                "Your Transcript",
                JOptionPane.PLAIN_MESSAGE,icon);





    }
}

