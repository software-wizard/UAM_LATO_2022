package pl.psi.creatures;

import com.google.common.collect.Range;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CreatureStats implements CreatureStatisticIf {

  private final String name;
  private final int attack;
  private final int armor;
  private final int maxHp;
  private int moveRange;
  private final Range<Integer> damage;
  private final int tier;
  private final String description;
  private final boolean isUpgraded;

  public CreatureStats(final CreatureStatisticIf that, final int aAttack, final int aArmor,
      final int aMaxHp) {
    this.maxHp = aMaxHp;
    this.armor = aArmor;
    this.attack = aAttack;
    this.name = that.getName();
    this.moveRange = that.getMoveRange();
    this.damage = that.getDamage();
    this.tier = that.getTier();
    this.description = that.getDescription();
    this.isUpgraded = that.isUpgraded();
  }

  public CreatureStats(final CreatureStatisticIf that) {
    this(that, that.getAttack(), that.getArmor(), that.getMaxHp());
  }
}
