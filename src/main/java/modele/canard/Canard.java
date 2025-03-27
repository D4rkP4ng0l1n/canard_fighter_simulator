package modele.canard;

import java.util.ArrayList;
import java.util.List;

import combat.Statut;
import combat.capacite.Capacite;

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
    protected boolean capaciteSpeciale; // True si la capacité spéciale du canard est disponible

    // ---------- CONSTRUCTEURS ---------- //

    /*
     * Constructeur de base d'un canard
     */
    public Canard(String nom, TypeCanard type) {
        this.nom = nom;
        this.type = type;

        this.statut = Statut.NEUTRE;

        this.niveau = 1;
        this.totalExp = 0;
        this.totalExpPourProchainNiveau = 100;

        this.stats = new ArrayList<>(3);
        this.initStats();
        this.statsEnCombat = stats;
        this.energie = MAX_ENERGIE;

        this.capacites = new ArrayList<>(4);
        this.capaciteSpeciale = true;
    }

    /*
     * Constructeur d'un canard avec un niveau spécifié
     */
    public Canard(String nom, TypeCanard type, int niveau) {
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
        this.statsEnCombat = stats;
        this.energie = MAX_ENERGIE;

        this.capacites = new ArrayList<>(4);
        this.capaciteSpeciale = true;
    }

    /*
     * Constructeur d'un canard avec des statistiques spécifiées
     */
    public Canard(String nom, TypeCanard type, int pv, int attaque, int vitesse) {
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
        this.statsEnCombat = stats;
        this.energie = MAX_ENERGIE;

        this.capacites = new ArrayList<>(4);
        this.capaciteSpeciale = true;
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
    public int getPointsDeVie() {
        return this.stats.get(0);
    }

    /*
     * Retourne le niveau du canard
     */
    public int getPointsAttaque() {
        return this.stats.get(1);
    }

    public int getVitesse() {
        return this.stats.get(2);
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
        double degats = (this.getPointsAttaque() * 0.2) * capacite.getDegats()
                * TypeCanard.getMultiplicateur(capacite.getType(), autreCanard.getType());
        // Infliger les degats
        autreCanard.subirDegats((int) degats);
    }

    /*
     * Méthode permettant d'infliger des degats a un canard
     */
    public void subirDegats(int degats) {
        this.stats.set(0, this.stats.get(0) - degats);
        if (this.stats.get(0) < 0)
            this.stats.set(0, 0);
    }

    /*
     * Méthode permettant de savoir si le canard est KO
     * 
     * @return true si le canard est KO, false sinon
     */
    public boolean estKO() {
        return this.stats.get(0) <= 0;
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

    @Override
    public String toString() {
        return this.nom + " (" + this.type + ") [ Niveau " + this.niveau + "] : " +
                "\n - PV : " + this.getPointsDeVie() +
                "\n - Attaque :" + this.getPointsAttaque() +
                "\n - Vitesse : " + this.getVitesse();
    }
}
