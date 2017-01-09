package de.btu.sst.evs.blatt11.persistence;

import java.util.List;

/**
 * This interface needs to be implemented by all classes which are meant to be
 * serialized into CSV format and deserialized from CSV format.
 */
public interface ICSV_Serializable {

    /**
     * This method returns a list of String[] objects. Each String field is
     * expected to start with a unique object identifier which is retrieved from
     * the toString() implementation defined in the Object class.
     * 
     * The first item in the list needs to represent the object on which the
     * method was called. All subsequent items in the list are the objects
     * aggregates (the objects contained by the first object of the list).
     * 
     * @return : List<String[]> -- list of String[] arrays representing the
     *         serialized object and its aggregates.
     */
    public List<String[]> serializeIncludingAggregates();

    /**
     * This method serializes the object on which its is called and returns a
     * String array with the corresponding values. The array is expected to
     * start with a unique object identifier. Subsequently all attributes should
     * be stored in its string representation.
     * 
     * @return : String[] -- A String array representing the serialized object.
     */
    public String[] serialize();

    /**
     * This method takes a list of String[] objects and deserializes it. The
     * first item in the list needs to represent the object on which the
     * serialize method was called. All subsequent items in the list are the
     * aggregated objects contained by the first object of the list. Any class
     * implementing this interface needs to handle the serialization order of
     * and deserialization order of its attributes and it self.
     *
     * @param values
     *            : List<String[]> values -- List of objects to be deserialized.
     */
    public void deserializeIncludingAggregates(List<String[]> values);

    /**
     * This method takes an array of Strings and deserializes it to the target
     * object. Any class implementing this interface needs to handle the
     * serialization order of and deserialization order of its attributes and it
     * self.
     *
     * @param values
     *            : String[] values -- Object to be deserialized.
     */
    public void deserialize(String[] values);

}
