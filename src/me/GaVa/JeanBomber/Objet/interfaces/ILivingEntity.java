package me.GaVa.JeanBomber.Objet.interfaces;

import me.GaVa.JeanBomber.Objet.State;

/**
 * Interface IEntity qui permet de s'assurer que l'objet
 * qui l'implémente peut être une entité du jeu qui nécessite
 * une gestion vivante de l'entité dans le sens que l'on peut avoir
 * un système d'état, de point de vie, ...
 */
public interface ILivingEntity {

    /**
     * Méthode pour récupérer l'état de l'entité
     *
     * @return Méthode pour récupérer l'état de l'entité
     *
     * @see State
     */
    State getState();

    /**
     * Méthode qui permet de changer l'état d'une entité
     *
     * @param state Le nouvel état
     */
    void setState(State state);

    /**
     * Méthode qui définit comment l'entité meurt pendant le jeu
     */
    void die();
}
