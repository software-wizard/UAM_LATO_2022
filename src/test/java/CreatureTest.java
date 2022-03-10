import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class CreatureTest
{

    private static final int NOT_IMPORTANT = 100;

    @Test
    void creatureShouldAttackProperly()
    {
        // given
        final Creature angel = new Creature.Bulider().hp( NOT_IMPORTANT )
            .attack( 50 )
            .defence( NOT_IMPORTANT )
            .bulid();
        final Creature dragon = new Creature.Bulider().hp( 100 )
            .attack( NOT_IMPORTANT )
            .defence( 10 )
            .bulid();
        // when
        angel.attack( dragon );
        // then
        assertThat( dragon.getCurrentHp() ).isEqualTo( 60 );
    }

    @Test
    void creatureShouldNotHealCreatureEvenHasLowerAttackThanDefenderArmor()
    {
        final Creature angel = new Creature.Bulider().hp( NOT_IMPORTANT )
            .attack( 1 )
            .defence( NOT_IMPORTANT )
            .bulid();
        final Creature dragon = new Creature.Bulider().hp( 100 )
            .attack( NOT_IMPORTANT )
            .defence( 10 )
            .bulid();
        // when
        angel.attack( dragon );
        // then
        assertThat( dragon.getCurrentHp() ).isEqualTo( 100 );
    }
}