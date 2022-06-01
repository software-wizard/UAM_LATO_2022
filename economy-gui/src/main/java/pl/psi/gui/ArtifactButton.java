package pl.psi.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import pl.psi.ProductType;
import pl.psi.artifacts.Artifact;

public class ArtifactButton extends AbstractButton<Artifact>{

    public ArtifactButton(EcoController ecoController, Artifact artifact, boolean canBuy) {
        super(ecoController, artifact, canBuy);
    }

    @Override
    void buyProductiNController(Artifact artifact,EcoController controller) {
        controller.buy(ProductType.ARTIFACT,product);
    }

    @Override
    void prepareTop(final FlowPane aTopPane) {
        aTopPane.getChildren().add(new Label("Single Cost: " + product.getGoldCost().getPrice()));
        aTopPane.getChildren().add(new Label("             "));
        String characteristics = "Placement : " + product.getPlacement();
        aTopPane.getChildren().add(new Label(characteristics));
    }

}