package de.btu.sst.evs.blatt11.checkIn.kundenverwaltung;

import java.util.ArrayList;
import java.util.List;

import de.btu.sst.evs.blatt11.checkIn.enums.CheckInKanal;
import de.btu.sst.evs.blatt11.checkIn.enums.SerializableUID;
import de.btu.sst.evs.blatt11.checkIn.exceptions.RabattNichtGefundenException;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Rabatt;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Zahlungsmittel;
import de.btu.sst.evs.blatt11.persistence.ICSV_Serializable;

public class Kunde implements ICSV_Serializable {

    private long kundenNr;
    private String eMail;
    private String name;
    private String vorname;
    private String nationalitaet;
    private CheckInKanal praeferierterCheckInKanal;
    private List<Zahlungsmittel> zahlungsmittel;
    // private Zahlungsmittel praeferiertesZahlungsmilttel;
    private List<Rabatt> rabatte;

    public Kunde(long kundenNr, String eMail, String name, String vorname, String nationalitaet,
	    CheckInKanal bevorzugterCheckInKanal, List<Zahlungsmittel> zahlungsmittelListe, List<Rabatt> rabatte) {
	super();

	this.kundenNr = kundenNr;
	this.eMail = eMail;
	this.name = name;
	this.vorname = vorname;
	this.nationalitaet = nationalitaet;
	this.praeferierterCheckInKanal = bevorzugterCheckInKanal;
	this.zahlungsmittel = zahlungsmittelListe;
	this.rabatte = rabatte;
    }

    public Kunde(long kundenNr, String eMail, String name, String vorname, String nationalitaet,
	    CheckInKanal bevorzugterCheckInKanal, List<Zahlungsmittel> zahlungsmittelListe) {
	super();

	this.kundenNr = kundenNr;
	this.eMail = eMail;
	this.name = name;
	this.vorname = vorname;
	this.nationalitaet = nationalitaet;
	this.praeferierterCheckInKanal = bevorzugterCheckInKanal;
	this.zahlungsmittel = zahlungsmittelListe;
	this.rabatte = new ArrayList<>();
    }

    public Kunde(long kundenNr, String eMail, String name, String vorname, String nationalitaet,
	    CheckInKanal bevorzugterCheckInKanal) {
	super();

	this.kundenNr = kundenNr;
	this.eMail = eMail;
	this.name = name;
	this.vorname = vorname;
	this.nationalitaet = nationalitaet;
	this.praeferierterCheckInKanal = bevorzugterCheckInKanal;
	this.zahlungsmittel = new ArrayList<>();
	this.rabatte = new ArrayList<>();
    }

    public Kunde(long kundenNr, String eMail, String name, String vorname, String nationalitaet) {
	super();

	this.kundenNr = kundenNr;
	this.eMail = eMail;
	this.name = name;
	this.vorname = vorname;
	this.nationalitaet = nationalitaet;
	this.praeferierterCheckInKanal = CheckInKanal.WEBBASIERT;
	this.zahlungsmittel = new ArrayList<>();
	this.rabatte = new ArrayList<>();
    }

    // this constructor is used for creating empty objects which are meant to be
    // filled by loading customer data from a file
    public Kunde() {
	super();
	this.rabatte = new ArrayList<>();
	this.zahlungsmittel = new ArrayList<>();
    }

    public long getKundennummer() {
	return 0;
    }

    public void setKundennummer(long kundennummer) {
    }

    public String getEmail() {
	return null;
    }

    public void setEmail(String email) {
    }

    public String getName() {
	return null;
    }

    public void setName(String name) {
    }

    public String getVorname() {
	return null;
    }

    public void setVorname(String vorname) {
    }

    public String getNationalitaet() {
	return null;
    }

    public void setNationalitaet(String nationalitaet) {
    }

    public CheckInKanal getPraeferierterCheckInKanal() {
	return null;
    }

    public void setPraeferierterCheckInKanal(CheckInKanal bevorzugterCheckInKanal) {
    }

    public List<Zahlungsmittel> getZahlungsmittel() {
	return null;
    }

    public void addZahlungsmittel(Zahlungsmittel zahlungsmittel) {
    }

    public void loescheZahlungsmittel(Zahlungsmittel zahlungsmittel) {
    }

    public List<Rabatt> getRabatte() {
	return null;
    }

    public Rabatt getRabatt(String aktionsCode) {
	return null;
    }

    public void gewaehreRabatt(Rabatt rabatt) {
    }

    public void widerrufeRabatt(String rabattcode) throws RabattNichtGefundenException {
    }

    @Override
    public List<String[]> serializeIncludingAggregates() {

	List<String[]> resultList = new ArrayList<>();
	resultList.add(this.serialize());

	for (Rabatt currRabatt : rabatte) {
	    resultList.addAll(currRabatt.serializeIncludingAggregates());
	}

	for (Zahlungsmittel currPaymentMethod : zahlungsmittel) {
	    resultList.addAll(currPaymentMethod.serializeIncludingAggregates());
	}

	return resultList;
    }

    @Override
    public void deserializeIncludingAggregates(List<String[]> values) {

	// iterate through the list of serialized objects
	for (String[] serializedValue : values) {
	    // this is the customer
	    if (serializedValue[0].equals(SerializableUID.KUNDE.toString())) {
		this.deserialize(serializedValue);
		// this is a payment method
	    } else if (serializedValue[0].equals(SerializableUID.ZAHLUNGSMITTEL.toString())) {
		Zahlungsmittel currPaymentMethod = new Zahlungsmittel();
		currPaymentMethod.deserialize(serializedValue);
		this.zahlungsmittel.add(currPaymentMethod);
		// this is a discount
	    } else if (serializedValue[0].equals(SerializableUID.RABATT.toString())) {
		Rabatt currDiscount = new Rabatt();
		currDiscount.deserialize(serializedValue);
		this.rabatte.add(currDiscount);
	    } else {
		System.err.println("Found Unexpected Entry during Deserialization -- " + serializedValue[0]);
	    }
	}
    }

    @Override
    public String[] serialize() {
	// TODO
	return null;
    }

    @Override
    public void deserialize(String[] serializedCustomer) {
	// TODO
    }
}
