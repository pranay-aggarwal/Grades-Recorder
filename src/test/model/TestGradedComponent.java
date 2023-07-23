package model;


import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a test class for GradedComponent
 */

public class TestGradedComponent {


    private GradedComponent GC1;


    @BeforeEach
    void runBefore() {
        GC1 = new GradedComponent ("quiz", 80,15);
    }


    @Test
    public void testConstructor(){
        assertEquals("quiz", GC1.getCompName());
        assertEquals(80, GC1.getMarks());
        assertEquals(15,GC1.getWeightage());
    }


    @Test
    public void testToString(){
        assertEquals("    Component Name: quiz Marks: 80.0 Weightage: 15.0",GC1.toString());
    }


    @Test
    public void testSetCompName(){
        GC1.setCompName("Midterm");
        assertEquals("Midterm",GC1.getCompName());
    }


    @Test
    public void testSetMarks(){
        GC1.setMarks(98);
        assertEquals(98,GC1.getMarks());
    }


    @Test
    public void testSetWeightage(){
        GC1.setWeightage(25);
        assertEquals(25,GC1.getWeightage());
    }


    @Test
    public void testToJson(){
        GradedComponent gradedComponent = new GradedComponent("Quiz", 85, 15);
        JSONObject expectedJson = new JSONObject();
        expectedJson.put("marks", 85);
        expectedJson.put("compName", "Quiz");
        expectedJson.put("weightage", 15);
        JSONObject actualJson = gradedComponent.toJson();
        assertEquals(expectedJson.toString(), actualJson.toString());

    }


}
