package me.GaVa.JeanBomber.Controller;

import javafx.scene.Scene;
import me.GaVa.JeanBomber.Objet.Direction;
import me.GaVa.JeanBomber.Objet.entity.Player;
import me.GaVa.JeanBomber.Utils.Constants;
/**
 * GameController qui gère le déplacement du joueur
 */
public class MoveController implements IGameController {

    private boolean goNorth, goSouth, goEast, goWest, bomb;
    private boolean goNorth2, goSouth2, goEast2, goWest2, bomb2;
    private Player hero, hero2;

    /** ====================================================== **/

    /**
     * Permet de gérer les touches du clavier pour le déplacement
     *  @param scene La scene sur laquelle on enregistre les touches
     * @param p Le player à trigger
     */
    public MoveController(Scene scene, Player p, Player p2) {

        this.hero = p;
        this.hero2 = p2;

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    goNorth = true;
                    break;
                case Z:
                    goNorth2 = true;
                    break;
                case DOWN:
                    goSouth = true;
                    break;
                case S:
                    goSouth2 = true;
                    break;
                case LEFT:
                    goWest = true;
                    break;
                case Q:
                    goWest2 = true;
                    break;
                case RIGHT:
                    goEast = true;
                    break;
                case D:
                    goEast2 = true;
                    break;
                case CONTROL:
                    bomb = true;
                    break;
                case SPACE:
                    bomb2 = true;
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:
                    goNorth = false;
                    break;
                case Z:
                    goNorth2 = false;
                    break;
                case DOWN:
                    goSouth = false;
                    break;
                case S:
                    goSouth2 = false;
                    break;
                case LEFT:
                    goWest = false;
                    break;
                case Q:
                    goWest2 = false;
                    break;
                case RIGHT:
                    goEast = false;
                    break;
                case D:
                    goEast2 = false;
                    break;
                case CONTROL:
                    bomb = false;
                    break;
                case SPACE:
                    bomb2 = false;
                    break;
            }
        });

    }

    /** ====================================================== **/

    /**
     * Met à jour les déplacements des entités
     */
    @Override
    public void update() {

        if (bomb) {
            hero.dropBomb();
        }

        if (bomb2) {
            hero2.dropBomb();
        }

        if (goNorth)
            hero.move(Constants.PLAYER_SPEED, Direction.UP);
        else if (goEast)
            hero.move(Constants.PLAYER_SPEED, Direction.RIGHT);
        else if (goWest)
            hero.move(Constants.PLAYER_SPEED, Direction.LEFT);
        else if (goSouth)
            hero.move(Constants.PLAYER_SPEED, Direction.DOWN);
        else
            hero.move(0, Direction.NONE);

        /** POUR LE MULTIJOUEUR **/
        if (goNorth2)
            hero2.move(Constants.PLAYER_SPEED, Direction.UP);
        else if (goEast2)
            hero2.move(Constants.PLAYER_SPEED, Direction.RIGHT);
        else if (goWest2)
            hero2.move(Constants.PLAYER_SPEED, Direction.LEFT);
        else if (goSouth2)
            hero2.move(Constants.PLAYER_SPEED, Direction.DOWN);
        else
            hero2.move(0, Direction.NONE);
        /** **/
    }
}
    
