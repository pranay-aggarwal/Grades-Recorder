package ui.gui;

import java.io.IOException;


/**
 * Contains the main method responsible for starting the application.
 * The application initializes and launches the graphical user interface
 */
public class Main {

    // EFFECTS: Runs the graphical user interface of the app
    public static void main(String[] args) {
        try {
            new StartApplication();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
