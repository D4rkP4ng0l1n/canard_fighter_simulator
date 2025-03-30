package modele.objet;

import modele.canard.Canard;

public abstract class Objet {

    private String nom;
    private String description;

    public Objet(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Utilise l'objet sur un canard.
     * Par défaut, cette méthode est vide et doit être surchargée par des
     * sous-classes.
     *
     * @param canard Le canard sur lequel l'objet est utilisé.
     */
    public abstract void utiliser(Canard canard);

    @Override
    public String toString() {
        return nom + " : " + description;
    }
}