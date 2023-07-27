package ui.gui;

import model.Term;

import javax.swing.*;
import java.io.IOException;


public class DisplayGUI extends StartApplication {

    public DisplayGUI() throws IOException {
        String result = "";
        for (Term term : termList) {
            result += term.toString();
        }

        JFrame frame = new JFrame("Your Transcript");
        JOptionPane.showMessageDialog(frame, result,
                "Your Transcript",
                JOptionPane.PLAIN_MESSAGE);





    }
}

