package pl.psi.gui;

import javafx.geometry.Orientation;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import pl.psi.ProductType;
import pl.psi.artifacts.Artifact;
import pl.psi.artifacts.EconomyArtifactFactory;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ArtifactButton extends Button
{

    private final Artifact artifact;

    public ArtifactButton( EcoController ecoController, Artifact artifact,boolean canBuy,boolean canBuyType)
    {
        this.artifact  = artifact;

        addEventHandler( MouseEvent.MOUSE_CLICKED, ( e ) -> {
            startDialog(ecoController,canBuy,canBuyType);
        } );
    }

    private void startDialog(EcoController ecoController,boolean canBuy,boolean canBuyType)
    {

        Stage dialogWindow = new Stage();
        // OK and Close buttons
        final VBox bottomPane = new VBox();
        // Pane for cost and info about item
        final FlowPane topPane = new FlowPane(Orientation.HORIZONTAL,0,5);
        if(!canBuy || !canBuyType) {
            HBox box = new HBox();
            if(!canBuy)
                box.getChildren().add(new Label("You don't have enought money to buy " + artifact.getName()));
            else if(!canBuyType)
                box.getChildren().add(new Label("You have already bought artifact of this type " + artifact.getPlacement()));
            bottomPane.getChildren().add(box);
        }
        final Stage dialog = prepareWindow( bottomPane, topPane,dialogWindow);
        // add buttons - OK and Close
        prepareConfirmAndCancelButton( bottomPane ,ecoController, dialogWindow, canBuy, canBuyType);
        // info on Top
        prepareTop( topPane);
        dialog.showAndWait();

    }

    private Stage prepareWindow(final Pane aBottom, final Pane aTop,final Stage dialog)
    {

        final BorderPane pane = new BorderPane();
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        final Scene scene = new Scene( pane, 600, 100 );
        scene.getStylesheets().add( "fxml/main.css" );
        dialog.setScene( scene );
        dialog.initOwner( this.getScene().getWindow() );
        dialog.initModality( Modality.APPLICATION_MODAL );
        dialog.initStyle(StageStyle.UNDECORATED);

        pane.setTop( aTop );
        pane.setBottom( aBottom );

        return dialog;
    }

    private void prepareConfirmAndCancelButton( final VBox aBottomPane , EcoController ecoController, Stage dialog,boolean canBuy,boolean canBuyType)
    {
        final HBox hBox = new HBox();
        Button okButton = null;

        if(canBuy && canBuyType) {
            okButton = new Button( "OK" );
            hBox.setAlignment(Pos.CENTER);
            okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                ecoController.buy(ProductType.ARTIFACT, new EconomyArtifactFactory().create(artifact.getName()));
                dialog.close();
            });
            okButton.setPrefWidth( 200 );
            hBox.getChildren().add( okButton );
            HBox.setHgrow( okButton, Priority.ALWAYS );
        }


        final Button cancelButton = new Button( "CLOSE" );
        cancelButton.addEventHandler( MouseEvent.MOUSE_CLICKED, ( e ) -> {
            dialog.close();
        } );
        cancelButton.setPrefWidth( 200 );
        hBox.getChildren().add( cancelButton );

        aBottomPane.getChildren().add(hBox);
        HBox.setHgrow( cancelButton, Priority.ALWAYS );

    }

    private void prepareTop( final FlowPane aTopPane )
    {

        aTopPane.getChildren().add( new Label( "Single Cost: " + artifact.getGoldCost().getPrice() ) );;
        aTopPane.getChildren().add( new Label( "             " ) );
        String characteristics = "Placement : "+ artifact.getPlacement();
        aTopPane.getChildren().add(new Label(characteristics));
    }

}