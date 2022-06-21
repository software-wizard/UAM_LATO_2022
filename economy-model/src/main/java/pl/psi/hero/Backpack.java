package pl.psi.hero;

import lombok.Getter;
import pl.psi.artifacts.EconomyArtifact;

import java.util.ArrayList;

@Getter
public class Backpack {
    ArrayList<EconomyArtifact> artifacts;

    public Backpack() {
        artifacts = new ArrayList<>();
    }

    public void addArtifact(Artifact aArtifact){
        artifacts.add(aArtifact);
    }
}
    int size() {
        return items.size();
    }
}