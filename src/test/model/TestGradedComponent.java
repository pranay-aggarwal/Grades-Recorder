package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


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
        assertEquals("Component Name: quiz Marks: 80.0 Weightage: 15.0",GC1.toString());
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




}
