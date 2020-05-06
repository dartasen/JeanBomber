package me.GaVa.JeanBomber.Objet.entity;

import javafx.scene.image.Image;
import me.GaVa.JeanBomber.Map;
import me.GaVa.JeanBomber.Objet.Hitbox;
import me.GaVa.JeanBomber.Objet.interfaces.ICollideableEntity;
import me.GaVa.JeanBomber.Objet.interfaces.IEntity;
import me.GaVa.JeanBomber.Utils.AnimationFactory;
import me.GaVa.JeanBomber.Utils.Constants;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Blocks indestructibles
 */
public class Wall implements IEntity, ICollideableEntity {

    //region variables
    private int x, y;
    private Image currentImage;
    private Hitbox hitbox;
    //endregion

    /**
     * Constructeur de la classe Wall qui permet d'instancier soit un mur des bordures soit interieur
     *
     * @param x La coordonnée x sur la grille
     * @param y La coordonnée y sur la grille
     * @param border Différencie les wall intérieurs et extérieurs;
     */
    public Wall(int x, int y, boolean border) {
        this.x = x;
        this.y = y;

        currentImage = border ? AnimationFactory.getBlockSprite("wall") : AnimationFactory.getBlockSprite("block");
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
     * @see IEntity
     * @return Retourne le nom de l'entité de manière simplifiée
     */
    @Override
    public String toString() {
        return String.format("Mur (%d, %d)", x, y);
    }
}