package fr.iut.duel.model;

import com.example.duel.R;

public class Paladin extends CharacterPlayable {

    public Paladin(String pseudo) {
        super(pseudo, 200, 100, 80,"Gaillard toujours prêt à servir.\n200 de vie,100 de defense,20 d'attaque", R.drawable.paladin, TypeAttack.PHYSIQUE);
    }

    /**
     * Parle lors de l'attaque
     * @param p : Le personnage adverse
     */
    @Override
    public void AttaquerParole(CharacterPlayable p) {
        System.out.println(dial()+"J'attaque avec toute ma force "+p.interpelation()+" !!!");
    }

    /**
     * La vie remonte sans depasser son maximum de vie
     */
    @Override
    public void Defense() {
        if(this.getVie() > 180) {
            this.setVie(200);
        }
        else{
            this.setVie(this.getVie() + 20);
            parler("J'utilise une potion de soin");
        }
    }

    /**
     * Reinitialise la vie au maximum
     */
    @Override
    public void reset() {
        this.setVie(200);
    }
}
