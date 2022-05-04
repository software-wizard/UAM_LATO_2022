package pl.psi.products.artifacts;

import pl.psi.products.BuyProductInterface;

public class EconomyArtifact implements BuyProductInterface {

    private final Artifact artifact;
    private int amount;

    EconomyArtifact(final Artifact artifact, final int aAmount)
    {
        this.artifact = artifact;
        amount = aAmount;
    }

    public int getAmount()
    {
        return amount;
    }

    public void increaseAmount(int aAmount){
        amount = amount + aAmount;
    }

    public int getGoldCost()
    {
        return artifact.getPrice();
    }

    public String getName()
    {
        return artifact.getName();
    }

    public Artifact getArtifact() {
        return artifact;
    }

}
