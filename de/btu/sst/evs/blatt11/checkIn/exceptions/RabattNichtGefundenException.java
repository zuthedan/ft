package de.btu.sst.evs.blatt11.checkIn.exceptions;

public class RabattNichtGefundenException extends Exception {

    /**
     * Expected to be declared as Exception implements the Serializable interface
     */
    private static final long serialVersionUID = 1L;

    public RabattNichtGefundenException(String message) {
	super(message);
    }
    
}
