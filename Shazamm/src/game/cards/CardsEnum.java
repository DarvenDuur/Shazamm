/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.cards;

/**
 *
 * @author darven
 */
public enum CardsEnum {
    Mutism(1,"Mutisme", "",
            "Aucun sort n’a plus d’effet pour les deux joueurs, "
            + "jusqu’à la fin de la manche. Les autres sorts éventuellement "
            + "posés sont perdus.")
    ;

    private final int id;
    private final String name;
    private final String imageName;
    private final String description;
        
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
