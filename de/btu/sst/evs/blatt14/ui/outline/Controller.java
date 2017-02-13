package de.btu.sst.evs.blatt14.ui.outline;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import de.btu.sst.evs.blatt14.ui.util.Dialogs;
import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Kunde;
import de.btu.sst.evs.blatt14.ui.BerlinAirApp;
import de.btu.sst.evs.blatt14.ui.util.Animations;
import de.btu.sst.evs.blatt14.ui.util.Images;

public class Controller implements Initializable {

  @FXML
  private StackPane stackPane;

  @FXML
  private BorderPane borderPane;

  @FXML
  protected ImageView menuButton, portrait, customerList, customerDetails, customerAdd, save, search, about, logOut;

  @FXML
  private Label title;

  @FXML
  private VBox menuRoot;
  
  @FXML
  private Label saveLabel;

  @FXML
  private Separator sep_1, sep_2, sep_3, sep_4, sep_5, sep_6, sep_7;

  @FXML
  private Label profileName;

  private boolean isOpen = true;
  private List<Separator> separators;

  private static final int DEFAULT_STARTING_X_POSITION = 0;
  private static final int DEFAULT_ENDING_X_POSITION = -175;
  private static final int DEFAULT_STACKPANE_WIDTH = 745;

  private Kunde currentlySelectedCustomer;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.initSeparators();
    this.setImages();

    BerlinAirApp.getPrimaryStage().widthProperty().addListener(width -> this.resizeStackPane());
    BerlinAirApp.setMainController(this);

//    BerlinAirApp.getCustomerManagement().ladeKundenAusDatei(new File("data/Beispiel.csv"));
    this.refreshViews();
  }

  private void resizeStackPane() {
    double currStageWidth = BerlinAirApp.getPrimaryStage().getWidth();
    if (currStageWidth > 800) {
      this.stackPane.setMinWidth(DEFAULT_STACKPANE_WIDTH + currStageWidth - 800);
    } else {
      this.stackPane.setMinWidth(DEFAULT_STACKPANE_WIDTH);
    }

  }

  public void initSeparators() {
    separators = new ArrayList<>();
    separators.addAll(Arrays.asList(sep_1, sep_2, sep_3, sep_4, sep_5, sep_6, sep_7));
  }

  private void setImages() {
    Images.setImageOn(menuButton, "res/img/menu.png", 35, 18);
    Images.setImageOn(portrait, BerlinAirApp.getPathToProfileIcon(), 150, 150);
    Images.setRectangularClipOf(portrait, 15, 15);
    Images.setImageOn(customerList, "res/img/list.png", 35, 18);
    Images.setImageOn(customerDetails, "res/img/customer.png", 35, 18);
    Images.setImageOn(customerAdd, "res/img/customer-add.png", 35, 18);
    Images.setImageOn(search, "res/img/customer-search.png", 35, 18);
    Images.setImageOn(save, "res/img/load.png", 35, 18);
    Images.setImageOn(about, "res/img/save.png", 35, 18);
    Images.setImageOn(logOut, "res/img/logout.png", 35, 18);
  }

  private void openAnimation() {
    Animations.applyTranslateAnimationOn(menuRoot, Animations.ANIMATION_TIME_FAST, DEFAULT_ENDING_X_POSITION,
	DEFAULT_STARTING_X_POSITION);
    Animations.applyTranslateAnimationOn(stackPane, Animations.ANIMATION_TIME_FAST, DEFAULT_ENDING_X_POSITION,
	DEFAULT_STARTING_X_POSITION);
    rotateMenuIcon();
    Images.setImageOn(portrait, BerlinAirApp.getPathToProfileIcon(), 150, 150);
    this.toggleMenuItemsVisibility(0f, 1.0f, null);
    this.isOpen = true;
  }

  private void toggleMenuItemsVisibility(double from, double to, EventHandler<ActionEvent> handler) {
    animateSeparators(from, to);
    Animations.applyFadeAnimationOn(portrait, Animations.ANIMATION_TIME_FAST, from, to, null);
    Animations.applyFadeAnimationOn(profileName, Animations.ANIMATION_TIME_FAST, from, to, null);
    Animations.applyFadeAnimationOn(saveLabel, Animations.ANIMATION_TIME_FAST, from, to, handler);
  }

  public void closeAnimation(EventHandler<ActionEvent> handlerOnFinished) {
    Animations.applyTranslateAnimationOn(menuRoot, Animations.ANIMATION_TIME_FAST, DEFAULT_STARTING_X_POSITION,
	DEFAULT_ENDING_X_POSITION);
    Animations.applyTranslateAnimationOn(stackPane, Animations.ANIMATION_TIME_FAST, DEFAULT_STARTING_X_POSITION,
	DEFAULT_ENDING_X_POSITION);
    rotateMenuIcon();
    this.toggleMenuItemsVisibility(1.0f, 0f, handlerOnFinished);
    this.isOpen = false;
  }

  private void closeAnimation() {
    this.closeAnimation(null);
  }

  @FXML
  private void toggleMenuVisibility() throws Exception {
    if (isOpen) {
      if (this.stackPane.getChildren().isEmpty()) {
	this.showCustomerListView();
      } else {
	this.closeAnimation();
      }
    } else {
      this.openAnimation();
    }
  }

  @FXML
  public void onSaveFile() throws Exception {
    FileChooser fc = new FileChooser();
    fc.setTitle("Save to CSV File");
    fc.getExtensionFilters().add(new ExtensionFilter("CSV Files", "*.csv"));
    File file = fc.showSaveDialog(null);
    if (file != null) {
      BerlinAirApp.getCustomerManagement().speichereDaten(file);
      Dialogs.showInformation("Die Daten wurden erfolgreich gespeichert");
    } else {
      Dialogs.showErrorDialog("Es wurde keine Datei ausgewählt!");
    }
    if (this.title.getText().equals("Check-In App")) {
      this.showCustomerListView();
    } else {
      this.closeAnimation();
    }
  }

  @FXML
  public void onLoadFile() throws Exception {
    FileChooser fc = new FileChooser();
    fc.setTitle("Load Customer Data File");
    fc.getExtensionFilters().add(new ExtensionFilter("CSV Files", "*.csv"));
    File file = fc.showOpenDialog(null);
    if (file != null) {
      BerlinAirApp.getCustomerManagement().ladeKundenAusDatei(file);
      this.refreshViews();
      Dialogs.showInformation("Die Daten wurden erfolgreich geladen!");
    } else {
      Dialogs.showErrorDialog("Es konnte keine Daten geladen werden!");
    }
    if (this.title.getText().equals("Check-In App")) {
      this.showCustomerListView();
    } else {
      this.closeAnimation();
    }
  }

  private void refreshViews() {
    if (BerlinAirApp.getCustomerSearchController() != null) {
      BerlinAirApp.getCustomerSearchController().refreshViews();
    }
    if (BerlinAirApp.getCustomerDataController() != null) {
      BerlinAirApp.getCustomerDataController().refreshViews();
    }
    if (BerlinAirApp.getCustomerListController() != null) {
      BerlinAirApp.getCustomerListController().refreshViews();
    }
  }

  @FXML
  private void reactOnCustomerDataClicked() throws Exception {
    this.showCustomerDataView();
  }

  @FXML
  private void reactOnCustomerListClicked() throws Exception {
    this.showCustomerListView();
  }

  @FXML
  private void reactOnCustomerSearchClicked() throws Exception {
    this.showCustomerSearchView();
  }

  @FXML
  private void reactOnCustomerAddClicked() throws Exception {
    this.showCustomerAddView();
  }

  @FXML
  private void profileSelected() throws Exception {
    String newMenuTitle = "Profil ändern";
    Parent profileView = FXMLLoader.load(new File("res/view/profileController.fxml").toURI().toURL());
    // change view
    if (!this.title.getText().equals(newMenuTitle)) {
      closeAnimation(onFinished -> this.fadeInNewContent(profileView, newMenuTitle));
    } else {
      closeAnimation();
    }
  }

  public void showCustomerAddView() throws Exception {
    String newMenuTitle = "Kunden registrieren";
    Parent customerSearchView = FXMLLoader.load(new File("res/view/customerAddController.fxml").toURI().toURL());
    this.closeMenuAndShowContent(newMenuTitle, customerSearchView);
  }

  public void showCustomerSearchView() throws Exception {
    String newMenuTitle = "Suche";
    Parent customerSearchView = FXMLLoader.load(new File("res/view/customerSearchController.fxml").toURI().toURL());
    this.closeMenuAndShowContent(newMenuTitle, customerSearchView);
  }

  public void showCustomerDataView() throws Exception {
    String newMenuTitle = "Datenblatt";
    Parent customerDataView = FXMLLoader.load(new File("res/view/customerDataController.fxml").toURI().toURL());
    this.closeMenuAndShowContent(newMenuTitle, customerDataView);
  }

  public void showCustomerListView() throws Exception {
    String newMenuTitle = "Liste aller Kunden";
    Parent customerListView = FXMLLoader.load(new File("res/view/customerListController.fxml").toURI().toURL());
    this.closeMenuAndShowContent(newMenuTitle, customerListView);
  }

  public void showCustomerListView(Kunde selectedCustomer) throws Exception {
    this.showCustomerListView();
    if (BerlinAirApp.getCustomerListController() != null) {
      BerlinAirApp.getCustomerListController().setSelectedCustomer(selectedCustomer);
    }

  }

  private void closeMenuAndShowContent(String newMenuTitle, Parent newContent) {
    // check if menu is opened
    if (this.isOpen == true) {
      // menu is opened and needs to be closed first
      if (!this.title.getText().equals(newMenuTitle)) {
	closeAnimation(onFinished -> this.fadeInNewContent(newContent, newMenuTitle));
      } else {
	// correct view is shown already, so only close the menu
	closeAnimation();
      }
      // menu is already closed, fade over to the new content
    } else if (!this.title.getText().equals(newMenuTitle)) {
      this.fadeInNewContent(newContent, newMenuTitle);
    }
  }

  private void rotateMenuIcon() {
    Animations.applyRotationOn(menuButton, Animations.ANIMATION_TIME_FAST, 180f, 1);
  }

  private void animateSeparators(double from, double to) {
    separators.stream()
	.forEach(sep -> Animations.applyFadeAnimationOn(sep, Animations.ANIMATION_TIME_FAST, from, to, null));
  }

  @FXML
  private void logoutSelected() {
    try {
      Parent loginView = FXMLLoader.load(new File("res/view/login.fxml").toURI().toURL());
      Animations.fadeOutNode(borderPane, Animations.ANIMATION_TIME_NORMAL, event -> {
	BerlinAirApp.getPrimaryStage().setScene(new Scene(loginView, 800, 700));
	BerlinAirApp.setTitle("Login");
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void fadeInNewContent(Node node, String newTitle) {
    if (stackPane.getChildren().isEmpty() == true) {
      this.stackPane.getChildren().add(node);
      Animations.fadeInNode(stackPane, Animations.ANIMATION_TIME_FAST);
    } else {
      Animations.fadeOutNode(stackPane, Animations.ANIMATION_TIME_FAST, event1 -> {
	this.stackPane.getChildren().clear();
	this.stackPane.getChildren().add(node);
	Animations.fadeInNode(stackPane, Animations.ANIMATION_TIME_FAST);
      });
    }
    this.title.setText(newTitle);
  }

  public Kunde getCurrentlySelectedCustomer() {
    return currentlySelectedCustomer;
  }

  public void setCurrentlySelectedCustomer(Kunde currentlySelectedCustomer) {
    this.currentlySelectedCustomer = currentlySelectedCustomer;
  }

}
