package pl.psi.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.SneakyThrows;
import pl.psi.EconomyEngine;
import pl.psi.converter.EcoBattleConverter;
import pl.psi.products.Products;
import pl.psi.products.creatures.EconomyCreature;
import pl.psi.products.creatures.EconomyNecropolisFactory;
import pl.psi.hero.EconomyHero;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EcoController implements PropertyChangeListener
{
    private final EconomyEngine economyEngine;
    @FXML
    HBox heroStateHBox;
    @FXML
    HBox shopsBox;
    @FXML
    Button readyButton;
    @FXML
    Label playerLabel;
    @FXML
    Label currentGoldLabel;
    @FXML
    Label roundNumberLabel;
    @FXML
    ScrollPane scrollPane;

    public EcoController( final EconomyHero aHero1, final EconomyHero aHero2 )
    {
        economyEngine = new EconomyEngine( aHero1, aHero2 );
    }

    @FXML
    void initialize() throws FileNotFoundException {
        refreshGui();
        economyEngine.addObserver( EconomyEngine.ACTIVE_HERO_CHANGED, this );
        economyEngine.addObserver( EconomyEngine.HERO_BOUGHT_CREATURE, this );
        economyEngine.addObserver( EconomyEngine.NEXT_ROUND, this );
        economyEngine.addObserver( EconomyEngine.END_SHOPPING, this );

        readyButton.addEventHandler( MouseEvent.MOUSE_CLICKED, ( e ) -> {
            if( economyEngine.getRoundNumber() < 2 )
            {
                economyEngine.pass();
            }
            else
            {
                goToBattle();
            }
        } );
    }

    private void goToBattle()
    {
        EcoBattleConverter.startBattle( economyEngine.getPlayer1(), economyEngine.getPlayer2() );
    }

    void refreshGui() throws FileNotFoundException {
        playerLabel.setText( economyEngine.getActiveHero()
                .toString() );
        currentGoldLabel.setText( String.valueOf( economyEngine.getActiveHero()
                .getGold() ) );
        roundNumberLabel.setText( String.valueOf( economyEngine.getRoundNumber() ) );
        shopsBox.getChildren()
                .clear();
        heroStateHBox.getChildren()
                .clear();

        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        final VBox creatureShop = new VBox();
        for( int i = 1; i < 8; i++ )
        {
            CreatureButton button = new CreatureButton( this, factory, false, i );
            String name = i + "0";
            // commit - Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/creatures/"+name+".png")));
            Image image = new Image(new FileInputStream("D:\\4 semestr informatyka\\Zaawansowane programowanie w Javie\\Projekt\\UAM_LATO_2022-develop — kopia1\\economy-gui\\src\\main\\resources\\creatures\\"+name+".png"));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(40);
            imageView.setFitWidth(40);
            button.setGraphic(imageView);
            button.setContentDisplay(ContentDisplay.LEFT);
            creatureShop.getChildren().add(button);

            CreatureButton button2 = new CreatureButton( this, factory, true, i );
            String name2 = i + "1";
            // commit - Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/creatures/"+name2+".png")));
            Image image2 = new Image(new FileInputStream("D:\\4 semestr informatyka\\Zaawansowane programowanie w Javie\\Projekt\\UAM_LATO_2022-develop — kopia1\\economy-gui\\src\\main\\resources\\creatures\\"+name2+".png"));
            ImageView imageView2 = new ImageView(image2);
            imageView2.setFitHeight(40);
            imageView2.setFitWidth(40);
            button2.setGraphic(imageView2);
            button2.setContentDisplay(ContentDisplay.LEFT);
            creatureShop.getChildren().add( button2 );
        }
        shopsBox.getChildren().add( creatureShop );

        final VBox creaturesBox = new VBox();
        economyEngine.getActiveHero()
                .getCreatures()
                .forEach( c -> {
                    final HBox tempHbox = new HBox();
                    tempHbox.getChildren()
                            .add( new Label( String.valueOf( c.getAmount() ) ) );
                    tempHbox.getChildren()
                            .add( new Label( c.getName() ) );
                    creaturesBox.getChildren()
                            .add( tempHbox );
                } );
        heroStateHBox.getChildren()
                .add( creaturesBox );
    }

    void buy(Products product , final EconomyCreature aCreature )
    {
        economyEngine.buy( product,aCreature );
    }

    @SneakyThrows
    @Override
    public void propertyChange( final PropertyChangeEvent aPropertyChangeEvent )
    {
        refreshGui();
    }
}
