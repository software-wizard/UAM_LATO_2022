package pl.psi.specialfields;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
public class FieryFields implements BuffSpellsIf {

    @Override
    public void buffSpells(List<Spells> spells) {


            spells.forEach(spell -> {
                if(spell.name == "Fireball") {
                    spell.setRang(3);
                }
            });
    }
    
}
}
