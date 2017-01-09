package de.btu.sst.evs.blatt11.checkIn.kundenverwaltung;

import java.util.ArrayList;
import java.util.List;

import de.btu.sst.evs.blatt11.checkIn.enums.SerializableUID;
import de.btu.sst.evs.blatt11.checkIn.enums.Zahlungskanal;
import de.btu.sst.evs.blatt11.persistence.ICSV_Serializable;

public class Zahlungsmittel implements ICSV_Serializable {

 // Bspw: Zahlungsmittel.DEBIT_CARD
    private Zahlungskanal zahlungskanal;
    // Bspw: Micheal Mustermann
    private String kontobesitzer;
    // Bspw: DE123456781234567890
    private String kontonummer;
    private String genehmigungsvermerk;
    private String notizen;

    public Zahlungsmittel(Zahlungskanal zahlungskanal, java.lang.String accountBesitzer, String accountNummer,
	    java.lang.String genehmigungsvermerk, String notizen) {
	super();
	this.zahlungskanal = zahlungskanal;
	this.kontobesitzer = accountBesitzer;
	this.kontonummer = accountNummer;
	this.genehmigungsvermerk = genehmigungsvermerk;
	this.notizen = notizen;
    }

    public Zahlungsmittel() {
	super();
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

    public String toString() {
	return zahlungskanal.toString();
    }

    @Override
    public List<String[]> serializeIncludingAggregates() {
	List<String[]> resultList = new ArrayList<>();
	resultList.add(this.serialize());
	return resultList;
    }

    @Override
    public void deserializeIncludingAggregates(List<String[]> values) {
	this.deserialize(values.get(0));
    }

    @Override
    public String[] serialize() {
	// TODO
	return null;
    }

    @Override
    public void deserialize(String[] serializedPaymentMethod) {
	// TODO
    }

}
