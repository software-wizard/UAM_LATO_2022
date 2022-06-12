package pl.psi.specialfields;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.psi.creatures.DamageCalculatorIf;

@Getter
@Setter
@ToString
public class Wall {
    private int currentHp;
    private DamageCalculatorIf calculator;

    Wall()
    {
    }

}
