package pl.psi.hero;

import lombok.Getter;
import pl.psi.artifacts.EconomyArtifact;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Equipment {
    private final List<EconomyArtifact> artifacts;
    private final Backpack backpack;

    public Equipment() {
        artifacts = new ArrayList<>();
        backpack = new Backpack();
    }

    public void equipArtifact(EconomyArtifact aArtifact){
        artifacts.add(aArtifact);
    }

    public List<EconomyArtifact> getArtifacts() {
        return List.copyOf(artifacts);
    }

}
