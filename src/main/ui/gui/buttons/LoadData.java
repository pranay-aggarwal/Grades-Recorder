package ui.gui.buttons;

import ui.gui.StartApplication;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Creates the GUI for the load data button which reads the data from a term file and stores it in the term list
 */
public class LoadData extends StartApplication {

    // MODIFIES: StartApplication
    // EFFECTS : reads the data from the path and stores it in termFile
    public LoadData() throws IOException {
        ImageIcon icon = new ImageIcon("images/succ.jpg");
        Image scaledImage = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        termList = termFile.readTermFromFile();
        JFrame frame = new JFrame("Data Loaded");
        JOptionPane.showMessageDialog(frame, "Data Loaded successfully",
                "Success",
                JOptionPane.INFORMATION_MESSAGE, scaledIcon);

    }
}
