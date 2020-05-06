package me.GaVa.JeanBomber.Objet.entity;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import me.GaVa.JeanBomber.Main;
import me.GaVa.JeanBomber.Map;
import me.GaVa.JeanBomber.Objet.Direction;
import me.GaVa.JeanBomber.Objet.Entity;
import me.GaVa.JeanBomber.Objet.Hitbox;
import me.GaVa.JeanBomber.Objet.State;
import me.GaVa.JeanBomber.Objet.interfaces.ICollideableEntity;
import me.GaVa.JeanBomber.Objet.interfaces.IEntity;
import me.GaVa.JeanBomber.Objet.interfaces.ILivingEntity;
import me.GaVa.JeanBomber.Utils.AnimationFactory;
import me.GaVa.JeanBomber.Utils.Constants;
import me.GaVa.JeanBomber.Utils.EntityFactory;

/**
 * Joueur du jeu
 */
public class Player implements IEntity, ICollideableEntity, ILivingEntity {


    //region variables
    private SimpleIntegerProperty x, y, bombe;
    private SimpleDoubleProperty score;
    private SimpleStringProperty name;

    private Image currentImage;
    private Hitbox hitbox;
    private State state;
    private long lastBomb;
    //endregion

    /** ====================================================== **/

    /**
     * Constructeur de Player qui permet simplement de créer un joueur qui se déplace
     * sur la grille et peut poser des bombes
     *
     * @param name Le nom à donner au personnage
     * @param x La coordonnée x sur la grille
     * @param y La coordonnée y sur la grille
     */
    public Player(String name, int x, int y) {
        this.name = new SimpleStringProperty(name);

        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.score = new SimpleDoubleProperty(0.0D);
        this.bombe = new SimpleIntegerProperty(100);

        state = State.ACTIVE;
        lastBomb = System.currentTimeMillis();
        currentImage = AnimationFactory.getPlayerSprite(Direction.NONE);
        hitbox = new Hitbox(x, y, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
    }

    /** ====================================================== **/

    //region interfaceredefinition
    /**
     * Méthode qui permet de savoir si on rentre en collision avec une entité
     * @param e l'entité avec laquelle on veut vérifier la collision
     */
    @Override
    public boolean isColliding(ICollideableEntity e) {
        return hitbox.checkCollision(e.getBoundingBox());
    }

    /**
     * Permet de dessiner le block sur la carte
     * @see IEntity
     */
    @Override
    public void draw() {
        if (currentImage != null)
            Map.draw(currentImage, x.get(), y.get());
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
        return x.get();
    }

    /**
     * @see IEntity
     * @return La coordonnée Y du bloc sur la grille
     */
    @Override
    public int getEntityY() {
        return y.get();
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
        setCurrentImage(AnimationFactory.getPlayerSprite(Direction.HEAVEN));
        Platform.runLater(() -> Map.stop());
        Main.ScoreCtrl.addScore(String.format("%s : %s", name.getValue(), score.get()));
    }

    /**
     * @see IEntity
     * @return Retourne le nom de l'entité de manière simplifiée
     */
    @Override
    public String toString() {
        return name.getValue();
    }
    //endregion

    /** ====================================================== **/

    //region BindingProperty

    /**
     * Getter qui permet de récupérer le nom pour faire du binding
     * @return Retourne une SimpleStringProperty du nom de l'entité
     */
    public SimpleStringProperty getNameProperty() {
        return name;
    }

    /**
     * Getter qui permet de récupérer le X pour faire du binding
     * @return Retourne une SimpleIntegerProperty du X de l'entité
     */
    public SimpleIntegerProperty getX() {
        return x;
    }

    /**
     * Getter qui permet de récupérer le Y pour faire du binding
     * @return Retourne une SimpleIntegerProperty du Y de l'entité
     */
    public SimpleIntegerProperty getY() {
        return y;
    }

    /**
     * Getter qui permet de récupérer le score pour faire du binding
     * @return Retourne une SimpleDoubleProperty du Y de l'entité
     */
    public SimpleDoubleProperty getScore() {
        return score;
    }

    /**
     * Setter qui permet d'incrémenter le score du binding
     * @param val le score à ajouter
     */
    public void addScore(double val) {
        score.set(score.doubleValue() + val);
    }

    /**
     * Getter qui permet de récupérer le score pour faire du binding
     * @return Retourne une SimpleDoubleProperty du Y de l'entité
     */
    public SimpleIntegerProperty getBombe() {
        return bombe;
    }

    /**
     * Setter qui permet d'incrémenter le nombre de bombe
     * @param val le score à ajouter
     */
    public void addBombe(int val) {
        bombe.set(bombe.get() + val);
    }
    //endregion

    /**
     * Permet de poser une bombe si le joueur en dispose et à dépasser le cooldown
     */
    public void dropBomb() {
        if (System.currentTimeMillis() > lastBomb + 2000 && bombe.get() > 0) {
            EntityFactory.addEntity(Entity.BOMB, getEntityX(), getEntityY());
            lastBomb = System.currentTimeMillis();
            addBombe(-1);
        }
    }

    /**
     * Permet de savoir si à des coordonnées spécifiées on rentre en collision avec une entité
     *
     * @param x La coordonnée x de l'emplacement
     * @param y La coordonéne y de l'emplacement
     *
     * @return Retourne un booléen
     */
    private boolean isColliding(int x, int y) {
        hitbox.setPosition(x, y);

        for (IEntity e : Map.getEntities())
            if (e != this && e instanceof ICollideableEntity && this.isColliding((ICollideableEntity) e))
                return true;

        hitbox.setPosition(getEntityX(), getEntityY());

        return false;
    }

    /**
     * Fonction qui permet au joueur de se déplacer d'un nombre nb dans une direction
     *
     * @param nb La distance à parcourir
     * @param direction La direction dans laquelle se déplacer
     *
     * @see Direction
     */
    public void move(int nb, Direction direction) {

        hitbox.setPosition(getEntityX(), getEntityY());

        if (nb == 0) {
            setCurrentImage(AnimationFactory.getPlayerSprite(Direction.NONE));
            return;
        }

        switch (direction) {
            case UP:
                if (!isColliding(getEntityX(), getEntityY() - nb)) {
                    y.set(getEntityY() - nb);
                    setCurrentImage(AnimationFactory.getPlayerSprite(Direction.UP));
                }
                break;

            case DOWN:
                if (!isColliding(getEntityX(), getEntityY() + nb)) {
                    y.set(getEntityY() + nb);
                    setCurrentImage(AnimationFactory.getPlayerSprite(Direction.DOWN));
                }
                break;

            case LEFT:
                if (!isColliding(getEntityX() - nb, getEntityY())) {
                    x.set(getEntityX() - nb);
                    setCurrentImage(AnimationFactory.getPlayerSprite(Direction.LEFT));
                }
                break;

            case RIGHT:
                if (!isColliding(getEntityX() + nb, getEntityY())) {
                    x.set(getEntityX() + nb);
                    setCurrentImage(AnimationFactory.getPlayerSprite(Direction.RIGHT));
                }
                break;

            default:
                setCurrentImage(AnimationFactory.getPlayerSprite(Direction.NONE));
        }
    }

    /**
     * Permet de changer le sprite de l'image à afficher à chaque tour de boucle de jeu
     *
     * @param img La nouvelle image retourner par la factory
     *
     * @see AnimationFactory
     */
    private void setCurrentImage(Image img) {
        currentImage = img;
    }
}