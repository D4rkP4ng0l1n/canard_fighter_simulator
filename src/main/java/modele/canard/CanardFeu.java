package modele.canard;

import combat.capacite.Capacite;
import combat.capaciteSpeciale.CapaciteSpecialeFeu;

public class CanardFeu extends Canard {

    // ---------- ATTRIBUTS ---------- //

    private static final int PV_MIN = 35;
    private static final int PV_MAX = 40;
    private static final int AJOUT_PV_MIN = 1;
    private static final int AJOUT_PV_MAX = 2;

    private static final int ATTAQUE_MIN = 45;
    private static final int ATTAQUE_MAX = 50;
    private static final int AJOUT_ATTAQUE_MIN = 2;
    private static final int AJOUT_ATTAQUE_MAX = 3;

    private static final int VITESSE_MIN = 40;
    private static final int VITESSE_MAX = 45;
    private static final int AJOUT_VITESSE_MIN = 1;
    private static final int AJOUT_VITESSE_MAX = 2;

    // ---------- CONSTRUCTEURS ---------- //

    /*
     * Constructeur d'un canard de type FEU
     */
    public CanardFeu(String nom) {
        super(nom, TypeCanard.FEU, new CapaciteSpecialeFeu());
        this.apprendreCapacite(Capacite.BRAISE_CANARD);
    }

    /*
     * Constructeur d'un canard de type FEU avec un niveau spécifié
     */
    public CanardFeu(String nom, int niveau) {
        super(nom, TypeCanard.FEU, new CapaciteSpecialeFeu(), niveau);
        this.apprendreCapacite(Capacite.BRAISE_CANARD);
    }

    /*
     * Constructeur d'un canard de type FEU avec des statistiques spécifiées
     */
    public CanardFeu(String nom, int pv, int attaque, int vitesse) {
        super(nom, TypeCanard.FEU, new CapaciteSpecialeFeu(), pv, attaque, vitesse);
        this.apprendreCapacite(Capacite.BRAISE_CANARD);
    }

    // ---------- METHODES ---------- //

    /*
     * Initialise les statistiques de base du canard de type FEU
     */
    protected void initStats() {
        this.stats.add(PV_MIN + (int) (Math.random() * ((PV_MAX - PV_MIN) + 1)));
        this.stats.add(ATTAQUE_MIN + (int) (Math.random() * ((ATTAQUE_MAX - ATTAQUE_MIN) + 1)));
        this.stats.add(VITESSE_MIN + (int) (Math.random() * ((VITESSE_MAX - VITESSE_MIN) + 1)));
    }

    /*
     * Calcule les statistiques du canard de type FEU
     */
    protected void calculerStats() {
        this.stats.set(0,
                this.stats.get(0) + AJOUT_PV_MIN + (int) (Math.random() * ((AJOUT_PV_MAX - AJOUT_PV_MIN) + 1)));
        this.stats.set(1,
                this.stats.get(1) + AJOUT_ATTAQUE_MIN
                        + (int) (Math.random() * ((AJOUT_ATTAQUE_MAX - AJOUT_ATTAQUE_MIN) + 1)));
        this.stats.set(2,
                this.stats.get(2) + AJOUT_VITESSE_MIN
                        + (int) (Math.random() * ((AJOUT_VITESSE_MAX - AJOUT_VITESSE_MIN) + 1)));
    }

    /*
     * Méthode permettant d'utiliser la capacité spéciale du canard
     */
    public void utiliserCapaciteSpeciale(Canard canardCible) {
        if (this.capaciteSpecialeDisponible) {
            this.capaciteSpeciale.activer(this, canardCible);
        }
        this.capaciteSpecialeDisponible = false;
    }

}
