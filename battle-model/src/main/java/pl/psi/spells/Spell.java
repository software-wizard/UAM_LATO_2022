package pl.psi.spells;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Spell {

    private final int category; // 1 - Damage, 2 - Buff/Debuff, 3 - obszar
    private final String name;
    private final int rang;
    private final int manaCost;
    private String fieldToChange;
    private int value;
    private final int multiplier;

    public Spell(int category, String name, int rang, int manaCost, int value, int multiplier) {
        this.category = category;
        this.name = name;
        this.rang = rang;
        this.manaCost = manaCost;
        this.value = value;
        this.multiplier = multiplier;
    }

}
