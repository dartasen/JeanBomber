package me.GaVa.JeanBomber.Utils;

import me.GaVa.JeanBomber.Map;
import me.GaVa.JeanBomber.Objet.Entity;
import me.GaVa.JeanBomber.Objet.Hitbox;
import me.GaVa.JeanBomber.Objet.State;
import me.GaVa.JeanBomber.Objet.entity.*;
import me.GaVa.JeanBomber.Objet.interfaces.ICollideableEntity;
import me.GaVa.JeanBomber.Objet.interfaces.IEntity;
import me.GaVa.JeanBomber.Objet.interfaces.ILivingEntity;

import java.util.stream.Collectors;

/**
 * Fabrique simple qui permet de créer/tuer des entitées dans le jeu
 */
public class EntityFactory {

    /**
     * Permet de fabriquer des entités et de les ajouter au jeu de manière indépendante
     *
     * @param name Le type de l'entité
     * @param x La coordonnée x sur la carte
     * @param y La coordonnée y sur la carte
     *
     * @see Entity
     * @see IEntity
     *
     * @return Un booléen si ça s'est bien passé
     */
    public static boolean addEntity(Entity name, int x, int y) {
        IEntity e;

        switch (name) {

            case WALL_EXT:
                e = new Wall(x, y, true);
                break;

            case WAll_INT:
                e = new Wall(x, y, false);
                break;

            case BLOCK:
                e = new Block(x, y);
                break;

            case BOMB:
                e = new Bomb(x, y, 2500);
                break;

            case FIRE:
                e = new Feu(x, y);
                break;

            case ITEM:
                e = new Item(x, y);
                break;

            case PLAYER:
                e = new Player("newHero", x, y);
                Map.setPlayer((Player) e);
                return true;

            default: return false;
        }

        return Map.addEntity(e);
    }

    public static void killEntity(int x, int y) {
        final Hitbox test = new Hitbox(x, y, 15, 15);

        Map.getEntities().stream()
                .filter(e -> e instanceof ILivingEntity && e instanceof ICollideableEntity)
                .filter(e -> test.checkCollision(((ICollideableEntity) e).getBoundingBox()))
                .forEach(e ->  {
                    ((ILivingEntity) e).setState(State.DYING);
                    Map.getPlayer().addScore(10.0D);
                });
    }
}
