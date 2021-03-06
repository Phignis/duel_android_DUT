package fr.iut.duel.manager;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import fr.iut.duel.model.CharacterPlayable;
import fr.iut.duel.util.Action;
import fr.iut.duel.util.RandomManager;
import fr.iut.duel.util.pattern.observer.Observer;
import fr.iut.duel.util.pattern.observer.Subject;

/**
 * Gère un combat entre deux CharacterPlayable, quels qu'ils soient
 * @see CharacterPlayable
 */
public class CombatManager implements Observer {

    /**
     * CharacterPlayable faisant en ce moment une action.
     */
    ArrayBlockingQueue<CharacterPlayable> charactersDoingAction = new ArrayBlockingQueue<>(1);

    /**
     * permet de savoir si un CharacterPlayable est mort ou non, donc possède un montant de pv inférieur ou égal à 0
     * @param p CharacterPlayable dont on souhaite connaitre l'état
     * @return true si le CharacterPlayable est mort au sens des PV, false sinon
     */
    public boolean isPersonnageDead(CharacterPlayable p) {
        return p.getVie() <= 0;
    }

    /**
     * Permet de générer une attaque entre deux CharacterPlayable, basé sur l'attaque de l'attacker, et leur type respectif
     * @param attacker CharacterPlayable attacker le second
     * @param defender CharacterPlayable subissant l'attaque
     */
    public void attaquer(CharacterPlayable attacker, CharacterPlayable defender) {
        defender.setVie(defender.getVie() - attacker.calculAttack(defender));

    }

    /**
     * permet d'ajouter un CharacterPlayable dans la ArrayBlockingQueue de CharacterPlayable
     * effectuant une action, en attendant si jamais le nombre maximum simultané d'actifs est atteint.
     * Abonne l'instance-ci au CharacterPlayable, pour savoir quand ce dernier n'est plus en action
     * @param toAdd CharacterPlayable a inscrire comme actif
     * @throws InterruptedException lancé si jamais, pendant que l'on attends pour ajouter toAdd comme actif, un InterruptedException est reçu
     */
    private void addToAct(CharacterPlayable toAdd) throws InterruptedException {
        charactersDoingAction.put(toAdd);
        toAdd.subscribe(this);
    }

    /**
     * représente l'execution d'une manche complète, et attends x temps pour chaque action
     * @param initiator CharacterPlayable initiant la manche
     * @param implied CharacterPlayable se défendant
     */
    public void mancheExecution(CharacterPlayable initiator, CharacterPlayable implied, long secondsToWaitByAction, Action actionInitiater) {
        if(actionInitiater == Action.ATTAQUE) {
            try {
                addToAct(initiator);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            attaquer(initiator, implied); // l'initiator attaque en premier
            initiator.waitFor(secondsToWaitByAction);
        } else if(actionInitiater == Action.SOIN) {
            initiator.Defense();
        }

        if(!new RandomManager(1).generateBoolean()) {

            // ici, notre ArrayBlockingQueue est full, il faudra donc attendre que l'action soit finit pour en refaire

            try {
                addToAct(implied);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            attaquer(implied, initiator); // le implied attaque en second
            implied.waitFor(secondsToWaitByAction);
        } else {
            implied.Defense();
        }
    }

    /**
     * permet d'enlever le CharacterPlayable en train d'effectuer l'action
     * @param notifier Subject notifiant l'Observer
     */
    @Override
    public void update(Subject notifier) {
        // ici, on se désabonne car notre CharacterPlayable a finit son action, et on l'enlève de la ArrayBlockingQueue
        notifier.unsubscribe(this);
        try {
            charactersDoingAction.poll(10, TimeUnit.MICROSECONDS); // on enlève le CharacterPlayable de la liste bloquante, avec timeout si personne n'est présent
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
