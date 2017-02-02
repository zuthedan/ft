package de.btu.sst.evs.blatt14.ui.util;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Images {

    public static void setImageOn(ImageView imageView, String path, int fitWidth, int fitHeight) {
        imageView.setImage(new Image(new File(path).toURI().toString()));
        imageView.setFitWidth(fitWidth);
        imageView.setFitHeight(fitHeight);
    }
    
    public static void setImageFileOn(ImageView imageView, File file, int fitWidth, int fitHeight) {
        imageView.setImage(new Image(file.toURI().toString()));
        imageView.setFitWidth(fitWidth);
        imageView.setFitHeight(fitHeight);
    }

    public static void setRectangularClipOf(ImageView imageView, int arcWidth, int arcHeight) {
        Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        clip.setArcHeight(arcHeight);
        clip.setArcWidth(arcWidth);
        imageView.setClip(clip);
    }
    
    public static ImageView createImageView(String path) {
	return new ImageView(new Image(new File(path).toURI().toString()));
    }
}
