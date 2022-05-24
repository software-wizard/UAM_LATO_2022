package pl.psi.artifacts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.psi.shop.BuyProductInterface;
import pl.psi.shop.ProductPrice;

@Getter
@AllArgsConstructor
public class Artifact implements BuyProductInterface {

    private final ArtifactPlacement placement;
    private final String name;
    private ProductPrice price;

    @Override
    public int getAmount() {
        return 1;
    }

    @Override
    public ProductPrice getGoldCost() {
        return price;
    }

    public Object clone(){
        Artifact artifact = new Artifact(this.placement,this.name,this.price);
        return artifact;
    }
}
