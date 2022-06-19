package pl.psi.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import pl.psi.ProductType;
import pl.psi.skills.EconomySkill;
import pl.psi.skills.SkillLevel;

import java.util.function.BiConsumer;

public class SkillButton extends AbstractButton<EconomySkill> {


    public SkillButton(BiConsumer<ProductType, EconomySkill> ecoController, EconomySkill economySkill, boolean canBuy, SkillLevel level) {
        super(ecoController, economySkill, canBuy);
        PATH = "/skills/" + economySkill.getSkillType().name() + ".png";
        DESCRIPTION = level + " " + economySkill.getSkillType() + " | " + economySkill.getGoldCost().getPrice();
    }

    @Override
    void acceptProduct(BiConsumer<ProductType, EconomySkill> buy) {
        buy.accept(ProductType.SKILL,product);
    }

    @Override
    void prepareTop(final FlowPane aTopPane) {
        aTopPane.getChildren().add(new Label("Single Cost: " + product.getGoldCost().getPrice()));
    }
}