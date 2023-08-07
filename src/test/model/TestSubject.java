package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a test class for Subject
 */

public class TestSubject {
    private Subject Sub1;
    private GradedComponent GC1;
    private GradedComponent GC2;
    private GradedComponent GC3;
    private GradedComponent GC4;
    private GradedComponent GC5;
    private GradedComponent GC6;






    @BeforeEach
    public void runBefore() {
        Sub1 = new Subject("CPSC210", 95);
        GC1 = new GradedComponent("quiz1",80, 15 );
        GC2 = new GradedComponent("quiz2",85, 15 );
        GC3 = new GradedComponent("midterm",75, 30 );
        GC4 = new GradedComponent("finals",90, 40 );
        Sub1.addGradedComp(GC1);
        Sub1.addGradedComp(GC2);
        Sub1.addGradedComp(GC3);
        Sub1.addGradedComp(GC4);
    }


    @Test
    public void testConstructor (){
        assertEquals("CPSC210", Sub1.getSubName());
        assertEquals(95, Sub1.getExpectedMarks());
    }


    @Test
    public void testAddGradedComp() {
        assertEquals(4, Sub1.getListOfGradedComp().size());
    }


    @Test
    public void testAverageGradedComp() {
        assertEquals(83.25, Sub1.averageGradedComp());
    }


    @Test
    public void testExpectedWhenRequirementNotMet() {
        assertEquals (11.75, Sub1.expectedRequirement());
    }

    @Test
    public void testExpectedWhenRequirementMet(){
        Sub1.setExpectedMarks(80);
        assertEquals(0,Sub1.expectedRequirement());
    }

    @Test
    public void testToString(){
        assertEquals("> CPSC210: Expected Marks: 95.0\n" +
                "    Component Name: quiz1 Marks: 80.0 Weightage: 15.0\n" +
                "    Component Name: quiz2 Marks: 85.0 Weightage: 15.0\n" +
                "    Component Name: midterm Marks: 75.0 Weightage: 30.0\n" +
                "    Component Name: finals Marks: 90.0 Weightage: 40.0\n", Sub1.toString());
    }





    @Test
    public void testSetSubName(){
        Sub1.setSubName("CPSC110");
        assertEquals("CPSC110",Sub1.getSubName());
    }


    @Test
    public void testSetListGradedComp(){
        GC5 = new GradedComponent("quiz3",30, 10 );
        GC6 = new GradedComponent("quiz4",95, 10 );
        ArrayList<GradedComponent> sampleList = new ArrayList<>();
        sampleList.add(GC1);
        sampleList.add(GC2);
        sampleList.add(GC3);
        sampleList.add(GC4);
        sampleList.add(GC5);
        sampleList.add(GC6);
        Sub1.setListOfGradedComp(sampleList);
        assertEquals(sampleList, Sub1.getListOfGradedComp());


    }
    @Test
    public void tesSetExpectedMarks(){
        Sub1.setExpectedMarks(70);
        assertEquals(70,Sub1.getExpectedMarks());
    }


    @Test
    public void testToJson() {
        Subject subject = new Subject("CPSC210", 85);
        GradedComponent gradedComponent = new GradedComponent("Quiz", 85, 15);
        ArrayList<GradedComponent> listOfGC = new ArrayList<>();
        listOfGC.add(gradedComponent);
        subject.setListOfGradedComp(listOfGC);


        JSONObject expectedJson = new JSONObject();
        expectedJson.put("subName", "CPSC210");
        expectedJson.put("expectedMarks", 85);

        JSONArray marksArray = new JSONArray();
        for (GradedComponent gc : subject.getListOfGradedComp()) {
            marksArray.put(gc.toJson());
        }
        expectedJson.put("gradedComps", marksArray);

        JSONObject actualJson = subject.toJson();
        assertEquals(expectedJson.toString(), actualJson.toString());

    }

    @Test
    public void testEquals() {
        Subject sameSubject = new Subject("CPSC210", 95);
        Subject differentSubject = new Subject("CPSC110", 85);
        Subject differentMarksSubject = new Subject("CPSC210", 80);

        assertTrue(Sub1.equals(Sub1));
        assertTrue(Sub1.equals(sameSubject));
        assertFalse(Sub1.equals(differentSubject));
        assertTrue(Sub1.equals(differentMarksSubject));
    }

    @Test
    public void testHashCode() {
        Subject sameSubject = new Subject("CPSC210", 95);
        Subject differentSubject = new Subject("CPSC110", 85);
        assertEquals(Sub1.hashCode(), Sub1.hashCode());
        assertEquals(Sub1.hashCode(), sameSubject.hashCode());
        assertNotEquals(Sub1.hashCode(), differentSubject.hashCode());
    }


}
