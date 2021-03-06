package de.btu.sst.evs.blatt11.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Diese Klasse stellt Funktionen zur Ausgabe von Fehlern und Informationen
 * mithilfe eines Fehlerdialogs bereit. CheckInSystems.
 * 
 * @author Mathias Schubanz
 */

public class Dialogs {

    protected static void showInformation(String content) {
	Alert alert = new Alert(AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText(content);
	alert.showAndWait();
    }

    protected static void showErrorDialog(String content) {
	Alert alert = new Alert(AlertType.ERROR);
	alert.setTitle("Fehler");
	alert.setHeaderText("Fehlerhafte Eingabe!");
	alert.setContentText(content);
	alert.showAndWait();
    }

}