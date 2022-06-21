package pl.psi.hero;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
public class Equipment {
    private final ArrayList<Artifact> artifacts;
    private final Backpack backpack;

    public Equipment() {
        artifacts = new ArrayList<>();
        backpack = new Backpack();
    }

    public void equipArtifact(Artifact aArtifact){
        for (Artifact artifactfromlist : artifacts){
            if (artifactfromlist.getPlacement().equals(aArtifact.getPlacement())){
                backpack.addArtifact(artifactfromlist);
                artifacts.remove(artifactfromlist);
                artifacts.add(aArtifact);
                return;
            }
        }
        artifacts.add(aArtifact);
    }

}
