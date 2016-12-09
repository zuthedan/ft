package de.btu.sst.evs.blatt8.checkIn.enums;

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
}

