package de.btu.sst.evs.blatt12.diagrammExample;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import de.btu.sst.evs.blatt12.diagrammExample.ui.ToggleSwitch;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PrettyPrinter extends Application {

    private static final int SLOW_PRINTING = 3000;
    private static final int FAST_PRINTING = 1000;

    private Button btnStartStop;
    private TextArea textArea;
    private ITimeFormatter timeFormatter;
    private IDateFormatter dateFormatter;
    private AtomicBoolean isPrintingDate;
    private AtomicBoolean isPrintingTime;
    private AtomicBoolean isPrinting;
    private AtomicBoolean isPrinting24HourFormat;
    private AtomicInteger waitTime;

    @Override
    public void start(Stage stage) throws Exception {

	this.initializePrintingService();
	this.layoutScene(stage);
	this.startPrintingService();

    }

    private void startPrintingService() {
	Runnable printThread = (() -> {
	    while (true) {
		if (isPrinting.get() == true) {
		    this.textArea.appendText(this.prettyPrint());
		}
		try {
		    Thread.sleep(waitTime.get());
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	});
	new Thread(printThread).start();
    }

    private String prettyPrint() {

	if (isPrintingDate.get() == true && isPrintingTime.get() == true) {
	    return (dateFormatter.printDate() + " - " + timeFormatter.printTime() + "\n");
	} else if (isPrintingDate.get() == true && isPrintingTime.get() == false) {
	    return (dateFormatter.printDate() + "\n");
	} else if (isPrintingDate.get() == false && isPrintingTime.get() == true) {
	    return (timeFormatter.printTime() + "\n");
	}
	return "";
    }

    private void initializePrintingService() {
	this.isPrinting24HourFormat = new AtomicBoolean(false);
	this.isPrinting = new AtomicBoolean(false);
	this.isPrintingDate = new AtomicBoolean(true);
	this.isPrintingTime = new AtomicBoolean(true);
	this.waitTime = new AtomicInteger(SLOW_PRINTING);
	this.dateFormatter = new DateFormatter_Impl();
	this.timeFormatter = new TimeFormatter_Impl();
    }

    private void layoutScene(Stage stage) {

	stage.setOnCloseRequest(event -> System.exit(0));

	final BorderPane root = new BorderPane();
	this.textArea = new TextArea();
	root.setCenter(textArea);
	btnStartStop = new Button("Start");
	btnStartStop.setOnAction(event -> {
	    this.toggleValue(this.isPrinting);
	    this.toggleButtonText();
	});
	final HBox buttonBox = new HBox(10);
	buttonBox.setAlignment(Pos.CENTER_RIGHT);
	buttonBox.setPadding(new Insets(10));
	buttonBox.getChildren().add(btnStartStop);
	root.setBottom(buttonBox);

	// vertical box for all switches
	final VBox allSwitches = new VBox(-10);

	final ToggleSwitch timeFormatToggle = new ToggleSwitch(40, 20, false);
	timeFormatToggle.switchedOnProperty().addListener(event -> toggleTimePrintingFormat());
	// layout the first toggle button and put it into place
	HBox timeFormatToggleBox = this.layoutToggleSwitch(timeFormatToggle, "Format - 12 Hour", "Format - 24 Hour");
	allSwitches.getChildren().add(timeFormatToggleBox);

	final ToggleSwitch timeToggle = new ToggleSwitch(40, 20, true);
	timeToggle.switchedOnProperty().addListener(event -> {
	    toggleValue(this.isPrintingTime);
	    // TODO
	    // timeFormatToggle.setDisable(!this.isPrintingTime.get());
	});
	// layout the first toggle button and put it into place
	allSwitches.getChildren()
		.add(this.layoutToggleSwitch(timeToggle, "Time printing - ON ", "Time printing - OFF "));

	final ToggleSwitch dateToggle = new ToggleSwitch(40, 20, true);
	dateToggle.switchedOnProperty().addListener(event -> toggleValue(this.isPrintingDate));
	// layout the first toggle button and put it into place
	allSwitches.getChildren()
		.add(this.layoutToggleSwitch(dateToggle, "Date printing - ON ", "Date printing - OFF "));

	final ToggleSwitch speedToggle = new ToggleSwitch(40, 20, false);
	speedToggle.switchedOnProperty().addListener(event -> this.togglePrintSpeed());
	// layout the first toggle button and put it into place
	allSwitches.getChildren()
		.add(this.layoutToggleSwitch(speedToggle, "Printing speed - FAST", "Printing speed - SLOW"));

	root.setTop(allSwitches);
	Scene scene = new Scene(root);
	// TODO
	// scene.getStylesheets().add("/styles/****.css");
	stage.setTitle("Date and Time Formatter");
	stage.setScene(scene);
	stage.show();

	allSwitches.getChildren().forEach(child -> {
	    HBox currHBox = ((HBox) child);
	    currHBox.setPrefWidth(root.getWidth());
	    currHBox.getChildren().forEach(childOfHBox -> {
		((HBox) childOfHBox).setPrefWidth(currHBox.getPrefWidth());
	    });
	});
    }

    private void toggleButtonText() {
	if (this.isPrinting.get() == true) {
	    this.btnStartStop.setText("Stop");
	} else {
	    this.btnStartStop.setText("Start");
	}

    }

    private void toggleTimePrintingFormat() {
	if (this.isPrinting24HourFormat.get() == true) {
	    this.isPrinting24HourFormat.set(false);
	    timeFormatter.setTimeFormat(TimeFormat.TWELVE_HOUR_FORMAT);
	} else {
	    this.isPrinting24HourFormat.set(true);
	    timeFormatter.setTimeFormat(TimeFormat.TWENTYFOUR_HOUR_FORMAT);
	}
    }

    private void toggleValue(AtomicBoolean value) {
	value.set(!value.get());
    }

    private void togglePrintSpeed() {
	if (this.waitTime.get() == SLOW_PRINTING) {
	    this.waitTime.set(FAST_PRINTING);
	} else {
	    this.waitTime.set(SLOW_PRINTING);
	}

    }

    private HBox layoutToggleSwitch(final ToggleSwitch toggle, String thenText, String otherwiseText) {
	final Text text = new Text();
	final HBox textBox = new HBox(text);
	textBox.setAlignment(Pos.CENTER_LEFT);
	final HBox toggleBox = new HBox(toggle);
	toggleBox.setAlignment(Pos.CENTER_RIGHT);
	final HBox switchBox = new HBox();
	switchBox.getChildren().addAll(textBox, toggleBox);
	switchBox.setPadding(new Insets(10));
	text.textProperty().bind(Bindings.when(toggle.switchedOnProperty()).then(thenText).otherwise(otherwiseText));

	return switchBox;
    }

    public static void main(String[] args) {
	launch(args);
    }

}
