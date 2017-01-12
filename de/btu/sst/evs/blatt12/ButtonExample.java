package de.btu.sst.evs.blatt12;

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ButtonExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

	GridPane gridPane = new GridPane();
	Button buttonOne = new Button("Button 1");
	Button buttonTwo = new Button("Button 2");
	gridPane.add(buttonOne, 0, 0);
	gridPane.add(buttonTwo, 1, 0);

	// set behavior for button pressed
	buttonOne.setOnMouseClicked(event -> handleEventOne());
	buttonTwo.setOnMouseClicked(event -> handleEventTwo(event));

	Scene firstScene = new Scene(gridPane);
	primaryStage.setScene(firstScene);
	primaryStage.show();
    }

    /**
     * Die Start-Methode ruft die launch() Methode der Applikation auf
     * 
     * @param args
     *            : Eingabeparameter
     */
    public static void main(String[] args) {
	launch(args);
    }

    private void handleEventOne() {
	System.out.println("Button 1 gedrueckt!");
    }

    private void handleEventTwo(Event event) {
	String eventSource = ((Button) event.getSource()).getText();
	System.out.println(eventSource + " gedrueckt!");
    }
}
