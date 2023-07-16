package model;


import java.util.ArrayList;


// Create a new Term
public class Term {
    private int year;
    private int termNum;
    private ArrayList<Subject> subjects;


    //REQUIRE : A valid year and a termNum either 1 or 2
    //EFFECTS : Creates a new term with a
    //          term year, term number and list of subjects
    public Term(int year, int termNum) {
        this.year = year;
        this.termNum = termNum;
        this.subjects = new ArrayList<Subject>();
    }


    //MODIFIES: this
    //EFFECTS : add a new subject to the Term
    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }


    //EFFECTS : returns the average of all the subjects in the term
    public double averageSub() {
        double sum = 0;
        for (Subject s : subjects) {
            sum += s.averageGradedComp();
        }
        sum = sum / subjects.size();
        return sum;
    }




    //EFFECTS : returns the output of subjects class for the user
    public String toStringSubjects(ArrayList<Subject> subjects) {
        String result = "";
        for (Subject subject: subjects) {
            result += subject.toString();
        }
        return result;
    }


    //EFFECTS : returns the output of term class for the user
    public String toString() {
        return "Year: " + year + " Term Number: " + termNum + "\n"
                + toStringSubjects(subjects);
    }


    ////
    public void setYear(int year) {
        this.year = year;
    }


    public void setTermNum(int termNum) {
        this.termNum = termNum;
    }


    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }
    ////




    ////
    public int getYear() {
        return year;
    }


    public int getTermNum() {
        return termNum;
    }


    public ArrayList<Subject> getSubjects() {
        return subjects;
    }
    ////
}

