package pl.psi.skills;

public class EconomySkillFactory {

    public EconomySkill create(final SkillType aSkillType, final SkillLevel aSkillLevel) {
        switch (aSkillLevel) {
            case BASIC:
                switch (aSkillType) {
                    case ARCHERY:
                        return new EconomySkill(aSkillType, 100, 0.1);
                    case OFFENCE:
                        return new EconomySkill(aSkillType, 100, 0.1);
                    case ARMOURER:
                        return new EconomySkill(aSkillType, 100, 0.05);
                    case RESISTANCE:
                        return new EconomySkill(aSkillType, 100, 0.05);
                    case LUCK:
                    case LEADERSHIP:
                        return new EconomySkill(aSkillType, 100, 1);
                    case FIRST_AID:
                        return new EconomySkill(aSkillType, 100, 50);
                    case ARTILLERY:
                        return new EconomySkill(aSkillType, 250, 1);
                    case BALLISTICS:
                        return new EconomySkill(aSkillType, 200, 1);
                    default:
                        throw new IllegalArgumentException("Invalid skill type");
                }
            case ADVANCED:
                switch (aSkillType) {
                    case ARCHERY:
                        return new EconomySkill(aSkillType, 200, 0.25);
                    case OFFENCE:
                        return new EconomySkill(aSkillType, 200, 0.2);
                    case ARMOURER:
                        return new EconomySkill(aSkillType, 200, 0.1);
                    case RESISTANCE:
                        return new EconomySkill(aSkillType, 200, 0.1);
                    case LUCK:
                    case LEADERSHIP:
                        return new EconomySkill(aSkillType, 200, 2);
                    case FIRST_AID:
                        return new EconomySkill(aSkillType, 200, 75);
                    case ARTILLERY:
                        return new EconomySkill(aSkillType, 350, 2);
                    case BALLISTICS:
                        return new EconomySkill(aSkillType, 300, 2);
                    default:
                        throw new IllegalArgumentException("Invalid skill type");
                }
            case EXPERT:
                switch (aSkillType) {
                    case ARCHERY:
                        return new EconomySkill(aSkillType, 300, 0.5);
                    case OFFENCE:
                        return new EconomySkill(aSkillType, 300, 0.3);
                    case ARMOURER:
                        return new EconomySkill(aSkillType, 300, 0.15);
                    case RESISTANCE:
                        return new EconomySkill(aSkillType, 300, 0.2);
                    case LUCK:
                    case LEADERSHIP:
                        return new EconomySkill(aSkillType, 300, 3);
                    case FIRST_AID:
                        return new EconomySkill(aSkillType, 300, 100);
                    case ARTILLERY:
                        return new EconomySkill(aSkillType, 450, 3);
                    case BALLISTICS:
                        return new EconomySkill(aSkillType, 400, 3);
                    default:
                        throw new IllegalArgumentException("Invalid skill type");
                }
            default:
                throw new IllegalArgumentException("Invalid skill level");
        }
    }
}
