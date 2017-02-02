package de.btu.sst.evs.blatt14.ui;

import java.io.File;
import java.net.URL;

import de.btu.sst.evs.blatt11.checkIn.kundenverwaltung.Kundenverwaltung;
import de.btu.sst.evs.blatt14.ui.content.CustomerDataController;
import de.btu.sst.evs.blatt14.ui.content.CustomerListController;
import de.btu.sst.evs.blatt14.ui.content.CustomerSearchController;
import de.btu.sst.evs.blatt14.ui.outline.Controller;
import de.btu.sst.evs.blatt14.ui.content.CustomerAddController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BerlinAirApp extends Application {

  private static Controller mainWindowController;
  private static CustomerDataController customerDataController;
  private static CustomerListController customerListController;
  private static CustomerSearchController customerSearchController;
  private static CustomerAddController customerAddController;

  private static String pathToProfileIcon;
  private static URL cssResource;

  private static Kundenverwaltung customerManagement;

  private static Scene scene;
  private static Stage stage;

  @Override
  public void start(Stage primaryStage) throws Exception {
    pathToProfileIcon = "res/img/login.png";
    cssResource = new File("res/view/GUI.css").toURI().toURL();

    stage = primaryStage;
    Parent root = FXMLLoader.load(new File("res/view/login.fxml").toURI().toURL());
    scene = new Scene(root, 800, 700);

    primaryStage.setTitle("Login");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

  public static Stage getPrimaryStage() {
    return stage;
  }

  public static void setTitle(String title) {
    stage.setTitle(title);
  }

  public static void setScene(Scene scene) {
    stage.setScene(scene);
  }

  public static URL getCSS_Resource() {
    return cssResource;
  }

  public static String getPathToProfileIcon() {
    return pathToProfileIcon;
  }

  public static void setPathToProfileIcon(String pathToProfileIcon) {
    BerlinAirApp.pathToProfileIcon = pathToProfileIcon;
  }

  public static Kundenverwaltung getCustomerManagement() {
    if (customerManagement == null) {
      customerManagement = new Kundenverwaltung();
    }
    return customerManagement;
  }

  public static void setMainController(Controller controller) {
    mainWindowController = controller;
  }

  public static Controller getMainWindowController() {
    return mainWindowController;
  }

  public static CustomerListController getCustomerListController() {
    return customerListController;
  }

  public static void setCustomerListController(CustomerListController customerListController) {
    BerlinAirApp.customerListController = customerListController;
  }

  public static CustomerSearchController getCustomerSearchController() {
    return customerSearchController;
  }

  public static void setCustomerSearchController(CustomerSearchController customerSearchController) {
    BerlinAirApp.customerSearchController = customerSearchController;
  }

  public static void setCustomerManagement(Kundenverwaltung customerManagement) {
    BerlinAirApp.customerManagement = customerManagement;
  }

  public static CustomerDataController getCustomerDataController() {
    return customerDataController;
  }

  public static void setCustomerDataController(CustomerDataController customerDataController) {
    BerlinAirApp.customerDataController = customerDataController;
  }

  public static CustomerAddController getCustomerAddController() {
    return customerAddController;
  }

  public static void setCustomerAddController(CustomerAddController customerAddController) {
    BerlinAirApp.customerAddController = customerAddController;
  }

}
