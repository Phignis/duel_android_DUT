package fr.iut.duel.util.pattern.observer;

import java.util.List;

/**
 * SujetObservableUneFois est une implémentation de Sujet. Il réalise donc la promesse de Sujet, et garentit en plus l'unicité des
 * Observateurs, c'est à dire qu'un Observateur ne peut observer qu'une seule fois cette instance de SujetObservableUneFois
 * Une classe fille typique est GenerateurTick
 * @see Subject
 * @see Observer
 */
public abstract class UniqObservableSubject implements Subject{

    /**
     * liste des observateurs abonnés au Sujet observé.
     * L'unicité des instances dans la liste est garantie
     * @see UniqObservableSubject#subscribe(Observer)
     * @see UniqObservableSubject#unsubscribe(Observer) 
     */
    private List<Observer> subscribers;

    @Override
    public boolean subscribe(Observer toAdd) {
        if(toAdd == null) return false;

        for(Observer alreadySubscribed : subscribers) {
            if(toAdd == alreadySubscribed) return false;
        }

        return subscribers.add(toAdd);
    }

    @Override
    public boolean unsubscribe(Observer toRemove) {
        return subscribers.remove(toRemove);
    }

    @Override
    public void notifyObservers() {
        return;
    }
}
