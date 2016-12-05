package de.btu.sst.evs.blatt8.checkIn.exceptions;

public class KundeNichtGefundenException extends Exception {

    /**
     * Expected to be declared as Exception implements the Serializable interface
     */
    private static final long serialVersionUID = 0L;

    public KundeNichtGefundenException(String message) {
	super(message);
    }
}
