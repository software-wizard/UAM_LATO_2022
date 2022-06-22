package pl.psi.hero;

import lombok.Getter;
import pl.psi.artifacts.EconomyArtifact;
import pl.psi.artifacts.ArtifactPlacement;

import java.util.ArrayList;

@Getter
public class Equipment {
    private final EqSlot headSlot;
    private final EqSlot neckSlot;
    private final EqSlot torsoSlot;
    private final EqSlot shouldersSlot;
    private final EqSlot rightHandSlot;
    private final EqSlot leftHandSlot;
    private final EqSlot feetSlot;

    public Equipment() {
        headSlot = new EqSlot(ArtifactPlacement.HEAD);
        neckSlot = new EqSlot(ArtifactPlacement.NECK);
        torsoSlot = new EqSlot(ArtifactPlacement.TORSO);
        shouldersSlot = new EqSlot(ArtifactPlacement.SHOULDERS);
        rightHandSlot = new EqSlot(ArtifactPlacement.RIGHT_HAND);
        leftHandSlot = new EqSlot(ArtifactPlacement.LEFT_HAND);
        feetSlot = new EqSlot(ArtifactPlacement.FEET);
    }

    public void setHead(EconomyArtifact aItem) {
        headSlot.setItem(aItem);
    }

    public void setNeck(EconomyArtifact aItem) {
        neckSlot.setItem(aItem);
    }

    public void setTorso(EconomyArtifact aItem) {
        torsoSlot.setItem(aItem);
    }

    public void setShoulders(EconomyArtifact aItem) {
        shouldersSlot.setItem(aItem);
    }

    public void setRightHand(EconomyArtifact aItem) {
        rightHandSlot.setItem(aItem);
    }

    public void setLeftHand(EconomyArtifact aItem) {
        leftHandSlot.setItem(aItem);
    }

    public void setFeet(EconomyArtifact aItem) {
        feetSlot.setItem(aItem);
    }

    public ArrayList<EconomyArtifact> getEquipment() {
        ArrayList<EconomyArtifact> items = new ArrayList<>();
        items.add(headSlot.getItem());
        items.add(neckSlot.getItem());
        items.add(torsoSlot.getItem());
        items.add(shouldersSlot.getItem());
        items.add(rightHandSlot.getItem());
        items.add(leftHandSlot.getItem());
        items.add(feetSlot.getItem());
        return items;
    }

}
