package ui.tui;


import model.GradedComponent;
import model.Subject;
import model.Term;
import persistence.TermFileHandler;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents the main application for the Grade Tracker.
 * It allows users to interact with the application through a menu-driven console interface.
 */



// StartYear application
public class StartYear {
    Scanner input = new Scanner(System.in);


    List<Term> terms = new ArrayList<>();
    ArrayList<Subject> existingSubjects = new ArrayList<>();

    final String filePath = "data/data.json";
    TermFileHandler fileHandler = new TermFileHandler(filePath);

    // EFFECT: call the StartYear app and display possible menu options
    public StartYear() throws IOException {
        displayMenu();
        System.out.println("NOTE: Load the data before running the application else you may loose your progress");
    }


    // EFFECT: Print the options available in displayMenu()
    public void printOut() {
        System.out.println("=====================Grade Tracker===========================");
        System.out.println("Select from:");
        System.out.println("1 -> Create a Term");
        System.out.println("2 -> Add a new Subject to an existing term");
        System.out.println("3 -> Add a new Grading Component to an existing Subject");
        System.out.println("4 -> Calculate Average of the term ");
        System.out.println("5 -> Calculate the Average of a single Subject");
        System.out.println("6 -> Calculate marks needed for expected score");
        System.out.println("7 -> Display result");
        System.out.println("8 -> Save Data");
        System.out.println("9 -> Load Data");
        System.out.println("Any other option -> Quit");
        System.out.println("===========================================================");


    }


    // EFFECT: Displays the function menu and input a valid option
    public void displayMenu() throws IOException {

        while (true) {
            printOut();
            int option = input.nextInt();

            if (option == 1) {
                createTerm();
            } else if (option == 2) {
                addSubject();
            } else if (option == 3) {
                addGradingComponent();
            } else if (option == 4) {
                calculateAverageOfTerm();
            } else if (option == 5) {
                calculateAverageOfSubject();
            } else if (option == 6) {
                calculateReqMarks();
            } else if (option == 7) {
                displayResults();
            } else {
                saveAndLoadData(option);
            }
        }
    }

    // EFFECTS : runs the save and load menu
    private void saveAndLoadData(int option) throws IOException {
        if (option == 8) {
            saveData();
        } else if (option == 9) {
            loadData();
        } else {
            System.out.println("Exiting the application....");
            System.exit(69);
        }
    }



    // EFFECT : Creates a new Term
    public void createTerm() {
        System.out.println("Enter the year of the term: ");
        int year = input.nextInt();
        System.out.println("Enter the term number(1/2): ");
        int termNum = input.nextInt();
        System.out.println("Term created successfully");
        Term term = new Term(year, termNum);
        term.setSubjects(new ArrayList<>());
        terms.add(term);
    }


    // REQUIRE : There exists a valid term
    // EFFECT  : Add a new Subject to an existing term
    public void addSubject() {
        System.out.println("Enter the year of the term: ");
        int year = input.nextInt();
        System.out.println("Enter the term number(1/2): ");
        int termNum = input.nextInt();
        boolean isAdded = false;
        for (Term t : terms) {
            if (t.getYear() == year && t.getTermNum() == termNum) {
                inputSubDetails(t);
                isAdded = true;
                break;
            } else {
                System.out.println("Term not found");
            }
        }
        if (isAdded) {
            System.out.println("Subject added successfully");
        } else {
            System.out.println("Could not find the Term or the Year");
        }
    }




    // EFFECT : input the details of subject to add
    private void inputSubDetails(Term t) {
        System.out.println("Enter the name of the subject: ");
        String subName = input.next();
        System.out.println("Enter the expected marks: ");
        double expMarks = input.nextFloat();
        Subject subject = new Subject(subName, expMarks);
        existingSubjects = t.getSubjects();
        existingSubjects.add(subject);
        t.setSubjects(existingSubjects);
    }


    // REQUIRE : There exists a valid subject
    // EFFECT: Add a new Grading Component to an existing Subject
    private void addGradingComponent() {
        System.out.println("Enter the year of the term: ");
        int year = input.nextInt();
        System.out.println("Enter the term number(1/2): ");
        int termNum = input.nextInt();
        boolean isAdded = false;
        for (Term t : terms) {
            if (t.getYear() == year && t.getTermNum() == termNum) {
                System.out.println("Enter the subject name: ");
                String subNum = input.next();
                for (Subject s : existingSubjects) {
                    if (s.getSubName().equals(subNum)) {
                        inputGCDetails(s);
                        isAdded = true;
                        break;
                    } else {
                        System.out.println("Subject not found");
                    }


                }
                finalResultDisplay(isAdded);




            } else {
                System.out.println("Could not find the year or term");
            }
        }
    }




    // EFFECT : input the details of Grading Component to add
    private void inputGCDetails(Subject s) {
        System.out.println("Enter the name of the Grading Component: ");
        String compName = input.next();
        System.out.println("Enter the marks: ");
        double marks = input.nextFloat();
        System.out.println("Enter the weightage: ");
        double weight = input.nextFloat();
        GradedComponent gradedComponent = new GradedComponent(compName, marks, weight);
        ArrayList<GradedComponent> result = s.getListOfGradedComp();
        result.add(gradedComponent);
        s.setListOfGradedComp(result);
    }


    // EFFECT : Display if the Grading Component was added or not
    private static void finalResultDisplay(boolean isAdded) {
        if (isAdded) {
            System.out.println("Graded Component added successfully");
        } else {
            System.out.println("Could not find the Subject");
        }
    }


    // REQUIRE : There exists valid subjects in the term with their final grades
    // EFFECT  : Calculates the average of individual Term
    private void calculateAverageOfTerm() {
        double result = 0.0;
        System.out.println("Enter the year of the term: ");
        int year = input.nextInt();
        System.out.println("Enter the term number(1/2): ");
        int termNum = input.nextInt();
        for (Term t : terms) {
            if (t.getYear() == year && t.getTermNum() == termNum) {
                result += t.averageSub();
            } else {
                System.out.println("Term not found");
            }
            System.out.println("Your Average for this term is: " + result);
        }
    }


    // REQUIRE : There exists valid Grading Component in the Subject with their final grades
    // EFFECT  : Calculates the average of individual subject
    private void calculateAverageOfSubject() {
        double result = 0.0;
        System.out.println("Enter the year of the term: ");
        int year = input.nextInt();
        System.out.println("Enter the term number(1/2): ");
        int termNum = input.nextInt();
        for (Term t : terms) {
            if (t.getYear() == year && t.getTermNum() == termNum) {
                System.out.println("Enter the name of the subject : ");
                String name = input.next();
                for (Subject s : existingSubjects) {
                    if (s.getSubName().equals(name)) {
                        result += s.averageGradedComp();
                    } else {
                        System.out.println("Subject not found");
                    }
                }
                System.out.println("The average of your subject : " + result);
            } else {
                System.out.println("Term not found");
            }
        }
    }




    // REQUIRE : The subject contains valid average marks obtained and expected marks
    // EFFECT  : Calculates the marks required to meet the expected score
    private void calculateReqMarks() {
        double result = 0.0;
        System.out.println("Enter the year of the term: ");
        int year = input.nextInt();
        System.out.println("Enter the term number(1/2): ");
        int termNum = input.nextInt();
        for (Term t : terms) {
            if (t.getYear() == year && t.getTermNum() == termNum) {
                System.out.println("Enter the name of the subject : ");
                String name = input.next();
                for (Subject s : existingSubjects) {
                    if (s.getSubName().equals(name)) {
                        result += s.expectedRequirement();
                    } else {
                        System.out.println("Subject not found");
                    }
                }
                System.out.println("Percentage needed for expected marks : " + result + "%");
            } else {
                System.out.println("Term not found");
            }
        }
    }


    // EFFECTS : Display the results of the operations
    private void displayResults() {
        String result = "";
        for (Term term : terms) {
            result += term.toString();
        }
        System.out.println(result);
    }

    // EFFECTS : Saves the data in the given file path
    public void saveData() throws IOException {
        fileHandler.writeTermToFile(terms);
        System.out.println("Data saved successfully");
    }


    // MODIFIES: this
    // EFFECTS : Loads the data from the given file path
    public void loadData() throws IOException {
        terms = fileHandler.readTermFromFile();
        System.out.println("Loaded Data Successfully");
    }
}
