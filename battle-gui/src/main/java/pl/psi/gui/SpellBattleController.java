package pl.psi.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pl.psi.GameEngine;
import pl.psi.spells.Spell;
import pl.psi.spells.SpellableIf;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class SpellBattleController {

    public static final String SPELL_SELECTED = "SPELL_SELECTED";
    private static final int NUMBER_OF_ROWS = 6;
    private static final int NUMBER_OF_COLUMNS = 4;
    private final GameEngine gameEngine;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);

    @FXML
    private GridPane AirGridPane;

    @FXML
    private GridPane FireGridPane;

    @FXML
    private GridPane EarthGridPane;

    @FXML
    private GridPane WaterGridPane;


    public SpellBattleController(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void addObserver(final String aEventType, final PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aEventType, aObserver);
    }

    @FXML
    private void initialize() {
        List<? extends Spell<? extends SpellableIf>> spells = gameEngine.getCurrentHero().getSpellBook().getSpells();
        for (int i = 0; i < spells.size(); i++) {
            String spellName = spells.get(i).getName().getValue();

            switch (spells.get(i).getSpellMagicClass()) {
                case AIR:
                    AirGridPane.add(createButton(spells, i, spellName), getColumn(AirGridPane), getRow(AirGridPane));
                    break;
                case FIRE:
                    FireGridPane.add(createButton(spells, i, spellName), getColumn(FireGridPane), getRow(FireGridPane));
                    break;
                case EARTH:
                    EarthGridPane.add(createButton(spells, i, spellName), getColumn(EarthGridPane), getRow(EarthGridPane));
                    break;
                case WATER:
                    WaterGridPane.add(createButton(spells, i, spellName), getColumn(WaterGridPane), getRow(WaterGridPane));
                    break;
                default:
                    AirGridPane.add(createButton(spells, i, spellName), getColumn(AirGridPane), getRow(AirGridPane));
                    FireGridPane.add(createButton(spells, i, spellName), getColumn(FireGridPane), getRow(FireGridPane));
                    EarthGridPane.add(createButton(spells, i, spellName), getColumn(EarthGridPane), getRow(EarthGridPane));
                    WaterGridPane.add(createButton(spells, i, spellName), getColumn(WaterGridPane), getRow(WaterGridPane));
                    break;
            }
        }
    }

    private Button createButton(List<? extends Spell<? extends SpellableIf>> spells, int i1, String spellName) {
        Button button = new Button(spellName);
        button.setMaxWidth(Double.MAX_VALUE);
        GridPane.setFillWidth(button, true);
        button.setMaxHeight(Double.MAX_VALUE);
        GridPane.setFillHeight(button, true);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Stage stage = (Stage) WaterGridPane.getScene().getWindow();
            gameEngine.getCurrentHero().getSpellBook().setHeroCastingSpell(true);
            observerSupport.firePropertyChange(SPELL_SELECTED, null, spells.get(i1));
            stage.close();
        });
        return button;
    }

    private int getRow(GridPane gridPane) {
        return gridPane.getChildren().size() / NUMBER_OF_ROWS;
    }

    private int getColumn(GridPane gridPane) {
        return gridPane.getChildren().size() % NUMBER_OF_ROWS;
    }
}
