package de.btu.sst.evs.blatt8.checkIn.enums;

/**
 * @author Mathias Schubanz
 */

public enum Zahlungskanal {
    
    VISA_CARD("Visa"),
    MASTER_CARD("Master"),
    DEBIT_CARD("Debit"),
    DIRECT_DEBIT("Direct Debit"),
    PAYPAL("Paypal"),
    CASH("Cash");
    
    private String textValue;
    
    Zahlungskanal(String textValue) {
	this.textValue = textValue;
    }
    
    public String toString() {
	return this.textValue;
    }
}
