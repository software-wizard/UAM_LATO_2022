package pl.psi.hero;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import pl.psi.artifacts.Artifact;
import pl.psi.artifacts.ArtifactApplierTarget;
import pl.psi.artifacts.ArtifactApplyingMode;
import pl.psi.artifacts.ArtifactEffect;
import pl.psi.artifacts.ArtifactEffectApplicable;
import pl.psi.converter.EcoBattleConverter;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatistic;
import pl.psi.creatures.CreatureStatisticIf;
import pl.psi.creatures.EconomyCreature;
import pl.psi.creatures.EconomyNecropolisFactory;

class EcoBattleConverterTest {

  @Test
  void shouldConvertCreaturesCorrectly() {
    final EconomyHero ecoHero = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, 1000);
    final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
    ecoHero.addCreature(factory.create(false, 1, 1));
    ecoHero.addCreature(factory.create(false, 2, 2));
    ecoHero.addCreature(factory.create(false, 3, 3));
    ecoHero.addCreature(factory.create(false, 4, 4));
    ecoHero.addCreature(factory.create(false, 5, 5));
    ecoHero.addCreature(factory.create(false, 6, 6));
    ecoHero.addCreature(factory.create(false, 7, 7));

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
  void shouldNotApplyAnyEffectsWhenNoEffectsGiven() {
    // given
    final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
    final Artifact artifact = Artifact.builder()
        .effects(Collections.emptySet())
        .build();
    final EconomyCreature economyCreature = factory.create(false, 2, 2);

    // when
    artifact.applyTo(economyCreature);

    // then
    final CreatureStatisticIf upgradedStats = economyCreature.getUpgradedStats();
    final CreatureStatistic baseStats = economyCreature.getBaseStats();

    assertEquals(baseStats.getAttack(), upgradedStats.getAttack());
    assertEquals(baseStats.getMaxHp(), upgradedStats.getMaxHp());
    assertEquals(baseStats.getArmor(), upgradedStats.getArmor());

  }

  @Test
  void shouldApplyArtifactCorrectly() {
    // given
    final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
    final ArtifactEffect<ArtifactEffectApplicable> effect1 = ArtifactEffect.builder()
        .effectValue(BigDecimal.valueOf(2))
        .effectApplyingMode(ArtifactApplyingMode.ADD)
        .applierTarget(ArtifactApplierTarget.ATTACK)
        .build();

    final ArtifactEffect<ArtifactEffectApplicable> effect2 = ArtifactEffect.builder()
        .effectValue(BigDecimal.valueOf(-2))
        .effectApplyingMode(ArtifactApplyingMode.ADD)
        .applierTarget(ArtifactApplierTarget.HEALTH)
        .build();

    final ArtifactEffect<ArtifactEffectApplicable> effect3 = ArtifactEffect.builder()
        .effectValue(BigDecimal.valueOf(-3))
        .effectApplyingMode(ArtifactApplyingMode.ADD)
        .applierTarget(ArtifactApplierTarget.DEFENCE)
        .build();

    final Artifact artifact = Artifact.builder()
        .effects(Set.of(effect1, effect2, effect3))
        .build();

    final EconomyCreature economyCreature = factory.create(false, 2, 2);

    // when
    artifact.applyTo(economyCreature);

    // then
    final CreatureStatisticIf upgradedStats = economyCreature.getUpgradedStats();
    final CreatureStatistic baseStats = economyCreature.getBaseStats();

    assertEquals(baseStats.getAttack() + 2, upgradedStats.getAttack());
    assertEquals(baseStats.getMaxHp() - 2, upgradedStats.getMaxHp());
    assertEquals(baseStats.getArmor() - 3, upgradedStats.getArmor());
  }

  @Test
  void shouldApplyArtifactWithMultiplyEffectCorrectly() {
    // given
    final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
    final ArtifactEffect<ArtifactEffectApplicable> effect1 = ArtifactEffect.builder()
        .effectValue(BigDecimal.valueOf(0.2))
        .effectApplyingMode(ArtifactApplyingMode.MULTIPLY)
        .applierTarget(ArtifactApplierTarget.ATTACK)
        .build();

    final Artifact artifact = Artifact.builder()
        .effects(Set.of(effect1))
        .build();

    final EconomyCreature economyCreature = factory.create(false, 2, 2);

    // when
    artifact.applyTo(economyCreature);

    // then
    final CreatureStatisticIf upgradedStats = economyCreature.getUpgradedStats();
    final CreatureStatistic baseStats = economyCreature.getBaseStats();

    assertEquals( (int) (baseStats.getAttack() + baseStats.getAttack() * 0.2),
        upgradedStats.getAttack());
  }
}