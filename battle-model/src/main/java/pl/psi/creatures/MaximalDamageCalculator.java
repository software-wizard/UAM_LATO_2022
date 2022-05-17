package pl.psi.creatures;

import java.util.Random;

public class MaximalDamageCalculator extends AbstractCalculateDamageStrategy{


    protected MaximalDamageCalculator(Random aRand) {
        super(aRand);
    }

    @Override
    boolean maximalDamageRange(){ return true; }

}