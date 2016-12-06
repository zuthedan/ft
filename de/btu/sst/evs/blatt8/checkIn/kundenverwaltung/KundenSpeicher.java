package de.btu.sst.evs.blatt8.checkIn.kundenverwaltung;

import java.util.ArrayList;
import java.util.List;

import de.btu.sst.evs.blatt8.checkIn.enums.CheckInKanal;
import de.btu.sst.evs.blatt8.checkIn.enums.Waehrung;
import de.btu.sst.evs.blatt8.checkIn.enums.Zahlungskanal;

/**
 * Diese Klasse stellt Beispieldaten zur Verfuegung. Eine Funktion zum Speichern
 * ist nicht implementiert.
 * 
 * @author Mathias Schubanz
 */

public class KundenSpeicher {

    private List<Rabatt> rabattListe;
    private List<Kunde> kundenListe;
    private long laufendeKundennummer;

    public KundenSpeicher() {
	super();
	this.rabattListe = new ArrayList<>();
	this.kundenListe = new ArrayList<>();
	this.laufendeKundennummer = 0;

	initialisiereRabattliste();
	initialisiereKundenliste();
    }

    /**
     * Die Funktion liefert Beispieldaten für 10 Kunden. Die Beispieldaten
     * werden in einer Liste mit Kunde Instanzen zurückgeliefert. Ein Nutzer ist
     * selbst wieder eine Liste von Zeichenketten.
     * 
     * Der Aufbau einer Nutzer-Liste ist folgender: Die erste Zeichenkette gibt
     * den Nutzertyp "Student" oder "Tutor" an. Danach folgt der Benutzername,
     * das Passwort, der Name, der Vorname, die Emailadresse und bei Studenten
     * die Matrikelnummer sowie der Studiengang des Nutzers.
     * 
     * @return Liste von Nutzern, wobei Nutzer wiederum Listen von Zeichenketten
     *         sind
     */
    public void initialisiereKundenliste() {

	// Kunde: kundenNr, eMail, name, vorname, nationalitaet,
	// bevorzugterCheckInKanal, zahlungsmittel,
	// praeferiertesZahlungsmilttel, rabatte
	//
	kundenListe.add(new Kunde(this.laufendeKundennummer++, "muellman@b-tu.de", "Müller", "Mannfred", "Deutschland"));
	
	List<Zahlungsmittel> tmp_zahlungsmittel = new ArrayList<>();
	tmp_zahlungsmittel.add(new Zahlungsmittel(Zahlungskanal.DEBIT_CARD, "Timo Arsenti", "DE1210020030123456789", "", ""));
	tmp_zahlungsmittel.add(new Zahlungsmittel(Zahlungskanal.VISA_CARD, "Timo Arsenti", "DE1210020030987654321", "", ""));			
	kundenListe.add(new Kunde(this.laufendeKundennummer++, "arsentim@b-tu.de", "Arsenti", "Timo", "Deutschland", CheckInKanal.MOBILGERAET, tmp_zahlungsmittel, null));
	
	tmp_zahlungsmittel = new ArrayList<>();
	tmp_zahlungsmittel.add(new Zahlungsmittel(Zahlungskanal.DEBIT_CARD, "Max Mustermann", "DE1210020030123456789", "", ""));
	tmp_zahlungsmittel.add(new Zahlungsmittel(Zahlungskanal.VISA_CARD, "Max Mustermann", "DE1210020030987654321", "", ""));			
	kundenListe.add(new Kunde(this.laufendeKundennummer++, "mustemax@b-tu.de", "Mustermann", "Max", "Deutschland", CheckInKanal.MOBILGERAET, tmp_zahlungsmittel, null));
	
	tmp_zahlungsmittel = new ArrayList<>();
	tmp_zahlungsmittel.add(new Zahlungsmittel(Zahlungskanal.DEBIT_CARD, "Max Mustermann", "DE1210020030123456789", "", ""));
	tmp_zahlungsmittel.add(new Zahlungsmittel(Zahlungskanal.VISA_CARD, "Max Mustermann", "DE1210020030987654321", "", ""));			
	kundenListe.add(new Kunde(this.laufendeKundennummer++, "mustema1@b-tu.de", "Mustermann", "Maxi", "Deutschland", CheckInKanal.MOBILGERAET, tmp_zahlungsmittel, null));
	
	tmp_zahlungsmittel = new ArrayList<>();
	tmp_zahlungsmittel.add(new Zahlungsmittel(Zahlungskanal.DEBIT_CARD, "Max Mustermann", "DE1210020030123456789", "", ""));
	tmp_zahlungsmittel.add(new Zahlungsmittel(Zahlungskanal.VISA_CARD, "Max Mustermann", "DE1210020030987654321", "", ""));			
	kundenListe.add(new Kunde(this.laufendeKundennummer++, "mustema2@b-tu.de", "Mustermann", "Martin", "Deutschland", CheckInKanal.MOBILGERAET, tmp_zahlungsmittel, null));

    }

    private void initialisiereRabattliste() {

	// Rabatt: aktionsName, rabattEinheiten, RabattEinheit, gueltigkeit,
	// rabattcode, notiz

	rabattListe.add(new Rabatt("SPONTAN_5_EURO", 5.0f, Waehrung.EURO, null, "SOFORT5E", ""));
	rabattListe.add(new Rabatt("SPONTAN_10_EURO", 10.0f, Waehrung.EURO, null, "SOFORT10E", ""));
	rabattListe.add(new Rabatt("SPONTAN_15_EURO", 15.0f, Waehrung.EURO, null, "SOFORT15E", ""));
	rabattListe.add(new Rabatt("SPONTAN_20_EURO", 20.0f, Waehrung.EURO, null, "SOFORT20E", ""));
	rabattListe.add(new Rabatt("SPONTAN_25_EURO", 25.0f, Waehrung.EURO, null, "SOFORT25E", ""));
	rabattListe.add(new Rabatt("SPONTAN_50_EURO", 50.0f, Waehrung.EURO, null, "SOFORT50E", ""));
	rabattListe.add(new Rabatt("SPONTAN_5_PROZ", 5.0f, Waehrung.PROZENT, null, "SOFORT5", ""));
	rabattListe.add(new Rabatt("SPONTAN_10_PROZ", 10.0f, Waehrung.PROZENT, null, "SOFORT5", ""));
	rabattListe.add(new Rabatt("SPONTAN_15_PROZ", 15.0f, Waehrung.PROZENT, null, "SOFORT5", ""));
	rabattListe.add(new Rabatt("SPONTAN_20_PROZ", 20.0f, Waehrung.PROZENT, null, "SOFORT5", ""));
	rabattListe.add(new Rabatt("SPONTAN_25_PROZ", 25.0f, Waehrung.PROZENT, null, "SOFORT5", ""));
	rabattListe.add(new Rabatt("SPONTAN_50_PROZ", 50.0f, Waehrung.PROZENT, null, "SOFORT5", ""));

    }

    public List<Rabatt> getRabattListe() {
	return rabattListe;
    }

    public List<Kunde> getKundenListe() {
	return kundenListe;
    }

    public long getLaufendeKundennummer() {
        return laufendeKundennummer;
    }
    
    

}