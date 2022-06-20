package pl.psi.spells;

import lombok.Getter;
import pl.psi.TurnQueue;
import pl.psi.creatures.Creature;
import pl.psi.creatures.DamageCalculatorIf;

import java.beans.PropertyChangeListener;
import java.util.function.BiConsumer;

public class ChangeCalculatorBuff extends Spell<Creature> {

    private final int time;
    @Getter
    private RoundTimer roundTimer;

    private final DamageCalculatorIf damageCalculator;
    private DamageCalculatorIf currentCalculator;

    public ChangeCalculatorBuff(SpellTypes category, SpellNames name, SpellMagicClass spellMagicClass, SpellRang rang, SpellAlignment spellAlignment, int manaCost, int time, DamageCalculatorIf damageCalculator) {
        super(category, name, spellMagicClass, rang, spellAlignment, manaCost);
        this.time = time;
        this.damageCalculator = damageCalculator;
    }

    public ChangeCalculatorBuff(ChangeCalculatorBuff changeCalculatorBuff, Creature creature, DamageCalculatorIf damageCalculator) {
        super(changeCalculatorBuff.getCategory(), changeCalculatorBuff.getName(), changeCalculatorBuff.getSpellMagicClass(), changeCalculatorBuff.getRang(), changeCalculatorBuff.getSpellAlignment(), changeCalculatorBuff.getManaCost());
        this.time = changeCalculatorBuff.time;
        this.damageCalculator = damageCalculator;
        this.roundTimer = new RoundTimer(changeCalculatorBuff.time, this, creature, null);
        currentCalculator = creature.getCalculator();
    }

    @Override
    public void castSpell(Creature aDefender, BiConsumer<String, PropertyChangeListener> consumer) {
        aDefender.addRunningSpell(this);
        ChangeCalculatorBuff changeCalculatorBuff = new ChangeCalculatorBuff(this, aDefender, damageCalculator);
        consumer.accept(TurnQueue.END_OF_TURN, changeCalculatorBuff.getRoundTimer());

        aDefender.setCalculator(damageCalculator);
    }

    @Override
    public void unCastSpell(Creature creature) {
        creature.setCalculator(currentCalculator);
    }
}
