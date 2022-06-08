package pl.psi.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import pl.psi.GameEngine;
import pl.psi.spells.Spell;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class SpellBattleController {

    public static final String SPELL_SELECTED = "SPELL_SELECTED";
    private final GameEngine gameEngine;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);

    @FXML
    private GridPane panel;


    public SpellBattleController(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void addObserver(final String aEventType, final PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aEventType, aObserver);
    }

    @FXML
    private void initialize() {
        List<? extends Spell> spells = gameEngine.getCurrentHero().getSpells();
        for(int i = 0; i < spells.size(); i++){
            final int i1 = i;
            String spellName = spells.get(i).getName().getValue();
            Button button = new Button(spellName);
            button.setMaxWidth(Double.MAX_VALUE);
            GridPane.setFillWidth(button, true);
            button.setMaxHeight(Double.MAX_VALUE);
            GridPane.setFillHeight(button, true);
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                gameEngine.getCurrentHero().setHeroCastingSpell(true);
                observerSupport.firePropertyChange(SPELL_SELECTED, null, spells.get(i1));
            });
            panel.add(button, i,0);
        }
    }
}
