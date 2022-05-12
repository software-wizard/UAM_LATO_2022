package pl.psi.spells;

public class SpellFactory {

    private static final String EXCEPTION_MESSAGE = "We support rang from 1 to 7";

    public Spell create(String name, int rang, int manaCost) {

        switch (name) {
            case "LightingBolt":
                switch (rang) {
                    case 1:
                        return new Spell(1, name, rang, manaCost, 10, 25);
                    case 2:
                        return new Spell(1, name, rang, manaCost, 20, 25);
                    case 3:
                        return new Spell(1, name, rang, manaCost, 50, 25);
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
            case "Haste":
                switch (rang) {
                    case 1:
                        return new Spell(2, name, rang, manaCost, "moveRange", 10, 1);
                    case 2:
                        return new Spell(2, name, rang, manaCost, "moveRange", 20, 1);
                    case 3:
                        return new Spell(2, name, rang, manaCost, "moveRange", 30, 1);
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
            case "FireBall":
                switch (rang) {
                    case 1:
                        return new Spell(3, name, rang, manaCost, 10, 1);
                    case 2:
                        return new Spell(3, name, rang, manaCost, 20, 2);
                    case 3:
                        return new Spell(3, name, rang, manaCost, 30, 3);
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
            default:
                throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
    }
}
