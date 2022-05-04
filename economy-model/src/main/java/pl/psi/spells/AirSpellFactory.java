package pl.psi.spells;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class AirSpellFactory implements SpellFactory {

    private static final Map<Integer, AirSpells> AIR_SPELLS_MAP = new HashMap<>();

    public AirSpellFactory() {
        //TODO: adding spells to the map
    }

    @Override
    public EconomySpell create(SpellStats name) {
        //TODO: switch depending on enum with names
        return null;
    }
}