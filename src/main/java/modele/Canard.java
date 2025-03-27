package modele;

import combat.CapaciteSpeciale;
import combat.Statut;

public abstract class Canard {

    private String nom;
    private TypeCanard type;
    private int pv, pa;
    private CapaciteSpeciale capaciteSpeciale;
    private Statut statut;

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
        this.statut = Statut.NEUTRE;
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

    public Statut getStatut() {
        return this.statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public void attaquer(Canard autreCanard, int degats) {
        if (autreCanard == null) {
            throw new IllegalArgumentException("Il doit y avoir une cible à l'attaque !");
        }
        if (autreCanard == this) {
            throw new IllegalArgumentException("Un canard ne peut pas s'attaquer lui-même !");
        }
        autreCanard.subirDegats((int) (degats * TypeCanard.getMultiplicateur(this.type, autreCanard.getType())));
    }

    public void subirDegats(int degats) {
        if (degats < 0) {
            throw new IllegalArgumentException("Les dégâts subis ne peuvent pas être négatifs !");
        }
        this.pv -= degats;
    }

    public boolean estKO() {
        return this.pv <= 0;
    }

    @Override
    public String toString() {
        return this.nom + " (" + this.type + ") : " + this.pv + " PV, " + this.pa + " PA";
    }
}
