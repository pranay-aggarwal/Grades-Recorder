package ui.gui;

import javax.swing.*;
import java.io.IOException;

/**
 * Creates the GUI for the load data button which reads the data from a term file and stores it in the term list
 */
public class LoadData extends StartApplication {

    // MODIFIES: StartApplication
    // EFFECTS : reads the data from the path and stores it in termFile
    public LoadData() throws IOException {
        termList = termFile.readTermFromFile();
        JFrame frame = new JFrame("Data Loaded");
        JOptionPane.showMessageDialog(frame, "Data Loaded successfully",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

         }
}
