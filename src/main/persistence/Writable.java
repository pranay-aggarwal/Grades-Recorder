package persistence;

import org.json.JSONObject;
/**
 * This interface represents a method to convert an object to a JSON object.
 * Any class that implements this interface must implement this method as well.
 */

public interface Writable {

    // EFFECTS : returns this as a JSON Object
    JSONObject toJson();
}
