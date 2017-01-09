package de.btu.sst.evs.blatt11.checkIn.enums;

/**
 * @author Mathias Schubanz
 */

public enum CheckInKanal {
    
    WEBBASIERT("Web-basiert"),
    SCHALTER("Schalter"),
    SELBSTBEDIENUNGSTERMINAL("Selbstbedienungsterminal"),
    MOBILGERAET("Mobilgerät");

    CheckInKanal(String value) {
	this.textValue = value;
    }

    private String textValue;

    public String toString() {
	return this.textValue;
    }

    /**
     * Returns for a given string the corresponding enum value. The submitted
     * string is compared with the textual representations of the enum values.
     * If a match is found, a corresponding enum value is returned. If no match
     * is found null will be returned.
     * 
     * @param value
     *            : String -- Textual representation of the desired value.
     * @return corresponding enum value for the submitted string
     */
    public static CheckInKanal getCheckInKanal(String value) {
	switch (value) {
	case "Web-basiert":
	    return CheckInKanal.WEBBASIERT;
	case "Schalter":
	    return CheckInKanal.SCHALTER;
	case "Selbstbedienungsterminal":
	    return CheckInKanal.SELBSTBEDIENUNGSTERMINAL;
	case "Mobilgerät":
	    return CheckInKanal.MOBILGERAET;
	default:
	    return null;
	}
    }
}
