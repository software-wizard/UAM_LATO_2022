package pl.psi.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
            Spell<? extends SpellableIf> spell = spells.get(i);

            switch (spells.get(i).getSpellMagicClass()) {
                case AIR:
                    AirGridPane.add(createButton(spell), getColumn(AirGridPane), getRow(AirGridPane));
                    break;
                case FIRE:
                    FireGridPane.add(createButton(spell), getColumn(FireGridPane), getRow(FireGridPane));
                    break;
                case EARTH:
                    EarthGridPane.add(createButton(spell), getColumn(EarthGridPane), getRow(EarthGridPane));
                    break;
                case WATER:
                    WaterGridPane.add(createButton(spell), getColumn(WaterGridPane), getRow(WaterGridPane));
                    break;
                default:
                    AirGridPane.add(createButton(spell), getColumn(AirGridPane), getRow(AirGridPane));
                    FireGridPane.add(createButton(spell), getColumn(FireGridPane), getRow(FireGridPane));
                    EarthGridPane.add(createButton(spell), getColumn(EarthGridPane), getRow(EarthGridPane));
                    WaterGridPane.add(createButton(spell), getColumn(WaterGridPane), getRow(WaterGridPane));
                    break;
            }
        }
    }

    private Button createButton(Spell<? extends SpellableIf> spell) {
        Image image = new Image(spell.getImagePath());
        Button button = new Button(spell.getName().getValue().concat("    " + spell.getManaCost()), new ImageView(image));
        button.setContentDisplay(ContentDisplay.TOP);
        button.setMaxWidth(Double.MAX_VALUE);
        GridPane.setFillWidth(button, true);
        button.setMaxHeight(Double.MAX_VALUE);
        GridPane.setFillHeight(button, true);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Stage stage = (Stage) WaterGridPane.getScene().getWindow();
            gameEngine.getCurrentHero().getSpellBook().setHeroCastingSpell(true);
            observerSupport.firePropertyChange(SPELL_SELECTED, null, spell);
            stage.close();
        });

        return button;
    }

    private int getRow(GridPane gridPane) {
        return gridPane.getChildren().size() / gridPane.getColumnCount();
    }

    private int getColumn(GridPane gridPane) {
        return gridPane.getChildren().size() % gridPane.getColumnCount();
    }
}
