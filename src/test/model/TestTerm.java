package model;


import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is a test class for Term
 */

public class TestTerm {
    private Term T1;
    private Subject Sub1;
    private Subject Sub2;
    private GradedComponent GC1;
    private GradedComponent GC2;
    private GradedComponent GC3;
    private GradedComponent GC4;




    @BeforeEach
    public void runBeforeEach(){
        T1 = new Term(2023,1);


        Sub1 = new Subject("CPSC210", 95);
        GC1= new GradedComponent("quiz", 90,30);
        GC2= new GradedComponent("midterm", 85, 30);
        GC3 = new GradedComponent("finals", 80, 40);
        Sub1.addGradedComp(GC1);
        Sub1.addGradedComp(GC2);
        Sub1.addGradedComp(GC3);
        T1.addSubject(Sub1);


        Sub2 = new Subject("DSCI100", 90);
        GC4 = new GradedComponent("finals", 85, 40);
        Sub2.addGradedComp(GC1);
        Sub2.addGradedComp(GC2);
        Sub2.addGradedComp(GC4);


    }


    @Test
    public void testConstructor(){
        assertEquals(2023, T1.getYear());
        assertEquals(1, T1.getTermNum());
    }


    @Test
    public void testAddSubject (){
        assertEquals (1, T1.getSubjects().size());
        T1.addSubject(Sub2);
        assertEquals (2, T1.getSubjects().size());
    }


    @Test
    public void testAverageSub (){
        assertEquals (84.5, T1.averageSub());
        T1.addSubject(Sub2);
        assertEquals (85.5, T1.averageSub());
    }


    @Test
    public void testToStringSubjects(){
        ArrayList<Subject> L1 = new ArrayList<>();
        L1.add(Sub1);
        assertEquals("> CPSC210: Expected Marks: 95.0\n" +
                "    Component Name: quiz Marks: 90.0 Weightage: 30.0\n" +
                "    Component Name: midterm Marks: 85.0 Weightage: 30.0\n" +
                "    Component Name: finals Marks: 80.0 Weightage: 40.0\n",T1.toStringSubjects(L1));


    }


    @Test
    public void testToString(){
        assertEquals("-------------------------------------------------------\n" +
                "Year: 2023 Term Number: 1\n" +
                "-------------------------------------------------------\n" +
                "> CPSC210: Expected Marks: 95.0\n" +
                "    Component Name: quiz Marks: 90.0 Weightage: 30.0\n" +
                "    Component Name: midterm Marks: 85.0 Weightage: 30.0\n" +
                "    Component Name: finals Marks: 80.0 Weightage: 40.0\n",T1.toString());
    }


    @Test
    public void testSetYear(){
        T1.setYear(2022);
        assertEquals(2022,T1.getYear());
    }


    @Test
    public void testSetTermNum(){
        T1.setTermNum(2);
        assertEquals(2,T1.getTermNum());
    }


    @Test
    public void testSetSubjects(){
        ArrayList<Subject> sampleList = new ArrayList<>();
        sampleList.add(Sub1);
        sampleList.add(Sub2);
        T1.addSubject(Sub2);
        T1.setSubjects(sampleList);
        assertEquals(sampleList,T1.getSubjects());
    }


    @Test
    public void testToJson(){
        Term term = new Term(2023,1);
        Subject subject = new Subject("CPSC210", 85);
        GradedComponent gradedComponent = new GradedComponent("Quiz", 85, 15);
        ArrayList<GradedComponent> listOfGC = new ArrayList<>();
        listOfGC.add(gradedComponent);
        subject.setListOfGradedComp(listOfGC);
        ArrayList<Subject> listOfSub = new ArrayList<>();
        listOfSub.add(subject);
        term.setSubjects(listOfSub);

        JSONObject expected = new JSONObject();
        expected.put("year", 2023);
        expected.put("termNum", 1);

        JSONArray termList = new JSONArray();
        for (Subject s : term.getSubjects()) {
            termList.put(s.toJson());
        }
        expected.put("subjects", termList);
        JSONObject actualJson = term.toJson();
        assertEquals(expected.toString(),actualJson.toString());
    }



}
