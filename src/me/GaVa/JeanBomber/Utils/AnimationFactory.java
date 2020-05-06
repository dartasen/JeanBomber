package me.GaVa.JeanBomber.Utils;

import javafx.scene.image.Image;
import me.GaVa.JeanBomber.Objet.Direction;

import java.io.File;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Fabrique simple qui permet de récupérer les différents sprites du jeu (block + joueur)
 */
public class AnimationFactory {

    /**
     * Une Map des images avec en clé un petit nom et en valeur l'image qui correspond
     */
    private static Map<String, Image> images;

    static {
        final Stream<String> stream = Stream.of("right", "left", "down", "up", "idle", "die", "block", "blockdestr", "wall", "bomb", "fire", "item");
        images = stream.collect(Collectors.toMap(s -> s, image -> loadImage(image + ".png")));
    }

    /** ====================================================== **/

    /**
     * Fonction qui nous renvoie le sprite d'un joueur en fonction de sa direction
     *
     * @param direction La direction dans laquelle le joueur va
     *
     * @see Direction
     * @see me.GaVa.JeanBomber.Objet.entity.Player
     * @see Image
     *
     * @return L'image correspondante
     */
    public static Image getPlayerSprite(Direction direction) {
        switch (direction) {
            case UP:
                return images.get("up");
            case DOWN:
                return images.get("down");
            case LEFT:
                return images.get("left");
            case RIGHT:
                return images.get("right");
            case HEAVEN:
                return images.get("die");
            default:
                return images.get("idle");
        }
    }

    /**
     * Fonction qui nous renvoie le sprite des blocks du jeu
     *
     * @param name Le nom du block à rechercher dans la map
     *
     * @return L'image correspondante
     */
    public static Image getBlockSprite(String name) {
        switch (name) {
            case "wall":
                return images.get("wall");
            case "bomb":
                return images.get("bomb");
            case "fire": case "flame":
                return images.get("fire");
            case "block":
                return images.get("block");
            case "blockdestr":
                return images.get("blockdestr");
            case "item":
                return images.get("item");
            default:
                return images.get("block");
        }
    }

    /** ====================================================== **/

    /**
     * Fonction qui permet de charger une image qui se trouve dans le dossier source
     *
     * @param path Le nom du fichier avec l'extension (ex: feu.png)
     *
     * @see Image
     *
     * @return L'image correspondant dans le répertoire sinon null
     */
    public static Image loadImage(String path) {
        File file = new File("ressources/" + path);

        if (!file.exists())
            return null;

        return new Image("file:" + file.getAbsolutePath());

    }
}