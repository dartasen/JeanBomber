package me.GaVa.JeanBomber.Objet.entity;

import javafx.scene.image.Image;
import me.GaVa.JeanBomber.Map;
import me.GaVa.JeanBomber.Objet.State;
import me.GaVa.JeanBomber.Objet.interfaces.IEntity;
import me.GaVa.JeanBomber.Objet.interfaces.ILivingEntity;
import me.GaVa.JeanBomber.Utils.AnimationFactory;

/**
 * Simple feu du jeu qui est généré par une bombe
 */
public class Feu implements IEntity, ILivingEntity {

    //region variables
    private int x, y;
    private long initialTime;
    private Image currentImage;
    private State state;
    //endregion

    /**
     * Constructeur d'un feu qui est obligatoirement créé par une bombe
     *
     * @param x La coordonnée x sur la grille
     * @param y La coordonnée y sur la grille
     */
    public Feu(int x, int y) {
        this.x = x;
        this.y = y;

        state = State.ACTIVE;
        initialTime = System.currentTimeMillis() + 1000;
        currentImage = AnimationFactory.getBlockSprite("fire");
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
        setState(State.DEAD);
    }

    /**
     * @see IEntity
     * @return Retourne le nom de l'entité de manière simplifiée
     */
    @Override
    public String toString() {
        return String.format("Feu (%d, %d)", x, y);
    }
}