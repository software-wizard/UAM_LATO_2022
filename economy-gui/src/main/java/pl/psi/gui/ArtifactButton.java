package pl.psi.gui;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.psi.ProductType;
import pl.psi.artifacts.Artifact;
import pl.psi.artifacts.EconomyArtifactFactory;

import java.io.FileNotFoundException;

public class ArtifactButton extends Button
{

    private final String artifactName;
    private Stage dialog;
    private final int cost;
    private final String charakteristics;

    // create each button to each Artifact from Factory ( Factory must delivery another group )
    public ArtifactButton(final EcoController aEcoController, final EconomyArtifactFactory aFactory, String name )
    {

        super( aFactory.create( name ).getName() );
        Artifact artifact = aFactory.create(name );
        artifactName = artifact.getName();
        cost = artifact.getGoldCost();

        charakteristics = "Placement : "+artifact.getPlacement();

        getStyleClass().add( "creatureButton" );

        addEventHandler( MouseEvent.MOUSE_CLICKED, ( e ) -> {
            final int amount = startDialogAndGetCreatureAmount();
            if( amount != 0 )
            {
                aEcoController.buy(ProductType.ARTIFACT, aFactory.create( name ) );
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
        prepareConfirmAndCancelButton( bottomPane );
        prepareTop( topPane);

        dialog.showAndWait();

        return 1;
    }

    // Top of window of buying
    private void prepareTop( final FlowPane aTopPane )
    {

        aTopPane.getChildren().add( new Label( "Single Cost: " + cost ) );;

        aTopPane.getChildren().add( new Label( "             " ) );
        aTopPane.getChildren().add(new Label(charakteristics));



    }

    // window of buying
    private Stage prepareWindow( final Pane aCenter, final Pane aBottom, final Pane aTop)
    {
        dialog = new Stage();
        final BorderPane pane = new BorderPane();
        pane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        final Scene scene = new Scene( pane, 600, 100 );
        scene.getStylesheets().add( "fxml/main.css" );
        dialog.setScene( scene );
        dialog.initOwner( this.getScene().getWindow() );
        dialog.initModality( Modality.APPLICATION_MODAL );
        dialog.setTitle( "Buying "  );
        dialog.initStyle(StageStyle.UNDECORATED);


        pane.setTop( aTop );
        pane.setCenter( aCenter );
        pane.setBottom( aBottom );
        return dialog;
    }

    // Close , OK button
    private void prepareConfirmAndCancelButton( final HBox aBottomPane )
    {
        final Button okButton = new Button( "OK" );
        aBottomPane.setAlignment( Pos.CENTER );
        okButton.addEventHandler( MouseEvent.MOUSE_CLICKED, ( e ) -> dialog.close() );
        okButton.setPrefWidth( 200 );

        final Button cancelButton = new Button( "CLOSE" );
        cancelButton.addEventHandler( MouseEvent.MOUSE_CLICKED, ( e ) -> {
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

}