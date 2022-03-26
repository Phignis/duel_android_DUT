package fr.iut.duel.util.pattern.observer;

import java.util.LinkedList;
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

    public UniqObservableSubject(){
        subscribers = new LinkedList<>();
    }

    /**
     * Abonne un Observer à cette instance de UniqObservableSubject, pour qu'il reçoive les notifications,
     * si l'instance n'est pas déjà présente
     * @see UniqObservableSubject#notifyObservers()
     * @param toAdd Observer à abonner au Sujet, pour qu'il recoive les notifications
     * @return true si l'Observer a bien été abonné, false sinon (si null, ou si l'instance est déjà abonnée)
     */
    @Override
    public boolean subscribe(Observer toAdd) {
        if(toAdd == null) return false;

        for(Observer alreadySubscribed : subscribers) {
            if(toAdd == alreadySubscribed) return false;
        }

        return subscribers.add(toAdd);
    }

    /**
     * Désabonne un Observer à cette instance de SujetObservableUneFois, pour qu'il ne reçoive plus les notifications
     * @see UniqObservableSubject#notifyObservers()
     * @param toRemove Observer a abonner au Sujet, pour qu'il recoive les notifications
     * @return true si l'Observer a bien été désabonné, false sinon (si l'Observer n'était déjà pas abonné)
     */
    @Override
    public boolean unsubscribe(Observer toRemove) {
        return subscribers.remove(toRemove);
    }

    /**
     * Notifie, via la méthode update de Observer, tous les Observateurs abonnés à cette instance, via la méthode attacher
     * @see Observer#update(Subject)
     * @see UniqObservableSubject#subscribe(Observer)
     */
    @Override
    public void notifyObservers() {
        for(Observer toNotify : subscribers) {
            toNotify.update(this);
        }
    }
}
