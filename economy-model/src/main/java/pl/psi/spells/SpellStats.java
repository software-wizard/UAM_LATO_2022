package pl.psi.spells;

import lombok.Getter;

import static pl.psi.spells.SpellMagicGuild.AIR;
import static pl.psi.spells.SpellMagicGuild.FIRE;

@Getter
public enum SpellStats implements SpellStatisticIf {
    MAGIC_ARROW("Magic Arrow", AIR, "Causes a bolt of magical energy to strike the selected unit.", 0),
    HASTE("Haste", AIR, "Increases the speed of the selected unit.", 1),
    FIREBALL("Fire Ball", FIRE, "Causes the selected target to burst into flames, inflicting fire damage to the target and any adjacent units.", 0);

    private final String name;
    private final SpellMagicGuild magicGuild;
    private final String description;
    private final int duration;

    SpellStats(String name, SpellMagicGuild magicGuild, String description, int duration) {
        this.name = name;
        this.magicGuild = magicGuild;
        this.description = description;
        this.duration = duration;
    }
}
