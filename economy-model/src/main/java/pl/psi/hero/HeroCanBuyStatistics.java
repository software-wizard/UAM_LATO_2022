package pl.psi.hero;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HeroCanBuyStatistics {

    private int limitHead;
    private int boughtHead;

    private int limitNeck;
    private int boughtNeck;

    private int limitTorso;
    private int boughtTorso;

    private int limitShoulders;
    private int boughtShoulders;

    private int limitRightHand;
    private int boughtRightHand;

    private int limitLeftHand;
    private int boughtLeftHand;

    private int limitFeet;
    private int boughtFeet;

    public void addLimitForAmountOfCreatures(final int amount){
        limitHead = limitHead +amount;
        limitNeck = limitHead + amount;
        limitTorso = limitTorso + amount;
        limitLeftHand = limitLeftHand + amount;
        limitRightHand = limitRightHand + amount;
        limitShoulders = limitShoulders + amount;
        limitFeet = limitFeet + amount;
    }
}
