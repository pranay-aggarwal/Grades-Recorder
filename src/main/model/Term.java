package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

/**
 * This class represents an academic term in a school year.
 * It contains information about the term's year, term number,
 * and a list of subjects offered during that term.
*/


// Create a new Term
public class Term implements Writable {
    private int year;
    private int termNum;
    private ArrayList<Subject> subjects;


    //REQUIRE : A valid year and a termNum either 1 or 2
    //EFFECTS : Creates a new term with a
    //          term year, term number and list of subjects
    public Term(int year, int termNum) {
        this.year = year;
        this.termNum = termNum;
        this.subjects = new ArrayList<>();
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
        return "-------------------------------------------------------" + "\n"
                + "Year: " + year + " Term Number: " + termNum + "\n"
                + "-------------------------------------------------------" + "\n"
                + toStringSubjects(subjects)



                ;
    }


    //// setters

    // MODIFIES: this
    // EFFECTS : sets the value of year to the given value
    public void setYear(int year) {
        this.year = year;
    }


    // MODIFIES: this
    // EFFECTS : sets the value of termNum to the given value
    public void setTermNum(int termNum) {
        this.termNum = termNum;
    }


    // MODIFIES: this
    // EFFECTS : sets the value of subjects to the given value
    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }
    ////




    //// getters

    // EFFECTS : returns the value of year
    public int getYear() {
        return year;
    }

    // EFFECTS : returns the value of termNum
    public int getTermNum() {
        return termNum;
    }

    // EFFECTS : returns the value of subjects
    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    ////

    // Persistence Code
    @Override
    public JSONObject toJson() {
        JSONObject result = new JSONObject();
        result.put("year", year);
        result.put("termNum", termNum);
        JSONArray termList = new JSONArray();
        for (Subject s : getSubjects()) {
            termList.put(s.toJson());
        }
        result.put("subjects", termList);
        return result;
    }
}

