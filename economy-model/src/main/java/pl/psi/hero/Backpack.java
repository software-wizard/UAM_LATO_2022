package pl.psi.hero;

import lombok.Getter;
import pl.psi.artifacts.Artifact;

import java.util.ArrayList;

@Getter
public class Backpack {
    ArrayList<Artifact> items;

    public Backpack() {
        items = new ArrayList<>();
    }

    public void addItem(Artifact aItem){
        items.add(aItem);
    }

    int size() {
        return items.size();
    }
}
