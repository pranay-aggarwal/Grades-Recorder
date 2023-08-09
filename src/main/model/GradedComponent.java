package model;



import org.json.JSONObject;
import persistence.Writable;

/**
 * This class represents a graded component within an academic subject.
 * It contains information about the component's name, marks obtained,
 * and weightage in the overall subject grade.
 */

// Create a new Graded Component for a subject
public class GradedComponent implements Writable {
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
        return "    " + "Component Name: " + compName + " Marks: " + marks + " Weightage: " + weightage;
    }


    //// setters

    // MODIFIES: this
    // EFFECTS : sets the value of compName to the given value
    public void setCompName(String compName) {
        this.compName = compName;
    }


    // MODIFIES: this
    // EFFECTS : sets the value of marks to the given value
    public void setMarks(double marks) {
        this.marks = marks;
    }


    // MODIFIES: this
    // EFFECTS : sets the value of weightage to the given value
    public void setWeightage(double weightage) {
        this.weightage = weightage;
    }
    ////


    //// getters

    // EFFECTS : returns the value of marks
    public double getMarks() {
        return marks;
    }

    // EFFECTS : returns the value of weightage
    public double getWeightage() {
        return weightage;
    }

    // EFFECTS : returns the value of compName
    public String getCompName() {
        return compName;
    }

    ////


    // Persistence code
    @Override
    public JSONObject toJson() {
        JSONObject result = new JSONObject();
        result.put("compName", compName);
        result.put("marks", marks);
        result.put("weightage", weightage);
        return result;
    }
}



