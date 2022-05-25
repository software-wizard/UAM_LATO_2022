package pl.psi.hero;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.psi.artifacts.model.Artifact;
import pl.psi.artifacts.CreatureArtifactApplicableProperty;
import pl.psi.artifacts.model.ArtifactApplyingMode;
import pl.psi.artifacts.model.ArtifactEffect;
import pl.psi.artifacts.ArtifactEffectApplicable;
import pl.psi.converter.EcoBattleConverter;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatistic;
import pl.psi.creatures.EconomyCreature;
import pl.psi.creatures.EconomyNecropolisFactory;

class EcoBattleConverterTest
{
  static EconomyNecropolisFactory necropolisFactory;

  @BeforeAll
  static void init()
  {
      necropolisFactory = new EconomyNecropolisFactory();
  }

  @Test
  void shouldConvertCreaturesCorrectly()
  {
    final EconomyHero ecoHero = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 1000 );

    ecoHero.addCreature(necropolisFactory.create(false, 1, 1));
    ecoHero.addCreature(necropolisFactory.create(false, 2, 2));
    ecoHero.addCreature(necropolisFactory.create(false, 3, 3));
    ecoHero.addCreature(necropolisFactory.create(false, 4, 4));
    ecoHero.addCreature(necropolisFactory.create(false, 5, 5));
    ecoHero.addCreature(necropolisFactory.create(false, 6, 6));
    ecoHero.addCreature(necropolisFactory.create(false, 7, 7));

    final List<Creature> convertedCreatures = EcoBattleConverter.convert(ecoHero)
        .getCreatures();

    assertEquals(7, convertedCreatures.size());

    assertEquals("Skeleton", convertedCreatures.get(0)
        .getName());
    assertEquals(1, convertedCreatures.get(0)
        .getAmount());

    assertEquals("Walking Dead", convertedCreatures.get(1)
        .getName());
    assertEquals(2, convertedCreatures.get(1)
        .getAmount());

    assertEquals("Wight", convertedCreatures.get(2)
        .getName());
    assertEquals(3, convertedCreatures.get(2)
        .getAmount());

    assertEquals("Vampire", convertedCreatures.get(3)
        .getName());
    assertEquals(4, convertedCreatures.get(3)
        .getAmount());

    assertEquals("Lich", convertedCreatures.get(4)
        .getName());
    assertEquals(5, convertedCreatures.get(4)
        .getAmount());

    assertEquals("Black Knight", convertedCreatures.get(5)
        .getName());
    assertEquals(6, convertedCreatures.get(5)
        .getAmount());

    assertEquals("Bone Dragon", convertedCreatures.get(6)
        .getName());
    assertEquals(7, convertedCreatures.get(6)
        .getAmount());
  }


  @Test
  void shouldNotApplyAnyEffectsWhenNoEffectsGiven()
  {
    // given
    final Artifact artifact = Artifact.builder()
        .effects( Collections.emptySet() )
        .build();
    final EconomyCreature economyCreature = necropolisFactory.create( false, 2, 2 );

    // when
    artifact.applyTo( economyCreature );

    // then
    final CreatureStatisticIf upgradedStats = economyCreature.getUpgradedStats();
    final CreatureStatistic baseStats = economyCreature.getBaseStats();

    assertEquals( baseStats.getAttack(), upgradedStats.getAttack() );
    assertEquals( baseStats.getMaxHp(), upgradedStats.getMaxHp() );
    assertEquals( baseStats.getArmor(), upgradedStats.getArmor() );

  }

  @Test
  void shouldApplyArtifactWithAddApplyingTypeCorrectly()
  {
    // given
    final ArtifactEffect< ArtifactEffectApplicable > effect1 = ArtifactEffect.builder()
        .effectValue( BigDecimal.valueOf( 2 ) )
        .effectApplyingMode( ArtifactApplyingMode.ADD )
        .applierTarget( CreatureArtifactApplicableProperty.ATTACK )
        .build();

    final ArtifactEffect< ArtifactEffectApplicable > effect2 = ArtifactEffect.builder()
        .effectValue( BigDecimal.valueOf( -2 ) )
        .effectApplyingMode( ArtifactApplyingMode.ADD )
        .applierTarget( CreatureArtifactApplicableProperty.HEALTH )
        .build();

    final ArtifactEffect< ArtifactEffectApplicable > effect3 = ArtifactEffect.builder()
        .effectValue( BigDecimal.valueOf( -3 ) )
        .effectApplyingMode( ArtifactApplyingMode.ADD )
        .applierTarget( CreatureArtifactApplicableProperty.DEFENCE )
        .build();

    final Artifact artifact = Artifact.builder()
        .effects( Set.of( effect1, effect2, effect3 ) )
        .build();

    final EconomyCreature economyCreature = necropolisFactory.create( false, 2, 2 );

    // when
    artifact.applyTo( economyCreature );

    // then
    final CreatureStatisticIf upgradedStats = economyCreature.getUpgradedStats();
    final CreatureStatistic baseStats = economyCreature.getBaseStats();

    assertEquals( baseStats.getAttack() + 2, upgradedStats.getAttack() );
    assertEquals( baseStats.getMaxHp() - 2, upgradedStats.getMaxHp() );
    assertEquals( baseStats.getArmor() - 3, upgradedStats.getArmor() );
  }

  @Test
  void shouldApplyArtifactWithMultiplyApplyingTypeCorrectly()
  {
    // given
    final ArtifactEffect< ArtifactEffectApplicable > effect1 = ArtifactEffect.builder()
        .effectValue( BigDecimal.valueOf( 0.2 ) )
        .effectApplyingMode( ArtifactApplyingMode.MULTIPLY )
        .applierTarget( CreatureArtifactApplicableProperty.ATTACK )
        .build();

    final Artifact artifact = Artifact.builder()
        .effects( Set.of( effect1 ) )
        .build();

    final EconomyCreature economyCreature = necropolisFactory.create( false, 2, 2 );

    // when
    artifact.applyTo( economyCreature );

    // then
    final CreatureStatisticIf upgradedStats = economyCreature.getUpgradedStats();
    final CreatureStatistic baseStats = economyCreature.getBaseStats();

    assertEquals( (int) (baseStats.getAttack() + baseStats.getAttack() * 0.2 ), upgradedStats.getAttack() );
  }

  @Test
  void shouldApplyArtifactWithBothMultiplyAndAddApplyingTypeCorrectly()
  {
    // given
    final ArtifactEffect< ArtifactEffectApplicable > effect1 = ArtifactEffect.builder()
            .effectValue( BigDecimal.valueOf( 0.2 ) )
            .effectApplyingMode( ArtifactApplyingMode.MULTIPLY )
            .applierTarget( CreatureArtifactApplicableProperty.HEALTH )
            .build();

    final ArtifactEffect< ArtifactEffectApplicable > effect2 = ArtifactEffect.builder()
            .effectValue( BigDecimal.valueOf( 3 ) )
            .effectApplyingMode( ArtifactApplyingMode.ADD )
            .applierTarget( CreatureArtifactApplicableProperty.HEALTH )
            .build();

    final Artifact artifact = Artifact.builder()
            .effects( Set.of( effect1, effect2 ) )
            .build();

    final EconomyCreature economyCreature = necropolisFactory.create( false, 2, 2 );

    // when
    artifact.applyTo( economyCreature );

    // then
    final CreatureStatisticIf upgradedStats = economyCreature.getUpgradedStats();
    final CreatureStatistic baseStats = economyCreature.getBaseStats();

    assertEquals( (int) ( baseStats.getMaxHp() + ( baseStats.getMaxHp() * 0.2 ) + 3 ), upgradedStats.getMaxHp() );
  }
}