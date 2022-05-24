package pl.psi.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import pl.psi.creatures.EconomyCreature;
import pl.psi.hero.EconomyHero;
import javafx.scene.image.Image;

import java.awt.*;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class FractionController {

    @FXML
    Button ready;

    @FXML
    BorderPane borderPane;

    @FXML
    private ComboBox<EconomyHero.Fraction> boxFractionPlayer1;
    @FXML
    private ComboBox<EconomyHero.Fraction> boxFractionPlayer2;

    @FXML
    public void initialize(){
        boxFractionPlayer1.getItems().setAll(EconomyHero.Fraction.CASTLE, EconomyHero.Fraction.NECROPOLIS);
        boxFractionPlayer2.getItems().setAll(EconomyHero.Fraction.CASTLE, EconomyHero.Fraction.NECROPOLIS);

        Image image = new Image("walp.jpg");
        BackgroundSize bSize = new BackgroundSize(400,300,false,false,false,false);
        borderPane.setBackground(new Background(new BackgroundImage(image, NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, bSize)));

    }

    public void goToEconomy() throws Exception {
        EconomyStart start = new EconomyStart();

        if(boxFractionPlayer1.getValue() == null){
            boxFractionPlayer1.setValue(EconomyHero.Fraction.NECROPOLIS);
        }
        if(boxFractionPlayer2.getValue() == null){
            boxFractionPlayer2.setValue(EconomyHero.Fraction.NECROPOLIS);
        }

        start.startApp(boxFractionPlayer1.getValue(),boxFractionPlayer2.getValue());
        Stage stage = (Stage) ready.getScene().getWindow();
        stage.close();
    }
}
