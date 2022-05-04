package pl.psi.hero;


import pl.psi.products.BuyProductInterface;
import pl.psi.products.Products;
import pl.psi.products.artifacts.ArtifactPlacement;
import pl.psi.products.artifacts.EconomyArtifact;
import pl.psi.products.creatures.EconomyCreature;

public class CreatureShop
{

    public void buy(Products product, final EconomyHero aHero, final BuyProductInterface buyProduct)
    {

        if(product.equals(Products.CREATURE)) {
            EconomyCreature creature = (EconomyCreature)buyProduct;
            if (aHero.canAddCreature(creature)) {
                aHero.substractGold(creature.getGoldCost() * creature.getAmount());
                aHero.addCreature(creature);
            } else {
                throw new IllegalStateException("hero cannot consume more creature");
            }
        }
        else if (product.equals(Products.ARTIFACT)){
            EconomyArtifact artifact = (EconomyArtifact) buyProduct;
            if(artifact.getArtifact().getPlacement().equals(ArtifactPlacement.HEAD)){
                if(aHero.canAddArtifact(ArtifactPlacement.HEAD,artifact.getAmount())){
                    aHero.substractGold(artifact.getGoldCost() * artifact.getAmount());
                    aHero.addArtifact(artifact);
                }
                else{
                    throw new IllegalStateException("hero cannot buy more artifacts");
                }
            }

        }
    }

}
