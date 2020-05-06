package me.GaVa.JeanBomber.Controller.fxml;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import me.GaVa.JeanBomber.Utils.Constants;

/**
 * FXMLController de la fenêtre de jeu
 */
public class GameFXMLController extends MainFXMLController {

    @FXML
    private Canvas gameMapPane;

    @FXML
    private Pane gameScoresPane;

    @FXML
    private TextField LabelNom;

    @FXML
    private Label LabelX, LabelY, LabelBombes, LabelScore;

    /**
     * Initialise le controller qui gère la fenêtre de jeu
     */
    @FXML
    private void initialize() {
        gameMapPane.setWidth(Constants.SCENE_WIDTH);
        gameMapPane.setHeight(Constants.SCENE_HEIGHT);
    }

    @FXML
    private void focus() {
        gameMapPane.requestFocus();
    }

    /**
     * Retourne la carte de jeu
     * @return Retourne la carte de jeu
     */
    public Canvas getGameMapPane() {
        return gameMapPane;
    }

    /**
     * Retourne les scores
     * @return Retourne les scores
     */
    public Pane getGameScoresPane() {
        return gameScoresPane;
    }

    /**
     * Permet de se bind sur un des labels
     *
     * @param name nom du label à viser
     * @param prop la propriété à bind
     */
    public void bindLabel(String name, ObservableValue<String> prop) {

        switch (name) {
            case "LabelX":
                LabelX.textProperty().bind(prop);
                break;
            case "LabelY":
                LabelY.textProperty().bind(prop);
                break;
            case "LabelBombes":
                LabelBombes.textProperty().bind(prop);
                break;
            case "LabelScore":
                LabelScore.textProperty().bind(prop);
        }
    }

    public void bindBiLabel(String name, StringProperty prop) {

        switch (name) {
            case "LabelNom":
                LabelNom.textProperty().bindBidirectional(prop);
                break;
        }
    }


}
