package pl.psi.hero;

import lombok.Getter;
import lombok.Setter;
import pl.psi.artifacts.EconomyArtifact;
import pl.psi.artifacts.ArtifactPlacement;

@Getter
@Setter
public class EqSlot {
    private final ArtifactPlacement type;
    private EconomyArtifact item;

    public EqSlot(final ArtifactPlacement aType) {
        type = aType;
    }

    public void setItem(EconomyArtifact aItem) {
        if (aItem.getPlacement().equals(type)) {
            item = aItem;
        } else throw new IllegalStateException("Incorrect item placement");
    }
}
