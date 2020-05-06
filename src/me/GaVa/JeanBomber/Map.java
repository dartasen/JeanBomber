package me.GaVa.JeanBomber;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;
import me.GaVa.JeanBomber.Controller.IGameController;
import me.GaVa.JeanBomber.Controller.LivingController;
import me.GaVa.JeanBomber.Controller.MoveController;
import me.GaVa.JeanBomber.Objet.Entity;
import me.GaVa.JeanBomber.Objet.entity.Player;
import me.GaVa.JeanBomber.Objet.interfaces.IEntity;
import me.GaVa.JeanBomber.Utils.Constants;
import me.GaVa.JeanBomber.Utils.EntityFactory;

import java.util.*;
import java.util.stream.Stream;

public class Map {

    //region variables
    private static List<IGameController> controllers;
    private static GraphicsContext gc;
    private static Timeline gameloop;
    private static Player p;
    private static Canvas c;

    private static ObservableList<IEntity> ent;
    //endregion

    /**
     * Fonction qui permet de créer la carte de jeu du bomberman
     *
     * @param s La scene sur laquelle dessiner
     */
    public static void setup(Scene s) {

        //region setupEntityScene
        p = new Player("hero", 28, 28);
        c = Main.GameCtrl.getGameMapPane();
        gc = c.getGraphicsContext2D();

        ent = FXCollections.observableList(new ArrayList<>());

        setPlayer(p);

        Player p2 = new Player("Benjamin vincent", 470, 350);
        addEntity(p2);

        setTiles();

        Main.GameCtrl.bindBiLabel("LabelNom", p.getNameProperty());
        Main.GameCtrl.bindLabel("LabelX", p.getX().asString());
        Main.GameCtrl.bindLabel("LabelY", p.getY().asString());
        Main.GameCtrl.bindLabel("LabelBombes", p.getBombe().asString());
        Main.GameCtrl.bindLabel("LabelScore", p.getScore().asString());

        controllers = Arrays.asList(new MoveController(s, p, p2), new LivingController());

        gameloop = new Timeline();
        gameloop.setCycleCount(Timeline.INDEFINITE);
        //endregion

        gameloop.getKeyFrames().add(new KeyFrame(
                Duration.seconds(0.017D), //60FPS
                ae -> {
                    try {
                        Map.getGraphicsContext().clearRect(0D, 0D, Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
                        Map.controllers.stream().forEach(c -> c.update());
                        Map.getEntities().stream().forEach(e -> e.draw());
                    } catch (Exception e) {
                        System.out.printf("Erreur de la boucle de jeu %s%n", e.getMessage());
                    }
                }
        ));
        gameloop.play();
    }

    /**
     * Permet de stopper la boucle et de jeu et de tout réinitialiser
     */
    public static void stop() {

        if (gameloop == null)
            return;

        gameloop.stop();
        controllers = null;
        p = null;
        gc = null;
        c = null;
        ent.clear();
    }

    /** ====================================================== **/


    /**
     * @return Le GraphicsContext de la carte de jeu
     * @see GraphicsContext
     */
    public static GraphicsContext getGraphicsContext() {
        return gc;
    }

    /**
     * @return La liste des entitées dans le jeu
     * @see IEntity
     */
    public static ObservableList<IEntity> getEntities() {
        return ent;
    }

    /**
     * @return Le joueur actuellement sur la carte
     */
    public static Player getPlayer() {
        return p;
    }

    /**
     * Permet de dessiner sur la carte de jeu initialisée
     *
     * @param img Le sprite de l'entité
     * @param x La coordonnée X sur la carte
     * @param y La coordonnée Y sur la carte
     */
    public static void draw(Image img, int x, int y) {
        gc.drawImage(img, (double) x, (double) y);
    }

    /** ====================================================== **/

    /**
     * Permet d'ajouter une entité dans le jeu si elle n'existe pas déjà
     *
     * @param e L'entité à ajouter
     *
     * @see IEntity
     *
     * @return Un booléen
     */
    public static boolean addEntity(IEntity e) {
        return !ent.contains(e) && ent.add(e);
    }

    /**
     * Permet de changer le joueur qui joue actuellement
     *
     * @param player le nouveau joueur
     *
     * @see Player
     */
    public static void setPlayer(Player player) {
        addEntity(p);
        p = player;
    }

    //region setTiles
    /**
     * Permet de générer les blocks sur la carte
     */
    private static void setTiles() {
        Random r = new Random();

        for (int t = 0; t < Constants.SCENE_WIDTH; t += Constants.BLOCK_WIDTH)
            for (int p = 0; p < Constants.SCENE_HEIGHT; p += Constants.BLOCK_HEIGHT)
                if (t == 0 || p == 0 || (p + Constants.BLOCK_HEIGHT) == Constants.SCENE_HEIGHT || (t + Constants.BLOCK_WIDTH) == Constants.SCENE_WIDTH)
                    EntityFactory.addEntity(Entity.WALL_EXT, t, p);


        for (int t = Constants.BLOCK_WIDTH * 2; t + Constants.BLOCK_WIDTH * 2 < Constants.SCENE_WIDTH; t += 2 * Constants.BLOCK_WIDTH)
            for (int p = Constants.BLOCK_HEIGHT * 2; p + Constants.BLOCK_HEIGHT * 2 < Constants.SCENE_HEIGHT; p += 2 * Constants.BLOCK_HEIGHT)
                EntityFactory.addEntity(Entity.WAll_INT, t, p);


        for (int t = Constants.BLOCK_WIDTH * 3; t + Constants.BLOCK_WIDTH < Constants.SCENE_WIDTH - Constants.BLOCK_WIDTH * 2; t += Constants.BLOCK_WIDTH) {
            EntityFactory.addEntity(Entity.BLOCK, t, Constants.BLOCK_HEIGHT);
            EntityFactory.addEntity(Entity.BLOCK, t, Constants.SCENE_HEIGHT-Constants.BLOCK_HEIGHT*2);
        }

        for (int p = Constants.BLOCK_HEIGHT * 3; p + Constants.BLOCK_HEIGHT < Constants.SCENE_HEIGHT - Constants.BLOCK_HEIGHT * 2; p += Constants.BLOCK_HEIGHT) {
            EntityFactory.addEntity(Entity.BLOCK, Constants.BLOCK_WIDTH, p);
            EntityFactory.addEntity(Entity.BLOCK, Constants.SCENE_WIDTH-Constants.BLOCK_WIDTH*2, p);
        }

        for (int t = Constants.BLOCK_WIDTH * 2; t + Constants.BLOCK_WIDTH < Constants.SCENE_WIDTH - Constants.BLOCK_WIDTH ; t += Constants.BLOCK_WIDTH)
            for (int p = Constants.BLOCK_HEIGHT * 3; p + Constants.BLOCK_HEIGHT < Constants.SCENE_HEIGHT - Constants.BLOCK_HEIGHT ; p += Constants.BLOCK_HEIGHT*2)
                if (r.nextInt(2)<1)
                    EntityFactory.addEntity(Entity.BLOCK, t, p);

        for (int t = Constants.BLOCK_WIDTH * 3; t + Constants.BLOCK_WIDTH < Constants.SCENE_WIDTH - Constants.BLOCK_WIDTH ; t += Constants.BLOCK_WIDTH*2)
            for (int p = Constants.BLOCK_HEIGHT * 2; p + Constants.BLOCK_HEIGHT < Constants.SCENE_HEIGHT - Constants.BLOCK_HEIGHT ; p += Constants.BLOCK_HEIGHT*2)
                if (r.nextInt(2)<1)
                    EntityFactory.addEntity(Entity.BLOCK, t, p);
    }
    //endregion
}
