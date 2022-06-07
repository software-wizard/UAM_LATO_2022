package pl.psi.gui;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

class MapTile extends StackPane {

    private final Rectangle rect;
    private final Text text;

    MapTile(final String aName) {
        rect = new Rectangle(60, 60);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.RED);
        getChildren().add(rect);
        text = new Text(aName);
        getChildren().add(text);
        text.setFill(Color.WHITE);
        Font font = Font.font("Arial", FontWeight.BOLD, 15);
        text.setFont(font);
    }

    void setName(final String aName) {
        text.setText(aName);
    }

    void setBackground(final Color aColor) {
        rect.setFill(aColor);
    }

    void setBackground(final Image img) {
        rect.setFill(new ImagePattern(img));
    }

}
