package de.btu.sst.evs.blatt14.ui.content;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import de.btu.sst.evs.blatt14.ui.BerlinAirApp;
import de.btu.sst.evs.blatt14.ui.util.Animations;
import de.btu.sst.evs.blatt14.ui.util.Images;

public class ProfileController implements Initializable {

    @FXML
    private ImageView portrait;

    @FXML
    private GridPane rootGridPane;

    @FXML
    private VBox vBoxProfileIcon, vBoxChangeImage;

    @FXML
    private StackPane stackPaneImageHolder;

    @FXML
    private Label labelLocalSource, labelDone;

    @FXML
    private TextField txField_localPath;
    @FXML
    private TextField txField_URL_path;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
	Images.setImageOn(portrait, BerlinAirApp.getPathToProfileIcon(), 170, 170);
	Images.setRectangularClipOf(portrait, 15, 15);
	BerlinAirApp.setTitle("Login");
    }

    @FXML
    private void mouseEntered() {
	Animations.applyFadeAnimationOn(portrait, Animations.ANIMATION_TIME_FAST, 1.0f, 0.6f, null);
    }

    @FXML
    private void mouseExited() {
	Animations.applyFadeAnimationOn(portrait, Animations.ANIMATION_TIME_FAST, 0.6f, 1.0f, null);
    }

    @FXML
    private void imageClicked() {
	// AnimationUtil.applyFadeAnimationOn(vBoxProfileIcon,
	// BerlinAirApp.ANIMATION_TIME_NORMAL, 1.0f, 0.5f, event -> {
	vBoxChangeImage.setVisible(true);
	vBoxProfileIcon.setDisable(true);
	Animations.applyFadeAnimationOn(vBoxChangeImage, Animations.ANIMATION_TIME_NORMAL, 0f, 1.0f, null);
	// });
    }

    @FXML
    private void chooseImageFromLocalSource() {
	FileChooser fileChooser = new FileChooser();
	fileChooser.setTitle("Choose a picture");
	fileChooser.getExtensionFilters()
		.addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
	File selectedFile = fileChooser.showOpenDialog(BerlinAirApp.getPrimaryStage());

	if (selectedFile != null) {
	    try {
		this.txField_localPath.setText(selectedFile.getPath());
		Images.setImageFileOn(portrait, selectedFile, 170, 170);
		Images.setRectangularClipOf(portrait, 15, 15);
	    } catch (Exception e) {
		// TODO: handle exception
	    }
	}
    }

    @FXML
    private void fileSelectionDoneClicked() {
	if (!txField_localPath.getText().isEmpty()) {
	    this.updateProfileIcon(this.txField_localPath.getText());
	} else if (!txField_URL_path.getText().isEmpty()) {
	    this.updateProfileIcon(this.txField_URL_path.getText());
	}
	Animations.applyFadeAnimationOn(vBoxChangeImage, Animations.ANIMATION_TIME_NORMAL, 1.0f, 0f, event -> {
	    vBoxChangeImage.setVisible(false);
	    vBoxProfileIcon.setDisable(false);
	    Animations.applyFadeAnimationOn(vBoxProfileIcon, Animations.ANIMATION_TIME_NORMAL, 0f, 1.0f, null);
	});
    }

    private void updateProfileIcon(String path) {
	BerlinAirApp.setPathToProfileIcon(path);
	try {
	    Images.setImageOn(portrait, BerlinAirApp.getPathToProfileIcon(), 170, 170);
	    Images.setRectangularClipOf(portrait, 15, 15);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    @FXML
    private void mouseEnteredLocalSource() {
	Animations.applyFadeAnimationOn(labelLocalSource, Animations.ANIMATION_TIME_FAST, 1.0f, 0.7f, null);
    }

    @FXML
    private void mouseEnteredDone() {
	Animations.applyFadeAnimationOn(labelDone, Animations.ANIMATION_TIME_FAST, 1.0f, 0.7f, null);
    }

    @FXML
    private void mouseExitedLocalSource() {
	Animations.applyFadeAnimationOn(labelLocalSource, Animations.ANIMATION_TIME_FAST, 0.7f, 1.0f, null);
    }

    @FXML
    private void mouseExitedDone() {
	Animations.applyFadeAnimationOn(labelDone, Animations.ANIMATION_TIME_FAST, 0.7f, 1.0f, null);
    }

}
