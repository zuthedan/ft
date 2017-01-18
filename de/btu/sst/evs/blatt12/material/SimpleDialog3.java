package de.btu.sst.evs.blatt12.material;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SimpleDialog3 extends Application {

    @Override
    public void start(Stage stage) throws Exception {

	Button button1 = new Button("Button1");
	button1.setOnAction(event -> System.out.println("Button1 gedrueckt"));
	Button button2 = new Button("Button2");
	button2.setOnAction(event -> System.out.println("Button2 gedrueckt"));

	final HBox buttonBox = new HBox();
	buttonBox.getChildren().addAll(button1, button2);

	final BorderPane root = new BorderPane();
	root.setCenter(buttonBox);

	stage.setTitle("Einfacher Dialog");
	stage.setOnCloseRequest(event -> System.exit(0));

	Scene scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
    }

    public static void main(String[] args) {
	launch(args);
    }
}
