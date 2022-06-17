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
import pl.psi.shop.BuyProductInterface;

import java.util.function.BiConsumer;


public abstract class AbstractButton <T extends BuyProductInterface> extends Button {

    protected final T product;
    protected String PATH;
    protected String DESCRIPTION;

    public AbstractButton(BiConsumer<ProductType,T> ecoController, T t, boolean canBuy) {
        this.product = t;

        addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            startDialog(ecoController, canBuy);
        });
    }

    private void startDialog(BiConsumer<ProductType,T> ecoController, boolean canBuy) {

        Stage dialogWindow = new Stage();
        // OK and Close buttons
        final VBox bottomPane = new VBox();
        // Pane for cost and info about item
        final FlowPane topPane = new FlowPane(Orientation.HORIZONTAL, 0, 5);
        if (!canBuy) {
            HBox box = new HBox();
            box.getChildren().add(new Label("You cannot buy this product \nyou have bought this type or you don't have enough money"));
            topPane.getChildren().add(box);
        }
        final Stage dialog = prepareWindow(bottomPane, topPane, dialogWindow);
        // add buttons - OK and Close
        prepareConfirmAndCancelButton(bottomPane, ecoController, dialogWindow, canBuy);
        // info on Top
        prepareTop(topPane);
        dialog.showAndWait();
    }


    private Stage prepareWindow(final Pane aBottom, final Pane aTop, final Stage dialog) {

        final BorderPane pane = new BorderPane();
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        final Scene scene = new Scene(pane, 750, 200);
        scene.getStylesheets().add("fxml/main.css");
        dialog.setScene(scene);
        dialog.initOwner(this.getScene().getWindow());
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);

        pane.setTop(aTop);
        pane.setBottom(aBottom);

        return dialog;
    }

    private void prepareConfirmAndCancelButton(final VBox aBottomPane, BiConsumer<ProductType,T> ecoController, Stage dialog, boolean canBuy) {
        final HBox hBox = new HBox();
        Button okButton = null;

        if (canBuy) {
            okButton = new Button("OK");
            hBox.setAlignment(Pos.CENTER);
            okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                acceptProduct(ecoController);
                dialog.close();
            });
            okButton.setPrefWidth(200);
            hBox.getChildren().add(okButton);
            HBox.setHgrow(okButton, Priority.ALWAYS);
        }


        final Button cancelButton = new Button("CLOSE");
        cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            dialog.close();
        });
        cancelButton.setPrefWidth(200);
        hBox.getChildren().add(cancelButton);

        aBottomPane.getChildren().add(hBox);
        HBox.setHgrow(cancelButton, Priority.ALWAYS);

    }

    abstract void acceptProduct(BiConsumer<ProductType,T > buy);

    abstract void prepareTop(final FlowPane aTopPane);


}
