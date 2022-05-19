package pl.psi.converter;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.Hero;
import pl.psi.creatures.Creature;
import pl.psi.gui.MainBattleController;
import pl.psi.gui.NecropolisFactory;
import pl.psi.hero.EconomyHero;
import pl.psi.spells.Spell;
import pl.psi.spells.SpellFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static pl.psi.spells.SpellRang.BASIC;

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

    public static Hero convert(final EconomyHero aPlayer1) {
        final List<Creature> creatures = new ArrayList<>();
        final NecropolisFactory factory = new NecropolisFactory();
        aPlayer1.getCreatures()
                .forEach(ecoCreature -> creatures.add(factory.create(ecoCreature.isUpgraded(),
                        ecoCreature.getTier(), ecoCreature.getAmount())));

        final Set<Spell> spells = new HashSet<>();
        final SpellFactory spellFactory = new SpellFactory();
        aPlayer1.getSpellList()
                .forEach(economySpell -> spells.add(spellFactory.create(economySpell.getName(), BASIC, 10, aPlayer1.getSpellPower())));

        return new Hero(creatures, spells);
    }
}
