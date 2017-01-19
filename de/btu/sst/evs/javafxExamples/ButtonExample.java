package de.btu.sst.evs.javafxExamples;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ButtonExample extends Application {

    @Override
    public void start(Stage stage) throws Exception {

	HBox hBoxLayout = new HBox();
	Button btnOne = new Button("Button 1");
	Button btnTwo = new Button("Button 2");
	hBoxLayout.getChildren().addAll(btnOne, btnTwo);

	// set behavior for button pressed (keyboard & mouse)
	btnOne.setOnAction(event -> switchTitles(btnOne, btnTwo));
	btnTwo.setOnAction(event -> switchTitles(btnTwo, btnOne));

	Scene mainScene = new Scene(hBoxLayout);
	stage.setScene(mainScene);
	stage.show();
    }

    public void switchTitles(Button sourceBtn, Button targetBtn) {
	String targetStr = targetBtn.getText();
	targetBtn.setText(sourceBtn.getText());
	sourceBtn.setText(targetStr);
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
