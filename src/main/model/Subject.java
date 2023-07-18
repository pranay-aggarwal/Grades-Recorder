package model;


import java.util.ArrayList;


// Create a new Subject for the term
public class Subject {
    private String subName;
    private double expectedMarks;
    private ArrayList<GradedComponent> listOfGradedComp;


    //REQUIRE : A non-zero subName and a positive expectedMarks
    //EFFECTS : Creates a new subject with a
    //          Subject name, Expected Marks and List of Graded Components
    public Subject(String subName, double expectedMarks) {
        this.subName = subName;
        this.expectedMarks = expectedMarks;
        this.listOfGradedComp = new ArrayList<>();
    }




    //MODIFIES: this
    //EFFECTS : add a new Graded Component to the Term
    public void addGradedComp(GradedComponent component) {
        this.listOfGradedComp.add(component);
    }


    //EFFECTS : returns the average of all the Graded Components in the subject
    public double averageGradedComp() {
        double sum = 0;
        for (GradedComponent g: listOfGradedComp) {
            sum += g.getWeightage() * g.getMarks() / 100.0;
        }
        return sum;


    }


    //EFFECTS : return the marks needed to meet the expected marks
    public double expectedRequirement() {
        double req;
        if (this.expectedMarks >= this.averageGradedComp()) {
            req = this.expectedMarks - this.averageGradedComp();
            return req;
        } else {
            System.out.println("You have already met the requirement");
            return 0;
        }
    }


    //EFFECTS : returns the output of subjects class for the user
    public String toString() {
        String result = "> " + subName + ":" + " Expected Marks: " + expectedMarks;
        String result1 = "\n";
        for (GradedComponent gradedComponent: listOfGradedComp) {
            result1 += gradedComponent.toString() + "\n";
        }
        return result + result1;
    }


    //// setters

    // MODIFIES: this
    // EFFECTS : sets the value of subName to the given value
    public void setSubName(String subName) {
        this.subName = subName;
    }


    // MODIFIES: this
    // EFFECTS : sets the value of listOfGradedComp to the given value
    public void setListOfGradedComp(ArrayList<GradedComponent> listOfGradedComp) {
        this.listOfGradedComp = listOfGradedComp;
    }


    // MODIFIES: this
    // EFFECTS : sets the value of expectedMarks to the given value
    public void setExpectedMarks(double expectedMarks) {
        this.expectedMarks = expectedMarks;
    }
    ////




    //// getters

    // EFFECTS : returns the value of subName
    public String getSubName() {
        return subName;
    }

    // EFFECTS : returns the value of listOfGradedComp
    public ArrayList<GradedComponent> getListOfGradedComp() {
        return listOfGradedComp;
    }

    // EFFECTS : returns the value of expectedMarks
    public double getExpectedMarks() {
        return expectedMarks;
    }
    ////




}


