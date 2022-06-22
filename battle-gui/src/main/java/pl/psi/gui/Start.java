package pl.psi.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.Hero;
import pl.psi.SpellsBook;
import pl.psi.creatures.DefaultDamageCalculator;
import pl.psi.creatures.WarMachinesFactory;
import pl.psi.hero.HeroStatistics;
import pl.psi.spells.SpellFactory;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static pl.psi.spells.SpellNames.*;
import static pl.psi.spells.SpellRang.BASIC;

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
        Hero ret =
                new Hero(List.of(
                        new WarMachinesFactory().create(1, 1, new DefaultDamageCalculator(new Random()), 1),
                        new NecropolisFactory().create(false, 1, 5),
                        new NecropolisFactory().create(true, 2, 5),
                        new NecropolisFactory().create(false, 3, 5)
                ), HeroStatistics.NECROMANCER,
                        SpellsBook.builder().spells(List.of(
                                new SpellFactory().create(HASTE, BASIC, 2),
                                new SpellFactory().create(MAGIC_ARROW, BASIC, 10),
                                new SpellFactory().create(FORTUNE, BASIC, 1),
                                new SpellFactory().create(LIGHTNING_BOLT, BASIC, 1),
                                new SpellFactory().create(PROTECTION_FROM_AIR, BASIC, 1),
                                new SpellFactory().create(CHAIN_LIGHTNING, BASIC, 1),
                                new SpellFactory().create(COUNTERSTRIKE, BASIC, 1),
                                new SpellFactory().create(SUMMON_AIR_ELEMENTAL, BASIC, 10),
                                new SpellFactory().create(PROTECTION_FROM_FIRE, BASIC, 1),
                                new SpellFactory().create(FIRE_BALL, BASIC, 1),
                                new SpellFactory().create(MISFORTUNE, BASIC, 10),
                                new SpellFactory().create(ARMAGEDDON, BASIC, 1),
                                new SpellFactory().create(INFERNO, BASIC, 1),
                                new SpellFactory().create(SUMMON_FIRE_ELEMENTAL, BASIC, 1),
                                new SpellFactory().create(SLOW, BASIC, 1),
                                new SpellFactory().create(DEATH_RIPPLE, BASIC, 1),
                                new SpellFactory().create(PROTECTION_FROM_EARTH, BASIC, 1),
                                new SpellFactory().create(SORROW, BASIC, 1),
                                new SpellFactory().create(STONESKIN, BASIC, 10),
                                new SpellFactory().create(METEOR_SHOWER, BASIC, 1),
                                new SpellFactory().create(IMPLOSION, BASIC, 1),
                                new SpellFactory().create(SUMMON_EARTH_ELEMENTAL, BASIC, 10),
                                new SpellFactory().create(ICE_BOLT, BASIC, 1),
                                new SpellFactory().create(WEAKNESS, BASIC, 1),
                                new SpellFactory().create(FROST_RING, BASIC, 1),
                                new SpellFactory().create(PRAYER, BASIC, 1),
                                new SpellFactory().create(DISPEL, BASIC, 1),
                                new SpellFactory().create(PROTECTION_FROM_WATER, BASIC, 1),
                                new SpellFactory().create(MIRTH, BASIC, 1),
                                new SpellFactory().create(SUMMON_WATER_ELEMENTAL, BASIC, 1)
                        )).mana(50).build());
        ret.getCreatures().forEach(creature -> creature.setHeroNumber(1));
        return ret;
    }

    private Hero createP2() {
        Hero ret = new Hero(List.of(
                new StrongholdFactory().create(true, 1, 100),
                new StrongholdFactory().create(true, 2, 3),
                new WarMachinesFactory().create(4, 1, new DefaultDamageCalculator(new Random()), 1),
                new WarMachinesFactory().create(2, 1, new DefaultDamageCalculator(new Random()), 1),
                new StrongholdFactory().create(false, 3, 25)
        ), HeroStatistics.KNIGHT,
                SpellsBook.builder().spells(List.of(
                        new SpellFactory().create(HASTE, BASIC, 2),
                        new SpellFactory().create(MAGIC_ARROW, BASIC, 10),
                        new SpellFactory().create(FORTUNE, BASIC, 1),
                        new SpellFactory().create(LIGHTNING_BOLT, BASIC, 1),
                        new SpellFactory().create(PROTECTION_FROM_AIR, BASIC, 1),
                        new SpellFactory().create(CHAIN_LIGHTNING, BASIC, 1),
                        new SpellFactory().create(COUNTERSTRIKE, BASIC, 1),
                        new SpellFactory().create(SUMMON_AIR_ELEMENTAL, BASIC, 10),
                        new SpellFactory().create(PROTECTION_FROM_FIRE, BASIC, 1),
                        new SpellFactory().create(FIRE_BALL, BASIC, 1),
                        new SpellFactory().create(MISFORTUNE, BASIC, 10),
                        new SpellFactory().create(ARMAGEDDON, BASIC, 1),
                        new SpellFactory().create(INFERNO, BASIC, 1),
                        new SpellFactory().create(SUMMON_FIRE_ELEMENTAL, BASIC, 1),
                        new SpellFactory().create(SLOW, BASIC, 1),
                        new SpellFactory().create(DEATH_RIPPLE, BASIC, 1),
                        new SpellFactory().create(PROTECTION_FROM_EARTH, BASIC, 1),
                        new SpellFactory().create(SORROW, BASIC, 1),
                        new SpellFactory().create(STONESKIN, BASIC, 10),
                        new SpellFactory().create(METEOR_SHOWER, BASIC, 1),
                        new SpellFactory().create(IMPLOSION, BASIC, 1),
                        new SpellFactory().create(SUMMON_EARTH_ELEMENTAL, BASIC, 10),
                        new SpellFactory().create(ICE_BOLT, BASIC, 1),
                        new SpellFactory().create(WEAKNESS, BASIC, 1),
                        new SpellFactory().create(FROST_RING, BASIC, 1),
                        new SpellFactory().create(PRAYER, BASIC, 1),
                        new SpellFactory().create(DISPEL, BASIC, 1),
                        new SpellFactory().create(PROTECTION_FROM_WATER, BASIC, 1),
                        new SpellFactory().create(MIRTH, BASIC, 1),
                        new SpellFactory().create(SUMMON_WATER_ELEMENTAL, BASIC, 1)
                )).mana(50).build());
        ret.getCreatures().forEach(creature -> creature.setHeroNumber(2));
        return ret;
    }

}
