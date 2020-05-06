package me.GaVa.JeanBomber.Objet;

/**
 * Définit l'état d'une entité à un instanté
 */
public enum State {

    /**
     * L'entité est vivante
     */
    ACTIVE,

    /**
     * L'entité est entrain de mourrir
     */
    DYING,

    /**
     * L'entité est morte
     */
    DEAD,

    /**
     * L'entité est désactivé
     */
    INACTIVE
}
