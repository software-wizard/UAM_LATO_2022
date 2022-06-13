package pl.psi.gui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import pl.psi.EconomyEngine;
import pl.psi.ProductType;
import pl.psi.artifacts.*;
import pl.psi.creatures.*;
import pl.psi.skills.*;
import pl.psi.spells.*;
import pl.psi.converter.EcoBattleConverter;
import pl.psi.hero.EconomyHero;
import pl.psi.shop.BuyProductInterface;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class EcoController implements PropertyChangeListener {

    private final EconomyEngine economyEngine;
    @FXML
    private ScrollPane heroBoughtScrollPane;
    @FXML
    private ScrollPane skillsScrollPane;
    @FXML
    private HBox spellsScrollPaneHBox;
    @FXML
    private HBox skillsScrollPaneHBox;
    @FXML
    private ScrollPane spellsScrollPane;
    @FXML
    private StackPane heroBoughtPane;
    @FXML
    private HBox shopsBox;
    @FXML
    private Button readyButton;
    @FXML
    private Label playerLabel;
    @FXML
    private Label fraction;
    @FXML
    private Label currentGoldLabel;
    @FXML
    private Label GoldImage;
    @FXML
    private Label roundNumberLabel;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox VBoxHero;
    @FXML
    private Button creaturete1;
    @FXML
    private Button creaturete2;
    @FXML
    private Button creaturete3;
    @FXML
    private Button creaturete4;
    @FXML
    private Button creaturete5;
    @FXML
    private Button creaturete6;
    @FXML
    private Button creaturete7;
    @FXML
    private Button HEAD;
    @FXML
    private Button TORSO;
    @FXML
    private Button NECK;
    @FXML
    private Button FEET;
    @FXML
    private Button RIGHT_HAND;
    @FXML
    private Button LEFT_HAND;
    @FXML
    private Button SHOULDERS;

    private ProductType shopChoose;
    private HashMap<ArtifactPlacement, Button> artifactPlacementButtonHashMap;
    private List<Button> creatureButtons;
    private List<Button> artifactButtons;


    public EcoController(final EconomyHero aHero1, final EconomyHero aHero2) {
        economyEngine = new EconomyEngine(aHero1, aHero2);
        shopChoose = ProductType.CREATURE;

    }

    @FXML
    void initialize(){

        artifactPlacementButtonHashMap = new HashMap<>();
        artifactPlacementButtonHashMap.put(ArtifactPlacement.HEAD, HEAD);
        artifactPlacementButtonHashMap.put(ArtifactPlacement.RIGHT_HAND, RIGHT_HAND);
        artifactPlacementButtonHashMap.put(ArtifactPlacement.LEFT_HAND, LEFT_HAND);
        artifactPlacementButtonHashMap.put(ArtifactPlacement.TORSO, TORSO);
        artifactPlacementButtonHashMap.put(ArtifactPlacement.NECK, NECK);
        artifactPlacementButtonHashMap.put(ArtifactPlacement.SHOULDERS, SHOULDERS);
        artifactPlacementButtonHashMap.put(ArtifactPlacement.FEET, FEET);

        creatureButtons = List.of(creaturete1, creaturete2, creaturete3, creaturete4, creaturete5, creaturete6, creaturete7);
        artifactButtons = List.of(HEAD, RIGHT_HAND, LEFT_HAND, FEET, NECK, TORSO, SHOULDERS);

        VBoxHero.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY)));

        Image imageGold = new Image("GOLD.png");
        ImageView imageViewGold = new ImageView(imageGold);
        imageViewGold.setFitWidth(40);
        imageViewGold.setFitHeight(30);
        GoldImage.setGraphic(imageViewGold);

        // enable scroll Width/Height
        scrollPane.setFitToWidth(true);
        heroBoughtScrollPane.setFitToWidth(true);
        spellsScrollPane.setFitToHeight(true);
        skillsScrollPane.setFitToHeight(true);
        scrollPane.setHmax(300);
        scrollPane.setHmin(300);

        Image imageHero = new Image("HERO.png");
        heroBoughtPane.setBackground(new Background(new BackgroundImage(imageHero, NO_REPEAT, NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        refreshStartGui();
        economyEngine.addObserver(EconomyEngine.ACTIVE_HERO_CHANGED, this);
        economyEngine.addObserver(EconomyEngine.HERO_BOUGHT_CREATURE, this);
        economyEngine.addObserver(EconomyEngine.HERO_BOUGHT_ARTIFACT, this);
        economyEngine.addObserver(EconomyEngine.HERO_BOUGHT_SKILL,this);
        economyEngine.addObserver(EconomyEngine.HERO_BOUGHT_SPELL,this);
        economyEngine.addObserver(EconomyEngine.NEXT_ROUND, this);
        economyEngine.addObserver(EconomyEngine.END_SHOPPING, this);

        playerLabel.setText(economyEngine.getActiveHero().toString());
        currentGoldLabel.setText(String.valueOf(economyEngine.getActiveHero().getGold().getPrice()));
        roundNumberLabel.setText(String.valueOf(economyEngine.getRoundNumber()));
        fraction.setText("Fraction : " + economyEngine.getActiveHero().getFraction().name());

        readyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            if (economyEngine.getRoundNumber() == 1) {
                economyEngine.pass();
            } else if (economyEngine.getRoundNumber() == 2) {
                economyEngine.pass();
                goToBattle();
            }
        });
    }

    private void goToBattle() {
        Stage stage = (Stage) heroBoughtPane.getScene().getWindow();
        stage.close();
        EcoBattleConverter.startBattle(economyEngine.getPlayer1(), economyEngine.getPlayer2());
    }


    void buy(ProductType productType, final BuyProductInterface product) {
        economyEngine.buy(productType, product);
    }

    @SneakyThrows
    @Override
    public void propertyChange(final PropertyChangeEvent aPropertyChangeEvent) {
        if (aPropertyChangeEvent.getPropertyName().equals("HERO_BOUGHT_CREATURE")) {
            refreshGuiHeroBoughtCreature();
        } else if (aPropertyChangeEvent.getPropertyName().equals("HERO_BOUGHT_ARTIFACT")) {
            refreshGuiHeroBoughtArtefact();
        }else if(aPropertyChangeEvent.getPropertyName().equals("HERO_BOUGHT_SKILL")) {
            refreshGuiHeroBoughtSkill();
        }
        else if(aPropertyChangeEvent.getPropertyName().equals("HERO_BOUGHT_SPELL")) {
            refreshGuiHeroBoughtSpell();
        }
        else if (aPropertyChangeEvent.getPropertyName().equals("NEXT_ROUND")) {
            refreshGuiHeroChanged();
        }
    }

    void refreshShopDependsOnWhatHasChose(){
        if (shopChoose.equals(ProductType.ARTIFACT)) {
            fillShopWithArtifacts();
        } else if (shopChoose.equals(ProductType.CREATURE)) {
            fillShopWithCreatures();
        }
        else if (shopChoose.equals(ProductType.SKILL)){
            fillShopWithSkills();
        }
        else if(shopChoose.equals(ProductType.SPELL)){
            fillShopWithSpells();
        }
    }


    void refreshStartGui() {
        playerLabel.setText(economyEngine.getActiveHero().toString());
        currentGoldLabel.setText(String.valueOf(economyEngine.getActiveHero().getGold().getPrice()));
        roundNumberLabel.setText(String.valueOf(economyEngine.getRoundNumber()));
        fraction.setText("Fraction : " + economyEngine.getActiveHero().getFraction().name());
        fillShopWithCreatures();

    }

    void refreshGuiHeroBoughtCreature() {
        currentGoldLabel.setText(String.valueOf(economyEngine.getActiveHero().getGold().getPrice()));
        shopsBox.getChildren().clear();
        refreshShopDependsOnWhatHasChose();

        List<EconomyCreature> creatureList = economyEngine.getActiveHero().getCreatures();
        int numberOfBoughtCreatures = creatureList.size();
        for (int i = 0; i < numberOfBoughtCreatures; i++) {

            EconomyCreature creature = creatureList.get(i);
            Button button = creatureButtons.get(i);
            int amount = creature.getAmount();
            int tier = creature.getTier();
            String upgrated = creature.isUpgraded() ? "1" : "0";
            String picture = tier + upgrated;

            button.setText(amount + "");
            Image image = new Image("/creatures/" + economyEngine.getActiveHero().getFraction().name() + "/" + picture + ".png");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(30);
            imageView.setFitWidth(24);
            button.setGraphic(imageView);
            button.setContentDisplay(ContentDisplay.LEFT);
        }
    }


    void refreshGuiHeroBoughtSkill(){
        skillsScrollPaneHBox.getChildren().clear();
        currentGoldLabel.setText(String.valueOf(economyEngine.getActiveHero().getGold().getPrice()));
        shopsBox.getChildren().clear();
        refreshShopDependsOnWhatHasChose();

        List<EconomySkill> economySkills = economyEngine.getActiveHero().getSkills();

        final HBox skills = new HBox();
        for (int i=0;i<economySkills.size();i++) {
            Button button = new Button();
            Image image = new Image("/skills/"+economySkills.get(i).getSkillType().name()+".png");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(60);
            imageView.setFitWidth(50);
            button.setGraphic(imageView);
            button.setText(economySkills.get(i).getSkillType().name() + "\nFACTOR : \n" + economySkills.get(i).getFactor());
            button.setContentDisplay(ContentDisplay.LEFT);
            button.setDisable(false);
            button.getStyleClass().add("BoxSkills");
            skills.getChildren().add(button);
        }
        skillsScrollPaneHBox.getChildren().add(skills);
    }

    void refreshGuiHeroBoughtSpell(){
        spellsScrollPaneHBox.getChildren().clear();
        currentGoldLabel.setText(String.valueOf(economyEngine.getActiveHero().getGold().getPrice()));
        shopsBox.getChildren().clear();
        refreshShopDependsOnWhatHasChose();

        List<EconomySpell> economySpells = economyEngine.getActiveHero().getSpells();
        final HBox spells = new HBox();
        for (int i=0;i<economySpells.size();i++) {
            Button button = new Button();
            Image image = new Image("/spells/"+economySpells.get(i).getSpellStats().name()+".png");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(45);
            imageView.setFitWidth(45);
            button.setGraphic(imageView);
            button.setText(economySpells.get(i).getSpellRang().name()+ "\n" + economySpells.get(i).getSpellStats());
            button.setContentDisplay(ContentDisplay.LEFT);
            button.setDisable(false);
            button.getStyleClass().add("BoxSpells");
            spells.getChildren().add(button);
        }
        spellsScrollPaneHBox.getChildren().add(spells);
    }


    void refreshGuiHeroBoughtArtefact() {
        currentGoldLabel.setText(String.valueOf(economyEngine.getActiveHero().getGold().getPrice()));
        shopsBox.getChildren().clear();
        refreshShopDependsOnWhatHasChose();
        //refresh boughtArtifacts
        List<EconomyArtifact> artifacts = economyEngine.getActiveHero().getArtifacts();
        for (Map.Entry<ArtifactPlacement, Button> b : artifactPlacementButtonHashMap.entrySet()) {
            ArtifactPlacement placement = b.getKey();
            for (EconomyArtifact a : artifacts) {
                if (a.getPlacement().equals(placement)) {
                    Button button = b.getValue();
                    Image image = new Image("/artifacts/" + a.getDisplayName() + ".png");
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(39);
                    imageView.setFitWidth(39);
                    button.setGraphic(imageView);
                    button.setContentDisplay(ContentDisplay.CENTER);
                }
            }
        }
    }


    void refreshGuiHeroChanged() {

        playerLabel.setText(economyEngine.getActiveHero().toString());
        currentGoldLabel.setText(String.valueOf(economyEngine.getActiveHero().getGold().getPrice()));
        roundNumberLabel.setText(String.valueOf(economyEngine.getRoundNumber()));
        fraction.setText("Fraction : " + economyEngine.getActiveHero().getFraction().name());

        // refresh buttons of creatures for the hero2 - without it  hero2 can see what hero1 bought
        for (int i = 0; i < 7; i++) {
            Image image = new Image("CLEAR.png");
            ImageView imageView = new ImageView(image);

            Button button = creatureButtons.get(i);
            imageView.setFitWidth(30);
            imageView.setFitHeight(30);
            button.setGraphic(imageView);
            button.setText("");

            Button button2 = artifactButtons.get(i);
            ImageView imageView2 = new ImageView(image);
            imageView2.setFitWidth(40);
            imageView2.setFitHeight(40);
            button2.setGraphic(imageView2);
        }

        shopsBox.getChildren().clear();
        skillsScrollPaneHBox.getChildren().clear();
        spellsScrollPaneHBox.getChildren().clear();
        refreshShopDependsOnWhatHasChose();
    }

    @FXML
    private void CreatureShopClicked(MouseEvent event) {
        shopsBox.getChildren().clear();
        fillShopWithCreatures();
        shopChoose = ProductType.CREATURE;
    }

    @FXML
    private void ArtifactShopClicked(MouseEvent event) {
        shopsBox.getChildren().clear();
        fillShopWithArtifacts();
        shopChoose = ProductType.ARTIFACT;

    }

    @FXML
    private void SkillsShopClicked(MouseEvent event) {
        shopsBox.getChildren().clear();
        fillShopWithSkills();
        shopChoose = ProductType.SKILL;
    }

    @FXML
    private void SpellsShopClicked(MouseEvent event) {
        shopsBox.getChildren().clear();
        fillShopWithSpells();
        shopChoose = ProductType.SPELL;
    }


    private void fillShopWithCreatures() {
        if (economyEngine.getActiveHero().getFraction().equals(EconomyHero.Fraction.NECROPOLIS))
            fillShopWithCreaturesOfFraction(EconomyHero.Fraction.NECROPOLIS,new EconomyNecropolisFactory());
        else if(economyEngine.getActiveHero().getFraction().equals(EconomyHero.Fraction.CASTLE))
            fillShopWithCreaturesOfFraction(EconomyHero.Fraction.CASTLE,new EconomyCastleFactory());
        else if(economyEngine.getActiveHero().getFraction().equals(EconomyHero.Fraction.STRONGHOLD))
            fillShopWithCreaturesOfFraction(EconomyHero.Fraction.STRONGHOLD,new EconomyStrongholdFactory());

    }

    private void fillShopWithCreaturesOfFraction(EconomyHero.Fraction fraction, FactoryInterface factory){
        final VBox creatureShop = new VBox();
        Text label = new Text("Here you can buy creatures for your Hero.There are\ndifferent types of creatures,these types depend on \nchosen fraction.You can buy only 7 types of creatures.\nCreature of one type you can buy as many as gold \nyou have.");
        label.getStyleClass().add("labelShop");
        creatureShop.getChildren().add(label);
        fillShopWithCreaturesDependsOnUpgrated(fraction,factory,false,creatureShop);
        fillShopWithCreaturesDependsOnUpgrated(fraction,factory,true,creatureShop);
        shopsBox.getChildren().add(creatureShop);
    }

    private void fillShopWithCreaturesDependsOnUpgrated(EconomyHero.Fraction fraction, FactoryInterface factory, boolean upgrated, VBox creatureShop){
        for (int i = 1; i < 8; i++) {
            EconomyCreature creature = (EconomyCreature) factory.create(upgrated, i, 1);
            int costOfCreature = creature.getGoldCost().getPrice();
            int maxCreaturesHeroCanBuy = economyEngine.getActiveHero().getGold().getPrice() / costOfCreature;
            boolean canBuyMore = economyEngine.getActiveHero().canAddCreature(creature);
            boolean canBuy = economyEngine.getActiveHero().getGold().haveEnoghMoney(creature.getGoldCost());
            CreatureButton button = new CreatureButton(this, creature, maxCreaturesHeroCanBuy, canBuy, canBuyMore);
            String up = upgrated ? "1" : "0";
            String name = i + "" + up ;
            Image image = new Image("/creatures/" + fraction.name() + "/" + name + ".png");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(40);
            imageView.setFitWidth(40);
            button.setGraphic(imageView);
            button.setText(creature.getName() + " | " + "Price : " + creature.getGoldCost().getPrice());
            if (canBuy && canBuyMore)
                button.getStyleClass().add("centerHBoxRight");
            else
                button.getStyleClass().add("centerHBoxGrey");
            creatureShop.getChildren().add(button);
        }
    }
    // TODO - one method , Factory - interface and factory has String with the path of images i getter na to  !!!!
    // TODO CreateCreaterPreFactory
    // TODO interface dla fabryki EcoCreaterIf ...



    // TODO w otdzielną metodę Images
    private void fillShopWithArtifacts() {
        final VBox artifactShop = new VBox();
        Text label = new Text("Here you can buy artifacts for your Hero. There are \ndifferent types of artifacts, these types don't depend\non chosen fraction. Each artifact has placement. One part\nof body can have only one artifact, that's why\nyou can buy all types of artifacts, but artifact \nof one type you can buy only once.");
        label.getStyleClass().add("labelShop");
        artifactShop.getChildren().add(label);
        final EconomyArtifactFactory economyArtifactFactory = new EconomyArtifactFactory();
        List<String> namesOfArtifacts = List.of("Cape of Conjuring", "Crown of Dragontooth", "Blackshard of the Dead Knight");
        int size = namesOfArtifacts.size();
        for (int i = 0; i < size; i++) {
            String name = namesOfArtifacts.get(i);
            EconomyArtifact artifact = economyArtifactFactory.create(name);
            if(economyEngine.getActiveHero().canAddArtifact(artifact.getPlacement())) {
                boolean canBuy = economyEngine.getActiveHero().getGold().haveEnoghMoney(artifact.getGoldCost());
                ArtifactButton button = new ArtifactButton(this::buy, artifact,canBuy);
                setImageToProducts(button,artifactShop,canBuy);
            }
        }
        shopsBox.getChildren().add(artifactShop);
    }

    private void fillShopWithSkills(){
        shopsBox.getChildren().clear();
        final VBox skillShop  = new VBox();
        Text label = new Text("Here you can buy skills for your Hero. There are \ndifferent types of skills, these types don't depend on \nchosen fraction.You can buy all types of skills, \nbut skill of one type you can buy only once.");
        label.getStyleClass().add("labelShop");
        skillShop.getChildren().add(label);
        List<SkillLevel> skillLevels = List.of(SkillLevel.BASIC,SkillLevel.ADVANCED,SkillLevel.EXPERT);
        List<SkillType> skillTypes = List.of(SkillType.ARCHERY,SkillType.OFFENCE,SkillType.ARMOURER,SkillType.RESISTANCE,SkillType.LUCK,SkillType.FIRST_AID);
        final EconomySkillFactory factory = new EconomySkillFactory();
        for(int i = 0; i<skillLevels.size(); i++){
            for(int j=0; j<skillTypes.size(); j++) {
                EconomySkill skill = factory.create(skillTypes.get(j), skillLevels.get(i));
                if (economyEngine.getActiveHero().canAddSkill(skill)) {
                    boolean canBuy = economyEngine.getActiveHero().getGold().haveEnoghMoney(skill.getGoldCost());
                    SkillButton button = new SkillButton(this::buy, skill, canBuy, skillLevels.get(i));
                    setImageToProducts(button,skillShop,canBuy);
                }
            }
        }
        shopsBox.getChildren().add(skillShop);
    }


    private void fillShopWithSpells(){
        shopsBox.getChildren().clear();
        final VBox spellShop  = new VBox();
        Text label = new Text("Here you can buy spells for your Hero. There are\ndifferent types of spells, these types don't depend on\nchosen fraction. You can buy all types of spells,\nbut spell of one type you can buy only once.");
        label.getStyleClass().add("labelShop");
        spellShop.getChildren().add(label);
        // TODO we can buy only spells with requiredMagicGuildLevel
        List<SpellStats> spellStats = List.of(SpellStats.HASTE,SpellStats.MAGIC_ARROW);
        List<SpellRang> spellRangs = List.of(SpellRang.BASIC,SpellRang.ADVANCED,SpellRang.EXPERT);
        final EconomySpellFactory factory = new EconomySpellFactory();
        for(int i = 0; i<spellStats.size(); i++){
            for(int j=0; j<spellRangs.size(); j++) {
                EconomySpell spell = factory.create(spellStats.get(i),spellRangs.get(j),1);
                if (economyEngine.getActiveHero().canAddSpell(spell)) {
                    boolean canBuy = economyEngine.getActiveHero().getGold().haveEnoghMoney(spell.getGoldCost());
                    SpellButton button = new SpellButton(this::buy, spell,canBuy );
                    setImageToProducts(button,spellShop,canBuy);
                }
            }
        }
        shopsBox.getChildren().add(spellShop);
    }

    private void setImageToProducts(AbstractButton button, VBox shop, boolean canBuy){
        Image image = new Image(button.PATH);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        button.setGraphic(imageView);
        button.setText(button.DESCRIPTION);
        button.getStyleClass().add("centerHBoxRight");
        shop.getChildren().add(button);
        if(canBuy){
            button.getStyleClass().add("centerHBoxRight");
        }else{
            button.getStyleClass().add("centerHBoxGrey");
        }
    }

}