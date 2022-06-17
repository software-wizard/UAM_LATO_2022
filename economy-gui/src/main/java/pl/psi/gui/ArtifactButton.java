package pl.psi.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import pl.psi.ProductType;
import pl.psi.artifacts.EconomyArtifact;

import java.util.function.BiConsumer;

public class ArtifactButton extends AbstractButton<EconomyArtifact>{


    public ArtifactButton(BiConsumer<ProductType, EconomyArtifact> ecoController, EconomyArtifact artifact, boolean canBuy) {
        super(ecoController, artifact, canBuy);
        PATH = "/artifacts/" + artifact.getNameHolder().toString() + ".png";
        DESCRIPTION = artifact.getDisplayName() + " | " + artifact.getGoldCost().getPrice();
    }

    @Override
    void acceptProduct(BiConsumer<ProductType, EconomyArtifact> buy) {
        buy.accept(ProductType.ARTIFACT,product);
    }

    @Override
    void prepareTop(final FlowPane aTopPane) {
        aTopPane.getChildren().add(new Label("Single Cost: " + product.getGoldCost().getPrice()));
        aTopPane.getChildren().add(new Label("             "));
        String characteristics = "Placement : " + product.getPlacement();
        aTopPane.getChildren().add(new Label(characteristics));
        aTopPane.getChildren().add(new Label(product.getDescription()));
    }

}