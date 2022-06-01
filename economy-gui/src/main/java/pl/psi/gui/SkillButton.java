package pl.psi.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import pl.psi.ProductType;
import pl.psi.skills.EconomySkill;

public class SkillButton extends AbstractButton<EconomySkill> {

    public SkillButton(EcoController ecoController, EconomySkill skill, boolean canBuy) {
        super(ecoController, skill, canBuy);
    }

    @Override
    void buyProductiNController(EconomySkill skill,EcoController controller) {
        controller.buy(ProductType.SKILL,skill);
    }

    @Override
     void prepareTop(final FlowPane aTopPane) {
        aTopPane.getChildren().add(new Label("Single Cost: " + product.getGoldCost().getPrice()));
        aTopPane.getChildren().add(new Label("FACTOR : " + product.getFactor()));
    }
}