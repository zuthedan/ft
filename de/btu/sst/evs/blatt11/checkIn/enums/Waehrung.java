package de.btu.sst.evs.blatt11.checkIn.enums;

/**
 * @author Mathias Schubanz
 */

public enum Waehrung {
    
    EURO ("Euro"),
    POUND_STIRLING ("GBP"),
    USD ("USD"), 
    DK ("DK"),
    SK ("SK"),
    PLN ("PLN"),
    PROZENT ("Prozent");

    private String textValue;
    
    private Waehrung(String value) {
	this.textValue = value;
    }
    
    public static Waehrung getWaehrung(String value) {
	switch (value) {
	case "Euro" :
	    return Waehrung.EURO;
	case "GBP" :
	    return Waehrung.POUND_STIRLING;
	case "USD" :
	    return Waehrung.USD;
	case "DK" :
	    return Waehrung.DK;
	case "SK" :
	    return Waehrung.SK;
	case "PLN" :
	    return Waehrung.PLN;
	case "Prozent" :
	    return Waehrung.PROZENT;
	default: 
	    return null;
	}
	
    }   
    
    public String toString() {
	return this.textValue;
    }
}
