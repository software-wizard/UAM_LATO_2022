package pl.psi.creatures;

import com.google.common.collect.Range;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CreatureStats implements CreatureStatisticIf
{
  private final String name;
  private final int attack;
  private final int armor;
  private final int maxHp;
  private int moveRange;
  private final Range<Integer> damage;
  private final int tier;
  private final String description;
  private final boolean isUpgraded;

  public CreatureStats( final CreatureStatisticIf aCreatureStatistics )
  {
      maxHp = aCreatureStatistics.getMaxHp();
      armor = aCreatureStatistics.getArmor();
      attack = aCreatureStatistics.getAttack();
      name = aCreatureStatistics.getName();
      moveRange = aCreatureStatistics.getMoveRange();
      damage = aCreatureStatistics.getDamage();
      tier = aCreatureStatistics.getTier();
      description = aCreatureStatistics.getDescription();
      isUpgraded = aCreatureStatistics.isUpgraded();
  }
}
