package fr.iut.duel.util.pattern.observer;

/**
 * Sujet a pour but d'envoyer des notifications à divers Observateurs
 * Il fait partie de la couche d'abstraction avec Observateur (Observer) du patron de conception
 * comportemental "Observateur"
 * Chaque implémentation de Sujet devrait définir une collection (au choix) d'Observateur abonné,
 * a qui il envoit des notifications
 * @see Observer
 * @see <a href="http://goprod.bouhours.net/?page=pattern&pat_id=16">Patron Observateur</a>
 */
public interface Subject {
    /**
     * A pour but d'abonner l'observateur passé en paramètre, en l'ajoutant à une collection d'abonnés,
     * pour qu'il reçoive les notifications
     * @param toAdd instance d'Observer a abonner
     * @return true si l'instance a bien été abonnée, false sinon. Voyez la documentation des implémentations
     * pour connaitre les raisons du false
     */
    boolean subscribe(Observer toAdd);

    /**
     * A pour but de désabonner l'observateur passé en paramètre, pour qu'il ne recoive plus les notifications.
     * Réciproque de attacher(Observateur)
     * @param toRemove Observateur à désabonner
     * @return true si o a bien été désabonné, false sinon
     */
    boolean unsubscribe(Observer toRemove);

    /**
     * A pour but de notifier tout les Observers présent dans la collection d'observateurs abonnés
     */
    void notifyObservers();
}
