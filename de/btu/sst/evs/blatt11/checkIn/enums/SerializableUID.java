package de.btu.sst.evs.blatt11.checkIn.enums;

import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Kunde;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Rabatt;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Zahlungsmittel;

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
