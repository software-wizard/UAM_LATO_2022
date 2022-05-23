package pl.psi;

import lombok.Getter;
import pl.psi.creatures.Creature;
import pl.psi.spells.Spell;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static pl.psi.TurnQueue.END_OF_TURN;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class GameEngine {

    public static final String CREATURE_MOVED = "CREATURE_MOVED";
    private final TurnQueue turnQueue;
    private final Board board;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
    @Getter
    private final Hero hero1;
    @Getter
    private final Hero hero2;

    public GameEngine(final Hero aHero1, final Hero aHero2) {
        this.hero1 = aHero1;
        this.hero2 = aHero2;
        turnQueue = new TurnQueue(aHero1.getCreatures(), aHero2.getCreatures());
        board = new Board(aHero1.getCreatures(), aHero2.getCreatures());
        hero1.getSpells().forEach(spell -> turnQueue.addObserver(END_OF_TURN, spell));
    }

    public void attack(final Point point) {
        board.getCreature(point)
                .ifPresent(defender -> turnQueue.getCurrentCreature()
                        .attack(defender));
    }

    public boolean canMove(final Point aPoint) {
        return board.canMove(turnQueue.getCurrentCreature(), aPoint);
    }

    public void move(final Point aPoint) {
        board.move(turnQueue.getCurrentCreature(), aPoint);
        observerSupport.firePropertyChange(CREATURE_MOVED, null, aPoint);
    }

    public Optional<Creature> getCreature(final Point aPoint) {
        return board.getCreature(aPoint);
    }

    public void pass() {
        turnQueue.next();
    }

    public void addObserver(final String aEventType, final PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aEventType, aObserver);
    }

    public boolean canAttack(final Point point) {
        return false;
    }

    public void castSpell(final Point point, Spell spell) {
        switch (spell.getCategory()) {
            case FIELD:
                board.getCreature(point)
                        .ifPresent(defender -> {
                            turnQueue.getCurrentCreature()
                                    .castSpell(defender, spell);
                        });
                break;
            case AREA:
                List<Creature> creatureList = new ArrayList<>();
                if (board.getCreature(new Point(point.getX(), point.getY() + 1)).isPresent())
                    board.getCreature(new Point(point.getX(), point.getY() + 1)).ifPresent(creatureList::add); // ToDo: CHANGE IT!!!!!!!!!!!!!!!
                board.getCreature(point)
                        .ifPresent(defender -> {
                                    creatureList.add(defender);
                                    turnQueue.getCurrentCreature().castSpell(creatureList, spell);
                                }
                        );
                break;
            default:
                throw new IllegalArgumentException("Not supported category.");
        }

    }
}
