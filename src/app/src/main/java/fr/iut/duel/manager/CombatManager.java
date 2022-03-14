package fr.iut.duel.manager;

import fr.iut.duel.model.CharacterPlayable;

/**
 * Gère un combat entre deux Personnages, quels qu'ils soient
 * @see CharacterPlayable
 */
public class CombatManager {
    /**
     * permet de savoir si un personnage est mort ou non, donc possède un montant de pv inférieur ou égal à 0
     * @param p personnage dont on souhaite connaitre l'état
     * @return true si le personnage est mort au sens des PV, false sinon
     */
    public boolean isPersonnageDead(CharacterPlayable p) {
        return p.getVie() <= 0;
    }

    /**
     * Permet de générer une attaque entre deux personnages, basé sur leur attaque et défense
     * @param attaquant CharacterPlayable attaquant le second
     * @param victime CharacterPlayable subissant l'attaque
     */
    public void attaquer(CharacterPlayable attaquant, CharacterPlayable victime) {
        victime.setVie(victime.getVie() - attaquant.getAttaque());
    }

    // TODO voir https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ArrayBlockingQueue.html
    public void mancheExecution(CharacterPlayable attaquant, CharacterPlayable victime) {
        attaquer(attaquant, victime); // l'attaquant attaque en premier
    }
}
