package pl.psi.artifacts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.psi.artifacts.holder.ArtifactNamesHolder;
import pl.psi.shop.BuyProductInterface;
import pl.psi.shop.Money;

@Getter
@AllArgsConstructor
public class EconomyArtifact implements BuyProductInterface {

    private final ArtifactPlacement placement;
    private final String displayName;
    private Money price;
    private final ArtifactNamesHolder nameHolder;
    private final String description;

    @Override
    public Money getGoldCost() {
        return price;
    }
}
