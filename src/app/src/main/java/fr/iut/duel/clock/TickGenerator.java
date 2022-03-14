package fr.iut.duel.clock;

import fr.iut.duel.util.pattern.observer.UniqObservableSubject;

/**
 * Génères des ticks à intervalles régulières, utile pour effectuer des boucles temporelles.
 * Il génère des ticks à intervalle régulière, servant à cadencer la fréquence d'apparition d'une boucle temporelle
 */
public class TickGenerator extends UniqObservableSubject implements Runnable {
    /**
     * Représente l'intervalle entre deux ticks généré par l'instance de la classe. Cette valeur est en milli-secondes
     * @see TickGenerator#TickGenerator(int)
     * @see TickGenerator#getIntervalBetweenTicks()
     */
    private int intervalBetweenTicks;

    /**
     * thread interne, servant d'effectuer, en parallèle du reste du jeu, la mise en place des ticks de 16ms
     * @see TickGenerator#run()
     */
    private Thread internalThread;

    /**
     * indique si le thread interne est en train de run ou non
     * @see TickGenerator#internalThread
     */
    private boolean running;

    /**
     * Constructeur de GenerateurTick.
     * Il lance en interne un nouveau thread (threadInterne), permettant de mettre en place les ticks, générés à intervalle régulière
     * @param intervalBetweenTicks intervalle entre deux ticks générés par l'instance. Cette valeur est en micro-secondes (ms)
     * @see TickGenerator#internalThread
     * @see TickGenerator#intervalBetweenTicks
     *
     */
    public TickGenerator(int intervalBetweenTicks) {
        this.intervalBetweenTicks = intervalBetweenTicks;
        this.running = true;
        this.internalThread = new Thread(this);
        internalThread.start();
    }

    /**
     * Constructeur de GenerateurTick.
     * Il lance en interne un nouveau thread (threadInterne), permettant de mettre en place les ticks, générés à intervalle régulière
     * Par défaut, la valeur de l'intervalle entre chaque ticks est de 16ms, pour un jeu à 60Hz
     * @see TickGenerator#internalThread
     * @see TickGenerator#intervalBetweenTicks
     */
    public TickGenerator() {
        this(16);
    }

    public int getIntervalBetweenTicks() {
        return intervalBetweenTicks;
    }

    /**
     * Permet d'interrompre le thread interne générant les ticks, via la méthode interrupt
     * Cette méthode ne devrait jamais renvoyer false.
     * La fonction réciproque est reprendreGenerateur
     * @return true si jamais il a été possible d'interrompre le thread interne, false sinon, normalement car le thread interne est initialisé à null
     * @see Thread#interrupt()
     * @see TickGenerator#internalThread
     * @see TickGenerator#reprendreGenerateur()
     */
    public boolean interruptTickGeneration() {
        if(internalThread != null) {
            internalThread.interrupt();
            running = false;
            return true;
        }
        return false;
    }

    /**
     * Permet de relancer la génération des ticks. On ne relance pas vraiment le thread interne,
     * mais on lui réattribue un nouveau thread, que l'on relance.
     * Il interrompt d'abord le thread interne avec interruptTickGeneration(),
     * afin d'éviter que le thread n'ayant plus de référence soit toujours actif.
     * @see TickGenerator#internalThread
     * @see TickGenerator#interruptTickGeneration()
     */
    public void reprendreGenerateur() {
        // on s'assure que le generateur est bien interrompu
        interruptTickGeneration();
        // on recréé un nouveau thread
        internalThread = new Thread(this);
        internalThread.start();
        running =true;
    }

    /**
     * Fonction effectuée par le thread interne à la classe permettant la mise en place du décompte de tick
     * @see TickGenerator#internalThread
     */
    @Override
    public void run() {
        // boucle du thread
        while(running) {
            try {
                Thread.sleep(intervalBetweenTicks);
                super.notify();
            } catch (InterruptedException e) {
                break; // thread interrompue durant le sleep
            }
        }
    }
}
