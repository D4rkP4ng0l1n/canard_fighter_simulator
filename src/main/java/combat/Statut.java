package combat;

public enum Statut {
    NEUTRE, BRULURE, GEL, PARALYSIE, EMPOISONNEMENT, SURCHARGE;

    public static final float DEGATS_BRULURE = 0.05f; // La brulure inflige 5% des points de vie de la cible en dégâts
    public static final float DEGATS_POISON = 0.1f; // Le poison inflige 10% des points de vie de la cible en dégâts
}
