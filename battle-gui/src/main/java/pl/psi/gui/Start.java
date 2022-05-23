package pl.psi.gui;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import pl.psi.Hero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.spells.SpellFactory;

import static pl.psi.spells.SpellRang.ADVANCED;
import static pl.psi.spells.SpellRang.BASIC;

public class Start extends Application
{

    public Start()
    {

    }

    static void main( final String[] args )
    {
        launch( args );
    }

    @Override
    public void start( final Stage primaryStage )
    {
        Scene scene = null;
        try
        {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation( Start.class.getClassLoader()
                .getResource( "fxml/main-battle.fxml" ) );
            loader.setController( new MainBattleController( createP1(), createP2() ) );
            scene = new Scene( loader.load() );
            primaryStage.setScene( scene );
            primaryStage.setX( 5 );
            primaryStage.setY( 5 );
            primaryStage.show();
        }
        catch( final IOException aE )
        {
            aE.printStackTrace();
        }
    }

    private Hero createP2()
    {
        final Hero ret = new Hero( List.of( new NecropolisFactory().create( true, 1, 5 ), new NecropolisFactory().create( true, 1, 5 ) ), Collections.emptyList());
        return ret;
    }

    private Hero createP1()
    {
        final Hero ret = new Hero( List.of( new NecropolisFactory().create( false, 1, 5 ) ), List.of(new SpellFactory().create("Haste", BASIC, 10,1), new SpellFactory().create("Haste", ADVANCED, 10,1)));
        return ret;
    }

}
