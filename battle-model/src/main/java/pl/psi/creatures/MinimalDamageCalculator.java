package pl.psi.creatures;

import java.util.Random;

public class MinimalDamageCalculator extends AbstractCalculateDamageStrategy{


    protected MinimalDamageCalculator(Random aRand) {
        super(aRand);
    }

    @Override
    boolean minimalDamageRange(){ return true; }

}
