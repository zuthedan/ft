package de.btu.sst.evs.blatt14.ui.util;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

public class Animations {

  public static final int ANIMATION_TIME_VERY_FAST = 50;
  public static final int ANIMATION_TIME_FAST = 200;
  public static final int ANIMATION_TIME_NORMAL = 400;
  public static final int ANIMATION_TIME_SLOW = 1000;

  public static void applyRotationOn(Node node, int duration, double angle, int cycleCount) {
    RotateTransition rotateTransition = new RotateTransition(Duration.millis(duration), node);
    rotateTransition.setFromAngle(0);
    rotateTransition.setToAngle(0 + angle);
    rotateTransition.setCycleCount(cycleCount);
    rotateTransition.setAutoReverse(true);
    rotateTransition.play();

  }

  public static void applyTranslateAnimationOn(Node node, int duration, double from, double to) {
    TranslateTransition translateTransition = new TranslateTransition(Duration.millis(duration), node);
    translateTransition.setFromX(from);
    translateTransition.setToX(to);
    translateTransition.setCycleCount(1);
    translateTransition.setAutoReverse(true);
    translateTransition.play();
  }

  public static void applyFadeAnimationOn(Node node, int duration, double from, double to,
      EventHandler<ActionEvent> eventOnFinished) {
    FadeTransition fadeTransition = new FadeTransition(Duration.millis(duration), node);
    fadeTransition.setOnFinished(eventOnFinished);
    fadeTransition.setFromValue(from);
    fadeTransition.setToValue(to);
    fadeTransition.setAutoReverse(true);
    fadeTransition.setCycleCount(1);
    fadeTransition.play();
  }

  public static void fadeOutNode(Node node, int duration, EventHandler<ActionEvent> onFinished) {
    Animations.applyFadeAnimationOn(node, duration, 1.0f, 0.f, onFinished);
  }

  public static void fadeInNode(Node node, int duration, EventHandler<ActionEvent> onFinished) {
    Animations.applyFadeAnimationOn(node, duration, 0f, 1.0f, onFinished);
  }
  
  public static void fadeOutNode(Node node, int duration) {
    Animations.applyFadeAnimationOn(node, duration, 1.0f, 0.f, null);
  }
  
  public static void fadeInNode(Node node, int duration) {
    Animations.applyFadeAnimationOn(node, duration, 0f, 1.0f, null);
  }
}
