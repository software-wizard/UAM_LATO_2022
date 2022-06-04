package pl.psi.gui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.psi.GameEngine;
import pl.psi.Hero;
import pl.psi.Point;

public class MainBattleController {
    private final GameEngine gameEngine;
    @FXML
    private GridPane gridMap;
    @FXML
    private Button waitButton;
    @FXML
    private Button defendButton;
    @FXML
    private Button passButton;
    @FXML
    private Button windowButton;
    @FXML
    private Label console;

    public MainBattleController(final Hero aHero1, final Hero aHero2) {
        gameEngine = new GameEngine(aHero1, aHero2);
    }

    @FXML
    private void initialize() {
        refreshGui();

        console.setText("");
        console.setAlignment(Pos.BOTTOM_LEFT);

        passButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            gameEngine.pass();
            refreshGui();
        });

        windowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            gameEngine.castSpell(new Point(14, 1), gameEngine.getHero1().getSpells().get(0));
            gameEngine.castSpell(new Point(14, 1), gameEngine.getHero1().getSpells().get(1));
            refreshGui();
        });

        waitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            if(gameEngine.allActionLeft()){
                gameEngine.waitAction();
                gameEngine.pass();
            }
            else{
                throw new RuntimeException("Action already performed.");
            }
            refreshGui();
        });

        defendButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            if(gameEngine.allActionLeft()){
                gameEngine.defendAction();
                gameEngine.pass();
            }
            else{
                throw new RuntimeException("Action already performed.");
            }
            refreshGui();
        });

        gameEngine.addObserver(GameEngine.CREATURE_MOVED, (e) -> refreshGui());
    }

    private void renderSpecialFields(MapTile mapTile, int x, int y) {
        if (x == 0 && y == 0) {
            Image img = new Image("/images/cracked_ice.png");
            mapTile.setBackground(img);
        }

        if (x == 4 && y == 8) {
            var img = new Image("/images/evilFog.png");
            mapTile.setBackground(img);
        }

        if (x == 5 && y == 5) {
            var img = new Image("/images/holyGround.png");
            mapTile.setBackground(img);
        }
    }

    public static void showStage(final String information){
        Stage newStage = new Stage();
        VBox comp = new VBox();
        Text text = new Text(information);
        text.setStyle("-fx-font: 24 arial;");
        comp.getChildren().add(text);

        Scene stageScene = new Scene(comp, 300, 195);
        newStage.setScene(stageScene);
        newStage.show();
    }

    private void refreshGui() {
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 10; y++) {
                final int x1 = x;
                final int y1 = y;
                final MapTile mapTile = new MapTile("");

                if (gameEngine.isCurrentCreature(new Point(x,y)) && gameEngine.isCurrentCreatureAlive()) {
                    mapTile.setBackground(Color.GREEN);
                }

                if (gameEngine.canMove(new Point(x, y))) {
                    mapTile.setBackground(Color.GREY);

                    mapTile.setOnMouseClicked(
                            e -> {
                                if(e.getButton() == MouseButton.PRIMARY){
                                    gameEngine.move(new Point(x1, y1));
                                }
                            });
                }

                if (gameEngine.canHeal(new Point(x, y))) {
                    mapTile.setBackground(Color.YELLOW);

                    mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED,
                            e -> gameEngine.heal(new Point(x1, y1)));
                }

                renderSpecialFields(mapTile, x, y);

                if(gameEngine.getCreature(new Point(x, y)).isPresent()){
                    if(gameEngine.getCreature(new Point(x, y)).get().isAlive()){
                        mapTile.setName(gameEngine.getCreature(new Point(x, y)).get().getName() + "\n\n"
                                + gameEngine.getCreature( new Point(x, y) ).get().getAmount());

                    }
                    else{
                        var img = new Image("/images/dead.jpg");
                        mapTile.setBackground(img);
                    }
                    int finalX = x;
                    int finalY = y;
                    mapTile.setOnMouseClicked(e -> {
                        if (e.getButton() == MouseButton.SECONDARY) {
                            showStage(gameEngine.getCreatureInformation(new Point(finalX,finalY)));
                        }
                    });

                    if (gameEngine.canAttack(new Point(x, y))) {
                        mapTile.setBackground(Color.RED);

                        mapTile.setOnMousePressed(
                                e -> {
                                    if(e.getButton() == MouseButton.PRIMARY){
                                        gameEngine.attack(new Point(x1, y1));
                                    }
                                });
                    }
                }

                console.setText(gameEngine.getAttackInformation());

                gridMap.add(mapTile, x, y);
            }
        }
    }
}
