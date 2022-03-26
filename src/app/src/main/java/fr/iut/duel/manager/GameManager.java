package fr.iut.duel.manager;

import fr.iut.duel.model.CharacterPlayable;

public class GameManager {
    private static GameManager instance;
    private CharacterPlayable joueur;
    private CharacterPlayable adversaire;
    private int niveauReussit;

    public static GameManager getInstance() {
        if (instance == null){
            instance = new GameManager();
        }
        return instance;
    }

    public void setJoueur(CharacterPlayable joueur) {
        this.joueur = joueur;
    }
    public CharacterPlayable getJoueur() {
        return joueur;
    }

    public void setAdversaire(CharacterPlayable adversaire) {
        this.adversaire = adversaire;
    }
    public CharacterPlayable getAdversaire() {
        return adversaire;
    }

    public void setNiveauReussit(int niveauReussit) {
        this.niveauReussit = niveauReussit;
    }
    public int getNiveauReussit() {
        return niveauReussit;
    }
}
