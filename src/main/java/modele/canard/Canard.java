package modele.canard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import combat.Combat;
import combat.Statut;
import combat.capacite.Capacite;
import combat.capacite.Effet;
import combat.capaciteSpeciale.CapaciteSpeciale;
import ui.Menu;

/*
 * Classe abstraite représentant un canard
 */
public abstract class Canard {

    // ---------- ATTRIBUTS ---------- //

    public static final int NIVEAU_MAX = 100;
    public static final int MAX_ENERGIE = 30;
    public static final int CHANCE_COUP_CRITIQUE = 10; // 10 % de chance de faire un coup critique
    public static final float MULTIPLICATEUR_DEGATS_CRITIQUES = 1.5f;

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
    private int chanceCoupCritique;

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
        this.chanceCoupCritique = CHANCE_COUP_CRITIQUE;
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
        for (int i = 0; i < niveau; i++) {
            this.calculerStats(); // On simule la montée de niveau du canard
        }
        this.statsEnCombat = new ArrayList<>(stats);
        this.energie = MAX_ENERGIE;

        this.capacites = new ArrayList<>(4);
        this.capaciteSpeciale = capaciteSpeciale;
        this.capaciteSpecialeDisponible = true;
        this.chanceCoupCritique = CHANCE_COUP_CRITIQUE;
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
        this.chanceCoupCritique = CHANCE_COUP_CRITIQUE;
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
     * Retourne le niveau actuel du canard
     */
    public int getNiveau() {
        return this.niveau;
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

    /*
     * Set le statut du canard
     */
    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    /*
     * Set la capacite speciale a disponible
     */
    public void setCapaciteSpecialeDisponible() {
        this.capaciteSpecialeDisponible = true;
    }

    /*
     * Ajouter une capacite a la liste des capacites
     */
    public void ajouterCapacite(Capacite capacite) {
        this.capacites.add(capacite);
    }

    /*
     * Remplace la capacite a la position "position"
     */
    public void setCapacite(Capacite capacite, int position) {
        this.capacites.set(position, capacite);
    }

    // ---------- METHODES ---------- //

    /*
     * Methodes permettant a un canard d'attaquer un autre canard
     */
    public void attaquer(Canard autreCanard, Capacite capacite) {
        if (this.statut == Statut.GEL) {
            System.out.println(this.nom + " est gelé...");
            Random random = new Random();
            if (random.nextInt(0, 2) != 0) {
                System.out.println("Il n'a pas pu attaqué");
                return;
            }
            System.out.println("Il a réussi a attaquer !");
        }
        if (this.energie < capacite.getCout()) {
            System.out.println(this.nom + " n'a pas assez d'energie pour lancer " + capacite.getNom());
            return;
        }
        // Calcul des dégâts
        double degats = (this.getPointsAttaque() * Combat.COEFF_EQUILIBRAGE) * capacite.getDegats()
                * TypeCanard.getMultiplicateur(capacite.getType(), autreCanard.getType());

        // Calcul du crit
        Random crit = new Random();
        boolean critique = (this.chanceCoupCritique == 0) || (crit.nextInt(this.chanceCoupCritique) == 0);
        if (critique) {
            degats *= 1.5;
            System.out.println("Coup critique ! ");
            this.chanceCoupCritique = CHANCE_COUP_CRITIQUE; // Dans le cas où le canard aurait reçu un boost
        }
        this.energie -= capacite.getCout();
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
     * Initialise les statistiques du Canard pour un combat
     */
    public void initialiserStatistiquesCombat() {
        this.statsEnCombat = new ArrayList<>(stats);
        this.energie = MAX_ENERGIE;
    }

    /*
     * Régénère l'énergie du canard
     */
    public void regenererEnergie(int energie) {
        this.energie += energie;
        if (this.energie > MAX_ENERGIE)
            this.energie = MAX_ENERGIE;
        System.out.println(this.nom + " possède " + this.energie + " PE");
    }

    /*
     * Applique un effet sur le canard
     */
    public void appliquerEffet(Effet effet, double montantEffet) {
        switch (effet) {
            case CRIT:
                System.out.println(
                        this.nom + " voit ses chances de coup critique atteindre " + (100 - montantEffet) + "%");
                this.chanceCoupCritique = (int) montantEffet;
                break;
            case SOIN:
                System.out.println(this.nom + " se soigne de " + montantEffet + " point de vie");
                this.soigner((int) montantEffet);
                break;
            case SOIN_STATUT:
                if (this.statut != Statut.NEUTRE) {
                    System.out.println(this.nom + " est guerri du statut " + this.statut);
                    this.retirerStatut();
                }
                break;
            case BOOST_ATTAQUE:
                System.out.println(this.nom + " voit son attaque augmenter !");
                this.statsEnCombat.set(1, (int) (this.statsEnCombat.get(1) * montantEffet));
                break;
            case BOOST_VITESSE:
                System.out.println(this.nom + " voit sa vitesse augmenter !");
                this.statsEnCombat.set(2, (int) (this.statsEnCombat.get(2) * montantEffet));
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
            System.out.println(this.nom + " subit " + statut);
        }
    }

    /*
     * Méthode permettant de retirer un statut du canard
     */
    public void retirerStatut() {
        this.statut = Statut.NEUTRE;
    }

    public Capacite selectionnerCapaciteAuHasard() {
        Random random = new Random();
        List<Capacite> capacitesDisponibles = new ArrayList<>();
        for (Capacite c : this.capacites) {
            if (c.getCout() <= this.energie)
                capacitesDisponibles.add(c);
        }
        if (capacitesDisponibles.isEmpty())
            return null;

        return capacitesDisponibles.get(random.nextInt(capacitesDisponibles.size()));
    }

    /*
     * Méthode permettant de calculer l'expérience gagner en fin de combat
     */
    public void gagnerExperience(Canard canardVaincu) {
        System.out.println(this.nom + " a gagné " + canardVaincu.getNiveau() * 100 + " Points d'expérience !");
        this.totalExp += canardVaincu.getNiveau() * 100;

        while (this.totalExp >= this.totalExpPourProchainNiveau) {
            this.totalExpPourProchainNiveau = (this.niveau + 1) * 100;
            this.monterNiveau();
        }
    }

    /*
     * Méthode permettant de donner de l'expérience a un canard
     */
    public void gagnerExperience(int exp) {
        System.out.println(this.nom + " a gagné " + exp + " Points d'expérience !");
        this.totalExp += exp;

        while (this.totalExp >= this.totalExpPourProchainNiveau) {
            this.totalExpPourProchainNiveau = (this.niveau + 1) * 100;
            this.monterNiveau();
        }
    }

    /*
     * Méthode permettant au canard de monté d'un niveau lorsqu'il a assez
     * d'expérience
     */
    private void monterNiveau() {
        if (this.niveau < NIVEAU_MAX) {
            this.niveau++;
            System.out.println(this.nom + " passe au niveau " + this.niveau + " !");
            System.out.println(
                    this.nom + " a besoin de " + this.totalExpPourProchainNiveau
                            + " exp pour passer au prochain niveau !");
            this.calculerStats();
            this.verifierNouvelleCapacite();
        } else {
            System.out.println(this.nom + " est au niveau maximum");
            this.totalExpPourProchainNiveau = 999999999;
        }
    }

    /*
     * Méthode pour permettre au canard de voir si il peut apprendre une nouvelle
     * attaque
     */
    private void verifierNouvelleCapacite() {
        for (Capacite capacite : Capacite.values()) {
            if (capacite.getType() == this.type && capacite.getNiveauApprentissage() == this.niveau) {
                Menu.apprendreCapacite(this, capacite);
            }
        }
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

    // ---------- METHODES D'AFFICHAGE ---------- //

    public void afficherCapacite() {
        List<Capacite> capacitesDisponibles = new ArrayList<>();
        for (Capacite c : this.capacites) {
            if (c.getCout() <= this.energie)
                capacitesDisponibles.add(c);
        }
        for (int i = 0; i < capacitesDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + capacitesDisponibles.get(i));
        }
        if (capaciteSpecialeDisponible())
            System.out.println("5. " + this.capaciteSpeciale);
    }

    @Override
    public String toString() {
        String canard = this.nom + " (" + this.type + ") [Niveau " + this.niveau + "] "
                + "[Energie : " + this.energie + "/" + MAX_ENERGIE + "] : " +
                "\n - PV : " + this.getPointsDeVie() +
                "\n - Attaque : " + this.getPointsAttaque() +
                "\n - Vitesse : " + this.getVitesse() +
                "\nListe des capacités : ";
        for (Capacite c : this.capacites) {
            canard += "\n - " + c;
        }
        canard += "\n";
        return canard;
    }
}
