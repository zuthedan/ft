package de.btu.sst.evs.blatt11.checkIn.exceptions;

public class ZahlungsmittelNichtGefundenException extends Exception {
    
    /**
     * Expected to be declared as Exception implements the Serializable interface
     */
    private static final long serialVersionUID = 1L;

    public ZahlungsmittelNichtGefundenException(String message) {
	super(message);
    }

}
