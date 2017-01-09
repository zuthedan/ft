package de.btu.sst.evs.blatt11.checkIn.kundenverwaltung;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.btu.sst.evs.blatt11.checkIn.enums.SerializableUID;
import de.btu.sst.evs.blatt11.checkIn.enums.Waehrung;
import de.btu.sst.evs.blatt11.persistence.ICSVSerializable;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Zahlungsmittel;

public class Rabatt implements ICSVSerializable {

    private String aktionsname;
    private float rabattEinheiten;
    private Waehrung waehrung;
    private List<Zahlungsmittel> eingeschraenkteZahlungsmittel;
    private Date gueltigkeit;
    private boolean widerrufen;
    private String notiz;
    private String rabattCode;

    public Rabatt(String aktionsname, float rabattEinheiten, Waehrung waehrung, Date gueltigkeit, boolean widerrufen,
	    String notiz, String rabattCode, List<Zahlungsmittel> eingeschränkteZahlungsmittel) {
	super();
	this.aktionsname = aktionsname;
	this.rabattEinheiten = rabattEinheiten;
	this.waehrung = waehrung;
	this.gueltigkeit = gueltigkeit;
	this.widerrufen = widerrufen;
	this.notiz = notiz;
	this.rabattCode = rabattCode;
	this.eingeschraenkteZahlungsmittel = eingeschränkteZahlungsmittel;
    }

    public Rabatt(String aktionsname, float rabattEinheiten, Waehrung waehrung, Date gueltigkeit, boolean widerrufen,
	    String notiz, String rabattCode) {
	super();
	this.aktionsname = aktionsname;
	this.rabattEinheiten = rabattEinheiten;
	this.waehrung = waehrung;
	this.gueltigkeit = gueltigkeit;
	this.widerrufen = widerrufen;
	this.notiz = notiz;
	this.rabattCode = rabattCode;
	this.eingeschraenkteZahlungsmittel = new ArrayList<>();
    }

    public Rabatt(String aktionsname, float rabattEinheiten, Waehrung waehrung, Date gueltigkeit, String rabattCode,
	    String notiz) {
	super();
	this.aktionsname = aktionsname;
	this.rabattEinheiten = rabattEinheiten;
	this.waehrung = waehrung;
	this.gueltigkeit = gueltigkeit;
	this.widerrufen = false;
	this.notiz = notiz;
	this.rabattCode = rabattCode;
	this.eingeschraenkteZahlungsmittel = new ArrayList<>();
    }

    public Rabatt() {
	super();
	this.eingeschraenkteZahlungsmittel = new ArrayList<>();
    }

    public String getAktionsname() {
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

    @Override
    public List<String[]> serializeIncludingAggregates() {
	List<String[]> resultList = new ArrayList<>();

	resultList.add(this.serialize());

	for (Zahlungsmittel currPaymentMethod : eingeschraenkteZahlungsmittel) {
	    resultList.addAll(currPaymentMethod.serializeIncludingAggregates());
	}

	return resultList;

    }

    @Override
    public void deserializeIncludingAggregates(List<String[]> values) {

	// deserialize discount
	this.deserialize(values.get(0));

	// deserialize aggregates
	for (String[] restrictedPaymentMethod : values.subList(1, values.size())) {
	    Zahlungsmittel currPaymentMethod = new Zahlungsmittel();
	    currPaymentMethod.deserialize(restrictedPaymentMethod);
	    this.eingeschraenkteZahlungsmittel.add(currPaymentMethod);
	}
    }

    @Override
    public String[] serialize() {

	return null;
    }

    @Override
    public void deserialize(String[] values) {
    }

}
