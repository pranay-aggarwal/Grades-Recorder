package ui.gui;

import model.GradedComponent;
import model.Subject;
import model.Term;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddGcGUI extends StartApplication {
    Term term;
    String subName;

    public AddGcGUI() throws IOException {
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
            addSubWindow();
        } else {
            JOptionPane.showMessageDialog(frame, "Term not found", "Failure",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void addSubWindow() {
        JFrame frame = new JFrame("Check existing Subject");
        JLabel nameLabel = new JLabel("Name of Subject:");
        JTextField nameField = new JTextField();
        makeEditableSub(nameField);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSub(nameField, frame);
            }
        });
        makePanelSub(nameLabel, nameField, submitButton, frame);
    }

    public void makeEditableSub(JTextField name) {
        name.setEditable(true);
    }


    private void handleSub(JTextField nameField, JFrame frame) {
        subName = nameField.getText();

        for (Term t : termList) {
            for (Subject s : t.getSubjects()) {
                if (s.getSubName().equals(subName)) {
                    addGCWindow();
                }
            }
        }
//        JOptionPane.showMessageDialog(frame, "Subject not found", "Failure",
//                    JOptionPane.INFORMATION_MESSAGE);
    }

    private void addGCWindow() {
        JFrame frame = new JFrame("Add Graded Component");
        JLabel nameLabel = new JLabel("Name of Graded Component:");
        JTextField nameField = new JTextField();
        JLabel marksLabel = new JLabel("Marks received in this GC: ");
        JTextField marksField = new JTextField();
        JLabel weightageLabel = new JLabel("Weightage of this GC: ");
        JTextField weightageField = new JTextField();
        makeEditableGC(nameField, marksField, weightageField);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleGC(nameField, marksField, weightageField, frame);
            }
        });
        makePanelGC(nameLabel, nameField, marksLabel, marksField, weightageLabel, weightageField, submitButton, frame);
    }

    private void makeEditableGC(JTextField field1, JTextField field2, JTextField field3) {
        field1.setEditable(true);
        field2.setEditable(true);
        field3.setEditable(true);

    }


    private void handleGC(JTextField nameField, JTextField marksField, JTextField weightField, JFrame frame) {
        String name = nameField.getText();
        double marks = Double.parseDouble(marksField.getText());
        double weightage = Double.parseDouble(weightField.getText());

        GradedComponent gradedComponent = new GradedComponent(name,marks,weightage);

        for (Term t : termList) {
            for (Subject s : t.getSubjects()) {
                if (s.getSubName().equals(subName)) {
                    s.addGradedComp(gradedComponent);
                }
            }
        }
        nameField.setText("");
        marksField.setText("");
        weightField.setText("");
        addGCSuccessMessage(frame);

    }


    private void addGCSuccessMessage(JFrame frame) {
        JOptionPane.showMessageDialog(frame, "Graded Component added successfully!", "Success",
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

    private void makePanelSub(JLabel nameLabel, JTextField nameField,
                              JButton submitButton,
                              JFrame frame) {
        JPanel panel = new JPanel(new GridLayout(2,2));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(submitButton);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void makePanelGC(JLabel nameLabel, JTextField nameField,
                           JLabel marksLabel, JTextField marksField,
                           JLabel weightLabel, JTextField weightField,
                           JButton submitButton,
                           JFrame frame) {

        JPanel panel = new JPanel(new GridLayout(4,2));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(marksLabel);
        panel.add(marksField);
        panel.add(weightLabel);
        panel.add(weightField);
        panel.add(submitButton);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
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

