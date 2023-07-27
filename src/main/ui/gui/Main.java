package ui.gui;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            StartApplication startApplication = new StartApplication();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
