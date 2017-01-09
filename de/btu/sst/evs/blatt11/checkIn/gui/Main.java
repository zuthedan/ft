package de.btu.sst.evs.blatt11.checkIn.gui;

import java.net.URL;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Diese Klasse enthält die main-Methode. Diese startet die JavaFX-Applikation.
 * Von dieser aus wird die Main-Methode aus aufgerufen. Durch weitere Aufrufe
 * wird ein Anmeldebildschirm erstellt und bei erfolgreicher Anmeldung der
 * Kontrolfluss an die KundenverwaltungsGUI übergeben.
 * 
 * @author Mathias Schubanz
 */

public class Main extends Application {

    Stage primaryStage = null;
    public static final URL CSS_RESSOURCE = Main.class.getResource("GUI.css");
    private static final int LOGIN_STAGE_WIDTH = 450;
    private static final int LOGIN_STAGE_HEIGHT = 275;

    /**
     * Die main-Methode ruft die launch() Methode der JavaFX-Applikation auf
     * 
     * @param args
     *            Eingabeparameter
     */
    public static void main(String[] args) {
	launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

	this.primaryStage = primaryStage;
	primaryStage.setTitle("Kundenmanagement-Anmeldung");
	this.showLogin();
	primaryStage.show();

    }

    /**
     * Hier wird der Anmeldedialog in Form einer Szene erzeugt und zurückgegeben
     * an die aufrufende Methode. ... Beim Klicken des Anmeldeknopfes wird der
     * Benutzername und das Passwort überprüft. Bei Erfolg wird die Liste der
     * registrierten Kunden angezeigt. Bei Misserfolg wird ein Fehler
     * ausgegeben.
     */
    private Scene showLogin() {

	GridPane grid = new GridPane();
	grid.setAlignment(Pos.TOP_CENTER);
	grid.setHgap(5);
	grid.setVgap(5);
	grid.setPadding(new Insets(20, 20, 20, 20));

	Scene scene = new Scene(grid, LOGIN_STAGE_WIDTH, LOGIN_STAGE_HEIGHT);
	scene.getStylesheets().add(CSS_RESSOURCE.toExternalForm());

	Text title = new Text("Kundenmanagement - Anmeldung");
	title.setId("titleText");
	grid.add(title, 0, 1);

	GridPane formGrid = new GridPane();
	formGrid.setAlignment(Pos.CENTER_LEFT);
	formGrid.setHgap(100);
	formGrid.setVgap(10);
	formGrid.setPadding(new Insets(10, 0, 10, 0));

	Label userName = new Label("Benutzername:");
	userName.setId("userName");
	formGrid.add(userName, 0, 1);
	Label password = new Label("Passwort:");
	password.setId("password");
	formGrid.add(password, 0, 2);
	final TextField userNameField = new TextField();
	userNameField.setText("Kundenmanager");
	formGrid.add(userNameField, 1, 1);
	final PasswordField passwordField = new PasswordField();
	passwordField.setText("Kundenmanager");
	formGrid.add(passwordField, 1, 2);

	Button submitButton = new Button("Anmelden");
	HBox hbBtn = new HBox(10);
	hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
	hbBtn.getChildren().add(submitButton);
	formGrid.add(hbBtn, 1, 4);

	submitButton.setOnAction(arg0 -> {
	    String submittedUser = userNameField.getText();
	    String submittedPassword = passwordField.getText();

	    if ("".equals(submittedUser) || "".equals(submittedPassword)) {
		Dialogs.showErrorDialog("Bitte geben Sie sowohl Nutzername als auch ein Passwort ein!");

	    } else if ("Kundenmanager".equals(submittedUser) && "Kundenmanager".equals(submittedPassword)) {
		// TODO -- hier müsste eigentlich eine ordentliche
		// Nutzerverwaltung die eingegebenen Zugangsdaten prüfen, die
		// Implementierung kann gern nachgetragen werden
		//
		// Dialogs.showInformation("Sie haben sich erfolgreich
		// eingeloggt");
		showCustomerManagement();
	    } else {

		Dialogs.showErrorDialog("Die eingegebenen Nutzerdaten sind nicht korrekt!");
		userNameField.setText("");
		passwordField.setText("");
	    }
	});

	grid.add(formGrid, 0, 2);

	Label noteText = new Label("Hinweis:");
	noteText.setId("helpTextHeader");
	grid.add(noteText, 0, 4);

	Label helpText = new Label(
		"Eingerichtet ist der Benutzer \"Kundenmanager\"\n" + "mit gleichlautendem Passwort.");
	helpText.setId("helpText");
	grid.add(helpText, 0, 5);

	scene.getStylesheets().add(CSS_RESSOURCE.toExternalForm());
	this.primaryStage.setScene(scene);

	return scene;
    }

    /**
     * Diese Methode erzeugt ein Kundenverwaltungsobjekt. Die PrimaryStage wird
     * übergeben. Damit geht nachdem Login der Kontrolfluss komplett an die
     * KunderverwaltungsGUI über.
     */
    private void showCustomerManagement() {
	new KundenverwaltungGUI(primaryStage);
    }

}
