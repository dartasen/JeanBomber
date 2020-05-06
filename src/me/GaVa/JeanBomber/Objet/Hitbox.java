package me.GaVa.JeanBomber.Objet;

import javafx.geometry.Rectangle2D;

/**
 * Objet gérant la hitbox en jeu
 */
public class Hitbox {

    /**
     * Les coordonnées de la hitbox sur la fenêtre
     */
    private double x, y;

    /**
     * La largeur et la hauteur de la hitbox
     */
    private int width, height;

    /**
     * Le rectangle2D qui va nous permettre de vérifier les collisions
     */
    private Rectangle2D boundary;

    /**
     * Constructeur d'une hitbox
     *
     * @param x La coordonnée X sur la carte
     * @param y La coordonnée Y sur la carte
     * @param w La largeur de la hitbox
     * @param h La hauteur de la hitbox
     */
    public Hitbox(double x, double y, int w, int h){
        this.x=x;
        this.y=y;
        width=w;
        height=h;
        boundary = new Rectangle2D(x, y, width, height);
    }

    /**
     * @return Retourne la hitbox
     */
    public Rectangle2D getBoundary() {
        return boundary;
    }

    /**
     * Fonction qui permet de savoir si deux hitbox se rentrent dedans
     *
     * @param b La hitbox d'une autre entité
     *
     * @return un booléen
     */
    public boolean checkCollision(Hitbox b) {
        return b.getBoundary().intersects(getBoundary());
    }

    /**
     * Fonction qui permet de repositionner la hitbox
     *
     * @param x Nouvelle coordonné X sur la carte
     * @param y Nouvelle cordonnée Y sur la carte
     */
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
        boundary = new Rectangle2D(x, y, width, height);
    }

}
