package me.GaVa.JeanBomber.Objet.interfaces;

import me.GaVa.JeanBomber.Objet.Hitbox;

/**
 * Interface IEntity qui permet de s'assurer que l'objet
 * qui l'implémente peut être une entité du jeu qui nécessite
 * une gestion des collisions avec les autres entités
 */
public interface ICollideableEntity {

    /**
     * Méthode pour récupérer la Hitbox de l'entité
     * @return Méthode pour récupérer la Hitbox de l'entité
     */
    Hitbox getBoundingBox();

    /**
     * Méthode qui permet de savoir si on rentre en collision avec une entité
     *
     * @param b l'entité avec laquelle on veut vérifier la collision
     *
     * @return Un booléen
     */
    boolean isColliding(ICollideableEntity b);
}
