package pl.psi.spells;

import lombok.Getter;
import pl.psi.TurnQueue;
import pl.psi.creatures.Creature;

import java.beans.PropertyChangeListener;
import java.util.Optional;
import java.util.function.BiConsumer;

public class LuckBuffDebuffSpell extends Spell<Creature> {

    private final int luck;
    private final int time;
    @Getter
    private RoundTimer roundTimer;
    private final SpellNames counterSpell;

    public LuckBuffDebuffSpell(SpellTypes category, SpellNames name, SpellMagicClass spellMagicClass, SpellRang rang, int manaCost, int luck, int time, SpellNames counterSpell) {
        super(category, name, spellMagicClass, rang, manaCost);
        this.luck = luck;
        this.time = time;
        this.counterSpell = counterSpell;
    }

    public LuckBuffDebuffSpell(LuckBuffDebuffSpell luckBuffDebuffSpell, Creature creature) {
        super(luckBuffDebuffSpell.getCategory(), luckBuffDebuffSpell.getName(), luckBuffDebuffSpell.getSpellMagicClass(), luckBuffDebuffSpell.getRang(), luckBuffDebuffSpell.getManaCost());
        this.luck = luckBuffDebuffSpell.luck;
        this.time = luckBuffDebuffSpell.time;
        this.counterSpell = luckBuffDebuffSpell.counterSpell;
        this.roundTimer = new RoundTimer(luckBuffDebuffSpell.time, this, creature);
    }

    @Override
    public void castSpell(Creature aDefender, BiConsumer<String, PropertyChangeListener> consumer) {
        Optional<Spell> any = aDefender.getRunningSpells().stream()
                .filter(spell -> spell.getName().equals(counterSpell))
                .findAny();
        if (any.isPresent()) {
            any.get().unCastSpell(aDefender);
            aDefender.getRunningSpells().removeIf(spell -> spell.getName().equals(counterSpell));
        }

        if (!aDefender.isRunningSpellsSlotsFull()){
            aDefender.getRunningSpells().poll().unCastSpell(aDefender);
        }

        aDefender.addRunningSpell(this);
        LuckBuffDebuffSpell moralBuffDebuffSpell = new LuckBuffDebuffSpell(this, aDefender);
        consumer.accept(TurnQueue.END_OF_TURN, moralBuffDebuffSpell.getRoundTimer());
        aDefender.buffLuck(luck);
    }

    @Override
    public void unCastSpell(Creature creature) {
        creature.buffLuck(-luck);
    }
}
