package pl.psi.converter;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.psi.Hero;
import pl.psi.SpellsBook;
import pl.psi.artifacts.ArtifactApplier;
import pl.psi.artifacts.ArtifactEffectApplicable;
import pl.psi.artifacts.ArtifactFactory;
import pl.psi.artifacts.EconomyArtifact;
import pl.psi.artifacts.model.ArtifactEffect;
import pl.psi.artifacts.model.ArtifactIf;
import pl.psi.artifacts.model.ArtifactTarget;
import pl.psi.creatures.*;
import pl.psi.gui.MainBattleController;
import pl.psi.gui.NecropolisFactory;
import pl.psi.gui.StrongholdFactory;
import pl.psi.hero.EconomyHero;
import pl.psi.skills.EconomySkill;
import pl.psi.spells.EconomySpell;
import pl.psi.spells.Spell;
import pl.psi.spells.SpellFactory;
import pl.psi.spells.SpellNames;
import pl.psi.spells.SpellableIf;
import pl.psi.spells.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class EcoBattleConverter {

    public static void startBattle(final EconomyHero aPlayer1, final EconomyHero aPlayer2) {
        Scene scene = null;
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EcoBattleConverter.class.getClassLoader()
                    .getResource("fxml/main-battle.fxml"));
            loader.setController(new MainBattleController(convert(aPlayer1,1), convert(aPlayer2,2)));
            scene = new Scene(loader.load());
            final Stage aStage = new Stage();
            aStage.setScene(scene);
            aStage.setX(5);
            aStage.setY(5);
            aStage.show();
        } catch (final IOException aE) {
            aE.printStackTrace();
        }
    }

    public static Hero convert(final EconomyHero aPlayer, int id) {

        final Multimap<ArtifactTarget, ArtifactIf> artifacts = getConvertedArtifacts(aPlayer);
        applyArtifactsToCreatures(aPlayer, artifacts);
        applyArtifactsToHero(aPlayer, artifacts);
            final List<EconomySkill> playerSkills = aPlayer.getSkillsList();

            final List<Creature> creatures = new ArrayList<>();
            switch (aPlayer.getFraction()) {
                case NECROPOLIS:
                    final NecropolisFactory necroFactory = new NecropolisFactory();

                    aPlayer.getCreatureList()
                            .forEach(ecoCreature -> creatures.add(necroFactory.create(ecoCreature.isUpgraded(),
                                    ecoCreature.getTier(), ecoCreature.getAmount())));
                    break;
                case STRONGHOLD:
                    final StrongholdFactory strongholdFactory = new StrongholdFactory();
                    aPlayer.getCreatureList()
                            .forEach(ecoCreature -> creatures.add(strongholdFactory.create(ecoCreature.isUpgraded(),
                                    ecoCreature.getTier(), ecoCreature.getAmount())));
                    break;

                case CASTLE:
                    final CastleCreatureFactory castleCreatureFactory = new CastleCreatureFactory();
                    aPlayer.getCreatureList()
                            .forEach(ecoCreature -> creatures.add(castleCreatureFactory.create(ecoCreature.getTier(),
                                    ecoCreature.isUpgraded(), ecoCreature.getAmount())));
                    break;
            }

            final List<EconomyCreature> warMachines = aPlayer.getWarMachines();
        WarMachinesFactory warMachinesFactory = new WarMachinesFactory();
        for (EconomyCreature warMachine: warMachines) {
            creatures.add(warMachinesFactory.create(warMachine.getTier(), warMachine.getAmount(), new DefaultDamageCalculator(new Random()), 1));
        }

            convertSkills(playerSkills, creatures, aPlayer, aPlayer.getSpellList());
            final List<Spell<? extends SpellableIf>> spells = new ArrayList<>();

            final SpellFactorsModifiers spellFactorsModifiers = applyArtifactsToSpells(artifacts);

            final SpellFactory spellFactory = new SpellFactory(spellFactorsModifiers);

            aPlayer.getSpellList()
                .forEach(economySpell -> spells.add(spellFactory.create(economySpell.getSpellStats().getName(), economySpell.getSpellRang(), aPlayer.getSpellPower())));

            SpellsBook spellsBook = SpellsBook.builder().spells(spells).mana(aPlayer.getHeroStats().getSpellPoints()).build();

            Hero aHero = new Hero(creatures, spellsBook);

            aHero.getCreatures().forEach(creature -> creature.setHeroNumber(id));

            return aHero;
        }

        private static Multimap<ArtifactTarget, ArtifactIf> getConvertedArtifacts ( final EconomyHero aPlayer )
        {
            final Multimap<ArtifactTarget, ArtifactIf> artifacts = ArrayListMultimap.create();
            final ArtifactFactory factory = new ArtifactFactory();
            final List<EconomyArtifact> economyArtifacts = aPlayer.getArtifacts();

            for (final EconomyArtifact economyArtifact : economyArtifacts) {
                final ArtifactTarget target = economyArtifact.getNameHolder().getHolderTarget();
                artifacts.put(target, factory.createArtifact(economyArtifact.getNameHolder()));
            }
            return artifacts;
        }

        private static void applyArtifactsToCreatures ( final EconomyHero aPlayer,
        final Multimap<ArtifactTarget, ArtifactIf> aArtifacts )
        {
            List<ArtifactEffect<ArtifactEffectApplicable>> artifactEffects = aArtifacts.get(ArtifactTarget.CREATURES).stream()
                    .flatMap(art -> art.getEffects().stream())
                    .collect(Collectors.toList());

            for (final EconomyCreature economyCreature : aPlayer.getCreatureList()) {
                artifactEffects.forEach(economyCreature::applyArtifactEffect);
            }
        }

        private static void applyArtifactsToHero (EconomyHero aPlayer,
        final Multimap<ArtifactTarget, ArtifactIf> aArtifacts){
            List<ArtifactEffect<ArtifactEffectApplicable>> artifactEffects = aArtifacts.get(ArtifactTarget.SKILL).stream()
                    .flatMap(art -> art.getEffects().stream())
                    .collect(Collectors.toList());

            artifactEffects.forEach(aPlayer::applyArtifactEffect);

        }

        private static SpellFactorsModifiers applyArtifactsToSpells (
        final Multimap<ArtifactTarget, ArtifactIf> aArtifacts){
            List<ArtifactEffect<ArtifactEffectApplicable>> artifactEffects = aArtifacts.get(ArtifactTarget.SPELLS).stream()
                    .flatMap(art -> art.getEffects().stream())
                    .collect(Collectors.toList());
            ArtifactApplier artifactApplier = new ArtifactApplier();

            SpellFactorsModifiers spellFactorsModifiers = SpellFactorsModifiers.builder()
                    .airDamageModifier(1)
                    .earthDamageModifier(1)
                    .fireDamageModifier(1)
                    .waterDamageModifier(1)
                    .addedDuration(0).build();

            for (final ArtifactEffect<ArtifactEffectApplicable> artifactEffect : artifactEffects) {
                spellFactorsModifiers = artifactApplier.calculateSpellFactorsModifiers(artifactEffect, spellFactorsModifiers);
            }

            return spellFactorsModifiers;
        }

        private static void convertSkills (List < EconomySkill > aSkills, List < Creature > aCreatures,
                EconomyHero aHero, List < EconomySpell > aSpells ){
            aSkills.forEach(aSkill -> {
                aSkill.apply(aCreatures);
                aSkill.apply(aHero);
                aSkill.applyForSpells(aSpells);
            });
        }

    }
