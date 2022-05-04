package pl.psi.products.artifacts;

public class EconomyArtifactFactory {

    private static final String EXCEPTION_MESSAGE = "We don't have artifact with this name. Try again.";

    public EconomyArtifact create(final String name , final int amount){

        switch (name){

            case "Cape of Conjuring":
                return new EconomyArtifact(new Artifact(ArtifactPlacement.SHOULDERS,name,300),amount);
            case "Crown of Dragontooth":
                return new EconomyArtifact(new Artifact(ArtifactPlacement.HEAD,name,300),amount);
            case "Blackshard of the Dead Knight":
                return new EconomyArtifact(new Artifact(ArtifactPlacement.RIGHT_HAND,name,300),amount);
            default:
                throw new IllegalArgumentException( EXCEPTION_MESSAGE );
        }

    }
}
