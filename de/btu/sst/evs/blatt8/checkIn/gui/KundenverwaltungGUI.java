package de.btu.sst.evs.blatt8.checkIn.gui;

import com.sun.javafx.collections.ObservableListWrapper;
import de.btu.sst.evs.blatt8.checkIn.enums.Waehrung;
import de.btu.sst.evs.blatt8.checkIn.enums.Zahlungskanal;
import de.btu.sst.evs.blatt8.checkIn.exceptions.KundeNichtGefundenException;
import de.btu.sst.evs.blatt8.checkIn.kundenverwaltung.Kunde;
import de.btu.sst.evs.blatt8.checkIn.kundenverwaltung.Kundenverwaltung;
import de.btu.sst.evs.blatt8.checkIn.kundenverwaltung.Rabatt;
import de.btu.sst.evs.blatt8.checkIn.kundenverwaltung.Zahlungsmittel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Diese Klasse erzeugt die Dialoge zur Kundenverwaltung. Dazu gehören die
 * Anzeige der Kunden sowie die Bearbeitung eines einzelnen Kundendatenstamms.
 * 
 * @author Mathias Schubanz
 */

public class KundenverwaltungGUI {

    private static final int CDATA_TABLEVIEW_WIDTH = 860;
    private static final int CDATA_STAGE_WIDTH = 900;
    private static final int CDATA_STAGE_HEIGHT = 600;

    private static final int CLIST_STAGE_WIDTH = 900;
    private static final int CLIST_STAGE_PADDING = 20;
    private static final int CLIST_STAGE_HEIGHT = 600;

    /**
     * Die Kundenverwaltung.
     */
    private Kundenverwaltung kundenverwaltung;

    /** Variablen zum Speichern lokaler GUI elemente */
    private Stage mainStage;
    // elements for showing the customer list
    private final Scene customerListScene;
    private final GridPane customerGrid;
    private final Text customerSceneTitle;
    private Button customerSubmitButton;
    private final TableView<Kunde> customerTableView;
    private ObservableList<Kunde> observableCustomerList;

    // gui elements for showing the customer data
    private final Scene customerDataScene;
    private final Text customerDataSceneTitle;
    private final TableView<Rabatt> rabattTableView;
    private final TableView<Zahlungsmittel> zahlungsmittelTableView;
    private final GridPane customerDataGrid;

    private final TextField cData_nationalityField;
    private final TextField cData_eMailField;
    private final TextField cData_firstNameField;
    private final TextField cData_nameField;
    private final TextField cData_customerNumberField;

    /**
     * Der Standard-Konstruktor erzeugt eine neue Übungsverwaltung und zeigt die
     * Übungsgruppenauswahl an.
     */
    public KundenverwaltungGUI(Stage primaryStage) {

	this.mainStage = primaryStage;

	this.kundenverwaltung = new Kundenverwaltung();
	this.customerGrid = new GridPane();
	this.customerGrid.setAlignment(Pos.TOP_CENTER);
	this.customerGrid.setHgap(5);
	this.customerGrid.setVgap(10);
	this.customerGrid.setPadding(new Insets(10, 10, 10, 10));
	this.customerGrid.setMinHeight(CLIST_STAGE_HEIGHT + CLIST_STAGE_PADDING);
	this.customerGrid.setMinWidth(CLIST_STAGE_WIDTH + CLIST_STAGE_PADDING);

	this.customerListScene = new Scene(customerGrid, CLIST_STAGE_WIDTH, CLIST_STAGE_HEIGHT);
	this.customerListScene.getStylesheets().add(Main.CSS_RESSOURCE.toExternalForm());
	this.customerTableView = new TableView<>();
	this.customerSceneTitle = new Text();

	this.initialiseCustomerListScene();

	this.customerDataGrid = new GridPane();
	this.customerDataGrid.setAlignment(Pos.TOP_CENTER);
	this.customerDataGrid.setHgap(10);
	this.customerDataGrid.setVgap(15);
	this.customerDataGrid.setPadding(new Insets(10, 10, 10, 10));

	this.customerDataScene = new Scene(customerDataGrid, CDATA_STAGE_WIDTH, CDATA_STAGE_HEIGHT);
	this.customerDataScene.getStylesheets().add(Main.CSS_RESSOURCE.toExternalForm());
	this.customerDataSceneTitle = new Text();
	this.rabattTableView = new TableView<>();
	this.zahlungsmittelTableView = new TableView<>();

	this.cData_nationalityField = new TextField("nationality");
	this.cData_eMailField = new TextField("eMail");
	this.cData_firstNameField = new TextField("firstName");
	this.cData_nameField = new TextField("surname");
	this.cData_customerNumberField = new TextField("cNumber");
	this.cData_customerNumberField.setDisable(true);

	this.initialiseCustomerDataScene();

	this.mainStage.setTitle("Kundenmanagement");
	this.showCustomerListScene();
    }

    /**
     * Die Übersicht der Kunden wird erstellt um später eine schnelle Anzeige zu
     * ermöglichen. Hierzu werden alle notwendigen Objekte erstellt und
     * ausgerichtet.
     */
    private void initialiseCustomerListScene() {

	customerSceneTitle.setText("Wählen Sie einen Kunden aus!");
	customerSceneTitle.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
	customerSceneTitle.setId("titleText");

	customerGrid.add(customerSceneTitle, 0, 0);

	customerTableView.setMinWidth(CLIST_STAGE_WIDTH - CLIST_STAGE_PADDING * 2);
	customerTableView.setMinHeight(CLIST_STAGE_HEIGHT - 95);
	customerTableView.setEditable(false);

	TableColumn<Kunde, String> lectureTableColumn = new TableColumn<>("Name");
	lectureTableColumn.setMinWidth(100);
	lectureTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
	customerTableView.getColumns().add(lectureTableColumn);

	TableColumn<Kunde, String> nameTC = new TableColumn<>("Vorname");
	nameTC.setMinWidth(100);
	nameTC.setCellValueFactory(new PropertyValueFactory<>("vorname"));
	customerTableView.getColumns().add(nameTC);

	TableColumn<Kunde, String> eMailTC = new TableColumn<>("eMail");
	eMailTC.setMinWidth(140);
	eMailTC.setCellValueFactory(new PropertyValueFactory<>("Email"));
	customerTableView.getColumns().add(eMailTC);

	TableColumn<Kunde, String> nationalityTC = new TableColumn<>("Nationalität");
	nationalityTC.setMinWidth(100);
	nationalityTC.setCellValueFactory(new PropertyValueFactory<>("nationalitaet"));
	customerTableView.getColumns().add(nationalityTC);

	TableColumn<Kunde, String> preferedCheckInTC = new TableColumn<>("Präf. CheckIn-Kanal");
	preferedCheckInTC.setMinWidth(100);
	preferedCheckInTC.setCellValueFactory(new PropertyValueFactory<>("praeferierterCheckInKanal"));
	customerTableView.getColumns().add(preferedCheckInTC);

	TableColumn<Kunde, String> paymentMethodTC = new TableColumn<>("Zahlungsmittel");
	paymentMethodTC.setMinWidth(140);
	paymentMethodTC.setCellValueFactory(currCustomerCDF -> this.formatPaymentMethods(currCustomerCDF));
	customerTableView.getColumns().add(paymentMethodTC);

	TableColumn<Kunde, String> discountTC = new TableColumn<>("Rabatte");
	discountTC.setMinWidth(190);
	discountTC.setCellValueFactory(currCustomer -> this.formatDiscounts(currCustomer));
	customerTableView.getColumns().add(discountTC);
	// customerTableView.set

	customerGrid.add(customerTableView, 0, 1);
	// fill table
	observableCustomerList = new ObservableListWrapper<>(kundenverwaltung.getKunden());
	customerTableView.setItems(observableCustomerList);

	customerSubmitButton = new Button("Kundendaten bearbeiten");
	customerSubmitButton.setOnAction(actionEvent -> {
	    ObservableList<Kunde> selectedCustomers = customerTableView.getSelectionModel().getSelectedItems();
	    if (selectedCustomers.size() == 0) {
		Dialogs.showErrorDialog("Sie haben keinen Kunden gewählt!");
	    } else if (selectedCustomers.size() > 1) {
		Dialogs.showErrorDialog("Bitte wählen Sie nur einen Kunden aus!");
	    } else if (selectedCustomers.get(0) == null) {
		Dialogs.showErrorDialog("Der ausgewählte Eintrag enthält keinen Kunden!");
	    } else {
		this.showCustomerDataScene(selectedCustomers.get(0));
	    }
	});

	HBox hbBtn = new HBox(10);
	hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
	hbBtn.getChildren().add(customerSubmitButton);
	customerGrid.add(hbBtn, 0, 2);

	customerSubmitButton.setText("Kundendaten bearbeiten");
    }

    private SimpleStringProperty formatPaymentMethods(CellDataFeatures<Kunde, String> currCustomerCDF) {

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

    private SimpleStringProperty formatDiscounts(CellDataFeatures<Kunde, String> currCustomerCDF) {

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

    /**
     * Die Bearbeitungsdialog für die Kundendaten wird erstellt. Hierzu werden
     * alle notwendigen Objekte erstellt und ausgerichtet.
     */
    private void initialiseCustomerDataScene() {

	customerDataSceneTitle.setId("titleText");
	customerDataSceneTitle.setText("Kundendaten bearbeiten ");
	customerDataSceneTitle.setTextAlignment(TextAlignment.CENTER);
	customerDataSceneTitle.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));

	customerDataGrid.add(customerDataSceneTitle, 0, 1);

	final GridPane formGrid = new GridPane();
	formGrid.setAlignment(Pos.CENTER_LEFT);
	formGrid.setHgap(85);
	formGrid.setVgap(10);
	formGrid.setPadding(new Insets(10, 0, 10, 0));

	Label customerNumberLabel = new Label("Kundennummer");
	customerNumberLabel.setId("cNumber");
	Label customerNameLabel = new Label("Name");
	customerNameLabel.setId("cName");
	Label customerFirstNameLabel = new Label("Vorname");
	customerFirstNameLabel.setId("cFirstName");
	Label customerEmailLabel = new Label("eMail");
	customerEmailLabel.setId("cEmail");
	Label customerNationalityLabel = new Label("Nationalität");
	customerNationalityLabel.setId("cNationality");

	formGrid.add(customerNumberLabel, 0, 0);
	formGrid.add(cData_customerNumberField, 1, 0);
	formGrid.add(customerNameLabel, 0, 1);
	formGrid.add(cData_nameField, 1, 1);
	formGrid.add(customerFirstNameLabel, 0, 2);
	formGrid.add(cData_firstNameField, 1, 2);
	formGrid.add(customerEmailLabel, 0, 3);
	formGrid.add(cData_eMailField, 1, 3);
	formGrid.add(customerNationalityLabel, 0, 4);
	formGrid.add(cData_nationalityField, 1, 4);

	TableColumn<Rabatt, String> rabattAktionsnameTC = new TableColumn<>("Aktionsname");
	rabattAktionsnameTC.setMinWidth(240);
	rabattAktionsnameTC.setCellValueFactory(new PropertyValueFactory<>("aktionsname"));
	rabattTableView.getColumns().add(rabattAktionsnameTC);

	TableColumn<Rabatt, Float> rabattEinheitenTC = new TableColumn<>("Rabattmenge");
	rabattEinheitenTC.setMinWidth(220);
	rabattEinheitenTC.setCellValueFactory(new PropertyValueFactory<>("rabattEinheiten"));
	rabattTableView.getColumns().add(rabattEinheitenTC);

	TableColumn<Rabatt, Waehrung> rabattWaehrungTC = new TableColumn<>("Währung");
	rabattWaehrungTC.setMinWidth(190);
	rabattWaehrungTC.setCellValueFactory(new PropertyValueFactory<>("waehrung"));
	rabattTableView.getColumns().add(rabattWaehrungTC);

	TableColumn<Rabatt, String> rabattCodeTC = new TableColumn<>("Code");
	rabattCodeTC.setMinWidth(220);
	rabattCodeTC.setCellValueFactory(new PropertyValueFactory<>("rabattCode"));
	rabattTableView.getColumns().add(rabattCodeTC);

	rabattTableView.setMinWidth(CDATA_TABLEVIEW_WIDTH);
	rabattTableView.setEditable(false);

	TableColumn<Zahlungsmittel, Zahlungskanal> zahlungsmittelKanalTC = new TableColumn<>("Zahlungskanal");
	zahlungsmittelKanalTC.setMinWidth(160);
	zahlungsmittelKanalTC.setCellValueFactory(new PropertyValueFactory<>("zahlungskanal"));
	zahlungsmittelTableView.getColumns().add(zahlungsmittelKanalTC);

	TableColumn<Zahlungsmittel, String> zahlungsmittelKontobesitzerTC = new TableColumn<>("Kontobesiter");
	zahlungsmittelKontobesitzerTC.setMinWidth(190);
	zahlungsmittelKontobesitzerTC.setCellValueFactory(new PropertyValueFactory<>("kontobesitzer"));
	zahlungsmittelTableView.getColumns().add(zahlungsmittelKontobesitzerTC);

	TableColumn<Zahlungsmittel, String> zahlungsmittelKontonummerTC = new TableColumn<>("Kontonummer");
	zahlungsmittelKontonummerTC.setMinWidth(240);
	zahlungsmittelKontonummerTC.setCellValueFactory(new PropertyValueFactory<>("kontonummer"));
	zahlungsmittelTableView.getColumns().add(zahlungsmittelKontonummerTC);

	TableColumn<Zahlungsmittel, String> zahlungsmittelGenehmigungTC = new TableColumn<>("Genehmigungsvermerk");
	zahlungsmittelGenehmigungTC.setMinWidth(280);
	zahlungsmittelGenehmigungTC.setCellValueFactory(new PropertyValueFactory<>("genehmigungsvermerk"));
	zahlungsmittelTableView.getColumns().add(zahlungsmittelGenehmigungTC);

	zahlungsmittelTableView.setMinWidth(CDATA_TABLEVIEW_WIDTH);
	zahlungsmittelTableView.setEditable(false);

	customerDataGrid.add(formGrid, 0, 2);
	customerDataGrid.add(rabattTableView, 0, 3);
	customerDataGrid.add(zahlungsmittelTableView, 0, 4);

	Button backButton = new Button("Abbrechen");
	backButton.setOnAction(actionEvent -> this.showCustomerListScene());
	Button saveButton = new Button("Speichern");
	saveButton.setOnAction(actionEvent -> {
	    this.saveCustomerData();
	    this.showCustomerListScene();
	});

	HBox hbBtn = new HBox(10);
	hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
	hbBtn.getChildren().add(saveButton);
	hbBtn.getChildren().add(backButton);

	customerDataGrid.add(hbBtn, 0, 5);
    }

    private void saveCustomerData() {
	try {
	    this.kundenverwaltung.aktualisiereKundendaten(Long.valueOf(this.cData_customerNumberField.getText()),
		    this.cData_nameField.getText(), this.cData_firstNameField.getText(),
		    this.cData_eMailField.getText(), this.cData_nationalityField.getText());
	} catch (KundeNichtGefundenException kngException) {
	    Dialogs.showErrorDialog("Speichern fehlgeschlagen, der Kunde ist nicht mehr verfügbar.");
	}

    }

    /**
     * Eine Übersicht der Kunden wird angezeigt. Ein Kunde kann ausgewählt
     * werden. Nach der Auswahl eines Kunden werden alle mögliche Aktionen für
     * diese Gruppe angezeigt.
     */
    private Scene showCustomerListScene() {

	// workaround to update all data
	customerTableView.getColumns().get(0).setVisible(false);
	customerTableView.getColumns().get(0).setVisible(true);

	mainStage.setScene(customerListScene);
	return customerListScene;
    }

    /**
     * TODO Hier soll ein Dialog angezeigt werden, der es ermöglicht einen
     * neunen Kunden anzulegen.
     */
    // private Scene zeigeNeukundenDialog() {
    // return null;
    // }

    /**
     * Für den ausgewählten Kunden wird ein Dialog zum Bearbeiten der Daten
     * angezeigt.
     * 
     * @param selectedCustomer
     *            : Kunde - zu bearbeitender Kunde
     */
    private Scene showCustomerDataScene(final Kunde selectedCustomer) {

	this.cData_nameField.setText(selectedCustomer.getName());
	this.cData_firstNameField.setText(selectedCustomer.getVorname());
	this.cData_customerNumberField.setText(String.valueOf(selectedCustomer.getKundennummer()));
	this.cData_eMailField.setText(selectedCustomer.getEmail());
	this.cData_nationalityField.setText(selectedCustomer.getNationalitaet());

	mainStage.setScene(customerDataScene);
	this.zahlungsmittelTableView.setItems(null);
	if (selectedCustomer.getZahlungsmittel() != null) {
	    this.zahlungsmittelTableView.setItems(new ObservableListWrapper<>(selectedCustomer.getZahlungsmittel()));
	}
	this.rabattTableView.setItems(null);
	if (selectedCustomer.getRabatte() != null) {
	    this.rabattTableView.setItems(new ObservableListWrapper<>(selectedCustomer.getRabatte()));
	}
	return customerDataScene;
    }
}
