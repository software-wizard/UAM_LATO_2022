package pl.psi.spells;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EconomySpell {

    private final String name;
    private final String magicGuild;
    private final String description;
    private final int level;
    private final int goldCost;

}
