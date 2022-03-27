# Explication du diagramme de cas d'utilisation

**Nom**	Jouer
**Acteur principal**
> Joueur
**Objectif de l'acteur principal**
> Effectuer une action de jeu
**Acteur secondaire**
> /
**Conditions initiales**
> Une partie est lancée, le joueur a donc choisit son personnage, et les pv des deux personnages s'affrontant ne sont pas à 0
**Scénario d’utilisation**
> *   Cela lance une execution de manche
> *   En même temps, une animation des personnages représentant leur action est mise en place

**Condition de fin**
> *   La manche est finit normalement
> *   Le jeu est interrompu soudainement

**Nom**	Attaquer
**Acteur principal**
> Joueur
**Objectif de l'acteur principal**
> Effectuer une action de jeu, ici attaquer
**Acteur secondaire**
> /
**Conditions initiales**
> Une partie est lancée, le joueur a donc choisit son personnage, et les pv des deux personnages s'affrontant ne sont pas à 0
**Scénario d’utilisation**
> *   Cela lance une execution de manche, avec comme action du joueur une attaque
> *   En même temps, une animation des personnages représentant leur action est mise en place

**Condition de fin**
> *   La manche est finit normalement
> *   Le jeu est interrompu soudainement

**Nom**	se soigner
**Acteur principal**
> Joueur
**Objectif de l'acteur principal**
> Effectuer une action de jeu, ici se soigner
**Acteur secondaire**
> /
**Conditions initiales**
> Une partie est lancée, le joueur a donc choisit son personnage, et les pv des deux personnages s'affrontant ne sont pas à 0
**Scénario d’utilisation**
> *   Cela lance une execution de manche, avec comme action du joueur un soin
> *   En même temps, une animation des personnages représentant leur action est mise en place

**Condition de fin**
> *   La manche est finit normalement
> *   Le jeu est interrompu soudainement

**Nom**	Choisir son personnage
**Acteur principal**
> Joueur
**Objectif de l'acteur principal**
> Le joueur veut choisir une classe de personnage jouable parmis ceux disponibles
**Acteur secondaire**
> /
**Conditions initiales**
> /
**Scénario d’utilisation**
> *   Le joueur se rend sur l'activité ChoixPerso
> *   Il choisit dans la liste une des classes
> *   Il valide son action avec le bouton correspondant

**Condition de fin**
> *   Le joueur a cliqué sur le bouton
