package pl.psi.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import pl.psi.ProductType;
import pl.psi.spells.EconomySpell;
import java.util.function.BiConsumer;

public class SpellButton extends AbstractButton<EconomySpell> {

    public SpellButton(final BiConsumer<ProductType, EconomySpell> ecoController,final EconomySpell economySpell, boolean canBuy) {
        super(ecoController, economySpell, canBuy);
        PATH = "/spells/" + economySpell.getSpellStats().toString() + ".png";
        DESCRIPTION = economySpell.getSpellRang() + " " + economySpell.getSpellStats().name() + " | " + economySpell.getGoldCost().getPrice();
    }

    @Override
    void acceptProduct(final BiConsumer<ProductType, EconomySpell> buy) {
        buy.accept(ProductType.SPELL,product);
    }

    @Override
    void prepareTop(final FlowPane aTopPane) {
        aTopPane.getChildren().add(new Label("Single Cost: " + product.getGoldCost().getPrice()));
        aTopPane.getChildren().add(new Label("SpellStats : " + product.getSpellStats().name()));
        aTopPane.getChildren().add(new Label("SpellRang : " + product.getSpellRang().name()));
        final Text text = new Text("\n" + product.getSpellStats().getDescription());
        text.setFont(Font.font ("Arial", 17));
        aTopPane.getChildren().add(text);
    }

}