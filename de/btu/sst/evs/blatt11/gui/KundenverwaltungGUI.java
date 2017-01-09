package de.btu.sst.evs.blatt11.gui;

import java.io.File;
import java.util.Arrays;

import com.sun.javafx.collections.ObservableListWrapper;

import de.btu.sst.evs.blatt11.checkIn.enums.Waehrung;
import de.btu.sst.evs.blatt11.checkIn.enums.Zahlungskanal;
import de.btu.sst.evs.blatt11.checkIn.exceptions.KundeNichtGefundenException;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Kunde;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Kundenverwaltung;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Rabatt;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Zahlungsmittel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
    private static final int CLIST_STAGE_PADDING = 30;
    private static final int CLIST_STAGE_HEIGHT = 600;

    /**
     * Die Kundenverwaltung.
     */
    private Kundenverwaltung kundenverwaltung;

    /** Variablen zum Speichern lokaler GUI elemente */
    private Stage mainStage;
    // elements for showing the customer list
    private final Scene cList_scene;
    private final Text cList_sceneTitle;
    private final GridPane cList_grid;
    private Button cList_openCDataForm;
    private final TableView<Kunde> cList_TableView;
    private final ObservableList<Kunde> observableCustomerList;

    // gui elements for showing the customer data form
    private final GridPane cDataForm_grid;
    private final Scene cDataForm_Scene;
    private final Text cDataForm_sceneTitle;
    private final TextField cDataForm_nationalityField;
    private final TextField cDataForm_eMailField;
    private final TextField cDataForm_firstNameField;
    private final TextField cDataForm_nameField;
    private final TextField cDataForm_customerNumberField;
    private final TableView<Rabatt> discountTableView;
    private final TableView<Zahlungsmittel> oaymentMethodTableView;

    /**
     * Der Standard-Konstruktor erzeugt eine neue Übungsverwaltung und zeigt die
     * Übungsgruppenauswahl an.
     */
    public KundenverwaltungGUI(Stage primaryStage) {

	this.mainStage = primaryStage;
	this.kundenverwaltung = new Kundenverwaltung();
	this.observableCustomerList = FXCollections.observableArrayList();
	this.observableCustomerList.addAll(this.kundenverwaltung.getKunden());

	this.cList_grid = new GridPane();
	this.cList_grid.setAlignment(Pos.TOP_CENTER);
	this.cList_grid.setHgap(5);
	this.cList_grid.setVgap(10);
	this.cList_grid.setPadding(new Insets(10, 10, 10, 10));
	this.cList_grid.setMinHeight(CLIST_STAGE_HEIGHT);
	this.cList_grid.setMinWidth(CLIST_STAGE_WIDTH);

	this.cList_scene = new Scene(cList_grid, CLIST_STAGE_WIDTH, CLIST_STAGE_HEIGHT);
	this.cList_scene.getStylesheets().add(Main.CSS_RESSOURCE.toExternalForm());
	this.cList_TableView = new TableView<>();
	this.cList_sceneTitle = new Text();

	this.initialiseCustomerListScene();

	this.cDataForm_grid = new GridPane();
	this.cDataForm_grid.setAlignment(Pos.TOP_CENTER);
	this.cDataForm_grid.setHgap(10);
	this.cDataForm_grid.setVgap(15);
	this.cDataForm_grid.setPadding(new Insets(10, 10, 10, 10));

	this.cDataForm_Scene = new Scene(cDataForm_grid, CDATA_STAGE_WIDTH, CDATA_STAGE_HEIGHT);
	this.cDataForm_Scene.getStylesheets().add(Main.CSS_RESSOURCE.toExternalForm());
	this.cDataForm_sceneTitle = new Text();
	this.discountTableView = new TableView<>();
	this.oaymentMethodTableView = new TableView<>();

	this.cDataForm_nationalityField = new TextField("nationality");
	this.cDataForm_eMailField = new TextField("eMail");
	this.cDataForm_firstNameField = new TextField("firstName");
	this.cDataForm_nameField = new TextField("surname");
	this.cDataForm_customerNumberField = new TextField("cNumber");
	this.cDataForm_customerNumberField.setDisable(true);

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

	cList_sceneTitle.setText("Wählen Sie einen Kunden aus!");
	cList_sceneTitle.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
	cList_sceneTitle.setId("titleText");

	cList_grid.add(cList_sceneTitle, 0, 0);

	cList_TableView.setMinWidth(CLIST_STAGE_WIDTH - CLIST_STAGE_PADDING * 2);
	cList_TableView.setMinHeight(CLIST_STAGE_HEIGHT - 95);
	cList_TableView.setEditable(false);

	TableColumn<Kunde, String> lectureTableColumn = new TableColumn<>("Name");
	lectureTableColumn.setMinWidth(100);
	lectureTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
	cList_TableView.getColumns().add(lectureTableColumn);

	TableColumn<Kunde, String> nameTC = new TableColumn<>("Vorname");
	nameTC.setMinWidth(100);
	nameTC.setCellValueFactory(new PropertyValueFactory<>("vorname"));
	cList_TableView.getColumns().add(nameTC);

	TableColumn<Kunde, String> eMailTC = new TableColumn<>("eMail");
	eMailTC.setMinWidth(140);
	eMailTC.setCellValueFactory(new PropertyValueFactory<>("Email"));
	cList_TableView.getColumns().add(eMailTC);

	TableColumn<Kunde, String> nationalityTC = new TableColumn<>("Nationalität");
	nationalityTC.setMinWidth(100);
	nationalityTC.setCellValueFactory(new PropertyValueFactory<>("nationalitaet"));
	cList_TableView.getColumns().add(nationalityTC);

	TableColumn<Kunde, String> preferedCheckInTC = new TableColumn<>("Präf. CheckIn-Kanal");
	preferedCheckInTC.setMinWidth(100);
	preferedCheckInTC.setCellValueFactory(new PropertyValueFactory<>("praeferierterCheckInKanal"));
	cList_TableView.getColumns().add(preferedCheckInTC);

	TableColumn<Kunde, String> paymentMethodTC = new TableColumn<>("Zahlungsmittel");
	paymentMethodTC.setMinWidth(140);
	paymentMethodTC.setCellValueFactory(currCustomerCDF -> this.formatPaymentMethod(currCustomerCDF));
	cList_TableView.getColumns().add(paymentMethodTC);

	TableColumn<Kunde, String> discountTC = new TableColumn<>("Rabatte");
	discountTC.setMinWidth(190);
	discountTC.setCellValueFactory(currCustomer -> this.formatDiscount(currCustomer));
	cList_TableView.getColumns().add(discountTC);
	// customerTableView.set

	cList_grid.add(cList_TableView, 0, 1);
	// fill table
	cList_TableView.setItems(observableCustomerList);

	cList_openCDataForm = new Button("Kunde bearbeiten");
	cList_openCDataForm.setOnAction(actionEvent -> {
	    ObservableList<Kunde> selectedCustomers = cList_TableView.getSelectionModel().getSelectedItems();
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

	Button customerSaveButton = new Button("In Datei speichern");
	customerSaveButton.setOnAction(actionEvent -> saveCustomerData());

	Button customerLoadButton = new Button("Aus Datei laden");
	customerLoadButton.setOnAction(actionEvent -> loadCustomerData());

	HBox bottomRightButtons = new HBox();
	bottomRightButtons.setAlignment(Pos.BOTTOM_RIGHT);
	bottomRightButtons.getChildren().add(cList_openCDataForm);
	bottomRightButtons.setPrefWidth(cList_TableView.getMinWidth() / 2 + CLIST_STAGE_PADDING);

	HBox bottomLeftButtons = new HBox(10);
	bottomLeftButtons.setAlignment(Pos.BOTTOM_LEFT);
	bottomLeftButtons.getChildren().addAll(customerLoadButton, customerSaveButton);
	bottomLeftButtons.setPrefWidth(cList_TableView.getMinWidth() / 2);

	HBox buttons = new HBox();
	buttons.getChildren().addAll(bottomLeftButtons, bottomRightButtons);
	cList_grid.add(buttons, 0, 2);

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

    /**
     * Die Bearbeitungsdialog für die Kundendaten wird erstellt. Hierzu werden
     * alle notwendigen Objekte erstellt und ausgerichtet.
     */
    private void initialiseCustomerDataScene() {

	cDataForm_sceneTitle.setId("titleText");
	cDataForm_sceneTitle.setText("Kundendaten bearbeiten ");
	cDataForm_sceneTitle.setTextAlignment(TextAlignment.CENTER);
	cDataForm_sceneTitle.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));

	cDataForm_grid.add(cDataForm_sceneTitle, 0, 1);

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
	formGrid.add(cDataForm_customerNumberField, 1, 0);
	formGrid.add(customerNameLabel, 0, 1);
	formGrid.add(cDataForm_nameField, 1, 1);
	formGrid.add(customerFirstNameLabel, 0, 2);
	formGrid.add(cDataForm_firstNameField, 1, 2);
	formGrid.add(customerEmailLabel, 0, 3);
	formGrid.add(cDataForm_eMailField, 1, 3);
	formGrid.add(customerNationalityLabel, 0, 4);
	formGrid.add(cDataForm_nationalityField, 1, 4);

	TableColumn<Rabatt, String> rabattAktionsnameTC = new TableColumn<>("Aktionsname");
	rabattAktionsnameTC.setMinWidth(240);
	rabattAktionsnameTC.setCellValueFactory(new PropertyValueFactory<>("aktionsname"));
	discountTableView.getColumns().add(rabattAktionsnameTC);

	TableColumn<Rabatt, Float> rabattEinheitenTC = new TableColumn<>("Rabattmenge");
	rabattEinheitenTC.setMinWidth(220);
	rabattEinheitenTC.setCellValueFactory(new PropertyValueFactory<>("rabattEinheiten"));
	discountTableView.getColumns().add(rabattEinheitenTC);

	TableColumn<Rabatt, Waehrung> rabattWaehrungTC = new TableColumn<>("Währung");
	rabattWaehrungTC.setMinWidth(190);
	rabattWaehrungTC.setCellValueFactory(new PropertyValueFactory<>("waehrung"));
	discountTableView.getColumns().add(rabattWaehrungTC);

	TableColumn<Rabatt, String> rabattCodeTC = new TableColumn<>("Code");
	rabattCodeTC.setMinWidth(220);
	rabattCodeTC.setCellValueFactory(new PropertyValueFactory<>("rabattCode"));
	discountTableView.getColumns().add(rabattCodeTC);

	discountTableView.setMinWidth(CDATA_TABLEVIEW_WIDTH);
	discountTableView.setEditable(false);

	TableColumn<Zahlungsmittel, Zahlungskanal> zahlungsmittelKanalTC = new TableColumn<>("Zahlungskanal");
	zahlungsmittelKanalTC.setMinWidth(160);
	zahlungsmittelKanalTC.setCellValueFactory(new PropertyValueFactory<>("zahlungskanal"));
	oaymentMethodTableView.getColumns().add(zahlungsmittelKanalTC);

	TableColumn<Zahlungsmittel, String> zahlungsmittelKontobesitzerTC = new TableColumn<>("Kontobesiter");
	zahlungsmittelKontobesitzerTC.setMinWidth(190);
	zahlungsmittelKontobesitzerTC.setCellValueFactory(new PropertyValueFactory<>("kontobesitzer"));
	oaymentMethodTableView.getColumns().add(zahlungsmittelKontobesitzerTC);

	TableColumn<Zahlungsmittel, String> zahlungsmittelKontonummerTC = new TableColumn<>("Kontonummer");
	zahlungsmittelKontonummerTC.setMinWidth(240);
	zahlungsmittelKontonummerTC.setCellValueFactory(new PropertyValueFactory<>("kontonummer"));
	oaymentMethodTableView.getColumns().add(zahlungsmittelKontonummerTC);

	TableColumn<Zahlungsmittel, String> zahlungsmittelGenehmigungTC = new TableColumn<>("Genehmigungsvermerk");
	zahlungsmittelGenehmigungTC.setMinWidth(280);
	zahlungsmittelGenehmigungTC.setCellValueFactory(new PropertyValueFactory<>("genehmigungsvermerk"));
	oaymentMethodTableView.getColumns().add(zahlungsmittelGenehmigungTC);

	oaymentMethodTableView.setMinWidth(CDATA_TABLEVIEW_WIDTH);
	oaymentMethodTableView.setEditable(false);

	cDataForm_grid.add(formGrid, 0, 2);
	cDataForm_grid.add(discountTableView, 0, 3);
	cDataForm_grid.add(oaymentMethodTableView, 0, 4);

	Button backButton = new Button("Abbrechen");
	backButton.setOnAction(actionEvent -> this.showCustomerListScene());
	Button saveButton = new Button("Speichern");
	saveButton.setOnAction(actionEvent -> {
	    this.updateCustomerData();
	    this.showCustomerListScene();
	    System.out.println(Arrays.toString(this.kundenverwaltung.getKunden().toArray()));
	});

	HBox hbBtn = new HBox(10);
	hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
	hbBtn.getChildren().add(saveButton);
	hbBtn.getChildren().add(backButton);

	cDataForm_grid.add(hbBtn, 0, 5);
    }

    /**
     * This methods updates the customer object just worked on.
     */
    private void updateCustomerData() {
	try {
	    this.kundenverwaltung.aktualisiereKundendaten(Long.valueOf(this.cDataForm_customerNumberField.getText()),
		    this.cDataForm_nameField.getText(), this.cDataForm_firstNameField.getText(),
		    this.cDataForm_eMailField.getText(), this.cDataForm_nationalityField.getText());
	} catch (KundeNichtGefundenException kngException) {
	    Dialogs.showErrorDialog("Speichern fehlgeschlagen, der Kunde ist nicht mehr verfügbar.");
	    kngException.printStackTrace();
	}
    }

    // choose a file to save the current data to and save it into the harddrive.
    private void saveCustomerData() {
	FileChooser fc = new FileChooser();
	fc.setTitle("Save to CSV File");
	fc.getExtensionFilters().add(new ExtensionFilter("CSV Files", "*.csv"));
	File file = fc.showOpenDialog(null);
	if (file != null) {
	    this.kundenverwaltung.speichereDaten(file);
	    Dialogs.showInformation("Die Daten wurden erfolgreich gespeichert");
	} else {
	    Dialogs.showErrorDialog("Es wurde keine Datei ausgewählt!");
	}
    }

    // choose a file to load data from and load it into the runtime.
    private void loadCustomerData() {

	FileChooser fc = new FileChooser();
	fc.setTitle("Load Customer Data File");
	fc.getExtensionFilters().add(new ExtensionFilter("CSV Files", "*.csv"));
	File file = fc.showOpenDialog(null);
	if (file != null) {
	    this.kundenverwaltung.ladeKundenAusDatei(file);
	    this.refreshCustomerTableView();
	    Dialogs.showInformation("Die Daten wurden erfolgreich geladen!");
	} else {
	    Dialogs.showErrorDialog("Es konnte keine Daten geladen werden!");
	}
    }

    private void refreshCustomerTableView() {
	this.observableCustomerList.clear();
	this.observableCustomerList.addAll(this.kundenverwaltung.getKunden());
    }

    /**
     * Eine Übersicht der Kunden wird angezeigt. Ein Kunde kann ausgewählt
     * werden. Nach der Auswahl eines Kunden werden alle mögliche Aktionen für
     * diese Gruppe angezeigt.
     */
    private Scene showCustomerListScene() {

	mainStage.setScene(cList_scene);
	this.refreshCustomerTableView();
	return cList_scene;
    }

    /**
     * Für den ausgewählten Kunden wird ein Dialog zum Bearbeiten der Daten
     * angezeigt.
     * 
     * @param selectedCustomer
     *            : Kunde - zu bearbeitender Kunde
     */
    private Scene showCustomerDataScene(final Kunde selectedCustomer) {

	this.cDataForm_nameField.setText(selectedCustomer.getName());
	this.cDataForm_firstNameField.setText(selectedCustomer.getVorname());
	this.cDataForm_customerNumberField.setText(String.valueOf(selectedCustomer.getKundennummer()));
	this.cDataForm_eMailField.setText(selectedCustomer.getEmail());
	this.cDataForm_nationalityField.setText(selectedCustomer.getNationalitaet());

	mainStage.setScene(cDataForm_Scene);
	this.oaymentMethodTableView.setItems(null);
	if (selectedCustomer.getZahlungsmittel() != null) {
	    this.oaymentMethodTableView.setItems(new ObservableListWrapper<>(selectedCustomer.getZahlungsmittel()));
	}
	this.discountTableView.setItems(null);
	if (selectedCustomer.getRabatte() != null) {
	    this.discountTableView.setItems(new ObservableListWrapper<>(selectedCustomer.getRabatte()));
	}
	return cDataForm_Scene;
    }
}
