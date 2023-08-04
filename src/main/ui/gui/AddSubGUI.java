package ui.gui;

import model.Subject;
import model.Term;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddSubGUI extends StartApplication {
    Term term;

    public AddSubGUI() throws IOException {
        super();
        JFrame frame = new JFrame("Check Term");
        JLabel yearLabel = new JLabel("Enter Term Year:");
        JTextField yearField = new JTextField();
        JLabel termNumLabel = new JLabel("Enter Term Num:");
        JTextField termNumField = new JTextField();
        makeEditable(yearField, termNumField);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleTerm(yearField, termNumField, frame);
            }
        });
        makePanel(yearLabel, yearField, termNumLabel, termNumField, submitButton, frame);


    }

    private void makeEditable(JTextField field1, JTextField field2) {
        field1.setEditable(true);
        field2.setEditable(true);
    }

    private void handleTerm(JTextField yearField, JTextField termNumField, JFrame frame) {
        int year = Integer.parseInt(yearField.getText());
        int termNum = Integer.parseInt(termNumField.getText());
        term = new Term(year, termNum);
        if (termList.contains(term)) {
            addWindow();
        } else {
            JOptionPane.showMessageDialog(frame, "Term not found", "Failure",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void addWindow() {
        JFrame frame = new JFrame("Add Subject");
        JLabel nameLabel = new JLabel("Name of Subject:");
        JTextField nameField = new JTextField();
        JLabel marksLabel = new JLabel("Expected marks in this subject: ");
        JTextField marksField = new JTextField();
        makeEditable(nameField, marksField);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSub(nameField, marksField, frame);
            }
        });
        makePanel(nameLabel, nameField, marksLabel, marksField, submitButton, frame);
    }




    private void handleSub(JTextField nameField, JTextField marksField, JFrame frame) {
        String name = nameField.getText();
        int marks = Integer.parseInt(marksField.getText());

        Subject subject = new Subject(name,marks);
        for (Term t : termList) {
            if (t.equals(term)) {
                t.addSubject(subject);
            }
        }

        nameField.setText("");
        marksField.setText("");
        addSubSuccessMessage(frame);
    }




    private void addSubSuccessMessage(JFrame frame) {
        JOptionPane.showMessageDialog(frame, "Subject added successfully!", "Success",
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



    private void makePanel(JLabel nameLabel, JTextField nameField,
                           JLabel marksLabel, JTextField marksField,
                           JButton submitButton,
                           JFrame frame) {

        JPanel panel = new JPanel(new GridLayout(3,2));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(marksLabel);
        panel.add(marksField);
        panel.add(submitButton);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

}
