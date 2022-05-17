package pl.psi.skills;

public class EconomySkillFactory {

    public EconomySkill create( final SkillName aSkillName, final SkillLevel aSkillLevel)
    {
        switch( aSkillLevel )
        {
            case BASIC:
                switch ( aSkillName )
                {
                    case ARCHERY:
                        return new EconomySkill( aSkillName, 100, 0.1 );
                    case OFFENCE:
                        return new EconomySkill( aSkillName, 100, 0.1 );
                    case ARMOURER:
                        return new EconomySkill( aSkillName, 100, 0.05 );
                    case RESISTANCE:
                        return new EconomySkill( aSkillName, 100, 0.05 );
                    case LUCK:
                        return new EconomySkill( aSkillName, 100, 1 );
                    case FIRST_AID:
                        return new EconomySkill( aSkillName, 100, 50 );
                    default:
                        throw new IllegalArgumentException( "Invalid skill name" );
                }
            case ADVANCED:
                switch ( aSkillName )
                {
                    case ARCHERY:
                        return new EconomySkill( aSkillName, 200, 0.25);
                    case OFFENCE:
                        return new EconomySkill( aSkillName, 200, 0.2);
                    case ARMOURER:
                        return new EconomySkill( aSkillName, 200, 0.1 );
                    case RESISTANCE:
                        return new EconomySkill( aSkillName, 200, 0.1 );
                    case LUCK:
                        return new EconomySkill( aSkillName, 200, 2 );
                    case FIRST_AID:
                        return new EconomySkill( aSkillName, 200, 75 );
                    default:
                        throw new IllegalArgumentException( "Invalid skill name" );
                }
            case EXPERT:
                switch ( aSkillName )
                {
                    case ARCHERY:
                        return new EconomySkill( aSkillName, 300, 0.5);
                    case OFFENCE:
                        return new EconomySkill( aSkillName, 300, 0.3);
                    case ARMOURER:
                        return new EconomySkill( aSkillName, 300, 0.15 );
                    case RESISTANCE:
                        return new EconomySkill( aSkillName, 300, 0.2 );
                    case LUCK:
                        return new EconomySkill( aSkillName, 300, 3 );
                    case FIRST_AID:
                        return new EconomySkill( aSkillName, 300, 100 );
                    default:
                        throw new IllegalArgumentException( "Invalid skill name" );
                }
            default:
                throw new IllegalArgumentException( "Invalid skill level" );
        }
    }
}
