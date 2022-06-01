package pl.psi.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import pl.psi.ProductType;
import pl.psi.spells.EconomySpell;

public class SpellButton extends AbstractButton<EconomySpell> {

    public SpellButton(EcoController ecoController, EconomySpell spell, boolean canBuy) {
        super(ecoController, spell, canBuy);
    }

    @Override
    void buyProductiNController(EconomySpell spell,EcoController controller) {
        controller.buy(ProductType.SPELL,spell);
    }

    @Override
    void prepareTop(final FlowPane aTopPane) {
        aTopPane.getChildren().add(new Label("Single Cost: " + product.getGoldCost().getPrice()));
        aTopPane.getChildren().add(new Label("SpellStats : " + product.getSpellStats().name()));
        aTopPane.getChildren().add(new Label("SpellRang : " + product.getSpellRang().name()));
    }

}