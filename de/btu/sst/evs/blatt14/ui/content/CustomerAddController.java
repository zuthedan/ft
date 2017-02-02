package de.btu.sst.evs.blatt14.ui.content;

import java.net.URL;
import java.util.ResourceBundle;

import de.btu.sst.evs.blatt11.checkIn.enums.CheckInKanal;
import de.btu.sst.evs.blatt11.checkIn.enums.Waehrung;
import de.btu.sst.evs.blatt11.checkIn.enums.Zahlungskanal;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Kunde;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Kundenverwaltung;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Rabatt;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Zahlungsmittel;
import de.btu.sst.evs.blatt14.ui.util.Images;
import de.btu.sst.evs.blatt14.ui.BerlinAirApp;
import de.btu.sst.evs.blatt14.ui.util.Dialogs;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 * Diese Klasse erzeugt die Dialoge zur Kundenverwaltung. Dazu gehören die
 * Anzeige der Kunden sowie die Bearbeitung eines einzelnen Kundendatenstamms.
 * 
 * @author Mathias Schubanz
 */

public class CustomerAddController implements Initializable {

  /**
   * Die Kundenverwaltung.
   */
  private Kundenverwaltung customerManagement;

  /** Variable zum Speichern der Kunden */
  private ObservableList<Rabatt> observableDiscounts;
  /** Variable zum Speichern der Kunden */
  private ObservableList<Zahlungsmittel> observablePaymentMethods;

  @FXML
  private Button removeDiscountBtn;
  @FXML
  private Button removePaymentBtn;

  @FXML
  private ImageView addDiscountImg;
  @FXML
  private ImageView removeDiscountImg;
  @FXML
  private ImageView addPaymentImg;
  @FXML
  private ImageView removePaymentImg;

  // elements connected to the table-view showing the discounts
  @FXML
  private TextField customerNumber;
  @FXML
  private TextField customerName;
  @FXML
  private TextField customerFirstName;
  @FXML
  private TextField customerMail;
  @FXML
  private TextField customerNationality;
  @FXML
  private ComboBox<CheckInKanal> customerCheckInChannel;

  // elements connected to the table-view showing the discounts
  @FXML
  private TableView<Rabatt> discountView;
  @FXML
  private TableColumn<Rabatt, String> discountEventName;
  @FXML
  private TableColumn<Rabatt, Float> discountAmount;
  @FXML
  private TableColumn<Rabatt, Waehrung> discountCurrency;
  @FXML
  private TableColumn<Rabatt, String> discountCode;

  // elements connected to the table-view showing the discounts
  @FXML
  private TableView<Zahlungsmittel> paymentView;
  @FXML
  private TableColumn<Zahlungsmittel, Zahlungskanal> paymentChannel;
  @FXML
  private TableColumn<Zahlungsmittel, String> paymentAccountOwner;
  @FXML
  private TableColumn<Zahlungsmittel, String> paymentAccountNumber;
  @FXML
  private TableColumn<Zahlungsmittel, String> paymentWithdrawAllowance;

  /**
   * Der Standard-Konstruktor erzeugt eine neue Übungsverwaltung und zeigt die
   * Übungsgruppenauswahl an.
   */

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    BerlinAirApp.setCustomerAddController(this);
    this.initialiseCustomerListScene();
  }

  /**
   * Die Übersicht der Kunden wird erstellt um später eine schnelle Anzeige zu
   * ermöglichen. Hierzu werden alle notwendigen Objekte erstellt und
   * ausgerichtet.
   */
  private void initialiseCustomerListScene() {

    this.setImages();
    // Bind remove-button availability to a selection within the corresponding
    // view
    this.discountView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
      this.removeDiscountBtn.setDisable(newValue == null);
    });
    this.paymentView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      this.removePaymentBtn.setDisable(newValue == null);
    });

    discountEventName.setCellValueFactory(new PropertyValueFactory<>("aktionsname"));
    discountAmount.setCellValueFactory(new PropertyValueFactory<>("rabatteinheiten"));
    discountCurrency.setCellValueFactory(new PropertyValueFactory<>("waehrung"));
    discountCode.setCellValueFactory(new PropertyValueFactory<>("rabattCode"));
    paymentChannel.setCellValueFactory(new PropertyValueFactory<>("zahlungskanal"));
    paymentAccountOwner.setCellValueFactory(new PropertyValueFactory<>("kontobesitzer"));
    paymentAccountNumber.setCellValueFactory(new PropertyValueFactory<>("kontonummer"));
    paymentWithdrawAllowance.setCellValueFactory(new PropertyValueFactory<>("genehmigungsvermerk"));
    // fill combo box
    this.customerCheckInChannel.getItems().addAll(CheckInKanal.values());

    this.customerManagement = BerlinAirApp.getCustomerManagement();
    this.observableDiscounts = FXCollections.observableArrayList();
    this.discountView.setItems(this.observableDiscounts);
    this.observablePaymentMethods = FXCollections.observableArrayList();
    this.paymentView.setItems(this.observablePaymentMethods);
  }

  private void setImages() {
    Images.setImageOn(this.addDiscountImg, "res/img/add.png", 12, 12);
    Images.setImageOn(this.removeDiscountImg, "res/img/delete.png", 12, 12);
    Images.setImageOn(this.removePaymentImg, "res/img/delete.png", 12, 12);
    Images.setImageOn(this.addPaymentImg, "res/img/add.png", 12, 12);
  }

  @FXML
  public void onAddPaymentClicked() {
    Zahlungsmittel newPayment = Dialogs.showPaymentDialog();
    if (newPayment != null) {
      this.paymentView.getItems().add(newPayment);
    }
  }

  @FXML
  public void onAddDiscountClicked() {
    Rabatt newDiscount = Dialogs.showDiscountDialog();
    if (newDiscount != null) {
      this.discountView.getItems().add(newDiscount);
    }
  }

  @FXML
  public void onRemovePaymentClicked() {
    this.paymentView.getItems().remove(this.paymentView.getSelectionModel().getSelectedIndex());

  }

  @FXML
  public void onRemoveDiscountClicked() {
    this.discountView.getItems().remove(this.discountView.getSelectionModel().getSelectedIndex());
  }

  @FXML
  public void onCancelClicked() throws Exception {
    BerlinAirApp.getMainWindowController().showCustomerListView();
  }

  @FXML
  public void onAddCustomerClicked() throws Exception {

    if (this.customerName.getText().isEmpty() || this.customerFirstName.getText().isEmpty()
	|| this.customerNationality.getText().isEmpty() || this.customerMail.getText().isEmpty()
	|| this.customerCheckInChannel.getSelectionModel().isEmpty()) {
      Dialogs.showErrorDialog("Bitte füllen Sie alle Felder im oberen Bereich aus!");
    } else {
      /**
       * ************************** EvS Aufgabe 2 **************************
       * 
       * TODO
       * 
       * Hier muss ein Kunde angelegt werden aus den existierenden Eingaben. Im
       * Anschluss müssen die Rabatte aus der Tabelle mit den Rabatten
       * (discountView) dem Kunden hinzugefügt werden. Die Zahlungsmittel
       * (paymentView) müssen dem Kunden ebenso hinzugefügt werden.
       * 
       * 
       * ************************** EvS Aufgabe 2 **************************
       */

      BerlinAirApp.getMainWindowController()
	  .showCustomerListView(/**
				 * Hier muss die Referenz des neuen
				 * Kunden-Objekts übergeben werden
				 */);
      this.clearElements();
    }
  }

  public void refreshViews() {
    this.clearElements();
  }

  private void clearElements() {
    this.observableDiscounts.clear();
    this.observablePaymentMethods.clear();
    this.customerName.clear();
    this.customerFirstName.clear();
    this.customerMail.clear();
    this.customerNationality.clear();
  }

}
