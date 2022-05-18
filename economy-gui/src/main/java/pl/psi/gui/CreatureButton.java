package pl.psi.gui;

import javafx.geometry.Orientation;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import pl.psi.ProductType;
import pl.psi.creatures.EconomyCreature;
import pl.psi.creatures.EconomyNecropolisFactory;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class CreatureButton extends Button
{

    private final String creatureName;
    private Stage dialog;
    private final int cost;
    private final String characteristics;

    // create each button to each Creature from Factory ( Factory must delivery another group )
    public CreatureButton( final EcoController aEcoController, final EconomyNecropolisFactory aFactory,
                           final boolean aUpgraded, final int aTier )
    {
        super( aFactory.create( aUpgraded, aTier, 1 ).getName() );
        EconomyCreature creature = aFactory.create( aUpgraded, aTier, 1 );
        creatureName = creature.getName();
        cost = creature.getGoldCost();
        String upgraded = null;
        if(aUpgraded)
            upgraded = " upgrated";
        else
            upgraded = " not upgrated";

        characteristics = "Tier : "+creature.getTier()+" , "+upgraded+" | Attack : "+creature.getStats().getAttack()+
                " | Armor : "+creature.getStats().getArmor()+" | HP : "+creature.getStats().getMaxHp();

        getStyleClass().add( "creatureButton" );

        addEventHandler( MouseEvent.MOUSE_CLICKED, ( e ) -> {
            final int amount = startDialogAndGetCreatureAmount();
            if( amount != 0 )
            {
                aEcoController.buy(ProductType.CREATURE, aFactory.create( aUpgraded, aTier, amount ) );
            }
            try {
                aEcoController.refreshGui();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        } );
    }

    private int startDialogAndGetCreatureAmount()
    {
        final VBox centerPane = new VBox();
        final HBox bottomPane = new HBox();
        final FlowPane topPane = new FlowPane(Orientation.HORIZONTAL,0,5);
        final Stage dialog = prepareWindow( centerPane, bottomPane, topPane);
        final Slider slider = createSlider();
        prepareConfirmAndCancelButton( bottomPane, slider );
        prepareTop( topPane,slider);
        centerPane.getChildren()
                .add( slider );

        dialog.showAndWait();

        return (int)slider.getValue();
    }


    // Top of window of buying
    private void prepareTop( final FlowPane aTopPane,final Slider aSlider )
    {
        // TODO creature cops should be visible here
        aTopPane.getChildren().add( new Label( "Single Cost: " + cost ) );

        aTopPane.getChildren().add( new Label( "Amount:" ) );
        final Label slideValueLabel = new Label( "0" );
        aTopPane.getChildren().add( slideValueLabel );

        aTopPane.getChildren().add( new Label( "Purchase Cost: " ) );
        final Label purchaseCost = new Label( "0" );
        aTopPane.getChildren().add( purchaseCost );

        aTopPane.getChildren().add( new Label( "             " ) );
        aTopPane.getChildren().add(new Label(characteristics));


        aSlider.valueProperty().addListener(( slider, aOld, aNew )
                -> {
            slideValueLabel.setText(String.valueOf(aNew.intValue()));
            purchaseCost.setText(String.valueOf(aNew.intValue()*cost));

        });


    }

    // window of buying
    private Stage prepareWindow( final Pane aCenter, final Pane aBottom, final Pane aTop)
    {
        dialog = new Stage();
        final BorderPane pane = new BorderPane();
        pane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        final Scene scene = new Scene( pane, 600, 250 );
        scene.getStylesheets().add( "fxml/main.css" );
        dialog.setScene( scene );
        dialog.initOwner( this.getScene().getWindow() );
        dialog.initModality( Modality.APPLICATION_MODAL );
        dialog.setTitle( "Buying " + creatureName );
        dialog.initStyle(StageStyle.UNDECORATED);


        pane.setTop( aTop );
        pane.setCenter( aCenter );
        pane.setBottom( aBottom );
        return dialog;
    }

    // Close , OK button
    private void prepareConfirmAndCancelButton( final HBox aBottomPane, final Slider aSlider )
    {
        final Button okButton = new Button( "OK" );
        aBottomPane.setAlignment( Pos.CENTER );
        okButton.addEventHandler( MouseEvent.MOUSE_CLICKED, ( e ) -> dialog.close() );
        okButton.setPrefWidth( 200 );

        final Button cancelButton = new Button( "CLOSE" );
        cancelButton.addEventHandler( MouseEvent.MOUSE_CLICKED, ( e ) -> {
            aSlider.setValue( 0 );
            dialog.close();
        } );
        cancelButton.setPrefWidth( 200 );

        HBox.setHgrow( okButton, Priority.ALWAYS );
        HBox.setHgrow( cancelButton, Priority.ALWAYS );
        aBottomPane.getChildren()
                .add( okButton );
        aBottomPane.getChildren()
                .add( cancelButton );
    }

    // Choose amount of creatures
    private Slider createSlider()
    {
        final Slider slider = new Slider();
        slider.setMin( 0 );
        slider.setMax( 20 );
        slider.setValue( 0 );
        slider.setShowTickLabels( true );
        slider.setShowTickMarks( true );
        slider.setMajorTickUnit( 10 );
        slider.setMinorTickCount( 5 );
        slider.setBlockIncrement( 10 );
        return slider;
    }
}
