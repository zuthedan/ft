package de.btu.sst.evs.blatt8.checkIn.kundenverwaltung;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.btu.sst.evs.blatt8.checkIn.enums.CheckInKanal;
import de.btu.sst.evs.blatt8.checkIn.enums.Waehrung;
import de.btu.sst.evs.blatt8.checkIn.enums.Zahlungskanal;
import de.btu.sst.evs.blatt8.checkIn.exceptions.KundeNichtGefundenException;
import de.btu.sst.evs.blatt8.checkIn.exceptions.RabattNichtGefundenException;

public class Kundenverwaltung {

    private KundenSpeicher kundenspeicher;
    private List<Kunde> kundenstamm;
    private Map<Long, Kunde> kundenIndex;
    private long laufendeKundennummer;

    public Kundenverwaltung() {
	super();
	this.kundenspeicher = new KundenSpeicher();
	this.kundenstamm = this.kundenspeicher.getKundenListe();
	this.kundenIndex = new HashMap<>();
	this.laufendeKundennummer = this.kundenspeicher.getLaufendeKundennummer();
    }

    public void registriereNeukunde(String name, String vorname, String nationalitaet, String eMail) {
	Kunde neuKunde = new Kunde(this.laufendeKundennummer++, eMail, name, vorname, nationalitaet);
	kundenstamm.add(neuKunde);
	kundenIndex.put(neuKunde.getKundennummer(), neuKunde);
    }

    public void aenderePraefCheckInKanal(long kundenNr, CheckInKanal kanal) throws KundeNichtGefundenException {

	if (this.istBereitsKunde(kundenNr)) {
	    kundenIndex.get(kundenNr).setPraeferierterCheckInKanal(kanal);
	    return;
	}

	throw new KundeNichtGefundenException("Kunde nicht gefunden");
    }

    public void aendereNachname(long kundenNr, String nachnameAlt, String nachnameNeu)
	    throws KundeNichtGefundenException {

	if (this.istBereitsKunde(kundenNr) && kundenIndex.get(kundenNr).getName().equals(nachnameAlt)) {
	    kundenIndex.get(kundenNr).setName(nachnameNeu);
	    return;
	}

	throw new KundeNichtGefundenException("Kunde nicht gefunden");
    }

    public void aendereEMail(long kundenNr, String eMailAlt, String eMailNeu) throws KundeNichtGefundenException {

	if (this.istBereitsKunde(kundenNr) && kundenIndex.get(kundenNr).getEmail().equals(eMailAlt)) {
	    kundenIndex.get(kundenNr).setEmail(eMailNeu);
	    return;
	}

	throw new KundeNichtGefundenException("Kunde nicht gefunden");
    }

    public void gewaehreRabatt(Long kundenNr, Zahlungsmittel zahlungsmittel, Rabatt rabatt)
	    throws KundeNichtGefundenException {

	if (this.istBereitsKunde(kundenNr)) {
	    kundenIndex.get(kundenNr).gewaehreRabatt(rabatt);
	    return;
	}

	throw new KundeNichtGefundenException("Kunde nicht gefunden");
    }

    public void gewaehreRabattInEuro(Long kundenNr, Zahlungsmittel zahlungsmittel, float rabattHoehe, Date gueltigkeit,
	    String rabattCode) throws KundeNichtGefundenException {

	if (this.istBereitsKunde(kundenNr)) {
	    Rabatt neuerRabattInEuro = new Rabatt("spontanRabatt", rabattHoehe, Waehrung.EURO, gueltigkeit, rabattCode,
		    "");
	    kundenIndex.get(kundenNr).gewaehreRabatt(neuerRabattInEuro);
	    return;
	}

	throw new KundeNichtGefundenException("Kunde nicht gefunden");
    }

    public long ladeKundendaten(String eMail) {
	for (Kunde k : kundenstamm) {
	    if (k.getEmail() != null && k.getEmail().equals(eMail)) {
		return k.getKundennummer();
	    }
	}
	return -1;
    }

    public void widerrufeRabattFuerEinenKunde(long kundenNr, String rabattCode)
	    throws KundeNichtGefundenException, RabattNichtGefundenException {

	if (this.istBereitsKunde(kundenNr)) {
	    if (kundenIndex.get(kundenNr).getRabatt(rabattCode) != null) {
		kundenIndex.get(kundenNr).getRabatt(rabattCode).setWiderrufen(true);
	    }
	    throw new RabattNichtGefundenException("Kunde nicht gefunden");
	}

	throw new KundeNichtGefundenException("Kunde nicht gefunden");
    }

    public void widerrufeRabattFuerAlleKunden(String rabattCode) {

	for (Kunde k : kundenstamm) {
	    if (k.getRabatt(rabattCode) != null) {
		k.getRabatt(rabattCode).setWiderrufen(true);
	    }
	}
    }

    public void fuegeZahlungsmittelHinzu(long kundenNr, Zahlungskanal zahlungskanal, String kontoBesitzer,
	    String kontonummer, String genehmigungsvermerk, String notizen) throws KundeNichtGefundenException {
	if (this.istBereitsKunde(kundenNr)) {
	    Zahlungsmittel neuesZahlungsmittel = new Zahlungsmittel(zahlungskanal, kontoBesitzer, kontonummer,
		    genehmigungsvermerk, notizen);
	    kundenIndex.get(kundenNr).addZahlungsmittel(neuesZahlungsmittel);
	    return;
	}

	throw new KundeNichtGefundenException("Kunde nicht gefunden");
    }

    public void loescheZahlungsmittel(long kundenNr, Zahlungsmittel zahlungsmittel) throws KundeNichtGefundenException {
	if (this.istBereitsKunde(kundenNr)) {
	    kundenIndex.get(kundenNr).loescheZahlungsmittel(zahlungsmittel);
	    return;
	}

	throw new KundeNichtGefundenException("Kunde nicht gefunden");
    }

    public List<Zahlungsmittel> getZahlungsmittel(long kundenNr) throws KundeNichtGefundenException {

	if (this.istBereitsKunde(kundenNr)) {
	    return kundenIndex.get(kundenNr).getZahlungsmittel();
	}

	throw new KundeNichtGefundenException("Kunde nicht gefunden");
    }

//    public Zahlungsmittel getPraeferiertesZahlungsmittel(long kundenNr) throws KundeNichtGefundenException {
//	if (this.istBereitsKunde(kundenNr)) {
//	    return kundenIndex.get(kundenNr).getPraeferiertesZahlungsmittel();
//	}
//
//	throw new KundeNichtGefundenException("Kunde nicht gefunden");
//
//    }
//
//    public void setPraeferiertesZahlungsmittel(long kundenNr, Zahlungsmittel zahlungsmittel)
//	    throws KundeNichtGefundenException, ZahlungsmittelNichtGefundenException {
//
//	if (this.istBereitsKunde(kundenNr)) {
//	    if (kundenIndex.get(kundenNr).getZahlungsmittel().contains(zahlungsmittel)) {
//		kundenIndex.get(kundenNr).setPraeferiertesZahlungsmittel(zahlungsmittel);
//		return;
//	    }
//	    throw new ZahlungsmittelNichtGefundenException("Kunde nicht gefunden");
//	}
//
//	throw new KundeNichtGefundenException("Kunde nicht gefunden");
//    }

    private boolean istBereitsKunde(long kundenNr) {
	return kundenIndex.get(kundenNr) != null;
    }

    public List<Kunde> getKunden() {
	return this.kundenstamm;
    }

}
