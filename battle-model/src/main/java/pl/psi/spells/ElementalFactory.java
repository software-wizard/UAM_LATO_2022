package pl.psi.spells;

import pl.psi.Hero;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatistic;
import pl.psi.creatures.NoCounterCreatureDecorator;
import pl.psi.creatures.SelfHealAfterTurnCreatureDecorator;

import java.util.List;

import static pl.psi.creatures.CreatureStatistic.*;
import static pl.psi.spells.SpellNames.*;

public class ElementalFactory {

    private static final String EXCEPTION_MESSAGE = "We don't support this creature";

    public Creature create(String name, Hero hero) {
        switch (name) {
            case "Air Elemental":
                Creature airElemental = new Creature.Builder().statistic(AIR_ELEMENTAL)
                        .amount(hero.getStats().getSpellPower())
                        .build();
                return new ElementalDecorator(airElemental, List.of(METEOR_SHOWER), List.of(LIGHTNING_BOLT, CHAIN_LIGHTNING));
            case "Fire Elemental":
                Creature fireElemental = new Creature.Builder().statistic(FIRE_ELEMENTAL)
                        .amount(hero.getStats().getSpellPower())
                        .build();
                return new ElementalDecorator(fireElemental, List.of(), List.of(FROST_RING));
            case "Earth Elemental":
                Creature earthElemental = new Creature.Builder().statistic(EARTH_ELEMENTAL)
                        .amount(hero.getStats().getSpellPower())
                        .build();
                return new ElementalDecorator(earthElemental, List.of(LIGHTNING_BOLT, CHAIN_LIGHTNING), List.of(METEOR_SHOWER));
            case "Water Elemental":
                Creature waterElemental = new Creature.Builder().statistic(WATER_ELEMENTAL)
                        .amount(hero.getStats().getSpellPower())
                        .build();
                return new ElementalDecorator(waterElemental, List.of(ICE_BOLT, FROST_RING), List.of(INFERNO, FIRE_BALL));
            default:
                throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
    }
}
