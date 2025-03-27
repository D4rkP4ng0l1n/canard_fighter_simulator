package modele;

import combat.CapaciteSpeciale;

public abstract class Canard {

    private String nom;
    private TypeCanard type;
    private int pv, pa;
    private CapaciteSpeciale capaciteSpeciale;

    protected Canard(String nom, TypeCanard type, int pv, int pa, CapaciteSpeciale capaciteSpeciale) {
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
        this.capaciteSpeciale = capaciteSpeciale;
    }

    public String getNom() {
        return this.nom;
    }

    public TypeCanard getType() {
        return this.type;
    }

    public int getPointsDeVie() {
        return this.pv;
    }

    public int getPointsAttaque() {
        return this.pa;
    }

    // attaquer

    public void subirDegats(int degats) {
        if (degats < 0) {
            throw new IllegalArgumentException("Les dégâts subis ne peuvent pas être négatifs !");
        }
        this.pv -= degats;
    }

    public boolean estKO() {
        return this.pv <= 0;
    }
}
