package pl.psi.hero;

import lombok.Getter;
import pl.psi.artifacts.EconomyArtifact;

import java.util.ArrayList;

@Getter
public class Backpack {
    ArrayList<EconomyArtifact> items;

    public Backpack() {
        items = new ArrayList<>();
    }

    public void addItem(EconomyArtifact aItem) {
        items.add(aItem);
    }

    int size() {
        return items.size();
    }
}
