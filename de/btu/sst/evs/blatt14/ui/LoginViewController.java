package de.btu.sst.evs.blatt14.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import de.btu.sst.evs.blatt14.ui.util.Animations;
import de.btu.sst.evs.blatt14.ui.util.Images;

public class LoginViewController implements Initializable {

  private static final KeyCombination KEY_COMBINATION_CLOSE_APP = new KeyCodeCombination(KeyCode.F4,
      KeyCombination.ALT_DOWN);

  @FXML
  private ImageView imgView;

  @FXML
  private GridPane root;

  @FXML
  private TextField txField;

  @FXML
  private PasswordField pwField;

  @FXML
  private Label loginLabel, registerLabel;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    Animations.fadeInNode(root, Animations.ANIMATION_TIME_NORMAL);
    Images.setImageOn(imgView, "res/img/login_small.png", 75, 75);
    setOnKeyPressed();
  }

  @FXML
  private void mouseMovedLoginLabel() {
    Animations.applyFadeAnimationOn(loginLabel, Animations.ANIMATION_TIME_NORMAL, 1.0f, 0.7f, null);
  }

  @FXML
  private void mouseMovedRegisterLabel() {
    Animations.applyFadeAnimationOn(registerLabel, Animations.ANIMATION_TIME_NORMAL, 1.0f, 0.7f, null);
  }

  @FXML
  private void mouseExitedLoginLabel() {
    Animations.applyFadeAnimationOn(loginLabel, Animations.ANIMATION_TIME_NORMAL, 0.7f, 1.0f, null);
  }

  @FXML
  private void mouseExitedRegisterLabel() {
    Animations.applyFadeAnimationOn(registerLabel, Animations.ANIMATION_TIME_NORMAL, 0.7f, 1.0f, null);
  }

  @FXML
  private void login() {
    if (loginSuccessful())
      animateWhenLoginSuccess();
    else
      animateWhenBadLogin();
  }

  private boolean loginSuccessful() {
    return "kundenmanager".equals(txField.getText()) && "kundenmanager".equals(pwField.getText());
  }

  private void setOnKeyPressed() {
    root.setOnKeyPressed(event -> {
      if (event.getCode().equals(KeyCode.ENTER) && loginSuccessful())
	animateWhenLoginSuccess();
      else if (event.getCode().equals(KeyCode.ENTER) && !loginSuccessful())
	animateWhenBadLogin();
      else if (KEY_COMBINATION_CLOSE_APP.match(event))
	System.exit(0);
    });
  }

  private void animateWhenLoginSuccess() {
    try {
      Parent targetController = FXMLLoader.load(new File("res/view/controller.fxml").toURI().toURL());
      showImageAndFadeOverToTarget(targetController, "Check-In App", "res/img/login_successfull.png");
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  private void animateWhenBadLogin() {
    try {
      Parent targetController = FXMLLoader.load(new File("res/view/login.fxml").toURI().toURL());
      showImageAndFadeOverToTarget(targetController, "Login", "res/img/login_failed.png");
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * This method will fades out the current root pane and briefly shows the
   * image found at <i>pathToImage</i>. Subsequently it will fade out the image
   * again and fade in the new <i>targetWindow</i>. Additionally it will set the
   * window title to <i>targetTitle</i>
   * 
   * @param targetWindow
   * @param targetTitle
   * @param pathToImage
   */
  private void showImageAndFadeOverToTarget(Parent targetWindow, String targetTitle, String pathToFile) {
    // create temporary view
    StackPane temporaryStackPane = new StackPane();
    // create and add image
    temporaryStackPane.getChildren().add(Images.createImageView(pathToFile));
    // set target panes transparent
    temporaryStackPane.setOpacity(0);    
    targetWindow.setOpacity(0.f);

    // animate the fade in fade out 
    Animations.fadeOutNode(root, Animations.ANIMATION_TIME_NORMAL, event -> {
      
      BerlinAirApp.setScene(new Scene(temporaryStackPane, 800, 700));      
      Animations.fadeInNode(temporaryStackPane, Animations.ANIMATION_TIME_NORMAL, event1 -> {
	
	Animations.fadeOutNode(temporaryStackPane, Animations.ANIMATION_TIME_NORMAL, event2 -> {	  
	  
	  BerlinAirApp.setScene(new Scene(targetWindow, 800, 700));	  
	  Animations.fadeInNode(targetWindow, Animations.ANIMATION_TIME_NORMAL);
	  BerlinAirApp.setTitle(targetTitle);
	});
      });
    });
  }

}
