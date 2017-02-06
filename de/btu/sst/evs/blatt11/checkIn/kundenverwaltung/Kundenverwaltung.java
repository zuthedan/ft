package de.btu.sst.evs.blatt11.checkIn.kundenverwaltung;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.btu.sst.evs.blatt11.checkIn.enums.CheckInKanal;
import de.btu.sst.evs.blatt11.checkIn.enums.SerializableUID;
import de.btu.sst.evs.blatt11.checkIn.enums.Waehrung;
import de.btu.sst.evs.blatt11.checkIn.enums.Zahlungskanal;
import de.btu.sst.evs.blatt11.checkIn.exceptions.KundeNichtGefundenException;
import de.btu.sst.evs.blatt11.checkIn.exceptions.RabattNichtGefundenException;
import de.btu.sst.evs.blatt11.persistence.CSVStorageManager;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Kunde;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.KundenSpeicher;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Rabatt;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Zahlungsmittel;

public class Kundenverwaltung {

  private CSVStorageManager storageManager;
  private final KundenSpeicher kundenspeicher;
  private final List<Kunde> kundenstamm;
  private final Map<Long, Kunde> kundenIndex;
  private long laufendeKundennummer;

  public Kundenverwaltung() {
    super();
    this.kundenspeicher = new KundenSpeicher();
    this.kundenstamm = this.kundenspeicher.getKundenListe();
    this.kundenIndex = new HashMap<>();
    this.aktualisiereKundenIndex();
    this.laufendeKundennummer = this.kundenspeicher.getLaufendeKundennummer();
  }

  private void aktualisiereKundenIndex() {
    this.kundenIndex.clear();
    for (Kunde currKunde : this.kundenstamm) {
      this.kundenIndex.put(currKunde.getKundennummer(), currKunde);
    }
  }

  public Kunde registriereNeukunde(String name, String vorname, String nationalitaet, String eMail,
      CheckInKanal checkInKanal) {
    Kunde neuKunde = new Kunde(this.laufendeKundennummer++, eMail, name, vorname, nationalitaet, checkInKanal);
    kundenstamm.add(neuKunde);
    kundenIndex.put(neuKunde.getKundennummer(), neuKunde);
    return neuKunde;
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
      Rabatt neuerRabattInEuro = new Rabatt("spontanRabatt", rabattHoehe, Waehrung.EURO, gueltigkeit, rabattCode, "");
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

  public void widerrufeRabattFuerEinenKunde(long kundenNr, String aktionsCode)
      throws KundeNichtGefundenException, RabattNichtGefundenException {

    if (this.istBereitsKunde(kundenNr)) {
      if (kundenIndex.get(kundenNr).getRabatt(aktionsCode) != null) {
	kundenIndex.get(kundenNr).getRabatt(aktionsCode).setWiderrufen(true);
      }
      throw new RabattNichtGefundenException("Kunde nicht gefunden");
    }

    throw new KundeNichtGefundenException("Kunde nicht gefunden");
  }

  public void widerrufeRabattFuerAlleKunden(String aktionsCode) {

    for (Kunde k : kundenstamm) {
      if (k.getRabatt(aktionsCode) != null) {
	k.getRabatt(aktionsCode).setWiderrufen(true);
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

  public List<Rabatt> getRabatt(long kundenNr) throws KundeNichtGefundenException {

    if (this.istBereitsKunde(kundenNr)) {
      return kundenIndex.get(kundenNr).getRabatte();
    }

    throw new KundeNichtGefundenException("Kunde nicht gefunden");
  }

  // public Zahlungsmittel getPraeferiertesZahlungsmittel(long kundenNr)
  // throws KundeNichtGefundenException {
  // if (this.istBereitsKunde(kundenNr)) {
  // return kundenIndex.get(kundenNr).getPraeferiertesZahlungsmittel();
  // }
  //
  // throw new KundeNichtGefundenException("Kunde nicht gefunden");
  //
  // }
  //
  // public void setPraeferiertesZahlungsmittel(long kundenNr, Zahlungsmittel
  // zahlungsmittel)
  // throws KundeNichtGefundenException, ZahlungsmittelNichtGefundenException
  // {
  //
  // if (this.istBereitsKunde(kundenNr)) {
  // if
  // (kundenIndex.get(kundenNr).getZahlungsmittel().contains(zahlungsmittel))
  // {
  // kundenIndex.get(kundenNr).setPraeferiertesZahlungsmittel(zahlungsmittel);
  // return;
  // }
  // throw new ZahlungsmittelNichtGefundenException("Kunde nicht gefunden");
  // }
  //
  // throw new KundeNichtGefundenException("Kunde nicht gefunden");
  // }

  private boolean istBereitsKunde(long kundenNr) {
    return kundenIndex.get(kundenNr) != null;
  }

  public List<Kunde> getKunden() {
    return this.kundenstamm;
  }

  public void aktualisiereKundendaten(Long kundenNr, String name, String vorname, String eMail, String nationalitaet)
      throws KundeNichtGefundenException {
    this.aktualisiereKundendaten(kundenNr, name, vorname, eMail, nationalitaet, null);
  }

  public void aktualisiereKundendaten(Long kundenNr, String name, String vorname, String eMail, String nationalitaet,
      CheckInKanal praeferierterCheckInKanal) throws KundeNichtGefundenException {
    if (this.istBereitsKunde(kundenNr)) {
      this.aktualisiereKundendaten(kundenIndex.get(kundenNr), name, vorname, eMail, nationalitaet,
	  praeferierterCheckInKanal);
    } else {
      throw new KundeNichtGefundenException("Kunde nicht gefunden");
    }
  }

  private void aktualisiereKundendaten(Kunde currKunde, String name, String vorname, String eMail, String nationalitaet,
      CheckInKanal kanal) {
    currKunde.setName(name);
    currKunde.setVorname(vorname);
    currKunde.setEmail(eMail);
    currKunde.setNationalitaet(nationalitaet);
    if (kanal != null) {
      currKunde.setPraeferierterCheckInKanal(kanal);
    }
  }

  public void speichereDaten(File file) {
    this.getStorageManager().storeObjects(file, this.kundenstamm);
  }

  /**
   * Lädt alle Kunden aus einer Datei als Liste von String Arrays. Diese werden
   * im Anschluss aufgesplittet in die einzelnen Teile der Liste, die den
   * einzelnen Kunden entsprechen und einzeln deserialisiert.
   */
  public void ladeKundenAusDatei(File file) {
    ArrayList<Kunde> resultList = new ArrayList<>();

    List<String[]> kundenDaten = this.getStorageManager().loadObjects(file);
    // extract complete customer entries
    int customerStartIndex = 0;
    for (int currIndex = 1; currIndex < kundenDaten.size(); currIndex++) {
      if (kundenDaten.get(currIndex)[0].equals(SerializableUID.KUNDE.toString())) {
	deserializeCustomter(kundenDaten, customerStartIndex, currIndex, resultList);
	customerStartIndex = currIndex;
      }
    }
    // extract last customer in the last
    deserializeCustomter(kundenDaten, customerStartIndex, kundenDaten.size(), resultList);
    this.kundenstamm.clear();
    this.kundenstamm.addAll(resultList);
    this.laufendeKundennummer = this.kundenstamm.size();
    this.aktualisiereKundenIndex();

    // System.out.println(Arrays.deepToString(this.kundenstamm.toArray()));

  }

  private void deserializeCustomter(List<String[]> kundenDaten, int customerStartIndex, int customerEndIndex,
      ArrayList<Kunde> resultList) {
    Kunde currKunde = new Kunde();
    currKunde.deserializeIncludingAggregates(
	new ArrayList<String[]>(kundenDaten.subList(customerStartIndex, customerEndIndex)));
    resultList.add(currKunde);
  }

  private CSVStorageManager getStorageManager() {
    if (this.storageManager == null) {
      this.storageManager = new CSVStorageManager();
    }
    return this.storageManager;
  }

  public Kunde ladeKundendaten(long kundennummer) {
    if (istBereitsKunde(kundennummer) == true) {
      return this.kundenIndex.get(kundennummer);
    }
    return null;
  }

}
