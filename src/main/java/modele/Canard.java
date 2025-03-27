package modele;

public class Canard {

    private String nom;
    private TypeCanard type;
    private int pv, pa;
    // Capacité spéciale

    public Canard(String nom, TypeCanard type, int pv, int pa) {
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
