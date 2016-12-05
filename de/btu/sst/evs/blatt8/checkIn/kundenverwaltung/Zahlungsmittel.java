package de.btu.sst.evs.blatt8.checkIn.kundenverwaltung;

import de.btu.sst.evs.blatt8.checkIn.enums.Zahlungskanal;

public class Zahlungsmittel {

    // Bspw: Zahlungsmittel.DEBIT_CARD
    private Zahlungskanal zahlungskanal;
    // Bspw: Micheal Mustermann
    private String kontobesitzer;
    // Bspw: DE123456781234567890
    private String kontonummer;
    private String genehmigungsvermerk;
    private String notizen;

    public Zahlungsmittel(Zahlungskanal zahlungskanal, String accountBesitzer, String accountNummer,
	    String genehmigungsvermerk, String notizen) {
	super();
	this.zahlungskanal = zahlungskanal;
	this.kontobesitzer = accountBesitzer;
	this.kontonummer = accountNummer;
	this.genehmigungsvermerk = genehmigungsvermerk;
	this.notizen = notizen;
    }

    public Zahlungskanal getZahlungskanal() {
	return null;
    }

    public void setZahlungskanal(Zahlungskanal zahlungskanal) {
    }

    public String getKontobesitzer() {
	return null;
    }

    public void setKontobesitzer(String accountBesitzer) {
    }

    public String getKontonummer() {
	return null;
    }

    public void setKontonummer(String kontonummer) {
    }

    public String getGenehmigungsvermerk() {
	return null;
    }

    public void setGenehmigungsvermerk(String genehmigungsvermerk) {
    }

    public String getNotizen() {
	return null;
    }

    public void setNotizen(String notizen) {
    }

}
