package model;




// Create a new Graded Component for a subject
public class GradedComponent {
    private String compName;
    private double marks;
    private double weightage;


    // REQUIRES: A non-zero name length, a non-negative mark
    //           and a non-negative weightage
    // EFFECTS : Creates a new Graded Component with the
    //           Component name, marks obtained, and the weightage of the component
    public GradedComponent(String name, double mark, double weight) {
        this.compName = name;
        this.marks = mark;
        this.weightage = weight;
    }


    //EFFECTS : returns the output of Graded Component class for the user
    public String toString() {
        return "Component Name: " + compName + " Marks: " + marks + " Weightage: " + weightage;
    }


    ////
    public void setCompName(String compName) {
        this.compName = compName;
    }


    public void setMarks(double marks) {
        this.marks = marks;
    }


    public void setWeightage(double weightage) {
        this.weightage = weightage;
    }
    ////


    ////
    public double getMarks() {
        return marks;
    }


    public double getWeightage() {
        return weightage;
    }


    public String getCompName() {
        return compName;
    }
}

////

