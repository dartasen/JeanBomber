package me.GaVa.JeanBomber.Controller.fxml;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import me.GaVa.JeanBomber.Main;
import me.GaVa.JeanBomber.Map;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXMLController de la fenêtre principale
 */
public class MainFXMLController extends Main {

    private static Logger log = Logger.getLogger(MainFXMLController.class.getCanonicalName());

    @FXML
    private void startNewGame() {

        try {
            final FXMLLoader loader = new FXMLLoader(MainFXMLController.class.getResource("GameStage.fxml"));
            loader.setController(Main.GameCtrl);

            final Scene s = new Scene(loader.load());
            loadStage(s);
			Map.setup(s);
        } catch (IOException e) {
            log.log(Level.SEVERE, String.format("Erreur lors du démarrage du jeu -> %s", e.getMessage()));
        }

    }

    @FXML
    private void openHighscores() {

        try {
            final FXMLLoader loader = new FXMLLoader(MainFXMLController.class.getResource("Highscores.fxml"));
            loader.setController(Main.ScoreCtrl);

            final Scene s = new Scene(loader.load());
            loadStage(s);
        } catch (IOException e) {
            log.log(Level.SEVERE, String.format("Erreur lors de l'ouverture des scores -> %s", e.getMessage()));
        }

    }

    @FXML
    private void exitGame() {
        getStage().close();
    }

    @FXML
    private void backToMenu() {

        try {
            FXMLLoader loader = new FXMLLoader(MainFXMLController.class.getResource("MainStage.fxml"));
            loader.setController(Main.MainCtrl);
            loadStage(new Scene(loader.load()));
            Map.stop();

        } catch(IOException e) {
            log.log(Level.SEVERE, "Impossible de retourner au menu");
        }

    }

    @FXML
    private void mouseEnteredButton(Event event) {
        Button eventButton = (Button) event.getTarget();
        eventButton.setEffect(new Glow(0.5));
    }

    @FXML
    private void mouseExitedButton(Event event) {
        Button eventButton = (Button) event.getTarget();
        eventButton.setEffect(new Glow(0.0));
    }

}
