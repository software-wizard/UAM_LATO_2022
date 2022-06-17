package pl.psi.converter;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.Hero;
import pl.psi.artifacts.ArtifactEffectApplicable;
import pl.psi.artifacts.ArtifactFactory;
import pl.psi.artifacts.EconomyArtifact;
import pl.psi.artifacts.model.Artifact;
import pl.psi.artifacts.model.ArtifactEffect;
import pl.psi.artifacts.model.ArtifactIf;
import pl.psi.artifacts.model.ArtifactTarget;
import pl.psi.creatures.Creature;
import pl.psi.creatures.EconomyCreature;
import pl.psi.gui.MainBattleController;
import pl.psi.gui.NecropolisFactory;
import pl.psi.hero.EconomyHero;
import pl.psi.spells.Spell;
import pl.psi.spells.SpellFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static Hero convert(final EconomyHero aPlayer)
    {
        final Multimap< ArtifactTarget, ArtifactIf> artifacts = getConvertedArtifacts( aPlayer );
        applyArtifactsToCreatures( aPlayer, artifacts );

        final List<Creature> creatures = new ArrayList<>();
        final NecropolisFactory factory = new NecropolisFactory();
        aPlayer.getCreatures()
                .forEach(ecoCreature -> creatures.add(factory.create(ecoCreature.isUpgraded(),
                        ecoCreature.getTier(), ecoCreature.getAmount())));

        final List<Spell> spells = new ArrayList<>();
        final SpellFactory spellFactory = new SpellFactory();
        aPlayer.getSpellList()
                .forEach(economySpell -> spells.add(spellFactory.create(economySpell.getSpellStats().getName(), economySpell.getSpellRang(), aPlayer.getSpellPower())));

        return new Hero(creatures, spells);
    }

    private static Multimap< ArtifactTarget, ArtifactIf > getConvertedArtifacts(final EconomyHero aPlayer )
    {
        final Multimap< ArtifactTarget, ArtifactIf> artifacts = ArrayListMultimap.create();
        final ArtifactFactory factory = new ArtifactFactory();
        final List< EconomyArtifact > economyArtifacts = aPlayer.getArtifactList();

        for( final EconomyArtifact economyArtifact: economyArtifacts )
        {
            final ArtifactTarget target = economyArtifact.getNameHolder().getHolderTarget();
            artifacts.put( target, factory.createArtifact( economyArtifact.getNameHolder() ) );
        }
        return artifacts;
    }

    private static void applyArtifactsToCreatures( final EconomyHero aPlayer, final Multimap< ArtifactTarget, ArtifactIf> aArtifacts )
    {
        List< ArtifactEffect< ArtifactEffectApplicable > > artifactEffects = aArtifacts.get( ArtifactTarget.CREATURES ).stream()
                .flatMap( art -> art.getEffects().stream() )
                .collect( Collectors.toList() );

        for( final EconomyCreature economyCreature : aPlayer.getCreatureList() )
        {
            artifactEffects.forEach( economyCreature::applyArtifactEffect );
        }
    }

}
