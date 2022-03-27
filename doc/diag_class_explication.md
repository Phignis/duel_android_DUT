# Explication du diagramme de classe

## Spécificité
*   un sigle public devant un attribut signifie normalement la présence d'un getter et setter sur l'attribut, et non que ce dernier est réellement public
*   un attribut private avec le stéréotype {ReadOnly} signifie que il n'existe qu'un getter, et non de setter.

## Architecture du diagramme
>
> Les classes s'organise autour du patron d'architecture MVC (modele view controller). La vue ne fait que dialoguer avec les controlleurs, qui manipulent le modèle. Cela permet de facilité la réusiabilité
> du modèle pour un autre système de vue.
>

## Description du diagramme:

*   FragmentPersos
    >
    > Fragment permettant d'afficher sur la vue du choix des persos les images correspondants aux classes
    >

### util
*   Action
    > 
    > Enum représentant les actions possibles lors d'une manche, ici une attaque ou un soin. Cela est utile nottament pour déterminer l'action choisie par le joueur dans le CombatManager,
    > générant l'execution de la manche
    >
*   RandomManager
    >
    > Cette classe permet de générer de manière random un entier, utile pour le système d'attaque de Voleur, et des booleans, utile pour choisir l'action effectuée par le bot lors de la manche,
    > en réponse à l'éxécution de la manche
    >
*   ### pattern.observer
    >
    > *   Subject
    >     > Interface permettant a une classe d'être sujet d'une observation par des Observers, afin que le Sujet puisse notifier les Observers lors d'un évènement entrainant un évènement chez les Obvservers
    > *   Observer
    >     > Interface permettant a une classe d'observer un Sujet, afin de s'update lorsque le Sujet nous notifie
    > *   UniqObservableSubject
    >     > Implémente l'interface Subject, cependant, elle garantie en plus l'unicitée des instances d'Observers observant UniqObservableSubject, c'est à dire qu'une instance de Observer ne peut s'abonner
    >     > plus d'une fois à une instance de UniqObservableSubject, afin d'éviter que le Observer reçoit une double notification. Cette unicité ne se fait pas via une méthode particulière tel
    >     > Object.equals(Object), utilisé par Set, car cela impliquerait que l'on ne peux redéfinir ce Equals par rapport a celui de Object, ce qui est trop contraignant.
    >
    
### clock
*   TickGenerator
    >
    > TickGenerator a pour but de générer a intervalles régulières (par défaut tout les 16ms), un tick (c'est à dire que TickGenerator, extension de UniqObservableSubject, notifie tous ceux l'observant).
    > Cela permet d'assurer une notion de temps unifiée, simplifiant aussi potentiellement une synchronisation et une modification du rythme du jeu et de tout les objets régit par les ticks.
    >
    
### model
*   TypeAttack
    >
    > TypeAttack est un enum, permettant de connaitre le type de l'attaque d'un CharacterPlayable. Cependant, il permet de plus de calculer les dégâts en prenant en compte forces et faiblesses des types,
    > à partir d'un montent de dégâts initial et d'un type sur lequel s'applique les dégâts. Il permet aussi de savoir si un des types définis par l'enum est super efficace ou très peu efficace contre un autre.
    >
*   CharacterPlayable
    >
    > CharacterPlayable est une classe abstraite, dont dérive tout les personnages jouables du Duel. Cela permet de les manipuler sans regard de leur type réel, et aussi permet de mettre en place pour la
    > méthode defense, qui est en réalité une méthode de soin, d'établir un patron stratégie, étant donné que l'on ne sait comment se soigner au niveau de CharacterPlayable, mais que l'on sait que l'on peut se soigner.
    > Il permet aussi de calculer le nombre de dégâts qu'il inflige à un autre CharacterPlayable, en déléguant ce calcul à TypeAttack. Le type d'attaque est par défaut physique (un simple paysan tapperait au physique)
    > Il permet aussi d'attendre un certain temps, au bout duquel il notifie les Observers. Cela est nottament utile pour les interfaces graphiques, où chaque action prend un certain temps.
    > La gestion de combat de l'interface peut dire au Character d'attaquer, d'attendre n secondes, afin que ce gestionnaire soit notifiée que l'action est terminée.
    >
*   Chimiste
    > Spécialisation de CharacterPlayable, son TypeAttack est Chimique
*   Magicien
    > Spécialisation de CharacterPlayable, son TypeAttack est Magique
*   Paladin
    > Spécialisation de CharacterPlayable, son TypeAttack est Physique
*   Voleur
    > Spécialisation de CharacterPlayable, son TypeAttack est Vicieux
    > De plus, ce dernier, lorsque il attaque, a une faible chance de voler de la vie, selon sa chance. Sa méthode calculAttack permet donc d'ajouter de la vie selon un certain degré de chance.
    >

### manager
*   Combatmanager
    >
    > Son but est de gérer un combat. Nottament, il permet de savoir si un personnage est mort, on peut imaginer que certain personnages deviennent des "morts-vivants", ce qui explique que la responsabilité ne
    > soit pas attribuée au CharacterPlayable directement. Cela permet d'initier une execution de manche, qui lance l'action du joueur donné en paramètre, attend sa fin d'execution, lance l'action du bot de
    > manière random, et attends la fin de cette action.
    >
*   GameManager
    >
    > Le GameManager ici permet juste de connaître le CharacterPlayable du joueur, et celui de l'adversaire.
    >

### Activity
*   MainActivity
    >
    > Point d'entrée de l'application. Essaye de charger les données du stockage, donc la persistance lourde, si existante, consistant a des joueurs et des CharacterPlayable liés.
    > Permet d'aller vers les autres activités, tel le choix de perso, ou le choix de niveau.
    >
*   ChoixPersoActivity
    >
    > back-end de la vue permettant de choisir son personnage. Cela a pour effet d'enregistrer dans le GameManager la bonne instance du perso choisit par le joueur.
    > cette classe permet aussi d'enregistrer le personnage choisie, ainsi que son nom, en persistance lourde.
    >
*   ChoixNiveauActivity
    >
    > back-end de la page permettant de sélectionner le niveau voulu, afin d'effectuer le Duel lié. Une fois le niveau sélectionné, cela a pour effet de "démarrer le niveau", c'est à dire que cette méthode a pour
    > responsabilitée d'enregistrer les instances de CharacterPlayable dans le GameManager, pour les réutiliser ensuite dans la vue du Duel, et de lancer cette dernière vue.
    >
*   BoucheTrouActivity
    >
    > Permet d'afficher la liste des classes de personnages jouables, ainsi que, dans un fragment (FragmentPersos), les images de ces classes.
    >
*   DuelActivity
    >
    > backend du déroulement d'un duel. Les joueurs affichés sont récupérés à l'aide du GameManager, et appuyer sur un bouton d'action tel l'attaque lance l'execution de la manche.
    >
