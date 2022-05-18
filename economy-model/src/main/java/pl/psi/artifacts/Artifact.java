package pl.psi.artifacts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.psi.shop.BuyProductInterface;

// Class only for compile ( later we will use Artifacts from another group )
@Getter
@AllArgsConstructor
public class Artifact implements BuyProductInterface {

    private final ArtifactPlacement placement;

    private final String name;

    private final int price;

    @Override
    public int getAmount() {
        return 1;
    }

    @Override
    public int getGoldCost() {
        return price;
    }

    public Object clone(){
        Artifact artifact = new Artifact(this.placement,this.name,this.price);
        return artifact;
    }
}
