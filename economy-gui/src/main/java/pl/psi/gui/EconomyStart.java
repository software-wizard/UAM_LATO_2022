package pl.psi.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.HeroStatistics;


public class EconomyStart extends Application {

    public void startApp(EconomyHero.Fraction player1, EconomyHero.Fraction player2) throws Exception {
        final Stage aStage = new Stage();
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader()
                .getResource("fxml/eco.fxml"));

        loader.setController(new EcoController(
                new EconomyHero(player1, HeroStatistics.NECROMANCER),
                new EconomyHero(player2, HeroStatistics.NECROMANCER)));
        final Scene scene = new Scene(loader.load());
        aStage.setScene(scene);

        Image icon = new Image("ICON.png");
        aStage.getIcons().add(icon);

        aStage.centerOnScreen();
        aStage.setResizable(false);
        aStage.show();
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
