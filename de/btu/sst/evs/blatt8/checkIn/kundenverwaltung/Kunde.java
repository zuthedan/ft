package de.btu.sst.evs.blatt8.checkIn.kundenverwaltung;

import java.util.ArrayList;
import java.util.List;

import de.btu.sst.evs.blatt8.checkIn.enums.CheckInKanal;
import de.btu.sst.evs.blatt8.checkIn.exceptions.RabattNichtGefundenException;

public class Kunde {

    private long kundenNr;
    private String eMail;
    private String name;
    private String vorname;
    private String nationalitaet;
    private CheckInKanal praeferierterCheckInKanal;
    private List<Zahlungsmittel> zahlungsmittel;
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

}
