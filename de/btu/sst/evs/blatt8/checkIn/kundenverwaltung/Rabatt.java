package de.btu.sst.evs.blatt8.checkIn.kundenverwaltung;

import java.util.Date;
import java.util.List;

import de.btu.sst.evs.blatt8.checkIn.enums.Waehrung;

public class Rabatt {

    private String aktionsName;
    private float rabattEinheiten;
    private Waehrung waehrung;
    // Eine Liste von Objekten Zahlungsmittel, auf die dieser Rabatt eingeschränkt ist.
    private List<Zahlungsmittel> eingeschraenkteZahlungsmittel;
    private Date gueltigkeit;
    private boolean widerrufen;
    private String notiz;
    private String rabattCode;

    public Rabatt(String aktionsName, float rabattInGeldeinheiten, Waehrung waehrung, Date gueltigkeit,
	    boolean widerrufen, String notiz, String rabattCode, List<Zahlungsmittel> eingeschränkteZahlungsmittel) {
	super();
	this.aktionsName = aktionsName;
	this.rabattEinheiten = rabattInGeldeinheiten;
	this.waehrung = waehrung;
	this.gueltigkeit = gueltigkeit;
	this.widerrufen = widerrufen;
	this.notiz = notiz;
	this.rabattCode = rabattCode;
	this.eingeschraenkteZahlungsmittel = eingeschränkteZahlungsmittel;
    }

    public Rabatt(String aktionsName, float rabattEinheiten, Waehrung waehrung, Date gueltigkeit, boolean widerrufen,
	    String notiz, String rabattCode) {
	super();
	this.aktionsName = aktionsName;
	this.rabattEinheiten = rabattEinheiten;
	this.waehrung = waehrung;
	this.gueltigkeit = gueltigkeit;
	this.widerrufen = widerrufen;
	this.notiz = notiz;
	this.rabattCode = rabattCode;
    }

    public Rabatt(String aktionsName, float rabattEinheiten, Waehrung waehrung, Date gueltigkeit, String rabattCode,
	    String notiz) {
	super();
	this.aktionsName = "";
	this.rabattEinheiten = rabattEinheiten;
	this.waehrung = waehrung;
	this.gueltigkeit = gueltigkeit;
	this.widerrufen = false;
	this.notiz = notiz;
	this.rabattCode = rabattCode;
    }

    public String getAktionsName() {
	return null;
    }

    public void setAktionsName(String aktionsName) {
    }

    public float getRabattEinheiten() {
	return 0;
    }

    public void setRabattEinheiten(float rabattEinheiten) {
    }

    public Waehrung getWaehrung() {
	return null;
    }

    public void setWaehrung(Waehrung waehrung) {
    }

    public Date getGueltigkeit() {
	return null;
    }

    public void setGueltigkeit(Date gueltigkeit) {
    }

    public boolean isWiderrufen() {
	return false;
    }

    public void setWiderrufen(boolean widerrufen) {
    }

    public String getNotiz() {
	return null;
    }

    public void setNotiz(String notiz) {
    }

    public String getRabattCode() {
	return null;
    }

    public void setRabattCode(String rabattCode) {
    }

    public List<Zahlungsmittel> getZahlungsmitteleinschraenkung() {
	return null;
    }

    public void setZahlungsmitteleingeschraenkung(List<Zahlungsmittel> eingeschraenkteZahlungsmittel) {
    }

}
