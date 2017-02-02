package de.btu.sst.evs.blatt14.ui.util;

import java.util.Date;
import java.util.Optional;

import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Zahlungsmittel;
import de.btu.sst.evs.blatt11.checkIn.enums.Waehrung;
import de.btu.sst.evs.blatt11.checkIn.enums.Zahlungskanal;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Rabatt;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Diese Klasse stellt Funktionen zur Ausgabe von Fehlern und Informationen
 * mithilfe eines Fehlerdialogs bereit. CheckInSystems.
 * 
 * @author Mathias Schubanz
 */

public class Dialogs {

  public static void showInformation(String content) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Information");
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
  }

  public static void showErrorDialog(String content) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Fehler");
    alert.setHeaderText("Fehlerhafte Eingabe!");
    alert.setContentText(content);
    alert.showAndWait();
  }

  public static Rabatt showDiscountDialog() {
    // Create the dialog to capture a new Rabatt.
    Dialog<Rabatt> dialog = new Dialog<>();
    dialog.setTitle("Rabatt erstellen!");
    dialog.setHeaderText("Bitte füllen Sie folgende Felder aus.");

    // Set the button types.
    ButtonType confirmButtonType = new ButtonType("Erstelle Rabatt", ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, ButtonType.CANCEL);

    // Create the username and password labels and fields.
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

    TextField campaignName = new TextField();
    campaignName.setPromptText("Name der Aktion");
    TextField discountQuantity = new TextField();
    discountQuantity.setPromptText("Rabattmenge");
    ComboBox<Waehrung> discountCurrency = new ComboBox<>();
    discountCurrency.setPrefWidth(150);
    discountCurrency.setMaxWidth(Double.MAX_VALUE);
    discountCurrency.getItems().addAll(Waehrung.values());
    TextField discountCode = new TextField();
    discountCode.setPromptText("Rabattcode");

    grid.add(new Label("Name der Aktion:"), 0, 0);
    grid.add(campaignName, 1, 0);
    grid.add(new Label("Rabattmenge:"), 0, 1);
    grid.add(discountQuantity, 1, 1);
    grid.add(new Label("Währung:"), 0, 2);
    grid.add(discountCurrency, 1, 2);
    grid.add(new Label("Rabattcode:"), 0, 3);
    grid.add(discountCode, 1, 3);

    // Enable/Disable login button depending on whether data was entered.
    Node confirmButton = dialog.getDialogPane().lookupButton(confirmButtonType);
    confirmButton.setDisable(true);

    // Do some validation -> enable or disable the confirm button.
    addCompletenessListeners(discountCurrency, campaignName, discountQuantity, discountCode, confirmButton);
    dialog.getDialogPane().setContent(grid);

    // Request focus on the field asking for the campaign name by default.
    Platform.runLater(() -> campaignName.requestFocus());

    // Convert the result to a discount object when the login button is clicked.
    dialog.setResultConverter(convertResultButton -> {
      if (convertResultButton == confirmButtonType) {
	Rabatt newDiscount = new Rabatt(campaignName.getText(), Float.valueOf(discountQuantity.getText()),
	    discountCurrency.getSelectionModel().getSelectedItem(), new Date(), discountCode.getText(), "");
	return newDiscount;
      }
      return null;
    });

    Optional<Rabatt> result = dialog.showAndWait();
    if (result.isPresent()) {
      return result.get();
    } else {
      return null;
    }

  }

  public static Zahlungsmittel showPaymentDialog() {
    // Create the dialog to capture a new Zahlungsmittel.
    Dialog<Zahlungsmittel> dialog = new Dialog<>();
    dialog.setTitle("Zahlungsmittel erstellen!");
    dialog.setHeaderText("Bitte füllen Sie folgende Felder aus.");

    // Set the button types.
    ButtonType confirmButtonType = new ButtonType("Erstelle Zahlungsmittel", ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, ButtonType.CANCEL);

    // Create the username and password labels and fields.
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

    ComboBox<Zahlungskanal> paymentChannel = new ComboBox<>();
    paymentChannel.setPrefWidth(150);
    paymentChannel.setMaxWidth(Double.MAX_VALUE);
    paymentChannel.getItems().addAll(Zahlungskanal.values());
    TextField accountOwner = new TextField();
    accountOwner.setPromptText("Name der Aktion");
    TextField accountNumber = new TextField();
    accountNumber.setPromptText("Rabattmenge");
    TextField withdrawalAllowance = new TextField();
    withdrawalAllowance.setPromptText("Rabattcode");

    grid.add(new Label("Zahlungskanal:"), 0, 0);
    grid.add(paymentChannel, 1, 0);
    grid.add(new Label("Kontobesitzer:"), 0, 1);
    grid.add(accountOwner, 1, 1);
    grid.add(new Label("Kontonummer:"), 0, 2);
    grid.add(accountNumber, 1, 2);
    grid.add(new Label("Genehmigungsvermerk:"), 0, 3);
    grid.add(withdrawalAllowance, 1, 3);

    // Enable/Disable login button depending on whether data was entered.
    Node confirmButton = dialog.getDialogPane().lookupButton(confirmButtonType);
    confirmButton.setDisable(true);

    // Do some validation -> enable or disable the confirm button.
    addCompletenessListeners(paymentChannel, accountOwner, accountNumber, withdrawalAllowance, confirmButton);
    dialog.getDialogPane().setContent(grid);

    // Request focus on the field asking for the campaign name by default.
    Platform.runLater(() -> accountOwner.requestFocus());

    // Convert the result to a discount object when the login button is clicked.
    dialog.setResultConverter(convertResultButton -> {
      if (convertResultButton == confirmButtonType) {
	Zahlungsmittel newPayment = new Zahlungsmittel(paymentChannel.getSelectionModel().getSelectedItem(),
	    accountOwner.getText(), accountNumber.getText(), withdrawalAllowance.getText(), "");
	return newPayment;
      }
      return null;
    });

    Optional<Zahlungsmittel> result = dialog.showAndWait();
    if (result.isPresent()) {
      return result.get();
    } else {
      return null;
    }

  }

  private static void addCompletenessListeners(ComboBox<?> combo, TextField tf1, TextField tf2, TextField tf3,
      Node disabledButton) {
    tf1.textProperty()
	.addListener((o, oV, nV) -> disabledButton.setDisable(Dialogs.isFormIncomplete(tf1, tf2, combo, tf3)));
    tf2.textProperty()
	.addListener((o, oV, nV) -> disabledButton.setDisable(Dialogs.isFormIncomplete(tf1, tf2, combo, tf3)));
    combo.getSelectionModel().selectedItemProperty()
	.addListener((o, oV, nV) -> disabledButton.setDisable(Dialogs.isFormIncomplete(tf1, tf2, combo, tf3)));
    tf3.textProperty()
	.addListener((o, oV, nV) -> disabledButton.setDisable(Dialogs.isFormIncomplete(tf1, tf2, combo, tf3)));
  }

  private static boolean isFormIncomplete(TextField tf1, TextField tf2, ComboBox<?> combo, TextField tf3) {
    return tf1.getText().isEmpty() || tf2.getText().isEmpty() || tf3.getText().isEmpty()
	|| combo.getSelectionModel().getSelectedItem() == null;
  }

}