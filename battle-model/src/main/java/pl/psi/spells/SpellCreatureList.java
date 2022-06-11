package pl.psi.spells;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.psi.creatures.Creature;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpellCreatureList implements SpellableIf{

    private List<Creature> creatureList;

}
