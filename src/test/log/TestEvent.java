package log;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class
 */

public class TestEvent {
    private Event e1, e2;

    @BeforeEach
    public void setUp() {
        this.e1 = new Event("Subject added");
        this.e2 = new Event("GC added");
    }

    @Test
    public void TestConstructor() {
        assertEquals("Subject added", e1.getDescription());
        assertEquals("GC added", e2.getDescription());
    }

    @Test
    public void TestGetDate() {
        assertEquals(Calendar.getInstance().getTime().toString(), e1.getDate().toString());
        assertEquals(Calendar.getInstance().getTime().toString(), e2.getDate().toString());
    }

    @Test
    public void TestEquals() {
        assertFalse(e1.equals(null));
        assertFalse(e1.equals("wrong data"));
        assertFalse(e1.equals(e2));
    }

    @Test
    public void TestEqualsSameDescriptionDifferentTime() throws InterruptedException {
        Thread.sleep(1000);
        Event e3 = new Event(e1.getDescription());
        assertFalse(e1.equals(e3));
    }

    @Test
    public void TestEqualsDifferentDescriptionDifferentTime() throws InterruptedException {
        Thread.sleep(1000);
        Event e3 = new Event(e2.getDescription());
        assertFalse(e1.equals(e3));
    }

    @Test
    public void TestHashCode() {
        assertNotEquals(-10549187, e1.hashCode());
    }


    @Test
    public void TestBothDescriptionAndDateEqual() {
        Event e3 = new Event("Subject added");
        Event e4 = new Event("Subject added");
        assertTrue(e4.equals(e3));
    }
}