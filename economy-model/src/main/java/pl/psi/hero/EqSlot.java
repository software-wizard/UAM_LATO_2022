package pl.psi.hero;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class EqSlot {
    private final ArtifactPlacement type;
    private Artifact item;

    public EqSlot(final ArtifactPlacement aType) {
        type = aType;
    }

    public void setItem(Artifact aItem) {
        if (aItem.getPlacement().equals(type)){
            item = aItem;
        }
        else throw new IllegalStateException( "Incorrect item placement" );
    }
}
