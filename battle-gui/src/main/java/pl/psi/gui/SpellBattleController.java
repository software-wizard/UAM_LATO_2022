package pl.psi.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
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

        List<? extends Spell> spells = gameEngine.getCurrentHero().getSpellBook().getSpells();
        for(int i = 0; i < spells.size(); i++){
            final int i1 = i;
            String spellName = spells.get(i).getName().getValue();
            Button button = new Button(spellName);
            button.setMaxWidth(Double.MAX_VALUE);
            GridPane.setFillWidth(button, true);
            button.setMaxHeight(Double.MAX_VALUE);
            GridPane.setFillHeight(button, true);
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                Stage stage = (Stage) panel.getScene().getWindow();
                gameEngine.getCurrentHero().getSpellBook().setHeroCastingSpell(true);
                observerSupport.firePropertyChange(SPELL_SELECTED, null, spells.get(i1));
                stage.close();
            });

            panel.add(button, i,0);
        }
    }
}
