package pl.psi.hero;

import lombok.Getter;
import pl.psi.artifacts.Artifact;


import java.util.ArrayList;

@Getter
public class Backpack {
    ArrayList<Artifact> artifacts;

    public Backpack() {
        artifacts = new ArrayList<>();
    }

    public void addArtifact(Artifact aArtifact){
        artifacts.add(aArtifact);
    }
}