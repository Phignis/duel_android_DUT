package fr.iut.duel.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Représente un type d'attaque.
 * Magique > Chimique > Physique > Vicieux.
 * {@link #MAGIQUE}
 * {@link #CHIMIQUE}
 * {@link #PHYSIQUE}
 * {@link #VICIEUX}
 */
enum TypeAttack {
    /**
     * Magique possède un coef faible de 0.3f, et un fort de 1.5f
     */
    MAGIQUE(0.3f, 1.5f), // fort contre Chimique, car protégé par la magie contre le poison
    /**
     * CHIMIQUE possède un coef faible de 0.6f, et un fort de 2.1f
     */
    CHIMIQUE(0.6f, 2.1f), // fort contre physique, car l'empoisonne et passe a travers l'armure
    /**
     * PHYSIQUE possède un coef faible de 0.5f, et un fort de 2f
     */
    PHYSIQUE(0.5f, 2), // fort contre vicieux car habitué au corps à corps
    /**
     * VICIEUX possède un coef faible de 0.8f, et un fort de 2.3f
     */
    VICIEUX(0.8f, 2.3f); // fort contre Magique car habitué a attaquer par surprise, sans qu'un magicien ne soit prêt

    /**
     * coefficient faible s'appliquant lorsque le membre attaquant est peu efficace contre un défenseur (Magique > Chimique > Physique > Vicieux)
     */
    private float coefFaible;
    /**
     * coefficient faible s'appliquant lorsque le membre attaquant est super efficace contre un défenseur (Magique > Chimique > Physique > Vicieux)
     */
    private float coefFort;

    /**
     * Construit un membre de l'enum
     * @param coefFaible coefficient s'appliquant lorsque le membre attaquant est peu efficace contre un défenseur (Magique > Chimique > Physique > Vicieux)
     * @param coefFort coefficient s'appliquant lorsque le membre attaquant est super efficace contre un défenseur (Magique > Chimique > Physique > Vicieux)
     */
    TypeAttack(float coefFaible, float coefFort) {
        this.coefFaible = coefFaible;
        this.coefFort = coefFort;
    }

    /**
     * permet de récupérer l'ordinale inverse de this
     * @return l'ordinale inverse de this
     */
    private static int reverseOrdinal(TypeAttack type) {
        List<TypeAttack> typeAttacksReverse = Arrays.asList(values());
        Collections.reverse(typeAttacksReverse);
        return typeAttacksReverse.indexOf(type);
    }

    /**
     * permet de savoir si un type est super efficace contre un autre
     * @param attack type attaquant
     * @param defence type subissant l'attaque
     * @return true si attack est super efficace contre defence, false sinon
     */
    public static boolean isVeryEffective(TypeAttack attack, TypeAttack defence) {
        return ((attack.ordinal() + 1) % values().length) == defence.ordinal();
    }

    /**
     * permet de savoir si un type est pas très efficace contre un autre
     * @param attack type attaquant
     * @param defense type subissant l'attaque
     * @return true si attack est pas très efficace contre defense, false sinon
     */
    public static boolean isNotVeryEffective(TypeAttack attack, TypeAttack defense) {
        return ((reverseOrdinal(attack) + 1 ) % values().length) == reverseOrdinal(defense);
    }

    /**
     * permet de calculer les dégâts infligés, en fonction de sa position dans la roue et de celle de celui attaqué
     * @param damage dégâts initiaux
     * @param typeDefense type de celui subissant l'attaque
     * @return le nombre de dégâts après passage à la roue
     */
    public int calculAttack(int damage, TypeAttack typeDefense) {
        if(isVeryEffective(this, typeDefense)) {
            return (int)(damage * coefFort);
        } else if(isNotVeryEffective(this, typeDefense)) {
            return (int)(damage * coefFaible);
        } else {
            return damage;
        }
    }
}