package me.GaVa.JeanBomber.Objet.entity;

import javafx.application.Platform;
import javafx.scene.image.Image;
import me.GaVa.JeanBomber.Map;
import me.GaVa.JeanBomber.Objet.Entity;
import me.GaVa.JeanBomber.Objet.State;
import me.GaVa.JeanBomber.Objet.interfaces.IEntity;
import me.GaVa.JeanBomber.Objet.interfaces.ILivingEntity;
import me.GaVa.JeanBomber.Utils.AnimationFactory;
import me.GaVa.JeanBomber.Utils.Constants;
import me.GaVa.JeanBomber.Utils.EntityFactory;

/**
 * Simple bombe noire du jeu
 */
public class Bomb implements IEntity, ILivingEntity {

    //region variables
    private int x, y;
    private Image currentImage;
    private long initialTime;
    private State state;
    //endregion

    /**
     * Constructeur d'une bombe qui dispose d'un temps de vie de 2.5s
     *
     * @param x La coordonnée x sur la grille
     * @param y La coordonnée y sur la grille
     * @param life Le temps de vie de la bombe
     */
    public Bomb(int x, int y, long life) {
        this.x = x;
        this.y = y;

        state = State.ACTIVE;
        initialTime = System.currentTimeMillis() + life;
        currentImage = AnimationFactory.getBlockSprite("bomb");
    }

    /** ====================================================== **/

    /**
     * Permet de dessiner le block sur la carte
     * @see IEntity
     */
    @Override
    public void draw() {
        if (currentImage != null)
            Map.draw(currentImage, x, y);
    }

    /**
     * @see IEntity
     * @return Retourne la coordonnée X du bloc sur la grille
     */
    @Override
    public int getEntityX() {
        return x;
    }

    /**
     * @see IEntity
     * @return La coordonnée Y du bloc sur la grille
     */
    @Override
    public int getEntityY() {
        return y;
    }

    /**
     * Retourne l'état actuelle de l'entité
     *
     * @see ILivingEntity
     * @see State
     */
    @Override
    public State getState() {

        if (state == State.DEAD)
            return state;

        if (System.currentTimeMillis() > initialTime) {
            state = State.DYING;
        }

        return state;
    }

    /**
     * Méthode qui permet de changer l'état d'une entité
     * @param state Le nouvel état
     */
    @Override
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Permet de définir comment l'entité va mourir
     * @see ILivingEntity
     */
    @Override
    public void die() {
        EntityFactory.killEntity(getEntityX(), getEntityY());
        EntityFactory.killEntity(getEntityX(), getEntityY() + Constants.BLOCK_WIDTH);
        EntityFactory.killEntity(getEntityX(), getEntityY() - Constants.BLOCK_WIDTH);
        EntityFactory.killEntity(getEntityX() + Constants.BLOCK_WIDTH, getEntityY());
        EntityFactory.killEntity(getEntityX() - Constants.BLOCK_WIDTH, getEntityY());

        Platform.runLater(() -> EntityFactory.addEntity(Entity.FIRE, x, y));
        setState(State.DEAD);
    }

    /**
     * @see IEntity
     * @return Retourne le nom de l'entité de manière simplifiée
     */
    @Override
    public String toString() {
        return String.format("Bomb (%d, %d : %ds)", x, y, initialTime);
    }
}