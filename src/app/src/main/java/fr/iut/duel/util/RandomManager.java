package fr.iut.duel.util;

import java.util.Random;

/**
 * RandomManager possède des méthodes afin de générer de manière plus poussée des nombres aléatoires.
 * Il peut etre utile lorsque vous souhaitez générer des nombres, mais dont l'écriture pour la génération
 * n'est pas concise.
 * RandomManager code tout avec l'API Java SE 7, pour assurer une compabilité avec la SDK 16 de Android
 */
public class RandomManager {

    /**
     * borne minimale de génération du random, incluse
     * @see RandomManager#generateRandom()
     */
    private int minLimit;

    /**
     * borne maximale de génération du random, incluse
     * @see RandomManager#generateRandom()
     */
    private int maxLimit;

    /**
     * noyau de randomisation, servant a générer les nombres aléatoires
     * @see RandomManager#generateRandom()
     */
    private Random random;

    /**
     * créé un RandomManager, pouvant générer des entiers randoms entre les deux bornes en paramètres, incluses
     * @param minLimit borne minimale de génération du random, incluse
     * @param maxLimit borne maximale de génération du random, incluse
     */
    public RandomManager(int minLimit, int maxLimit) {
        this.minLimit = minLimit;
        this.maxLimit = maxLimit;
        random = new Random();
    }

    /**
     * créé un RandomManager, pouvant générer des entiers randoms entre 0 et une borne maximale, incluse
     * @param maxLimit borne maximale de génération du random, incluse
     */
    public RandomManager(int maxLimit) {
        this(-1, maxLimit);
    }

    public int getMinLimit() {
        return minLimit;
    }

    public int getMaxLimit() {
        return maxLimit;
    }

    public void setMinLimit(int minLimit) {
        this.minLimit = minLimit;
    }

    public void setMaxLimit(int maxLimit) {
        this.maxLimit = maxLimit;
    }

    /**
     * génère un entier aléatoire entre la borne minimale si spécifié au constructeur, et la borne maximale (incluse)
     * Si la borne minimale n'est pas spécifié, la borne minimale est 0
     * @return un entier pseudo aléatoire
     * @see RandomManager#RandomManager(int)
     * @see RandomManager#RandomManager(int, int) 
     */
    public int generateRandom() {
        // la bound de nextInt est exclue, d'où le + 1
        return random.nextInt(maxLimit + 1 - minLimit) + minLimit;

        // return random.ints(minLimit, (maxLimit + 1)).findFirst().getAsInt();
    }

    /**
     * génère un boolean aléatoire. La probabilité d'obtenir false équivaut
     * à 1 / nombre de valeurs différente de 0. Se référer aux bornes pour voir cette probabilité
     * Si la borne minimale n'est pas spécifié, la borne minimale est 0
     * @return un boolean pseudo aléatoire
     * @see RandomManager#RandomManager(int)
     * @see RandomManager#RandomManager(int, int)
     */
    public boolean generateBoolean() {
        return this.generateRandom() != 0;
    }
}
