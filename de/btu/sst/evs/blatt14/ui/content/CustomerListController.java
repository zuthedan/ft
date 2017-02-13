package de.btu.sst.evs.blatt14.ui.content;

import java.net.URL;
import java.util.ResourceBundle;

import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Kunde;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Kundenverwaltung;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Rabatt;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Zahlungsmittel;
import de.btu.sst.evs.blatt14.ui.BerlinAirApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Diese Klasse erzeugt die Dialoge zur Kundenverwaltung. Dazu gehören die
 * Anzeige der Kunden sowie die Bearbeitung eines einzelnen Kundendatenstamms.
 * 
 * @author Mathias Schubanz
 */

public class CustomerListController implements Initializable {

  /**
   * Die Kundenverwaltung.
   */
  private Kundenverwaltung customerManagement;

  /** Variable zum Speichern der Kunden */
  private ObservableList<Kunde> observableCustomerList;

  @FXML
  private Button editCustomer;

  @FXML
  private TableView<Kunde> customerView;
  @FXML
  private TableColumn<Kunde, String> nameTC;
  @FXML
  private TableColumn<Kunde, String> firstNameTC;
  @FXML
  private TableColumn<Kunde, String> mailTC;
  @FXML
  private TableColumn<Kunde, String> nationalityTC;
  @FXML
  private TableColumn<Kunde, String> preferedCheckInTC;
  @FXML
  private TableColumn<Kunde, String> paymentMethodTC;
  @FXML
  private TableColumn<Kunde, String> discountTC;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.initialiseCustomerListScene();
  }

  /**
   * Die Übersicht der Kunden wird erstellt um später eine schnelle Anzeige zu
   * ermöglichen. Hierzu werden alle notwendigen Objekte erstellt und
   * ausgerichtet.
   */
  private void initialiseCustomerListScene() {

    BerlinAirApp.setCustomerListController(this);

    this.customerView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
      this.editCustomer.setDisable(newValue == null);
    });

    nameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
    firstNameTC.setCellValueFactory(new PropertyValueFactory<>("vorname"));
    mailTC.setCellValueFactory(new PropertyValueFactory<>("Email"));
    nationalityTC.setCellValueFactory(new PropertyValueFactory<>("nationalitaet"));
    preferedCheckInTC.setCellValueFactory(new PropertyValueFactory<>("praeferierterCheckInKanal"));
    paymentMethodTC.setCellValueFactory(currCustomerCDF -> this.formatPaymentMethod(currCustomerCDF));
    discountTC.setCellValueFactory(currCustomerCDF -> this.formatDiscount(currCustomerCDF));

    this.customerManagement = BerlinAirApp.getCustomerManagement();
    this.observableCustomerList = FXCollections.observableArrayList(this.customerManagement.getKunden());
    this.customerView.setItems(this.observableCustomerList);
  }

  private SimpleStringProperty formatPaymentMethod(CellDataFeatures<Kunde, String> currCustomerCDF) {

    if (currCustomerCDF.getValue().getZahlungsmittel() != null) {
      String resultString = "";
      for (Zahlungsmittel currPayment : currCustomerCDF.getValue().getZahlungsmittel()) {
	if (!("".equals(resultString))) {
	  resultString += ", ";
	}
	resultString += currPayment;
      }
      return new SimpleStringProperty(resultString);
    }
    return new SimpleStringProperty("");
  }

  private SimpleStringProperty formatDiscount(CellDataFeatures<Kunde, String> currCustomerCDF) {

    if (currCustomerCDF.getValue().getRabatte() != null) {
      String resultString = "";
      for (Rabatt currRabat : currCustomerCDF.getValue().getRabatte()) {
	if (!("".equals(resultString))) {
	  resultString += ", ";
	}
	resultString += currRabat.getRabattCode();
      }
      return new SimpleStringProperty(resultString);
    }
    return new SimpleStringProperty("");
  }

  public void onCustomerSelected() throws Exception {
    BerlinAirApp.getMainWindowController()
	.setCurrentlySelectedCustomer(this.customerView.getSelectionModel().getSelectedItem());
    BerlinAirApp.getMainWindowController().showCustomerDataView();
  }

  public void onSearchCustomerClicked() throws Exception {
    BerlinAirApp.getMainWindowController().showCustomerSearchView();
  }

  public void refreshViews() {
    this.observableCustomerList.clear();
    this.observableCustomerList.addAll(this.customerManagement.getKunden());
  }

  public void setSelectedCustomer(Kunde selectedCustomer) {
    this.customerView.getSelectionModel().select(selectedCustomer);
  }
}
