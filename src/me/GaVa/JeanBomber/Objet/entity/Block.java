package me.GaVa.JeanBomber.Objet.entity;

import javafx.scene.image.Image;
import me.GaVa.JeanBomber.Map;
import me.GaVa.JeanBomber.Objet.Entity;
import me.GaVa.JeanBomber.Objet.Hitbox;
import me.GaVa.JeanBomber.Objet.State;
import me.GaVa.JeanBomber.Objet.interfaces.ICollideableEntity;
import me.GaVa.JeanBomber.Objet.interfaces.IEntity;
import me.GaVa.JeanBomber.Objet.interfaces.ILivingEntity;
import me.GaVa.JeanBomber.Utils.AnimationFactory;
import me.GaVa.JeanBomber.Utils.Constants;
import me.GaVa.JeanBomber.Utils.EntityFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Random;

/**
 * Block simple du jeu
 */
public class Block implements IEntity, ICollideableEntity, ILivingEntity {

    //region variables
    private int x, y;
    private Image currentImage;
    private Hitbox hitbox;
    private State state;
    //endregion

    /**
     * Constructeur de la classe Block qui permet d'isntancier soit un mur, soit un bloc indestructible
     * soit un bloc indestrucible en fonction des paramètres rentrés
     *
     * @param x La coordonnée x sur la grille
     * @param y La coordonnée y sur la grille
     */
    public Block(int x, int y) {
        this.x = x;
        this.y = y;

        state = State.ACTIVE;
        currentImage = AnimationFactory.getBlockSprite("blockdestr");
        hitbox = new Hitbox(x, y, Constants.BLOCK_WIDTH, Constants.BLOCK_HEIGHT);
    }

    /** ====================================================== **/

    /**
     * Méthode qui permet de savoir si on rentre en collision avec une entité
     * @param e l'entité avec laquelle on veut vérifier la collision
     */
    @Override
    public boolean isColliding(ICollideableEntity e) {
        throw new NotImplementedException();
    }

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
     * @return Retourne la hitbox du block
     */
    @Override
    public Hitbox getBoundingBox() {
        return hitbox;
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
        return String.format("Block (%d, %d)", x, y);
    }
}