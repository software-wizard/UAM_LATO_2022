package pl.psi.gui;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.psi.ProductType;
import pl.psi.creatures.EconomyCreature;

// TODO createConfirm - Stage and Top - nie
// TODO &&
public class CreatureButton extends Button {
    private final EconomyCreature creature;

    public CreatureButton(final EcoController aEcoController, EconomyCreature creature, final int maxSliderValue, boolean canBuy, boolean canBuyMore) {
        this.creature = creature;

        addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            final int amount = startDialogAndGetCreatureAmount(maxSliderValue, canBuy, canBuyMore);
            if (amount != 0) {
                creature.increaseAmount(amount-1);
                aEcoController.buy(ProductType.CREATURE,creature);

            }

        });
    }

    private int startDialogAndGetCreatureAmount(final int maxSliderValue, boolean canBuy, boolean canBuyMore) {
        // Slider
        final VBox centerPane = new VBox();
        // OK and Close Bottoms
        final HBox bottomPane = new HBox();
        // Pane for cost and info about item
        final FlowPane topPane = new FlowPane(Orientation.HORIZONTAL, 0, 5);


        final Slider slider = createSlider(maxSliderValue);
        slider.setMaxWidth(610);

        // if hero cannot buy centerPane instead of Slider show Label , because to do delete Slider a lot of code can be changed
        if (canBuy && canBuyMore)
            centerPane.getChildren().add(slider);
        else if (!canBuy)
            centerPane.getChildren().add(new Label("You don't have enought money to buy " + creature.getName()));
        else if (!canBuyMore)
            centerPane.getChildren().add(new Label("You already have bought 7 types of creatures"));

        prepareTop(topPane, slider);

        final Stage dialogWindow = new Stage();
        final Stage dialog = prepareWindow(centerPane, bottomPane, topPane, dialogWindow);
        prepareConfirmAndCancelButton(bottomPane, slider, dialogWindow);

        dialog.showAndWait();

        return (int) slider.getValue();
    }

    private Stage prepareWindow(final Pane aCenter, final Pane aBottom, final Pane aTop, final Stage dialog) {

        final BorderPane pane = new BorderPane();
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        final Scene scene = new Scene(pane, 620, 300);
        scene.getStylesheets().add("fxml/main.css");
        dialog.setScene(scene);
        dialog.initOwner(this.getScene().getWindow());
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);

        pane.setTop(aTop);
        pane.setCenter(aCenter);
        pane.setBottom(aBottom);
        return dialog;
    }


    // Top of window of buying
    private void prepareTop(final FlowPane aTopPane, final Slider aSlider) {

        aTopPane.getChildren().add(new Label("Single Cost: " + creature.getGoldCost().getPrice()));

        aTopPane.getChildren().add(new Label("Amount:"));
        final Label slideValueLabel = new Label("0");
        aTopPane.getChildren().add(slideValueLabel);

        aTopPane.getChildren().add(new Label("Purchase Cost: "));
        final Label purchaseCost = new Label("0");
        aTopPane.getChildren().add(purchaseCost);


        String upgraded = null;
        if (creature.isUpgraded())
            upgraded = " upgrated";
        else
            upgraded = " not upgrated";
        String characteristics = "Tier : " + creature.getTier() + " , " + upgraded + " | Attack : " + creature.getStats().getAttack() +
                " | Armor : " + creature.getStats().getArmor() + " | HP : " + creature.getStats().getMaxHp();

        aTopPane.getChildren().add(new Label("             "));
        aTopPane.getChildren().add(new Label(characteristics));
        Text text = new Text();
        text.setFont(Font.font("Arial", FontWeight.MEDIUM, FontPosture.REGULAR, 9));
        text.setText(creature.getStats().getDescription());
        aTopPane.getChildren().add(text);


        aSlider.valueProperty().addListener((slider, aOld, aNew)
                -> {
            slideValueLabel.setText(String.valueOf(aNew.intValue()));
            purchaseCost.setText(String.valueOf(aNew.intValue() * creature.getGoldCost().getPrice()));

        });


    }


    private void prepareConfirmAndCancelButton(final HBox aBottomPane, final Slider aSlider, final Stage dialog) {
//        aBottomPane.setAlignment(Pos.CENTER);
        aBottomPane.setSpacing(30);
        final Button okButton = new Button("OK");
        okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> dialog.close());
        okButton.setPrefWidth(200);
        aBottomPane.getChildren().add(okButton);


        final Button cancelButton = new Button("CLOSE");
        cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            aSlider.setValue(0);
            dialog.close();
        });
        cancelButton.setPrefWidth(200);
        aBottomPane.getChildren().add(cancelButton);

        HBox.setHgrow(okButton, Priority.ALWAYS);
        HBox.setHgrow(cancelButton, Priority.ALWAYS);
    }

    private Slider createSlider(final int maxSliderValue) {
        final Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(maxSliderValue);
        slider.setValue(0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(10);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(5);
        return slider;
    }

}