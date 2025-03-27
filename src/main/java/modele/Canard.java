package modele;

public abstract class Canard {

    private String nom;
    private TypeCanard type;
    private int pv, pa;
    // Capacité spéciale

    protected Canard(String nom, TypeCanard type, int pv, int pa) {
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("Le nom du canard ne peut pas être vide !");
        }
        if (pv <= 0) {
            throw new IllegalArgumentException("Les points de vie du canard ne peuvent pas être négatifs !");
        }
        if (pa <= 0) {
            throw new IllegalArgumentException("Les points d'attaque du canard ne peuvent pas être négatifs !");
        }
        this.nom = nom;
        this.type = type;
        this.pv = pv;
        this.pa = pa;
    }

    public String getNom() {
        return nom;
    }

    public TypeCanard getType() {
        return type;
    }

    public int getPointsDeVie() {
        return pv;
    }

    public int getPointsAttaque() {
        return pa;
    }
}
