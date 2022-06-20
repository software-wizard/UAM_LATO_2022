package pl.psi.skills;

import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;
import pl.psi.creatures.ShooterCreatureDecorator;
import pl.psi.creatures.ShooterCreature;
import pl.psi.creatures.WarMachinesAbstract;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.HeroStats;
import pl.psi.spells.*;

/**
 * Class that represents changing creature stats based on current skill
 */

public class UpgradeCalculator {

    private final SkillType skillName;
    private final double skillEffect;

    public UpgradeCalculator(SkillType aName, double aEffect) {
        this.skillName = aName;
        this.skillEffect = aEffect;
    }

    public CreatureStats calculate(Creature aCreature) {
        double changedStat;
        CreatureStats statsToApply;
        switch (this.skillName) {
            case ARCHERY:
                changedStat = (1 + this.skillEffect) * aCreature.getStats().getAttack();
                statsToApply = this.getShooterCreaturesStats(aCreature, changedStat);
                break;
            case OFFENCE:
                changedStat = (1 + this.skillEffect) * aCreature.getStats().getAttack();
                statsToApply = this.getHandToHandFighterStats(aCreature, changedStat);
                break;
            case ARTILLERY:
            case BALLISTICS:
            case FIRST_AID:
                this.upgradeWarMachineLevel(aCreature);
            case ARMOURER:
                changedStat = (1 + this.skillEffect) * aCreature.getStats().getArmor();
                statsToApply = CreatureStats.builder()
                                .armor(changedStat)
                                .build();
                break;
            default:
                statsToApply = CreatureStats.builder().build();
                break;
        }
        return statsToApply;
    }

    public HeroStats calculate(EconomyHero aHero) {
        HeroStats changedStats;
        int newEffect;
        switch (this.skillName) {
            case LEADERSHIP:
                newEffect = (int) (Math.round(this.skillEffect) + aHero.getHeroStats().getMorale());
                changedStats = HeroStats.builder().morale(newEffect).build();
                break;
            case LUCK:
                newEffect = (int) (Math.round(this.skillEffect) + aHero.getHeroStats().getLuck());
                changedStats = HeroStats.builder().luck(newEffect).build();
                break;
            default:
                changedStats = (HeroStats) aHero.getHeroStats();
        }
        return changedStats;
    }

    public SpellRang calculate(EconomySpell aSpell) {
        SpellRang convertedSpellRang = convertIntToSpellRang((int) this.skillEffect );
        SpellRang changedSpellRang = SpellRang.BASIC;
        switch (this.skillName) {
            case AIR_MAGIC:
                if (aSpell.getSpellStats().getMagicGuild() == SpellMagicGuild.AIR) {
                    changedSpellRang = convertedSpellRang;
                }
                break;
            case FIRE_MAGIC:
                if (aSpell.getSpellStats().getMagicGuild() == SpellMagicGuild.FIRE) {
                    changedSpellRang = convertedSpellRang;
                }
                break;
            case EARTH_MAGIC:
                if (aSpell.getSpellStats().getMagicGuild() == SpellMagicGuild.EARTH) {
                    changedSpellRang = convertedSpellRang;
                }
                break;
            case WATER_MAGIC:
                if (aSpell.getSpellStats().getMagicGuild() == SpellMagicGuild.WATER) {
                    changedSpellRang = convertedSpellRang;
                }
                break;
            default:
                changedSpellRang = aSpell.getSpellRang();
        }
        return changedSpellRang;
    }

    private void upgradeWarMachineLevel(Creature aCreature ) {
       if (aCreature instanceof WarMachinesAbstract) {
           ((WarMachinesAbstract) aCreature).upgradeSkillLevel(new Double(this.skillEffect).intValue());
       }
    }

    private CreatureStats getHandToHandFighterStats( Creature aCreature, double changedStat ) {
        if ( !(aCreature instanceof WarMachinesAbstract) ) {
            if (aCreature.getBasicStats().isGround()) {
                return  CreatureStats.builder()
                        .attack(changedStat)
                        .build();
            }
        }
        return CreatureStats.builder().build();
    }

    private CreatureStats getShooterCreaturesStats( Creature aCreature, double changedStat ) {
        if (!(aCreature instanceof WarMachinesAbstract)) {
            if (!aCreature.getBasicStats().isGround()) {
                return CreatureStats.builder()
                        .attack(changedStat)
                        .build();
            }
        }
        return CreatureStats.builder().build();
    }

    private SpellRang convertIntToSpellRang(int aIntSpellRang) {
        switch(aIntSpellRang) {
            case 1: return SpellRang.BASIC;
            case 2: return SpellRang.ADVANCED;
            case 3: return SpellRang.EXPERT;
            default: throw new IllegalArgumentException("Invalid spell rang");
        }
    }
}
