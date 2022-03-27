package fr.iut.duel.model;

import com.example.duel.R;

import fr.iut.duel.util.RandomManager;

public class Voleur extends CharacterPlayable {

    private int chance;
    private RandomManager generateurRandom;

    public Voleur(String pseudo, int minRandomLimit, int maxRandomLimit, int chance) {
        super(pseudo, 100, 20, 80,
                "Un vil parmis les vils.\n100 de vie,20 de defense,50 d'attaque", R.drawable.voleur, TypeAttack.VICIEUX);
        this.chance = chance;
        generateurRandom = new RandomManager(minRandomLimit, maxRandomLimit);
    }

    public Voleur(String pseudo, int minRandomLimit, int maxRandomLimit) {
        this(pseudo, minRandomLimit, maxRandomLimit, 50);
    }

    public Voleur(String pseudo, int maxRandomLimit) {
        this(pseudo, 0, maxRandomLimit);
    }

    public Voleur(String pseudo) {
        this(pseudo, 100);
    }

    /**
     * Reduit la vie du personnage p
     * Suivant un nombre au hasard le vole de la vie ou rate
     * @param p : Le personnage adverse
     */
    @Override
    public void AttaquerParole(CharacterPlayable p) {
        int r = generateurRandom.generateRandom();

        if(r > chance) {
            parler(p.interpelation() + " voici ma lame rouillé préféré");

            if (getVie() < 100){
                setVie((getVie() + 20) % 100);
                parler("et voilà mon dut !");
            }
        } else {
            parler("mince j'aurais pas du essayer de voler " + p.interpelation() + " en meme temps");
        }

    }

    /**
     * La vie remonte sans depasser son maximum de vie
     */
    @Override
    public void Defense() {
        if(this.getVie() > 80) {
            this.setVie(100);
        }
        else {
            this.setVie(this.getVie() + 20);
            parler("J'utilise une potion de soin");
        }
    }

    /**
     * Reinitialise la vie au maximum
     */
    @Override
    public void reset() {
        this.setVie(100);
    }
  
   @Override
    public int calculAttack(CharacterPlayable target) {
        if(generateurRandom.generateRandom() > chance && getVie() < 100) {
            setVie(getVie() + (getVie() + 20) % 100);
        } // sinon il rate l'attaque
        return super.calculAttack(target);
    }

}
