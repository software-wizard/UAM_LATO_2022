package pl.psi.shop;

import pl.psi.artifacts.Artifact;
import pl.psi.hero.EconomyHero;

public class ArtifactShop extends AbstractShop<Artifact> {

    @Override
    public void addToHero(Artifact artifact, EconomyHero hero) {
        if(hero.canAddArtifact(artifact.getPlacement())){
            hero.substractGold(artifact.getGoldCost().getPrice());
            hero.addArtifact(artifact);
        }
        else{
            throw new IllegalStateException("hero cannot buy 2 artifacts of the same type");
        }
    }
}
