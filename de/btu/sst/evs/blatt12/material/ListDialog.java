package de.btu.sst.evs.blatt12.material;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ListDialog extends Application {

    private ListView<String> listView;
    private List<String> listElements;

    @Override
    public void start(Stage stage) throws Exception {

	this.listElements = new ArrayList<>();
	listView = new ListView<>();
	listView.getItems().addAll(this.listElements);

	final Button btnAdd = new Button("Add element");
	btnAdd.setOnAction(event -> addElem());
	final Button btnRmv = new Button("Remove element");
	btnRmv.setOnAction(event -> removeElem());
	final HBox buttonBox = new HBox(10);
	buttonBox.setPadding(new Insets(10));
	buttonBox.getChildren().addAll(btnAdd, btnRmv);
	
	final BorderPane root = new BorderPane();
	root.setCenter(listView);
	root.setBottom(buttonBox);

	stage.setTitle("Einfacher ListDialog");
	stage.setOnCloseRequest(event -> System.exit(0));
	Scene scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
    }

    private void removeElem() {
	if (!this.listElements.isEmpty()) {
	    this.listElements.remove(this.listElements.size() - 1);
	    this.updateListView();
	} 
    }

    private void addElem() {
	TextInputDialog textDialog = new TextInputDialog();
	textDialog.setTitle("Eingabefenster - Neues Listenelement");
	textDialog.setHeaderText("Bitte geben Sie den Text für ein neues Listenelement ein.");
	Optional<String> result = textDialog.showAndWait();
	result.ifPresent(input -> this.listElements.add(input));
	this.updateListView();
    }

    private void updateListView() {
	this.listView.getItems().clear();
	this.listView.getItems().addAll(listElements);
    }

    public static void main(String[] args) {
	launch(args);
    }
}