package pl.psi.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.psi.Hero;
import pl.psi.creatures.Creature;
import pl.psi.spells.AirSpellFactory;
import pl.psi.spells.Spell;
import pl.psi.gui.MainBattleController;
import pl.psi.gui.NecropolisFactory;
import pl.psi.hero.EconomyHero;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static pl.psi.spells.AirSpells.HASTE;

public class EcoBattleConverter
{

    public static void startBattle( final EconomyHero aPlayer1, final EconomyHero aPlayer2 )
    {
        Scene scene = null;
        try
        {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation( EcoBattleConverter.class.getClassLoader()
                .getResource( "fxml/main-battle.fxml" ) );
            loader.setController( new MainBattleController( convert( aPlayer1 ), convert( aPlayer2 ) ) );
            scene = new Scene( loader.load() );
            final Stage aStage = new Stage();
            aStage.setScene( scene );
            aStage.setX( 5 );
            aStage.setY( 5 );
            aStage.show();
        }
        catch( final IOException aE )
        {
            aE.printStackTrace();
        }
    }

    public static Hero convert( final EconomyHero aPlayer1 )
    {
        final List< Creature > creatures = new ArrayList<>();
        final NecropolisFactory factory = new NecropolisFactory();
        aPlayer1.getCreatures()
            .forEach( ecoCreature -> creatures.add( factory.create( ecoCreature.isUpgraded(),
                ecoCreature.getTier(), ecoCreature.getAmount() ) ) );

        // Creating battle spells
        // Get spell rang from eco-hero

        List<Spell> spells = new ArrayList<>();
        AirSpellFactory spellFactory = new AirSpellFactory();

        spellFactory.create(HASTE); // and spell rang


        return new Hero( creatures );
    }
}
