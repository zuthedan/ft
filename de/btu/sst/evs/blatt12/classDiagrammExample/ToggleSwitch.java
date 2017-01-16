package de.btu.sst.evs.blatt12.classDiagrammExample;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Parent;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ToggleSwitch extends Parent {

    private BooleanProperty switchedOn = new SimpleBooleanProperty(false);

    private TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.1));
    private FillTransition fillAnimation = new FillTransition(Duration.seconds(0.1));
    private ParallelTransition animation = new ParallelTransition(translateAnimation, fillAnimation);

    private final Rectangle background;
    private final Circle trigger;

    public BooleanProperty switchedOnProperty() {
	return switchedOn;
    }

    public ToggleSwitch(double switchWidth, double switchHeight, boolean initialState) {
	background = new Rectangle(switchWidth, switchHeight);
	background.setArcWidth(switchHeight);
	background.setArcHeight(switchHeight);
	background.setFill(Color.WHITE);
	background.setStroke(Color.LIGHTGRAY);

	trigger = new Circle(switchHeight / 2);
	trigger.setCenterX(switchHeight / 2);
	trigger.setCenterY(switchHeight / 2);
	trigger.setFill(Color.WHITE);
	trigger.setStroke(Color.LIGHTGRAY);

	DropShadow shadow = new DropShadow();
	shadow.setRadius(2);
	trigger.setEffect(shadow);

	translateAnimation.setNode(trigger);
	fillAnimation.setShape(background);

	getChildren().addAll(background, trigger);

	switchedOn.addListener((obs, oldState, newState) -> {
	    boolean isOn = newState.booleanValue();
	    translateAnimation.setToX(isOn ? switchWidth / 2 : 0);
	    fillAnimation.setFromValue(isOn ? Color.WHITE : Color.DODGERBLUE);
	    fillAnimation.setToValue(isOn ? Color.DODGERBLUE : Color.WHITE);

	    animation.play();
	});

	setOnMouseClicked(event -> toggle());

	if (initialState == true) {
	    toggle();
	}
    }

    private void toggle() {
	switchedOn.set(!switchedOn.get());
    }
//
//    public void setDisable(Boolean value) {
//	//TODO
//	super.setDisable(value);
//    }
}