package game.cards;

public enum CardsEnum {
    Mutism(1,"Mutisme", "01",
            "Aucun sort n’a plus d’effet pour les deux joueurs, "
            + "jusqu’à la fin de la manche. Les autres sorts éventuellement "
            + "posés sont perdus.", 4),
    Clone(2,"Clone","02","Je pose devant moi une des cartes jouées par "
            + "l’adversaire au tour précédent. Cette carte est appliquée à ce"
            + " tour, comme si je l’avais jouée normalement.", 3),
    Theft(3,"Larcin","03","Tous les sorts joués à ce tour sont sous mon contrôle."
            + " Chacun est, à mon choix, appliqué comme si je l’avais joué,"
            + " ou annulé et défaussé.", 3),
    EndOfRound(4,"Fin de Manche","04","La manche est finie ! Les sorciers "
            + "se replacent à 3 pas du mur de feu (dans sa position actuelle),"
            + " et on commence une nouvelle manche.", 4),
    Middle(5,"Milieu","05","Je replace immédiatement le mur de feu à égale "
            + "distance des deux sorciers. "
            + "Le tour continue ensuite normalement.", 1),
    Recycling(6,"Recyclage","06","Je peux rectifier ma mise, en ajoutant ou "
            + "retranchant jusqu’à 5 points de mana.", 1),
    AttackBoost(7,"Boost attaque","07","La puissance de mon attaque "
            + "est augmentée de 7", 2),
    DoubleDose(8,"Double dose","08","La puissance de mon attaque "
            + "est multipliée par deux.", 2),
    WhoWinLose(9,"Qui perd gagne","09","Le mur de feu avance en sens inverse :"
            + " vers celui qui a gagné ce tour. N’a pas d’effet si le mur de "
            + "feu ne devait pas bouger.", 4),
    Blaze(10,"Brasier","10","Le mur de feu se déplace de deux cases "
            + "au lieu d’une. Seulement s’il devait se déplacer, bien sûr", 3),
    Rezilliance(11,"Résistance","11","Si le mur de feu devait avancer vers moi,"
            + " il ne bouge pas.", 2),
    Scrooge(12,"Harpagon","12","Si je perds ce tour"
            + " (i.e. si le mur de feu avance effectivement"
            + " vers moi), ma mise n’est pas retranchée de ma réserve de"
            + " Mana.", 1),
    StockBoost(13,"Boost réserve","13","Ma réserve de Mana s’augmente de "
            + "13 points. Après que j’ai payé ce que je dois.", 3),
    SuckBet(14,"Aspiration","14","Ma réserve de Mana s’augmente du montant de la "
            + "mise de l’adversaire.", 1);
    

    private final int id;
    private final String name;
    private final String imageName;
    private final String description;
    private final int weight;
    public static final CardsEnum[] CARDS = new CardsEnum[]{
        Mutism, Clone, Theft, EndOfRound, Middle, Recycling, AttackBoost,
        DoubleDose, WhoWinLose, Blaze, Rezilliance, Scrooge, StockBoost, SuckBet
    };
        
    private CardsEnum(int id, String name, String imageName, String description,
            int weight) {
        this.id = id;
        this.name = name;
        this.imageName = imageName;
        this.description = description;
        this.weight = weight;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the imageName
     */
    public String getImageName() {
        return imageName+"v.jpg";
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }
    
}
