package pl.psi.specialfields;

import pl.psi.creatures.Creature;

import java.util.List;

public class Field {
    private String imagePath = "";

    public Field() {}

    public Field(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void handleEffect(List<Creature> creature) {}
}
