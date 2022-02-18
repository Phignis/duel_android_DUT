package fr.iut.duel.manager;

import fr.iut.duel.model.Personnage;

/**
 * Gère un combat entre deux Personnages, quels qu'ils soient
 * @see fr.iut.duel.model.Personnage
 */
public class CombatManager {
    /**
     * permet de savoir si un personnage est mort ou non, donc possède un montent pv inférieur ou égal à 0
     * @param p personnage dont on souhaite connaitre l'état
     * @return true si le personnage est mort au sens des PV, false sinon
     */
    public boolean isPersonnageDead(Personnage p) {
        return p.getVie() <= 0;
    }

    /**
     * Permet de générer une attaque entre deux personnages, basé sur leur attaque et défense
     * @param attaquant Personnage attaquant le second
     * @param victime Personnage subissant l'attaque
     */
    public void attaquer(Personnage attaquant, Personnage victime) {
        victime.setVie(victime.getVie() - attaquant.getAttaque());
    }
}
