package de.btu.sst.evs.javafxExamples;

import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SceneChanger extends Application {

    private Stage myStage;
    private Button buttonOne;
    private Button buttonTwo;
    private Scene currScene;
    private Scene sceneOne;
    private Scene sceneTwo;

    @Override
    public void start(Stage stage) throws Exception {
	myStage = stage;
	myStage.setTitle("SceneChanger");

	HBox hBoxLayoutOne = new HBox(5);
	hBoxLayoutOne.setFillHeight(true);
	buttonOne = new Button("Change to scene 2!");
	hBoxLayoutOne.getChildren().add(buttonOne);

	HBox hBoxLayoutTwo = new HBox(20);
	hBoxLayoutTwo.setAlignment(Pos.CENTER);
	buttonTwo = new Button("Change to scene 1!");
	Label labelTwo = new Label("This is the scene number 2!");
	hBoxLayoutTwo.getChildren().addAll(labelTwo, buttonTwo);

	// set behaviour for button pressed
	buttonOne.setOnMouseClicked(buttonPressedEvent -> switchScene());
	buttonTwo.setOnMouseClicked(buttonPressedEvent -> switchScene());

	sceneOne = new Scene(hBoxLayoutOne);
	sceneTwo = new Scene(hBoxLayoutTwo, 400, 100);
	myStage.setScene(sceneOne);
	currScene = sceneOne;
	myStage.show();
    }

    public void switchScene() {
	if (currScene == sceneOne) {
	    currScene = sceneTwo;
	    this.myStage.setScene(sceneTwo);
	} else {
	    currScene = sceneOne;
	    this.myStage.setScene(sceneOne);
	}
    }

    /**
     * Die launch()-Methode ruft start() die Methode der Applikation auf
     * 
     * @param args
     */
    public static void main(String[] args) {
	launch(args);
    }
}
