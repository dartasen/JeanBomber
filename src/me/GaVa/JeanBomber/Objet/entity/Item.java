package me.GaVa.JeanBomber.Objet.entity;

import javafx.scene.image.Image;
import me.GaVa.JeanBomber.Map;
import me.GaVa.JeanBomber.Objet.interfaces.IEntity;
import me.GaVa.JeanBomber.Utils.AnimationFactory;

/**
 * Simple bonus du jeu
 */
public class
Item implements IEntity {

    //region variables
    private int x, y;
    private Image currentImage;
    //endregion

    /**
     * Constructeur d'un feu qui est obligatoirement créé par une bombe
     *
     * @param x La coordonnée x sur la grille
     * @param y La coordonnée y sur la grille
     */
    public Item(int x, int y) {
        this.x = x;
        this.y = y;

        currentImage = AnimationFactory.getBlockSprite("item");
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
     * @see IEntity
     * @return Retourne le nom de l'entité de manière simplifiée
     */
    @Override
    public String toString() {
        return String.format("Item (%d, %d)", x, y);
    }
}