package pl.psi.artifacts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.psi.shop.BuyProductInterface;
import pl.psi.shop.Money;

@Getter
@AllArgsConstructor
public class Artifact implements BuyProductInterface {

    private final ArtifactPlacement placement;
    private final String name;
    private Money price;

    @Override
    public Money getGoldCost() {
        return price;
    }
}
