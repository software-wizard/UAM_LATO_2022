package pl.psi.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.Hero;
import pl.psi.artifacts.model.Artifact;
import pl.psi.creatures.Creature;
import pl.psi.gui.MainBattleController;
import pl.psi.hero.EconomyHero;

public class EcoBattleConverter {

    public static void startBattle(final EconomyHero aPlayer1, final EconomyHero aPlayer2) {
        Scene scene = null;
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EcoBattleConverter.class.getClassLoader()
                .getResource("fxml/main-battle.fxml"));
            loader.setController(new MainBattleController(convert(aPlayer1), convert(aPlayer2)));
            scene = new Scene(loader.load());
            final Stage aStage = new Stage();
            aStage.setScene(scene);
            aStage.setX(5);
            aStage.setY(5);
            aStage.show();
        } catch (final IOException aE) {
            aE.printStackTrace();
        }
    }

    public static Hero convert(final EconomyHero aPlayer) {
//         I think we should get already filtered Artifact from Equipment, but for now let's have it this way...
        final List<Artifact> artifacts = Collections.emptyList(); // temporary empty list

        final List<Artifact> creatureArtifacts = new ArrayList<>();
        final List<Artifact> skillArtifacts = new ArrayList<>();
        final List<Artifact> spellArtifacts = new ArrayList<>();

        artifacts.forEach(artifact -> {
            switch (artifact.getTarget()) {
                case CREATURES:
                    creatureArtifacts.add(artifact);
                    break;
                case SPELLS:
                    spellArtifacts.add(artifact);
                    break;
                case SKILL:
                    skillArtifacts.add(artifact);
                    break;
            }
        });

        final List<Creature> creatures = aPlayer.getCreatures()
            .stream()
            .map(economyCreature -> {

                creatureArtifacts.forEach(artifact -> artifact.applyTo(economyCreature));
                return new Creature(
                    economyCreature.getUpgradedStats(), null,
                    economyCreature.getAmount());
            })
            .collect(Collectors.toList());

        return new Hero(creatures);
    }
}
