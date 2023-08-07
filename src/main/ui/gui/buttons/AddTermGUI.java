package ui.gui.buttons;

import model.Term;
import ui.gui.StartApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


/**
 * The AddTermGUI class represents the graphical user interface (GUI) for adding a new term to the
 * Transcript Record application.
 * It extends the StartApplication class.
 * When the user submits the data, the class handles the term creation, adds it to the term list,
 * and displays a success message.
 * It also provides an option to save the data to a file upon successful addition.
 */

public class AddTermGUI extends StartApplication {

    //EFFECTS : Creates a new popup window to input the term details
    public AddTermGUI() throws IOException {
        super();
        JFrame frame = new JFrame("Add Term");
        JLabel yearLabel = new JLabel("Term Year:");
        JTextField yearField = new JTextField();
        JLabel termNumLabel = new JLabel("Term Num:");
        JTextField termNumField = new JTextField();
        makeEditable(yearField, termNumField);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleTerm(yearField, termNumField, frame);
            }
        });
        makePanel(yearLabel, yearField, termNumLabel, termNumField,submitButton, frame);
    }

    // MODIFIES: this
    // EFFECTS : makes the fields editable.
    private void makeEditable(JTextField yearField, JTextField termNumField) {
        yearField.setEditable(true);
        termNumField.setEditable(true);
    }

    // REQUIRES: all the JTextFields and JFrame
    // MODIFIES: this
    // EFFECTS: handles the term fields and creates a new term
    private void handleTerm(JTextField yearField, JTextField termNumField, JFrame frame) {
        int year = Integer.parseInt(yearField.getText());
        int termNum = Integer.parseInt(termNumField.getText());
        Term term = new Term(year,termNum);
        termList.add(term);
        yearField.setText("");
        termNumField.setText("");
        addTermSuccessMessage(frame);
    }

    // MODIFIES: this
    // EFFECTS: displays the appropriate message to the user after saving the data
    private void addTermSuccessMessage(JFrame frame) {
        JOptionPane.showMessageDialog(frame, "Term added successfully!", "Success",
                JOptionPane.INFORMATION_MESSAGE);
        int option = JOptionPane.showConfirmDialog(frame, "Do you want to save the data?", "Save data",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try {
                termFile.writeTermToFile(termList);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(frame, "Data saved successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS : makes the panel for the popup window
    private void makePanel(JLabel yearLabel, JTextField yearField,
                           JLabel termNumLabel, JTextField termNumField,
                           JButton submitButton,
                           JFrame frame) {
        JPanel panel = new JPanel(new GridLayout(3,2));
        panel.add(yearLabel);
        panel.add(yearField);
        panel.add(termNumLabel);
        panel.add(termNumField);
        panel.add(submitButton);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }


}