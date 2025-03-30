package modele.objet;

import modele.canard.Canard;

/**
 * Classe représentant un objet utilisable dans le jeu.
 * Les objets peuvent être utilisés pour soigner ou améliorer les canards.
 */
public class Objet {

    private String nom;
    private String description;

    /**
     * Constructeur de la classe Objet.
     *
     * @param nom         Le nom de l'objet.
     * @param description La description de l'objet.
     */
    public Objet(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    /**
     * Retourne le nom de l'objet.
     *
     * @return Le nom de l'objet.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne la description de l'objet.
     *
     * @return La description de l'objet.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Utilise l'objet sur un canard.
     * Cette méthode doit être surchargée par les sous-classes pour définir un
     * comportement spécifique.
     *
     * @param canard Le canard sur lequel l'objet est utilisé.
     */
    public void utiliser(Canard canard) {
        System.out.println("L'objet " + nom + " a été utilisé sur " + canard.getNom() + ".");
    }

    @Override
    public String toString() {
        return nom + " : " + description;
    }
}