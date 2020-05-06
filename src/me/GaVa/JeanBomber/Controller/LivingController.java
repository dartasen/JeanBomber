package me.GaVa.JeanBomber.Controller;

import me.GaVa.JeanBomber.Map;
import me.GaVa.JeanBomber.Objet.Hitbox;
import me.GaVa.JeanBomber.Objet.interfaces.IEntity;
import me.GaVa.JeanBomber.Objet.interfaces.ILivingEntity;

import java.util.Iterator;

/**
 * GameController qui permet de gérer le cycle de vie des différentes entitées
 */
public class LivingController implements IGameController {

    private Hitbox test;

    /**
     * Met à jour les entitées vivantes
     */
    @Override
    public void update() {
        Iterator<IEntity> it = Map.getEntities().iterator();
        IEntity ent;

        while (it.hasNext()) {
            ent = it.next();

            if (ent instanceof ILivingEntity) {
                final ILivingEntity ient = (ILivingEntity) ent;

                switch (ient.getState()) {

                    case DEAD:
                        it.remove();
                        break;

                    case DYING:
                        ient.die();
                }
            }
        }
    }


}
