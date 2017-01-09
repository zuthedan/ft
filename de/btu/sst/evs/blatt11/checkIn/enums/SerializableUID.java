package de.btu.sst.evs.blatt11.checkIn.enums;

/**
 * @author Mathias Schubanz
 */
public enum SerializableUID {

    KUNDE (1),
    RABATT (2),
    ZAHLUNGSMITTEL (3);

    private int value;

    SerializableUID(int value) {
	this.value = value;
    }
    
    public String toString() {
	return String.valueOf(this.value);
    }
}
