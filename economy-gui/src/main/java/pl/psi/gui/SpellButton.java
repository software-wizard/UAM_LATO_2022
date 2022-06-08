package pl.psi.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import pl.psi.ProductType;
import pl.psi.spells.EconomySpell;

import java.util.function.BiConsumer;

public class SpellButton extends AbstractButton<EconomySpell> {


    public SpellButton(BiConsumer<ProductType, EconomySpell> ecoController, EconomySpell economySpell, boolean canBuy) {
        super(ecoController, economySpell, canBuy);
        PATH = "/spells/" + economySpell.getSpellStats().name() + ".png";
        DESCRIPTION = economySpell.getSpellRang() + " " + economySpell.getSpellStats().name() + " | " + economySpell.getGoldCost().getPrice();
    }

    @Override
    void acceptProduct(BiConsumer<ProductType, EconomySpell> buy) {
        buy.accept(ProductType.SPELL,product);
    }

    @Override
    void prepareTop(final FlowPane aTopPane) {
        aTopPane.getChildren().add(new Label("Single Cost: " + product.getGoldCost().getPrice()));
        aTopPane.getChildren().add(new Label("SpellStats : " + product.getSpellStats().name()));
        aTopPane.getChildren().add(new Label("SpellRang : " + product.getSpellRang().name()));
    }

}