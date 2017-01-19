package de.btu.sst.evs.javafxExamples;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LayoutManagerDemo extends Application {



    public void start(Stage stage) throws Exception {
	stage.setTitle("Artikel ändern");

	// all the layouting objects
	VBox vContainer = new VBox();
	FlowPane flowPane = new FlowPane();
	flowPane.setPadding(new Insets(10));
	flowPane.setHgap(10);
	flowPane.setVgap(10);
	flowPane.setAlignment(Pos.CENTER);

	GridPane gridPane = new GridPane();
	gridPane.setMinWidth(450);
	gridPane.getColumnConstraints().add(new ColumnConstraints());
	gridPane.getColumnConstraints().add(new ColumnConstraints());
	gridPane.getColumnConstraints().add(new ColumnConstraints());
	gridPane.getColumnConstraints().get(0).setHgrow(Priority.SOMETIMES);
	gridPane.getColumnConstraints().get(1).setHgrow(Priority.SOMETIMES);
	gridPane.getColumnConstraints().get(2).setHgrow(Priority.SOMETIMES);

	BorderPane borderPane = new BorderPane();

	vContainer.getChildren().addAll(flowPane, gridPane, borderPane);

	// After the layouts have been created, realize all the buttons
	//
	// create all the buttons and add them to the panes
	flowPane.getChildren().addAll(new Button("FlowPaneLayout 1"), new Button("FlowPaneLayout 2"),
		new Button("FlowPaneLayout 3"), new Button("FlowPaneLayout 4"), new Button("FlowPaneLayout 5"));

	// GridPane Buttons
	//
	Button gp_buttonOne = new Button("GridPaneLayout 1");
	gp_buttonOne.setMaxWidth(Double.MAX_VALUE);
	gridPane.add(gp_buttonOne, 0, 0);
	//
	Button gp_buttonTwo = new Button("GridPaneLayout 2");
	gp_buttonTwo.setMaxWidth(Double.MAX_VALUE);
	gridPane.add(gp_buttonTwo, 1, 0);
	//
	Button gp_buttonThree = new Button("GridPaneLayout 3");
	gp_buttonThree.setMaxWidth(Double.MAX_VALUE);
	gridPane.add(gp_buttonThree, 2, 0);
	//
	Button gp_buttonFour = new Button("GridPaneLayout 4");
	gp_buttonFour.setMaxWidth(Double.MAX_VALUE);
	gridPane.add(gp_buttonFour, 0, 1);
	//
	Button gp_buttonFive = new Button("GridPaneLayout 5");
	gp_buttonFive.setMaxWidth(Double.MAX_VALUE);
	gridPane.add(gp_buttonFive, 1, 1);

	// BorderPane Buttons
	//
	Button topButton = new Button("BorderPaneTop");
	topButton.setMaxWidth(Double.MAX_VALUE);
	borderPane.setTop(topButton);
	//
	Button bottomButton = new Button("BorderPaneBottom");
	bottomButton.setMaxWidth(Double.MAX_VALUE);
	borderPane.setBottom(bottomButton);
	//
	Button leftButton = new Button("BorderPaneLeft");
	leftButton.setMaxHeight(Double.MAX_VALUE);
	leftButton.setMaxWidth(Double.MAX_VALUE);
	borderPane.setLeft(leftButton);
	//
	Button rightButton = new Button("BorderPaneRight");
	rightButton.setMaxHeight(Double.MAX_VALUE);
	rightButton.setMaxWidth(Double.MAX_VALUE);
	borderPane.setRight(rightButton);
	//
	Button centerButton = new Button("BorderPaneCenter");
	centerButton.setMaxHeight(Double.MAX_VALUE);
	centerButton.setMaxWidth(Double.MAX_VALUE);
	centerButton.setMinHeight(100);
	borderPane.setCenter(centerButton);

	Scene scene = new Scene(vContainer, 450, 323);
	stage.setScene(scene);
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
