package ui.gui;

import model.Term;
import persistence.TermFileHandler;
import ui.gui.buttons.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * The StartApplication class represents the main graphical user interface (GUI) for the Transcript Record application.
 * It extends JFrame to create the application window and contains various buttons for user interaction.
 * Contains various features such as add user data, save and load data, display data and calculate the Term average
 */
public class StartApplication extends JFrame {
    private JButton loadTerm;
    private JButton addTerm;
    private JButton addSub;
    private JButton addGC;
    private JButton displayTranscript;
    private JButton termAverage;
    protected final int dimX = 1920; // represents the width of the JFrame
    protected final int dimY = 1080; // represents the height of the JFrame
    private final int buttonHeight = 80;




    protected final String termPath = "./data/data.json";
    protected TermFileHandler termFile = new TermFileHandler(termPath);

    protected static List<Term> termList = new ArrayList<>();

    // EFFECTS: Creates the panel of the app
    public StartApplication() throws IOException {
        this.setTitle("Transcript Record");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.getContentPane().setBackground(Color.WHITE);
        this.setVisible(true);
        this.setResizable(true);
        this.setSize(dimX, dimY);
        JPanel panel = addPanel();
        panel.setBackground(Color.decode("#00003f"));


        this.add(panel);

    }



    // MODIFIES: this
    // EFFECTS: creates a background for the panel
    private JPanel createBackgroundPanel() {
        JPanel res = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the background image
                try {
                    BufferedImage image = ImageIO.read(new File("images/bg.jpg"));
                    // Draw the background image
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        return res;
    }


    // MODIFIES: this
    // EFFECTS: Adds relevant buttons and labels on the panel
    private void addComponentsToPanel(JPanel panel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx++;
        panel.add(addWelcomeLabel(), gbc);
        gbc.gridy++;
        panel.add(loadDataButton(), gbc);
        gbc.gridy++;
        panel.add(addTermButton(), gbc);
        gbc.gridy++;
        panel.add(addSubButton(), gbc);
        gbc.gridy++;
        panel.add(addGCButton(), gbc);
        gbc.gridy++;
        panel.add(termAverage(), gbc);
        gbc.gridy++;
        panel.add(displayButton(), gbc);
    }


    // MODIFIES: this
    // EFFECTS: creates a panel
    private JPanel addPanel() {
        JPanel panel = createBackgroundPanel();
        addComponentsToPanel(panel);
        return panel;
    }




    // MODIFIES: this
    // EFFECTS : Adds a welcome label on the panel
    private JLabel addWelcomeLabel() {
        JLabel welcomeLabel = new JLabel("Welcome To Your Transcript Record!!!");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBounds(0, 50, 250, 50);
        welcomeLabel.setFont(new Font("Arial",Font.BOLD, 30));
        welcomeLabel.setVerticalAlignment(JLabel.TOP);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        return welcomeLabel;
    }

    // MODIFIES: this
    // EFFECTS : Adds the load data button
    private JButton loadDataButton() {
        this.loadTerm = new JButton("Load Existing Data");
        Font font = new Font("Arial", Font.ITALIC, 26);
        loadTerm.setFont(font);
        loadTerm.setPreferredSize(new Dimension(650, buttonHeight));
        loadTerm.addActionListener(e -> {
            try {
                dispose();
                new LoadData();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        return loadTerm;

    }

    // MODIFIES: this
    // EFFECTS : Adds the button to add a new term
    private JButton addTermButton() {
        this.addTerm = new JButton("Add a new Term");
        Font font = new Font("Arial", Font.ITALIC, 26);
        addTerm.setFont(font);
        addTerm.setPreferredSize(new Dimension(650, buttonHeight));
        addTerm.addActionListener(e -> {
            try {
                dispose();
                new AddTermGUI();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return addTerm;
    }

    // MODIFIES: this
    // EFFECTS : Adds the button to add a new subject
    private JButton addSubButton() {
        this.addSub = new JButton("Add a new Subject");
        Font font = new Font("Arial", Font.ITALIC, 26);
        addSub.setFont(font);
        addSub.setPreferredSize(new Dimension(650, buttonHeight));
        addSub.addActionListener(e -> {
            try {
                dispose();
                new AddSubGUI();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return addSub;
    }

    // MODIFIES: this
    // EFFECTS : Adds the button to add a new Graded component
    private JButton addGCButton() {
        this.addGC = new JButton("Add a new Graded Component");
        Font font = new Font("Arial", Font.ITALIC, 26);
        addGC.setFont(font);
        addGC.setPreferredSize(new Dimension(650, buttonHeight));
        addGC.addActionListener(e -> {
            try {
                dispose();
                new AddGcGUI();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return addGC;

    }


    // MODIFIES: this
    // EFFECTS : Adds the button to calculate the term averages
    private JButton termAverage() {
        this.termAverage = new JButton("Calculate term average");
        Font font = new Font("Arial", Font.ITALIC, 26);
        termAverage.setFont(font);
        termAverage.setPreferredSize(new Dimension(650, buttonHeight));
        termAverage.addActionListener(e -> {
            try {
                dispose();
                new TermAverage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return termAverage;
    }

    // MODIFIES: this
    // EFFECTS : Adds the display button
    protected JButton displayButton() {
        this.displayTranscript = new JButton("Display Transcript");
        Font font = new Font("Arial", Font.ITALIC, 26);
        displayTranscript.setFont(font);
        displayTranscript.setPreferredSize(new Dimension(650, buttonHeight));
        displayTranscript.addActionListener(e -> {
            try {
                dispose();
                new DisplayGUI();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return displayTranscript;

    }





}
