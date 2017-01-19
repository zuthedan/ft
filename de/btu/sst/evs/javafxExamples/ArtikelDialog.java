package de.btu.sst.evs.javafxExamples;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ArtikelDialog extends Application {

    public void start(Stage stage) throws Exception {
	stage.setTitle("Artikel ändern");

	Label labelOne = new Label("Nummer");
	labelOne.setMinWidth(75);
	Label labelTwo = new Label("Name");
	labelTwo.setMinWidth(75);

	TextField textFieldOne = new TextField("0");
	textFieldOne.setMinWidth(100);
	TextField textFieldTwo = new TextField();
	textFieldTwo.setMinWidth(100);

	Button button = new Button("OK");
	button.setOnAction(event -> System.exit(0));

	VBox root = new VBox(-10);
	HBox hBoxFirstLine = new HBox(40);
	hBoxFirstLine.setPadding(new Insets(10));
	hBoxFirstLine.getChildren().addAll(labelOne, textFieldOne);
	
	HBox hBoxSecondLine = new HBox(40);
	hBoxSecondLine.setPadding(new Insets(10));
	hBoxSecondLine.getChildren().addAll(labelTwo, textFieldTwo);

	HBox hBoxBtn = new HBox();
	hBoxBtn.setPadding(new Insets(10));
	hBoxBtn.setAlignment(Pos.CENTER_RIGHT);
	hBoxBtn.getChildren().add(button);
	
	root.getChildren().addAll(hBoxFirstLine, hBoxSecondLine, hBoxBtn);
	Scene firstScene = new Scene(root);
	stage.setScene(firstScene);
	stage.show();
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
