package pl.psi.spells;

import lombok.Getter;
import pl.psi.TurnQueue;
import pl.psi.creatures.Creature;

import java.beans.PropertyChangeListener;
import java.util.function.BiConsumer;

public class MoralBuffDebuffSpell extends Spell<Creature> {

    private final int moral;
    private final int time;
    @Getter
    private RoundTimer roundTimer;
    private final SpellNames counterSpell;

    public MoralBuffDebuffSpell(SpellTypes category, SpellNames name, SpellMagicClass spellMagicClass, SpellRang rang, SpellAlignment spellAlignment, int manaCost, int moral, int time, SpellNames counterSpell) {
        super(category, name, spellMagicClass, rang, spellAlignment, manaCost);
        this.moral = moral;
        this.time = time;
        this.counterSpell = counterSpell;
    }

    public MoralBuffDebuffSpell(MoralBuffDebuffSpell moralBuffDebuffSpell, Creature creature) {
        super(moralBuffDebuffSpell.getCategory(), moralBuffDebuffSpell.getName(), moralBuffDebuffSpell.getSpellMagicClass(), moralBuffDebuffSpell.getRang(), moralBuffDebuffSpell.getSpellAlignment(), moralBuffDebuffSpell.getManaCost());
        this.moral = moralBuffDebuffSpell.moral;
        this.time = moralBuffDebuffSpell.time;
        this.counterSpell = moralBuffDebuffSpell.counterSpell;
        this.roundTimer = new RoundTimer(moralBuffDebuffSpell.time, this, creature, moralBuffDebuffSpell.counterSpell);
    }

    @Override
    public void castSpell(Creature aDefender, BiConsumer<String, PropertyChangeListener> consumer) {
        aDefender.addRunningSpell(this);
        MoralBuffDebuffSpell moralBuffDebuffSpell = new MoralBuffDebuffSpell(this, aDefender);
        consumer.accept(TurnQueue.END_OF_TURN, moralBuffDebuffSpell.getRoundTimer());
        aDefender.buffMorale(moral);
    }

    @Override
    public void unCastSpell(Creature creature) {
        creature.buffMorale(-moral);
    }
}
