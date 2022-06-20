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

/**
Class represents button to buy one product
 */

public abstract class AbstractButton <T extends BuyProductInterface> extends Button {

    protected final T product;
    protected String PATH;
    protected String DESCRIPTION;

    public AbstractButton(final BiConsumer<ProductType,T> ecoController, T t, boolean canBuy) {
        this.product = t;

        addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            startDialog(ecoController, canBuy);
        });
    }

    private void startDialog(final BiConsumer<ProductType,T> ecoController, boolean canBuy) {

        final Stage dialogWindow = new Stage();
        final VBox bottomPane = new VBox();
        final FlowPane topPane = new FlowPane(Orientation.HORIZONTAL, 0, 5);
        if (!canBuy) {
            HBox box = new HBox();
            box.getChildren().add(new Label("You cannot buy this product \nyou have bought this type or you don't have enough money"));
            topPane.getChildren().add(box);
        }
           prepareWindow(bottomPane, topPane, dialogWindow);
           prepareConfirmAndCancelButton(bottomPane, ecoController, dialogWindow, canBuy);
           dialogWindow.showAndWait();
    }


    private void prepareWindow(final Pane aBottom, final FlowPane aTop, final Stage dialog) {

        final BorderPane pane = new BorderPane();
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        prepareTop(aTop);
        pane.setTop(aTop);
        pane.setBottom(aBottom);

        final Scene scene = new Scene(pane, 750, 200);
        scene.getStylesheets().add("fxml/main.css");
        dialog.setScene(scene);
        dialog.initOwner(this.getScene().getWindow());
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);

    }

    private void prepareConfirmAndCancelButton(final VBox aBottomPane, BiConsumer<ProductType,T> ecoController, Stage dialog, boolean canBuy) {
        final HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        Button okButton = null;

        if (canBuy) {
            okButton = new Button("OK");
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

    abstract void acceptProduct(final BiConsumer<ProductType,T > buy);

    abstract void prepareTop(final FlowPane aTopPane);


}
