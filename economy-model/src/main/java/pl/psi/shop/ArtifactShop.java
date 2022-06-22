package pl.psi.shop;

import pl.psi.artifacts.EconomyArtifact;
import pl.psi.hero.EconomyHero;

public class ArtifactShop extends AbstractShop<EconomyArtifact> {

    @Override
    public void addToHero(EconomyArtifact artifact, EconomyHero hero) {
        if (hero.canAddArtifact(artifact.getPlacement())) {
            hero.substractGold(artifact.getGoldCost().getPrice());
            hero.equipArtifact(artifact);
        } else {
            throw new IllegalStateException("hero cannot buy 2 artifacts of the same type");
        }
    }
}
