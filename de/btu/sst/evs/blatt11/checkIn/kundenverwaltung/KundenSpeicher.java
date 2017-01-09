package de.btu.sst.evs.blatt11.checkIn.kundenverwaltung;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.btu.sst.evs.blatt11.checkIn.enums.CheckInKanal;
import de.btu.sst.evs.blatt11.checkIn.enums.Waehrung;
import de.btu.sst.evs.blatt11.checkIn.enums.Zahlungskanal;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Kunde;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Rabatt;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Zahlungsmittel;

/**
 * Diese Klasse stellt Beispieldaten zur Verfuegung. Eine Funktion zum Speichern
 * ist nicht implementiert.
 * 
 * @author Mathias Schubanz
 */

public class KundenSpeicher {

    private List<Rabatt> rabatte;
    private List<Kunde> kundenListe;
    private long laufendeKundennummer;

    public KundenSpeicher() {
	super();
	this.rabatte = new ArrayList<>();
	this.kundenListe = new ArrayList<>();
	this.laufendeKundennummer = 0;

	initialisiereRabattliste();
	initialisiereKundenliste();
    }

    /**
     * Die Funktion liefert Beispieldaten fuer 5 Kunden. Die Beispieldaten
     * werden in einer Liste mit Kunde Instanzen gespeichert.
     * 
     */
    public void initialisiereKundenliste() {

	// Kunde: kundenNr, eMail, name, vorname, nationalitaet,
	// bevorzugterCheckInKanal, zahlungsmittel,
	// praeferiertesZahlungsmilttel, rabatte
	//
	List<Zahlungsmittel> tmp_zahlungsmittel = new ArrayList<>();
	tmp_zahlungsmittel
		.add(new Zahlungsmittel(Zahlungskanal.DEBIT_CARD, "Manfred Müller", "DE1210020030123456789", "", ""));
	tmp_zahlungsmittel
		.add(new Zahlungsmittel(Zahlungskanal.VISA_CARD, "Manfred Müller", "DE1210020030987654321", "", ""));
	kundenListe.add(new Kunde(this.laufendeKundennummer++, "muellman@b-tu.de", "Müller", "Mannfred", "Deutschland",
		CheckInKanal.MOBILGERAET, tmp_zahlungsmittel,
		this.getRabattListe("NEU2016_5", "NEU2016_10", "NEU2016_25")));

	tmp_zahlungsmittel = new ArrayList<>();
	tmp_zahlungsmittel
		.add(new Zahlungsmittel(Zahlungskanal.DEBIT_CARD, "Timo Arsenti", "DE1210020030123456789", "", ""));
	tmp_zahlungsmittel
		.add(new Zahlungsmittel(Zahlungskanal.VISA_CARD, "Timo Arsenti", "DE1210020030987654321", "", ""));
	kundenListe.add(new Kunde(this.laufendeKundennummer++, "arsentim@b-tu.de", "Arsenti", "Timo", "Deutschland",
		CheckInKanal.MOBILGERAET, tmp_zahlungsmittel,
		this.getRabattListe("NEU2016_5", "NEU2016_10", "NEU2016_25")));

	tmp_zahlungsmittel = new ArrayList<>();
	tmp_zahlungsmittel
		.add(new Zahlungsmittel(Zahlungskanal.DEBIT_CARD, "Max Mustermann", "DE1210020030123456789", "", ""));
	tmp_zahlungsmittel
		.add(new Zahlungsmittel(Zahlungskanal.VISA_CARD, "Max Mustermann", "DE1210020030987654321", "", ""));
	kundenListe.add(new Kunde(this.laufendeKundennummer++, "mustemax@b-tu.de", "Mustermann", "Max", "Deutschland",
		CheckInKanal.MOBILGERAET, tmp_zahlungsmittel,
		this.getRabattListe("KULANZ_5_EURO", "KULANZ_5_PROZENT")));

	tmp_zahlungsmittel = new ArrayList<>();
	tmp_zahlungsmittel
		.add(new Zahlungsmittel(Zahlungskanal.DEBIT_CARD, "Max Mustermann", "DE1210020030123456789", "", ""));
	tmp_zahlungsmittel
		.add(new Zahlungsmittel(Zahlungskanal.VISA_CARD, "Max Mustermann", "DE1210020030987654321", "", ""));
	kundenListe.add(new Kunde(this.laufendeKundennummer++, "mustema1@b-tu.de", "Mustermann", "Maxi", "Deutschland",
		CheckInKanal.MOBILGERAET, tmp_zahlungsmittel,
		this.getRabattListe("NEU2016_5", "NEU2016_10", "NEU2016_25")));

	tmp_zahlungsmittel = new ArrayList<>();
	tmp_zahlungsmittel
		.add(new Zahlungsmittel(Zahlungskanal.DEBIT_CARD, "Max Mustermann", "DE1210020030123456789", "", ""));
	tmp_zahlungsmittel
		.add(new Zahlungsmittel(Zahlungskanal.VISA_CARD, "Max Mustermann", "DE1210020030987654321", "", ""));
	kundenListe.add(new Kunde(this.laufendeKundennummer++, "mustema2@b-tu.de", "Mustermann", "Martin",
		"Deutschland", CheckInKanal.MOBILGERAET, tmp_zahlungsmittel,
		this.getRabattListe("KULANZ_5_EURO", "KULANZ_5_PROZENT")));

    }

    private void initialisiereRabattliste() {

	// Rabatt: aktionsName, rabattEinheiten, RabattEinheit, gueltigkeit,
	// rabattcode, notiz

	rabatte.add(new Rabatt("SPONTAN_5_EURO", 5.0f, Waehrung.EURO, null, "SOFORT5E", ""));
	rabatte.add(new Rabatt("SPONTAN_10_EURO", 10.0f, Waehrung.EURO, null, "SOFORT10E", ""));
	rabatte.add(new Rabatt("SPONTAN_15_EURO", 15.0f, Waehrung.EURO, null, "SOFORT15E", ""));
	rabatte.add(new Rabatt("SPONTAN_20_EURO", 20.0f, Waehrung.EURO, null, "SOFORT20E", ""));
	rabatte.add(new Rabatt("SPONTAN_25_EURO", 25.0f, Waehrung.EURO, null, "SOFORT25E", ""));
	rabatte.add(new Rabatt("SPONTAN_50_EURO", 50.0f, Waehrung.EURO, null, "SOFORT50E", ""));
	rabatte.add(new Rabatt("SPONTAN_5_PROZ", 5.0f, Waehrung.PROZENT, null, "SOFORT5", ""));
	rabatte.add(new Rabatt("SPONTAN_10_PROZ", 10.0f, Waehrung.PROZENT, null, "SOFORT5", ""));
	rabatte.add(new Rabatt("SPONTAN_15_PROZ", 15.0f, Waehrung.PROZENT, null, "SOFORT5", ""));
	rabatte.add(new Rabatt("SPONTAN_20_PROZ", 20.0f, Waehrung.PROZENT, null, "SOFORT5", ""));
	rabatte.add(new Rabatt("SPONTAN_25_PROZ", 25.0f, Waehrung.PROZENT, null, "SOFORT5", ""));
	rabatte.add(new Rabatt("SPONTAN_50_PROZ", 50.0f, Waehrung.PROZENT, null, "SOFORT5", ""));
	rabatte.add(new Rabatt("ERÖFFNUNG16", 25.0f, Waehrung.PROZENT, new Date(0), false, "", "NEU2016_25", null));
	rabatte.add(new Rabatt("ERÖFFNUNG16", 10.0f, Waehrung.PROZENT, new Date(0), false, "", "NEU2016_10", null));
	rabatte.add(new Rabatt("ERÖFFNUNG16", 5.0f, Waehrung.PROZENT, new Date(0), false, "", "NEU2016_5", null));
	rabatte.add(new Rabatt("KULANZ", 5.0f, Waehrung.EURO, new Date(0), false, "", "KULANZ_5_EURO", null));
	rabatte.add(new Rabatt("KULANZ", 5.0f, Waehrung.PROZENT, new Date(0), false, "", "KULANZ_5_PROZENT", null));
    }

    public List<Rabatt> getRabattListe() {
	return rabatte;
    }

    public List<Kunde> getKundenListe() {
	return kundenListe;
    }

    public long getLaufendeKundennummer() {
	return laufendeKundennummer;
    }

    private List<Rabatt> getRabattListe(String... rabattCodes) {
	List<Rabatt> resultList = new ArrayList<>();

	for (String rabattCode : rabattCodes) {
	    for (Rabatt currRabatt : this.rabatte) {
		if (rabattCode.equals(currRabatt.getRabattCode())) {
		    Rabatt newRabattObj = new Rabatt(currRabatt.getAktionsname(), currRabatt.getRabattEinheiten(),
			    currRabatt.getWaehrung(), currRabatt.getGueltigkeit(), currRabatt.getRabattCode(),
			    currRabatt.getNotiz());
		    resultList.add(newRabattObj);
		}
	    }
	}
	return resultList;
    }

}
