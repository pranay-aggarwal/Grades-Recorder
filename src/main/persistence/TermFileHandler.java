package persistence;


import model.GradedComponent;
import model.Subject;
import model.Term;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *  Represents the class to read and write terms onto data file. The class provides methods for getting the file
 *  path, writing the list of terms on to file and reading the list of terms from the file.
 */

// Create the file handler for term
public class TermFileHandler {
    private final String filePath;

    // EFFECTS: creates a TermFileHandler object with the given file path
    public TermFileHandler(String filePath) {
        this.filePath = filePath;
    }



    //// Reading

    // EFFECTS : Reads the terms from the file and return them in the proper list format
    public List<Term> readTermFromFile() throws IOException {
        List<Term> result = new ArrayList<>();
        String jsonString =  new String(Files.readAllBytes(Paths.get(filePath)));
        JSONArray jsonArray = new JSONArray(jsonString);
        for (Object obj : jsonArray) {
            JSONObject json = (JSONObject) obj;
            int year = Integer.parseInt(json.get("year").toString());
            int termNum = Integer.parseInt(json.get("termNum").toString());
            JSONArray jsonSubjects = json.getJSONArray("subjects");
            ArrayList<Subject> subjects = (ArrayList<Subject>) toJsonSubjects(jsonSubjects);
            Term term = new Term(year, termNum);
            term.setSubjects(subjects);
            result.add(term);
        }
        return result;
    }

    // REQUIRES: a JSONArray of subjects
    // EFFECTS : converts JSONArray to List<Subject> and returns the value
    private List<Subject> toJsonSubjects(JSONArray jsonSubjects) {
        List<Subject> result = new ArrayList<>();
        for (Object sub : jsonSubjects) {
            JSONObject jsonSub = (JSONObject) sub;
            String subName = jsonSub.getString("subName");
            Double expectedMarks = Double.parseDouble(jsonSub.get("expectedMarks").toString());
            JSONArray jsonGC = jsonSub.getJSONArray("gradedComps");
            ArrayList<GradedComponent> gradedComponents = (ArrayList<GradedComponent>) toJsonGradedComp(jsonGC);
            Subject subject = new Subject(subName, expectedMarks);
            subject.setListOfGradedComp(gradedComponents);
            result.add(subject);

        }
        return result;

    }

    // REQUIRES: a JSONArray of graded components
    // EFFECTS : converts JSONArray to List<GradedComponent> and returns the value
    private List<GradedComponent> toJsonGradedComp(JSONArray jsonGC) {
        List<GradedComponent> result = new ArrayList<>();
        for (Object gc : jsonGC) {
            JSONObject jsonGradedComponent = (JSONObject) gc;
            String compName = jsonGradedComponent.getString("compName");
            Double marks = Double.parseDouble(jsonGradedComponent.get("marks").toString());
            Double weightage = Double.parseDouble(jsonGradedComponent.get("weightage").toString());
            GradedComponent gradedComponent = new GradedComponent(compName, marks,weightage);
            result.add(gradedComponent);

        }
        return result;
    }
    ////



    //// Writing

    // REQUIRES: a non-null list of terms
    // MODIFIES: this
    // EFFECTS : writes the list of terms to the file storing the data
    public void writeTermToFile(List<Term> terms) throws IOException {
        JSONArray jsonArray = new JSONArray();
        for (Term t : terms) {
            jsonArray.put(t.toJson());
        }
        Files.write(Paths.get(filePath), jsonArray.toString().getBytes());
    }
    ////





    // EFFECTS: returns the path of the file as a string
    public String getFilePath() {
        return filePath;
    }
}
