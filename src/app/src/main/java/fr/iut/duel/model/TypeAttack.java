package fr.iut.duel.model;

enum TypeAttack {
    CHIMIQUE(0.25f, 1.5f),
    MENTAL(0.25f, 1.5f),
    PHYSIQUE(0.5f, 2),
    VICIEUX(0.5f, 2);

    private float coefFaible;
    private float coefFort;



    TypeAttack(float coefFaible, float coefFort) {
        this.coefFaible = coefFaible;
        this.coefFort = coefFort;
    }

    float calculDamage(int damage, TypeAttack typeAttack) {
        if(typeAttack.ordinal() + 1 == ordinal() ) {
            return damage * coefFort;
        } else if(typeAttack.ordinal() - 1 == ordinal()) {
            return damage * coefFaible;
        } else {
            return damage;
        }
    }
}