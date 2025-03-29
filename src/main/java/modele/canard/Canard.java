package modele.canard;

import java.util.ArrayList;
import java.util.List;

import combat.Combat;
import combat.Statut;
import combat.capacite.Capacite;
import combat.capacite.Effet;
import combat.capaciteSpeciale.CapaciteSpeciale;

/*
 * Classe abstraite représentant un canard
 */
public abstract class Canard {

    // ---------- ATTRIBUTS ---------- //

    private static final int NIVEAU_MAX = 100;
    private static final int MAX_ENERGIE = 100;

    private String nom;
    private TypeCanard type;

    private Statut statut;

    private int niveau;
    private int totalExpPourProchainNiveau, totalExp;

    // PV, Attaque, Vitesse
    protected List<Integer> stats, statsEnCombat;
    private int energie;

    protected List<Capacite> capacites;
    protected CapaciteSpeciale capaciteSpeciale;
    protected boolean capaciteSpecialeDisponible; // True si la capacité spéciale du canard est disponible

    // ---------- CONSTRUCTEURS ---------- //

    /*
     * Constructeur de base d'un canard
     */
    public Canard(String nom, TypeCanard type, CapaciteSpeciale capaciteSpeciale) {
        this.nom = nom;
        this.type = type;

        this.statut = Statut.NEUTRE;

        this.niveau = 1;
        this.totalExp = 0;
        this.totalExpPourProchainNiveau = 100;

        this.stats = new ArrayList<>(3);
        this.initStats();
        this.statsEnCombat = new ArrayList<>(stats);
        this.energie = MAX_ENERGIE;

        this.capacites = new ArrayList<>(4);
        this.capaciteSpeciale = capaciteSpeciale;
        this.capaciteSpecialeDisponible = true;

    }

    /*
     * Constructeur d'un canard avec un niveau spécifié
     */
    public Canard(String nom, TypeCanard type, CapaciteSpeciale capaciteSpeciale, int niveau) {
        if (niveau > NIVEAU_MAX) {
            throw new IllegalArgumentException(
                    "Le niveau du canard ne peut pas dépasser le niveau " + NIVEAU_MAX + " !");
        }

        this.nom = nom;
        this.type = type;

        this.statut = Statut.NEUTRE;

        this.niveau = niveau;
        this.totalExp = this.niveau * 100;
        this.totalExpPourProchainNiveau = (this.niveau + 1) * 100;

        this.stats = new ArrayList<>(3);
        this.initStats();
        this.calculerStats();
        this.statsEnCombat = new ArrayList<>(stats);
        this.energie = MAX_ENERGIE;

        this.capacites = new ArrayList<>(4);
        this.capaciteSpeciale = capaciteSpeciale;
        this.capaciteSpecialeDisponible = true;
    }

    /*
     * Constructeur d'un canard avec des statistiques spécifiées
     */
    public Canard(String nom, TypeCanard type, CapaciteSpeciale capaciteSpeciale, int pv, int attaque, int vitesse) {
        this.nom = nom;
        this.type = type;

        this.statut = Statut.NEUTRE;

        this.niveau = 1;
        this.totalExp = 0;
        this.totalExpPourProchainNiveau = 100;

        this.stats = new ArrayList<>(3);
        this.stats.add(pv);
        this.stats.add(attaque);
        this.stats.add(vitesse);
        this.statsEnCombat = new ArrayList<>(stats);
        this.energie = MAX_ENERGIE;

        this.capacites = new ArrayList<>(4);
        this.capaciteSpeciale = capaciteSpeciale;
        this.capaciteSpecialeDisponible = true;
    }

    // ---------- GETTERS ---------- //

    /*
     * Retourne le nom du canard
     */
    public String getNom() {
        return this.nom;
    }

    /*
     * Retourne le type du canard
     */
    public TypeCanard getType() {
        return this.type;
    }

    /*
     * Retourne le statut du canard
     */
    public Statut getStatut() {
        return this.statut;
    }

    /*
     * Retourne les points de vie du canard
     */
    public int getPointsDeVie() {
        return this.stats.get(0);
    }

    /*
     * Retourne les points de vie du canard en combat
     */
    public int getPointsDeVieCombat() {
        return this.statsEnCombat.get(0);
    }

    /*
     * Retourne les points d'attaque du canard
     */
    public int getPointsAttaque() {
        return this.stats.get(1);
    }

    /*
     * Retourne les points d'attaque du canard en combat
     */
    public int getPointsAttaqueCombat() {
        return this.statsEnCombat.get(1);
    }

    /*
     * Retourne la vitesse du canard
     */
    public int getVitesse() {
        return this.stats.get(2);
    }

    /*
     * Retourne la vitesse du canard en combat
     */
    public int getVitesseCombat() {
        return this.statsEnCombat.get(2);
    }

    /*
     * Retourne l'energie restante du canard
     */
    public int getEnergie() {
        return this.energie;
    }

    /*
     * Retourne les capacites du canard
     */
    public List<Capacite> getCapacites() {
        return this.capacites;
    }

    /*
     * Retourne true si la capacite speciale du canard est disponible, false sinon
     */
    public boolean capaciteSpecialeDisponible() {
        return this.capaciteSpecialeDisponible;
    }

    // ---------- SETTERS ---------- //

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public void apprendreCapacite(Capacite capacite) {
        if (this.capacites.size() < 4)
            this.capacites.add(capacite);
    }

    // ---------- METHODES ---------- //

    /*
     * Methodes permettant a un canard d'attaquer un autre canard
     */
    public void attaquer(Canard autreCanard, Capacite capacite) {
        // Calcul des dégâts
        double degats = (this.getPointsAttaque() * Combat.COEFF_EQUILIBRAGE) * capacite.getDegats()
                * TypeCanard.getMultiplicateur(capacite.getType(), autreCanard.getType());
        // Infliger les degats
        autreCanard.subirDegats((int) degats);
    }

    /*
     * Méthode permettant d'infliger des degats a un canard
     */
    public void subirDegats(int degats) {
        this.statsEnCombat.set(0, this.statsEnCombat.get(0) - degats);
        if (this.statsEnCombat.get(0) < 0)
            this.statsEnCombat.set(0, 0);
    }

    /*
     * Méthode permettant de soigner un canard
     */
    public void soigner(int soin) {
        this.statsEnCombat.set(0, this.statsEnCombat.get(0) + soin);
        if (this.statsEnCombat.get(0) > this.stats.get(0))
            this.statsEnCombat.set(0, this.stats.get(0));
    }

    /*
     * Applique un effet sur le canard
     */
    public void appliquerEffet(Effet effet, double montantEffet) {
        switch (effet) {
            case CRIT:
                break;
            case SOIN:
                break;
            case SOIN_STATUT:
                break;
            case BOOST_ATTAQUE:
                break;
            case BOOST_VITESSE:
                break;
            default:
                break;
        }
    }

    /*
     * Méthode permettant d'appliquer un statut sur le canard
     */
    public void appliquerEffetStatut(Statut statut) {
        if (this.statut == Statut.NEUTRE) {
            this.statut = statut;
        }
    }

    /*
     * Méthode permettant de retirer un statut du canard
     */
    public void retirerStatut() {
        this.statut = Statut.NEUTRE;
    }

    /*
     * Méthode permettant de savoir si le canard est KO
     * 
     * @return true si le canard est KO, false sinon
     */
    public boolean estKO() {
        return this.statsEnCombat.get(0) <= 0;
    }

    // ---------- METHODES ABSTRAITES ---------- //

    /*
     * Méthode abstraite permettant d'initialiser les statistiques du canard
     * en fonction de son type
     */
    protected abstract void initStats();

    /*
     * Méthode permettant de calculer les stats d'un canard après monté d'un niveau
     */
    protected abstract void calculerStats();

    /*
     * Méthode permettant d'utiliser la capacité spéciale du canard
     */
    public abstract void utiliserCapaciteSpeciale(Canard canardCible);

    public void afficherCapacite() {
        for (Capacite c : this.capacites) {
            System.out.println(c);
        }
        System.out.println(this.capaciteSpeciale);
    }

    @Override
    public String toString() {
        return this.nom + " (" + this.type + ") [ Niveau " + this.niveau + "] : " +
                "\n - PV : " + this.getPointsDeVie() +
                "\n - Attaque :" + this.getPointsAttaque() +
                "\n - Vitesse : " + this.getVitesse();
    }
}
