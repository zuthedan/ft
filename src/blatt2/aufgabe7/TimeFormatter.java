import java.util.Optional;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;

public class TimeFormatter extends Application {

	public void start(Stage primaryStage) throws Exception {

		TextInputDialog textDialog = new TextInputDialog();
		textDialog.setTitle("Eingabefenster - Zeit");
		textDialog.setHeaderText("Software zur Umrechnung einer Zeit auf Basis von Sekunden");
		textDialog.setContentText("Bitte geben Sie die gewünschte Zeit in Sekunden an:");

		Optional<String> result = textDialog.showAndWait();
		// lambda expression  
		result.ifPresent(input -> {
			if (input.equals("")) {
				showError("Die Eingabe ist leer!");
			} else if (!isNumeric(input)) {
				showError("Die Eingabe ist keine Zahl!");
			} else if (Integer.valueOf(input) < 0) { // Falls die Eingabe negativ ist
				showError("Die eingegebene Zahl ist negativ!");
			} else {
				showInformation("Die berechnete Zeit in Sekunden beträgt:\n" + formatTime(Integer.valueOf(input)));
			}
		});
	}

	private void showInformation(String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}

	private void showError(String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Fehler");
		alert.setHeaderText("Fehlerhafte Eingabe!");
		alert.setContentText(content);
		alert.showAndWait();
	}

	/**
	 * Die Start-Methode ruft die launch() Methode der Applikation auf
	 * @param args : Eingabeparameter
	 */
	public static void main(String[] args) {
		launch(args);
	}


	private String formatTime(int inputValue) {

		/**
		 * TODO Hier muss euer Code eingefügt werden, welcher die Sekunden umrechnet
		 */
		return "Hier muss das Ergebnis zurückgegeben werden"; 

	}
	
	private boolean isNumeric(String str) {
		/**
		 * TODO Hier muss euer Code eingefügt werden, welcher prüft ob die Eingabe eine Zahl ist.
		 */
		return false; 

	}
	
}