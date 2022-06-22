package pl.psi.hero;

import lombok.Getter;
import pl.psi.artifacts.EconomyArtifact;
import pl.psi.artifacts.ArtifactPlacement;

import java.util.ArrayList;

@Getter
public class Equipment {
    private final ArrayList<EconomyArtifact> artifacts;
    private final Backpack backpack;

    public Equipment() {
        artifacts = new ArrayList<>();
        backpack = new Backpack();
    }

    public void equipArtifact(EconomyArtifact aArtifact){
        for (EconomyArtifact artifactfromlist : artifacts){
            if (artifactfromlist.getPlacement().equals(aArtifact.getPlacement())){
                backpack.addArtifact(artifactfromlist);
                this.artifacts.remove(artifactfromlist);
                this.artifacts.add(aArtifact);
                return;
            }
        }
        artifacts.add(aArtifact);
    }
}
