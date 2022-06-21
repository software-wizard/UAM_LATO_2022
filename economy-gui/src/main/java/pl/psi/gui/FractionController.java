package pl.psi.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import pl.psi.hero.EconomyHero;
import java.util.Arrays;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

/**
 * Class to choose fraction with default Fraction Necropolis
 */

public class FractionController {

    @FXML
    private Button ready;
    @FXML
    private BorderPane borderPane;
    @FXML
    private ComboBox<EconomyHero.Fraction> boxFractionPlayer1;
    @FXML
    private ComboBox<EconomyHero.Fraction> boxFractionPlayer2;
    private final EconomyHero.Fraction defaultFraction  = EconomyHero.Fraction.NECROPOLIS;

    @FXML
    public void initialize() {

        boxFractionPlayer1.getItems().setAll(Arrays.asList(EconomyHero.Fraction.values()));
        boxFractionPlayer2.getItems().setAll(Arrays.asList(EconomyHero.Fraction.values()));

        Image image = new Image("walp.jpg");
        BackgroundSize bSize = new BackgroundSize(400, 300, false, false, false, false);
        borderPane.setBackground(new Background(new BackgroundImage(image, NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, bSize)));

    }

    public void goToEconomy() throws Exception {
        EconomyStart start = new EconomyStart();

        if (boxFractionPlayer1.getValue() == null) {
            boxFractionPlayer1.setValue(defaultFraction);
        }
        if (boxFractionPlayer2.getValue() == null) {
            boxFractionPlayer2.setValue(defaultFraction);
        }

        start.startApp(boxFractionPlayer1.getValue(), boxFractionPlayer2.getValue());
        Stage stage = (Stage) ready.getScene().getWindow();
        stage.close();
    }
}