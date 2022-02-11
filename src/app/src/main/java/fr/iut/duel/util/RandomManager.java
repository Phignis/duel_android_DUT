package fr.iut.duel.util;

import java.util.Random;

/**
 * RandomManager possède des méthodes afin de générer de manière plus poussée des nombres aléatoires.
 * Il peut etre utile lorsque vous souhaitez générer des nombres, mais dont l'écriture pour la génération
 * n'est pas concise.
 * RandomManager code tout avec l'API Java SE 7, pour assurer une compabilité avec la SDK 16 de Android
 */
public class RandomManager {
    private int minLimit;
    private int maxLimit;
    private Random random;

    public RandomManager(int min_limit, int max_limit) {
        this.minLimit = min_limit;
        this.maxLimit = max_limit;
        random = new Random();
    }

    public RandomManager(int max_limit) {
        this(-1, max_limit);
    }

    /**
     * génère un entier aléatoire entre la borne minimale si spécifié au constructeur, et la borne maximale (incluse)
     * @return un entier pseudo aléatoire
     * @see RandomManager#RandomManager(int)
     * @see RandomManager#RandomManager(int, int) 
     */
    public int generateRandom() {
        return random.nextInt(maxLimit + 1 - minLimit) + minLimit;

        // return random.ints(minLimit, (maxLimit + 1)).findFirst().getAsInt();
    }
}
