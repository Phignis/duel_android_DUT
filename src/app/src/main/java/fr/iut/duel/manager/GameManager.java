package fr.iut.duel.manager;

import fr.iut.duel.model.Personnage;

public class GameManager {
    private static GameManager instance;
    private Personnage joueur;
    private Personnage adversaire;
    private int niveauReussit;

    public static GameManager getInstance() {
        if (instance == null){
            instance = new GameManager();
        }
        return instance;
    }

    public void setJoueur(Personnage joueur) {
        this.joueur = joueur;
    }
    public Personnage getJoueur() {
        return joueur;
    }

    public void setAdversaire(Personnage adversaire) {
        this.adversaire = adversaire;
    }
    public Personnage getAdversaire() {
        return adversaire;
    }

    public void setNiveauReussit(int niveauReussit) {
        this.niveauReussit = niveauReussit;
    }
    public int getNiveauReussit() {
        return niveauReussit;
    }
}
