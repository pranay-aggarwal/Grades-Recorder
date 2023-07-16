package model;


import java.util.ArrayList;


// Create a new Subject for the term
public class Subject {
    private String subName;
    private double expectedMarks;
    private ArrayList<GradedComponent> listGradedComp;


    //REQUIRE : A non-zero subName and a positive expectedMarks
    //EFFECTS : Creates a new subject with a
    //          Subject name, Expected Marks and List of Graded Components
    public Subject(String subName, double expectedMarks) {
        this.subName = subName;
        this.expectedMarks = expectedMarks;
        this.listGradedComp = new ArrayList<>();
    }




    //MODIFIES: this
    //EFFECTS : add a new Graded Component to the Term
    public void addGradedComp(GradedComponent component) {
        this.listGradedComp.add(component);
    }


    //EFFECTS : returns the average of all the Graded Components in the subject
    public double averageGradedComp() {
        double sum = 0;
        for (GradedComponent g: listGradedComp) {
            sum += g.getWeightage() * g.getMarks() / 100.0;
        }
        return sum;


    }


    //EFFECTS : return the marks needed to meet the expected marks
    public double expectedRequirement() {
        double req = 0;
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
        String result = subName + ":" + " Expected Marks: " + expectedMarks;
        String result1 = "\n";
        for (GradedComponent gradedComponent: listGradedComp) {
            result1 += gradedComponent.toString() + "\n";
        }
        return result + result1;
    }


    ////
    public void setSubName(String subName) {
        this.subName = subName;
    }


    public void setListGradedComp(ArrayList<GradedComponent> listGradedComp) {
        this.listGradedComp = listGradedComp;
    }


    public void setExpectedMarks(double expectedMarks) {
        this.expectedMarks = expectedMarks;
    }
    ////




    ////
    public String getSubName() {
        return subName;
    }


    public ArrayList<GradedComponent> getListGradedComp() {
        return listGradedComp;
    }


    public double getExpectedMarks() {
        return expectedMarks;
    }
    ////




}


