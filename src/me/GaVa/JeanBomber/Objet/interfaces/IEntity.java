package me.GaVa.JeanBomber.Objet.interfaces;

/**
 * Interface IEntity qui permet de s'assurer que l'objet
 * qui l'implémente peut être une entité du jeu
 */
public interface IEntity {

    /**
     * Retourne la coordonnée X de l'entité
     * @return Retourne la coordonnée X de l'entité
     */
    int getEntityX();

    /**
     * Retourne la coordonnée Y de l'entité
     * @return Retourne la coordonnée Y de l'entité
     */
    int getEntityY();

    /**
     * Retourne le nom de l'entité de manière simplifiée
     * @return Retourne le nom de l'entité de manière simplifiée
     */
    String toString();

    /**
     * Permet de d'afficher une entité sur la map
     */
    void draw();
}
