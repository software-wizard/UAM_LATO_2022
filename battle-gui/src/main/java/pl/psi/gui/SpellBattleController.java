package pl.psi.gui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class SpellBattleController {

    @FXML
    private GridPane panel;

    @FXML
    private void initialize() {
        for(int i = 0; i < 3; i++){
            Button button = new Button("Test " + i);
            button.setAlignment(Pos.TOP_CENTER);
            panel.add(button, i,0);
        }
    }
}
