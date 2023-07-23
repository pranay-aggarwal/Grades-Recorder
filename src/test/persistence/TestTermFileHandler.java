package persistence;

import model.GradedComponent;
import model.Subject;
import model.Term;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test Case for TermFileHandler
 */
public class TestTermFileHandler {

    @Test
    public void testReadingNoFile() {
        TermFileHandler termFileHandler = new TermFileHandler("./data/wrongFilePath.json");
        try {
            List <Term> result = termFileHandler.readTermFromFile();
            fail("No Such File exists");
        } catch (IOException e) {
            // Exception expected

        }
    }


    @Test
    public void testReadTermsFromEmptyFile() throws IOException {
        TermFileHandler termFileHandler = new TermFileHandler("data/testFile.json");
        List<Term> result = termFileHandler.readTermFromFile();
        assertEquals(0 , result.size());
    }


    @Test
    public void testReadTermsFromExistingData(){
        TermFileHandler termFileHandler = new TermFileHandler("data/testFileFull.json");
        List<Term> result = null;
        try {
            result = termFileHandler.readTermFromFile();
        } catch (IOException e) {
            fail("Not expecting IOException");
        }
        assertEquals(2 , result.size());
    }



    @Test
    public void testWriteTermsToAFile() {
        TermFileHandler termFileHandler = new TermFileHandler("data/testFileToWrite.json");

        GradedComponent gc1 = new GradedComponent("quiz", 85, 15);
        GradedComponent gc2 = new GradedComponent("MT", 90, 35);
        ArrayList<GradedComponent> listOfGC = new ArrayList<>();
        listOfGC.add(gc1);
        listOfGC.add(gc2);

        Subject sub1 = new Subject("CPSC210", 85);
        sub1.setListOfGradedComp(listOfGC);
        ArrayList<Subject> listOfSub = new ArrayList<>();
        listOfSub.add(sub1);

        Term t1 = new Term(2023,1);
        t1.setSubjects(listOfSub);

        List<Term> toBeEntered = new ArrayList<>();
        toBeEntered.add(t1);
        assertEquals(1,toBeEntered.size());

        try {
            termFileHandler.writeTermToFile(toBeEntered);
        } catch (IOException e) {
            fail("Did not expect exception");
        }
        List<Term> result1 = null;
        try {
            result1 = termFileHandler.readTermFromFile();
        } catch (IOException e) {
            fail("Did not expect exception while reading");
        }

        assertEquals(1,result1.size());
        assertEquals("-------------------------------------------------------\n" +
                "Year: 2023 Term Number: 1\n" +
                "-------------------------------------------------------\n" +
                "> CPSC210: Expected Marks: 85.0\n" +
                "    Component Name: quiz Marks: 85.0 Weightage: 15.0\n" +
                "    Component Name: MT Marks: 90.0 Weightage: 35.0\n",
                result1.get(0).toString());

    }



    @Test
    public void testGetFilePath(){
        TermFileHandler termFileHandler = new TermFileHandler("./data/dataOne.json");
        assertEquals("./data/dataOne.json", termFileHandler.getFilePath());
    }



}
