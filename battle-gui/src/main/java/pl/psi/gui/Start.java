package pl.psi.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.Hero;
import pl.psi.hero.HeroStatistics;

import java.io.IOException;
import java.util.List;

public class Start extends Application {

    public Start() {

    }

    static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        Scene scene = null;
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Start.class.getClassLoader()
                    .getResource("fxml/main-battle.fxml"));
            loader.setController(new MainBattleController(createP1(), createP2()));
            scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.setX(5);
            primaryStage.setY(5);
            primaryStage.show();
        } catch (final IOException aE) {
            aE.printStackTrace();
        }
    }

    private Hero createP1() {
        final Hero ret =
                new Hero(List.of(
                        new NecropolisFactory().create(true, 5, 5),
                        new NecropolisFactory().create(true, 3, 5),
                        new NecropolisFactory().create(true,6,5),
                        new NecropolisFactory().create(true,7,5)
                        ),HeroStatistics.NECROMANCER);
        ret.getCreatures().forEach(creature -> creature.setHeroNumber(1));
        return ret;
    }

    private Hero createP2() {
        final Hero ret = new Hero(List.of(
                new StrongholdFactory().create(true, 3, 30),
                new StrongholdFactory().create(true, 5, 15),
                new StrongholdFactory().create(true, 7, 100)
        ),HeroStatistics.KNIGHT);
        ret.getCreatures().forEach(creature -> creature.setHeroNumber(2));
        return ret;
    }


}
