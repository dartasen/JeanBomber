package me.GaVa.JeanBomber;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import me.GaVa.JeanBomber.Controller.fxml.GameFXMLController;
import me.GaVa.JeanBomber.Controller.fxml.HighscoreFXMLController;
import me.GaVa.JeanBomber.Controller.fxml.MainFXMLController;
import me.GaVa.JeanBomber.Utils.AnimationFactory;
import me.GaVa.JeanBomber.Utils.Constants;

import java.io.IOException;

public class Main extends Application {

    private static Stage stage;

    public static MainFXMLController MainCtrl = new MainFXMLController();
    public static GameFXMLController GameCtrl = new GameFXMLController();
    public static HighscoreFXMLController ScoreCtrl = new HighscoreFXMLController();

    MediaPlayer musicplayer;
    {
        try {
            musicplayer = new MediaPlayer(new Media(Main.class.getResource("music.mp3").toExternalForm()));
            musicplayer.setAutoPlay(true);
            musicplayer.setVolume(0.2);

            musicplayer.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    musicplayer.seek(Duration.ZERO);
                }
            });
        } catch (Exception e) {
            System.out.println("Impossible de charger la musique d'ambiance du jeu");
        }
    }

    /**
     * Initialise la fenêtre javaFX
     *
     * @param stage La fenêtre de base
     */
    @Override
    public void start(Stage stage) {

        try {
            Main.stage = stage;

            final FXMLLoader loader = new FXMLLoader(MainFXMLController.class.getResource("MainStage.fxml"));
            loader.setController(MainCtrl);

            loadStage(new Scene(loader.load()));
        } catch (IOException e) {
            System.out.printf("Impossible de charger le FXML du MainFXMLController -> %s %n", e.getMessage());
        }

    }

    /**
     * Retourne la fenêtre actuelle
     *
     * @return Stage
     *
     * @see Stage
     */
    public static Stage getStage() {
        return stage;
    }

    /**
     * Permet de changer de fenêtre
     *
     * @param s La nouvelle scene à afficher
     *
     * @see Scene
     */
    public static void loadStage(Scene s) {
        stage.setTitle(Constants.GAME_NAME + " " + Constants.GAME_VERSION);
        stage.getIcons().add(AnimationFactory.loadImage("icon.png"));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(s);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}