package pl.psi.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ChooseFraction extends Application {

    public static void main(final String[] args) {
        launch();
    }

    @Override
    public void start(final Stage aStage) throws Exception {

        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/fraction.fxml"));
        loader.setController(new FractionController());

        final Scene scene = new Scene(loader.load());
        aStage.setScene(scene);
        aStage.setWidth(400);
        aStage.setHeight(280);

        Image icon = new Image("ICON.png");
        aStage.getIcons().add(icon);

        aStage.centerOnScreen();
        aStage.setResizable(false);
        aStage.show();
    }
}