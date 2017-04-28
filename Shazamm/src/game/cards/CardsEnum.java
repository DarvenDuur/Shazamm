package game.cards;

public enum CardsEnum {
    Mutism(1,"Mutisme", "",
            "Aucun sort n’a plus d’effet pour les deux joueurs, "
            + "jusqu’à la fin de la manche. Les autres sorts éventuellement "
            + "posés sont perdus."),
    Clone(2,"Clone","","Je pose devant moi une des cartes jouées par "
            + "l’adversaire au tour précédent. Cette carte est appliquée à ce"
            + " tour, comme si je l’avais jouée normalement."),
    Theft(3,"Larcin","","Tous les sorts joués à ce tour sont sous mon contrôle."
            + " Chacun est, à mon choix, appliqué comme si je l’avais joué,"
            + " ou annulé et défaussé."),
    EndOfRound(4,"Fin de Manche","","La manche est finie ! Les sorciers "
            + "se replacent à 3 pas du mur de feu (dans sa position actuelle),"
            + " et on commence une nouvelle manche."),
    Middle(5,"Milieu","","Je replace immédiatement le mur de feu à égale "
            + "distance des deux sorciers. "
            + "Le tour continue ensuite normalement."),
    Recycling(6,"Recyclage","","Je peux rectifier ma mise, en ajoutant ou "
            + "retranchant jusqu’à 5 points de mana."),
    AttackBoost(7,"Boost attaque","","La puissance de mon attaque "
            + "est augmentée de 7"),
    DoubleDose(8,"Double dose","","La puissance de mon attaque "
            + "est multipliée par deux."),
    WhoWinLose(9,"Qui perd gagne","","Le mur de feu avance en sens inverse :"
            + " vers celui qui a gagné ce tour. N’a pas d’effet si le mur de "
            + "feu ne devait pas bouger."),
    Blaze(10,"Brasier","","Le mur de feu se déplace de deux cases "
            + "au lieu d’une. Seulement s’il devait se déplacer, bien sûr"),
    Rezilliance(11,"Résistance","","Si le mur de feu devait avancer vers moi,"
            + " il ne bouge pas."),
    Scrooge(12,"Harpagon","","Si je perds ce tour"
            + " (i.e. si le mur de feu avance effectivement"
            + " vers moi), ma mise n’est pas retranchée de ma réserve de"
            + " Mana."),
    StockBoost(13,"Boost réserve","","Ma réserve de Mana s’augmente de "
            + "13 points. Après que j’ai payé ce que je dois."),
    SuckBet(14,"Aspiration","","Ma réserve de Mana s’augmente du montant de la "
            + "mise de l’adversaire.");
    

    private final int id;
    private final String name;
    private final String imageName;
    private final String description;
    public static final CardsEnum[] CARDS = new CardsEnum[]{
        Mutism, Clone, Theft, EndOfRound, Middle, Recycling, AttackBoost,
        DoubleDose, WhoWinLose, Blaze, Rezilliance, Scrooge, StockBoost, SuckBet
    };
        
    private CardsEnum(int id, String name, String imageName, String description) {
        this.id = id;
        this.name = name;
        this.imageName = imageName;
        this.description = description;
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
        return imageName;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
}
