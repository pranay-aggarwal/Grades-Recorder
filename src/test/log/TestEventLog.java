package log;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestEventLog {

    @Test
    public void TestEventLogGetInstance() {
        assertNotNull(EventLog.getInstance());
    }


    @Test
    public void TestClearLog() {
        EventLog.getInstance().clear();
        assertNotEquals("", EventLog.getInstance().toString());

        Event SampleEvent = new Event("Some Event");
        String result = "EventLog{events=[" +  SampleEvent.getDate() + "\n" +
                "Event log cleared.]}";
        assertNotEquals(result, EventLog.getInstance().toString());
    }

    @Test
    public void TestGetIterator() {
        assertNotNull(EventLog.getInstance().iterator());
    }
}