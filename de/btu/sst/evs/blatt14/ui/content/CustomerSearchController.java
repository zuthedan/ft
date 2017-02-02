package de.btu.sst.evs.blatt14.ui.content;

import java.net.URL;
import java.util.ResourceBundle;

import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Kunde;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Kundenverwaltung;
import de.btu.sst.evs.blatt14.ui.BerlinAirApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Diese Klasse erzeugt die Dialoge zur Kundenverwaltung. Dazu gehören die
 * Anzeige der Kunden sowie die Bearbeitung eines einzelnen Kundendatenstamms.
 * 
 * @author Mathias Schubanz
 */

public class CustomerSearchController implements Initializable {

  /**
   * Die Kundenverwaltung.
   */
  private Kundenverwaltung customerManagement;

  /** Variable zum Speichern der Kunden */
  private ObservableList<Kunde> observableCustomers;
  private FilteredList<Kunde> filteredCustomers;

  // elements connected to the table-view showing the discounts
  @FXML
  private TextField customerName;
  @FXML
  private TextField customerMail;
  @FXML
  private TextField customerNationality;

  // elements connected to the table-view showing the discounts
  @FXML
  private TableView<Kunde> customerView;
  @FXML
  private TableColumn<Kunde, String> customerNameTC;
  @FXML
  private TableColumn<Kunde, String> customerFirstNameTC;
  @FXML
  private TableColumn<Kunde, String> customerMailTC;
  @FXML
  private TableColumn<Kunde, String> customerNationalityTC;

  /**
   * Der Standard-Konstruktor erzeugt eine neue Übungsverwaltung und zeigt die
   * Übungsgruppenauswahl an.
   */

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.initialiseCustomerSearchScene();
    // this.setCurrentCustomer(BerlinAirApp.getMainWindowController().getCurrentlySelectedCustomer());
  }

  /**
   * Die Übersicht der Kunden wird erstellt um später eine schnelle Anzeige zu
   * ermöglichen. Hierzu werden alle notwendigen Objekte erstellt und
   * ausgerichtet.
   */
  private void initialiseCustomerSearchScene() {
    
    BerlinAirApp.setCustomerSearchController(this);

    customerNameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
    customerFirstNameTC.setCellValueFactory(new PropertyValueFactory<>("vorname"));
    customerMailTC.setCellValueFactory(new PropertyValueFactory<>("Email"));
    customerNationalityTC.setCellValueFactory(new PropertyValueFactory<>("nationalitaet"));

    this.customerManagement = BerlinAirApp.getCustomerManagement();
    this.observableCustomers = FXCollections.observableArrayList(this.customerManagement.getKunden());

    // Wrap ObservableList in a FilteredList (initially display all data).
    filteredCustomers = new FilteredList<>(observableCustomers, customer -> true);
    // Set the filter Predicate whenever the filter changes.
    customerName.textProperty()
	.addListener((observable, oldValue, newValue) -> this.updateList(observable, oldValue, newValue));
    customerMail.textProperty()
	.addListener((observable, oldValue, newValue) -> this.updateList(observable, oldValue, newValue));
    customerNationality.textProperty()
	.addListener((observable, oldValue, newValue) -> this.updateList(observable, oldValue, newValue));

    // Wrap the FilteredList in a SortedList.
    SortedList<Kunde> sortedData = new SortedList<>(filteredCustomers);
    // Bind the SortedList comparator to the TableView comparator.
    // Otherwise, sorting the TableView would have no effect.
    sortedData.comparatorProperty().bind(customerView.comparatorProperty());
    // Add sorted (and filtered) data to the table.
    this.customerView.setItems(sortedData);

  }

  private void updateList(ObservableValue<? extends String> observable, String oldValue, String newValue) {

    if (customerName.getText().isEmpty() && customerMail.getText().isEmpty()
	&& customerNationality.getText().isEmpty()) {
      filteredCustomers.setPredicate(currCustomer -> true);
    } else {

      filteredCustomers.setPredicate(currCustomer -> {
	boolean result = true;
	// Compare first name and last name of every person with filter text.
	if (customerName.getText().isEmpty() == false) {
	  String lowerCaseFilter = customerName.getText().toLowerCase();
	  if (currCustomer.getName().toLowerCase().indexOf(lowerCaseFilter) == -1
	      && currCustomer.getVorname().toLowerCase().indexOf(lowerCaseFilter) == -1) {
	    result = false; // Filter matches first name.
	  }
	}
	// Compare inserted mail with the available mail adresses of the customers
	if (customerMail.getText().isEmpty() == false) {
	  String lowerCaseFilter = customerMail.getText().toLowerCase();
	  if (currCustomer.getEmail().toLowerCase().indexOf(lowerCaseFilter) == -1) {
	    result = false; // Filter matches first name.
	  }
	}

	// Compare nationalities.
	if (customerNationality.getText().isEmpty() == false) {
	  String lowerCaseFilter = customerNationality.getText().toLowerCase();
	  if (currCustomer.getNationalitaet().toLowerCase().indexOf(lowerCaseFilter) == -1) {
	    result = false; // Filter matches first name.
	  }
	}
	return result;
      });
    }
  }

  public void onOpenDetailedCustomerListClicked() throws Exception {
    BerlinAirApp.getMainWindowController().showCustomerListView();
  }
  
  public void onCustomerSelected() throws Exception {

    if (this.customerView.getSelectionModel().getSelectedItem() != null) {
      BerlinAirApp.getMainWindowController()
	  .setCurrentlySelectedCustomer(this.customerView.getSelectionModel().getSelectedItem());
      BerlinAirApp.getMainWindowController().showCustomerDataView();
    }
  }
  
  public void refreshViews() {
    this.observableCustomers.clear();
    this.observableCustomers.addAll(this.customerManagement.getKunden());
  }

}
