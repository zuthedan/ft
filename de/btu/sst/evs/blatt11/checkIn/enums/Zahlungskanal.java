package de.btu.sst.evs.blatt11.checkIn.enums;

/**
 * @author Mathias Schubanz
 */

public enum Zahlungskanal {

    VISA_CARD("Visa"), MASTER_CARD("Master"), DEBIT_CARD("Debit"), DIRECT_DEBIT("Direct Debit"), PAYPAL("Paypal"), CASH(
	    "Cash");

    private String textValue;

    Zahlungskanal(String textValue) {
	this.textValue = textValue;
    }

    public String toString() {
	return this.textValue;
    }

    public static Zahlungskanal getZahlungskanal(String value) {
	switch (value) {
	case "Visa":
	    return Zahlungskanal.VISA_CARD;
	case "Master":
	    return Zahlungskanal.MASTER_CARD;
	case "Debit":
	    return Zahlungskanal.DEBIT_CARD;
	case "Direct Debit":
	    return Zahlungskanal.DIRECT_DEBIT;
	case "Paypal":
	    return Zahlungskanal.PAYPAL;
	case "Cash":
	    return Zahlungskanal.CASH;
	default:
	    return null;
	}

    }
}
