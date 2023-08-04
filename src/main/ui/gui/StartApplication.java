package ui.gui;

import model.Subject;
import model.Term;
import persistence.TermFileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StartApplication extends JFrame {

    private JButton loadTerm;
    private JButton addTerm;
    private JButton addSub;
    private JButton addGC;
    private JButton displayTranscript;
    protected final int dimX = 800; // represents the width of the JFrame
    protected final int dimY = 1000; // represents the height of the JFrame
    private int buttonHeight = 100;


    private final String termPath = "./data/data.json";
    protected List<Term> termList = new ArrayList<>(); // stores the patients made

    protected TermFileHandler termFile = new TermFileHandler(termPath);


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

        try {
            termList.addAll(termFile.readTermFromFile());
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }


    private JPanel addPanel() throws IOException {
        JPanel res = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        res.add(addWelcomeLabel(), gbc);
        gbc.gridy++;
        res.add(loadDataButton(), gbc);
        gbc.gridy++;
        res.add(addTermButton(), gbc);
        gbc.gridy++;
        res.add(addSubButton(), gbc);
        gbc.gridy++;
        res.add(addGCButton(), gbc);
        gbc.gridy++;
        res.add(displayButton(), gbc);
        return res;
    }




    private JLabel addWelcomeLabel() {
        JLabel welcomeLabel = new JLabel("Welcome To Your Transcript Record!!!");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBounds(0, 50, 250, 50);
        welcomeLabel.setFont(new Font("Arial",Font.BOLD, 30));
        welcomeLabel.setVerticalAlignment(JLabel.TOP);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        return welcomeLabel;
    }


    private JButton loadDataButton() {
        this.loadTerm = new JButton("Load Existing Data");
        Font font = new Font("Arial", Font.ITALIC, 26);
        loadTerm.setFont(font);
        loadTerm.setPreferredSize(new Dimension(650, buttonHeight));
        JFrame frame = new JFrame("Loading Data ...");
        loadTerm.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Data Loaded successfully",
                "Success",
                JOptionPane.INFORMATION_MESSAGE));
        return loadTerm;
    }


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
