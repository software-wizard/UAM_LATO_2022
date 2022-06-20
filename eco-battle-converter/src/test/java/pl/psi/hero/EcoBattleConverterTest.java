package pl.psi.hero;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.psi.artifacts.ArtifactEffectApplicable;
import pl.psi.artifacts.CreatureArtifactApplicableProperty;
import pl.psi.artifacts.model.Artifact;
import pl.psi.artifacts.model.ArtifactApplyingMode;
import pl.psi.artifacts.model.ArtifactEffect;
import pl.psi.converter.EcoBattleConverter;
import pl.psi.creatures.*;
import pl.psi.gui.NecropolisFactory;
import pl.psi.skills.EconomySkill;
import pl.psi.skills.EconomySkillFactory;
import pl.psi.skills.SkillLevel;
import pl.psi.skills.SkillType;
import pl.psi.spells.*;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EcoBattleConverterTest {
    static EconomyNecropolisFactory necropolisFactory;

    @BeforeAll
    static void init() {
        necropolisFactory = new EconomyNecropolisFactory();
    }

    @Test
    void shouldConvertCreaturesCorrectly() {
        final EconomyHero ecoHero = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, HeroStatistics.CLERIC);

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
    void shouldNotApplyAnyEffectsWhenNoEffectsGiven() {
        // given
        final Artifact artifact = Artifact.builder()
                .effects(Collections.emptySet())
                .build();
        final EconomyCreature economyCreature = necropolisFactory.create(false, 2, 2);

        // when
        artifact.applyTo(economyCreature);

        // then
        final CreatureStatisticIf upgradedStats = economyCreature.getUpgradedStats();
        final CreatureStatisticIf baseStats = economyCreature.getBaseStats();

        assertEquals(baseStats.getAttack(), upgradedStats.getAttack());
        assertEquals(baseStats.getMaxHp(), upgradedStats.getMaxHp());
        assertEquals(baseStats.getArmor(), upgradedStats.getArmor());

    }

    @Test
    void shouldApplyArtifactWithAddApplyingTypeCorrectly() {
        // given
        final ArtifactEffect<ArtifactEffectApplicable> effect1 = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(2))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(CreatureArtifactApplicableProperty.ATTACK)
                .build();

		final ArtifactEffect<ArtifactEffectApplicable> effect2 = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(-2))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(CreatureArtifactApplicableProperty.HEALTH)
			.build();

		final ArtifactEffect<ArtifactEffectApplicable> effect3 = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(-3))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(CreatureArtifactApplicableProperty.DEFENCE)
			.build();

		final Artifact artifact = Artifact.builder()
                .effects(Set.of(effect1, effect2, effect3))
			.build();

        final EconomyCreature economyCreature = necropolisFactory.create(false, 2, 2);

		// when
        artifact.applyTo(economyCreature);

		// then
		final CreatureStatisticIf upgradedStats = economyCreature.getUpgradedStats();
		final CreatureStatisticIf baseStats = economyCreature.getBaseStats();

        assertEquals(baseStats.getAttack() + 2, upgradedStats.getAttack());
        assertEquals(baseStats.getMaxHp() - 2, upgradedStats.getMaxHp());
        assertEquals(baseStats.getArmor() - 3, upgradedStats.getArmor());
	}

	@Test
	void shouldApplyArtifactWithMultiplyApplyingTypeCorrectly() {
		// given
		final ArtifactEffect<ArtifactEffectApplicable> effect1 = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(0.2))
                .effectApplyingMode(ArtifactApplyingMode.MULTIPLY)
                .applierTarget(CreatureArtifactApplicableProperty.ATTACK)
			.build();

		final Artifact artifact = Artifact.builder()
                .effects(Set.of(effect1))
			.build();

        final EconomyCreature economyCreature = necropolisFactory.create(false, 2, 2);

		// when
        artifact.applyTo(economyCreature);

		// then
		final CreatureStatisticIf upgradedStats = economyCreature.getUpgradedStats();
		final CreatureStatisticIf baseStats = economyCreature.getBaseStats();

        assertEquals((int) (baseStats.getAttack() + baseStats.getAttack() * 0.2), upgradedStats.getAttack());
	}

	@Test
	void shouldApplyArtifactWithBothMultiplyAndAddApplyingTypeCorrectly() {
		// given
		final ArtifactEffect<ArtifactEffectApplicable> effect1 = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(0.2))
                .effectApplyingMode(ArtifactApplyingMode.MULTIPLY)
                .applierTarget(CreatureArtifactApplicableProperty.HEALTH)
			.build();

		final ArtifactEffect<ArtifactEffectApplicable> effect2 = ArtifactEffect.builder()
                .effectValue(BigDecimal.valueOf(3))
                .effectApplyingMode(ArtifactApplyingMode.ADD)
                .applierTarget(CreatureArtifactApplicableProperty.HEALTH)
			.build();

		final Artifact artifact = Artifact.builder()
                .effects(Set.of(effect1, effect2))
			.build();

        final EconomyCreature economyCreature = necropolisFactory.create(false, 2, 2);

		// when
        artifact.applyTo(economyCreature);

		// then
		final CreatureStatisticIf upgradedStats = economyCreature.getUpgradedStats();
		final CreatureStatisticIf baseStats = economyCreature.getBaseStats();

        assertEquals((int) (baseStats.getMaxHp() + (baseStats.getMaxHp() * 0.2) + 3), upgradedStats.getMaxHp());
	}

	@Test
	void shouldHaveCorrectSkillsApplied() {
		NecropolisFactory factory = new NecropolisFactory();
        WarMachinesAbstract aidTent = new WarMachinesFactory().create(2, 1, null, 0);
        Creature creature1 = factory.create(false, 1, 100);
        Creature creature2 = factory.create(false, 2, 100);
        List<Creature> creatureList = new LinkedList<>(List.of(aidTent, creature1, creature2));
        EconomySkill skill = new EconomySkillFactory().create(SkillType.OFFENCE, SkillLevel.BASIC);
        skill.apply(creatureList);
        assertEquals(0, aidTent.getExternalStats().getAttack());
        assertEquals(5.5, creature1.getExternalStats().getAttack());
	}

	@Test
	void shouldApplySpellSkillCorrectly() {
		EconomySpellFactory economySpellFactory = new EconomySpellFactory();

		EconomySpell airMagicSpell1 = economySpellFactory.create( SpellStats.HASTE, SpellRang.BASIC );
		EconomySpell airMagicSpell2 = economySpellFactory.create( SpellStats.MAGIC_ARROW, SpellRang.BASIC );
		EconomySpell fireMagicSpell1 = economySpellFactory.create( SpellStats.FIRE_BALL, SpellRang.BASIC );
		List<EconomySpell> spellsList = new LinkedList<>(List.of(airMagicSpell1, airMagicSpell2, fireMagicSpell1));

		EconomySkill skill = new EconomySkillFactory().create( SkillType.AIR_MAGIC, SkillLevel.ADVANCED );
		skill.applyForSpells(spellsList);
		assertEquals( SkillLevel.ADVANCED.name(), airMagicSpell1.getSpellRang().name() );
		assertEquals( SkillLevel.ADVANCED.name(), airMagicSpell2.getSpellRang().name() );
		assertEquals( SkillLevel.BASIC.name(), fireMagicSpell1.getSpellRang().name() );
	}

}