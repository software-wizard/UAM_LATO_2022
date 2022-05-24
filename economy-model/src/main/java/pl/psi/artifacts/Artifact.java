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
    public int getAmount() {
        return 1;
    }

    @Override
    public Money getGoldCost() {
        return price;
    }
}
