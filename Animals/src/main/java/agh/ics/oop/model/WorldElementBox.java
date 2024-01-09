package agh.ics.oop.model;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class WorldElementBox {

    @FXML
    private ImageView imageView;

    @FXML
    private final Label info = new Label();

    @FXML
    private final VBox vBox = new VBox();

    private final WorldElement element;


    public WorldElementBox(WorldElement element) {
        this.element = element;
        initializeImageView();
        setVBox();
    }

    private void setVBox() {
        vBox.getChildren().addAll(imageView, info);
        vBox.setAlignment(Pos.CENTER);
    }

    private void initializeImageView() {
        Image image = new Image(String.valueOf(getClass().getResource(element.getImageURL())));
        imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
    }

    private void updateInfo() {
        info.setText(element.getInfo());
    }

    private void updateImage() {
        Image image = new Image(String.valueOf(getClass().getResource(element.getImageURL())));
        imageView.setImage(image);
    }

    public VBox getVBox() {
        updateInfo();
        updateImage();
        return vBox;
    }

    public Vector2d getElementPosition(){
        return element.getPosition();
    }
}

