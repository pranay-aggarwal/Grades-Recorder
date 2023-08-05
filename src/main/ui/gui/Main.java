package ui.gui;

import model.Term;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Contains the main method responsible for starting the application.
 * The application initializes and launches the graphical user interface
 */
public class Main {

    // EFFECTS: Runs the graphical user interface of the app
    public static void main(String[] args) {
        try {
            StartApplication startApplication = new StartApplication();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
