package pl.psi.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import pl.psi.ProductType;
import pl.psi.artifacts.Artifact;

import java.util.function.BiConsumer;

public class ArtifactButton extends AbstractButton<Artifact>{


    public ArtifactButton(BiConsumer<ProductType, Artifact> ecoController, Artifact artifact, boolean canBuy) {
        super(ecoController, artifact, canBuy);
        PATH = "/artifacts/" + artifact.getName() + ".png";
        DESCRIPTION = artifact.getName() + " | " + artifact.getGoldCost().getPrice();
    }

    @Override
    void acceptProduct(BiConsumer<ProductType,Artifact > buy) {
        buy.accept(ProductType.ARTIFACT,product);
    }

    @Override
    void prepareTop(final FlowPane aTopPane) {
        aTopPane.getChildren().add(new Label("Single Cost: " + product.getGoldCost().getPrice()));
        aTopPane.getChildren().add(new Label("             "));
        String characteristics = "Placement : " + product.getPlacement();
        aTopPane.getChildren().add(new Label(characteristics));
    }

}