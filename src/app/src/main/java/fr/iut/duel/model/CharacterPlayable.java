package fr.iut.duel.model;

public abstract class CharacterPlayable {

    private String pseudo;
    private int vie;
    private int def;
    private int attaque;
    private String description;
    private int image;

    private TypeAttack typeAttack = TypeAttack.PHYSIQUE;

    public CharacterPlayable(String pseudo, int vie, int def, int attaque, String description, int photo) {
        this.pseudo = pseudo;
        this.vie = vie;
        this.def = def;
        this.attaque = attaque;
        this.description = description;
        this.image = photo;
    }

    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getAttaque() {
        return this.attaque;
    }

    public int getVie() {
        return this.vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    /**
     * Detaille toutes ses statistiques
     * @return String : Une phrase contenant tout
     */
    @Override
    public String toString() {
        return dial() + "\n" +
                "\t pseudo :"+this.getPseudo()+"\n"+
                "\t vie : "+vie+"\n"+
                "\t def : "+def+"\n"+
                "\t attaque : "+attaque+"\n"+
                "\t type : "+this.getClass().getSimpleName()+"\n"+
                "\t description : "+description;
    }

    /**
     * utilisé pour les dialogues
     * @return : le pseudo suivit d'un tab
     */
    public String dial() {
        return this.getPseudo()+" : \t";
    }

    /**
     * Fait parler le personnage
     * @param s : message
     */
    public void parler(String s) {
        System.out.println(dial() + s);
    }

    /**
     * Permet d'ajouter la classe du personnage au dialogue
     * @return
     */
    public String interpelation() {
        return  getClass().getSimpleName() + " " + this.getPseudo();
    }

    /**
     * Utilise lorsque un personnage en attaque un autre
     * @param p : victime de l'attaque
     * @deprecated
     * @see CharacterPlayable#attack(CharacterPlayable)
     */
    public void Attaquer(CharacterPlayable p) {
        BaisseDeVie(this.getAttaque(), p);
        AttaquerParole(p);
    }

    /**
     * a implémenter, définit comment chaque personnage selon sa classe en attaque un autre
     * @param target personnage victime de l'attaque
     */
    public void attack(CharacterPlayable target) {
        typeAttack.calculDamage(target.attaque, target.typeAttack);
    }

    /**
     * Fait une phrase pour dire que le personnage attaque
     * @param p
     */
    public void AttaquerParole(CharacterPlayable p) {
        System.out.println(dial() + "HAAAA non je t'en supplie " +
                p.interpelation() + " je n'ai pas appris a me battre !!!");
    }

    /**
     * Baisse la vie de la victime avec les degats
     * @param degats : degats a infliger
     * @param victime : reçoit l'attaque
     */
    public final void BaisseDeVie(int degats, CharacterPlayable victime) {
        if (victime.getVie() >= degats){
            victime.setVie(victime.getVie() - degats);
        }else{
            victime.setVie(0);
        }
    }

    /**
     * A redefinir pour mettre la defense voulue
     */
    public abstract void Defense();

    public void reset() {
        System.out.println("je ne peux rien faire");
    }
}
