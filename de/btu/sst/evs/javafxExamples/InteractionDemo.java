package de.btu.sst.evs.javafxExamples;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InteractionDemo extends Application {

    public void start(Stage stage) throws Exception {
	stage.setTitle("InteractionDemo");

	Label label = new Label("Beschriftung");
	TextField textField = new TextField("Textfeld");
	CheckBox checkBox = new CheckBox("Kontrollkästchen");

	ToggleGroup toggle = new ToggleGroup();
	RadioButton radioOne = new RadioButton("Alternative 1");
	RadioButton radioTwo = new RadioButton("Alternative 1");
	radioOne.setSelected(true);
	radioOne.setToggleGroup(toggle);
	radioTwo.setToggleGroup(toggle);

	ListView<String> listView = new ListView<>();
	ObservableList<String> items = FXCollections.observableArrayList("Listeneintrag 1", "Listeneintrag 2",
		"Listeneintrag 3");
	listView.setItems(items);

	Button btn = new Button("Schließen");
	btn.setOnMouseClicked(buttonClickedEvent -> System.exit(0));

	// setting the menu
	MenuBar menuBar = new MenuBar();
	Menu menu = new Menu("_Menü 1");
	MenuItem menuItemOne = new MenuItem("Punkt 1");
	MenuItem menuItemTwo = new MenuItem("Punkt 2");
	menu.getItems().addAll(menuItemOne, menuItemTwo);
	menuBar.getMenus().add(menu);

	VBox vBoxCenter = new VBox(5);
	vBoxCenter.setPadding(new Insets(10));
	vBoxCenter.setAlignment(Pos.CENTER);
	// order of elements is important
	vBoxCenter.getChildren().addAll(label, textField, checkBox, radioOne, radioTwo, listView, btn);

	BorderPane root = new BorderPane();
	root.setCenter(vBoxCenter);
	root.setTop(menuBar);
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
