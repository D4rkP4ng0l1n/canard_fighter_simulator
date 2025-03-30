package combat;

import java.util.Random;

import combat.capacite.Capacite;
import modele.canard.Canard;
import modele.canard.TypeCanard;

public class Combat {

    public static final float COEFF_EQUILIBRAGE = 0.03f; // Coefficient d'équilibrage pour les dégâts infligés
    public static final int REGEN_ENERGIE_FIN_DE_TOUR = 5; // A chaque fin de tour, les canards régénèrent 5 PE

    private Canard attaquant, cible;
    private int tour; // Tour actuel du combat ( Un tour = une attaque )

    // ---------- CONSTRUCTEURS ---------- //

    /*
     * Constructeur de la classe Combat. Il prend en paramètre deux objets Canard et
     * attribut à chacun leur rôle pour le premier tour. Le canard le plus rapide
     * sera le premier attaquant. Si leur vitesse est la même, le choix du premier
     * attaquant sera fait au hasard.
     */
    public Combat(Canard canard1, Canard canard2) {
        canard1.initialiserStatistiquesCombat();
        canard2.initialiserStatistiquesCombat();
        if (canard1.getVitesse() > canard2.getVitesse()) {
            this.attaquant = canard1;
            this.cible = canard2;
        } else if (canard1.getVitesse() == canard2.getVitesse()) {
            Random random = new Random();
            if (random.nextInt(2) == 0) {
                this.attaquant = canard1;
                this.cible = canard2;
            } else {
                this.attaquant = canard2;
                this.cible = canard1;
            }
        } else {
            this.attaquant = canard2;
            this.cible = canard1;
        }
    }

    // ---------- GETTERS ---------- //

    /*
     * Retourne l'attaquant du combat
     */
    public Canard getAttaquant() {
        return this.attaquant;
    }

    /*
     * Retourne le canard cible du combat
     */
    public Canard getCible() {
        return this.cible;
    }

    /*
     * Retourne le tour actuel du combat
     */
    public int getTour() {
        return this.tour;
    }

    // ---------- METHODES ---------- //

    /*
     * Change l'attaquant du combat. Le canard qui était attaquant devient le canard
     * attaqué et vice versa.
     */
    public void changerAttaquant() {
        Canard temp = this.attaquant;
        this.attaquant = this.cible;
        this.cible = temp;
    }

    /*
     * Joue une phase de combat
     */
    public void jouerPhase(Capacite capacite) {
        System.out.print(this.attaquant.getNom() + " utilise " + capacite.getNom() + " !");
        if (TypeCanard.getMultiplicateur(this.attaquant.getType(), this.cible.getType()) == 1.5) {
            System.out.print(" C'est super efficace !");
        } else if (TypeCanard.getMultiplicateur(this.attaquant.getType(), this.cible.getType()) == 0.5) {
            System.out.print(" Ce n'est pas très efficace...");
        }
        System.out.print("\n");
        this.attaquant.attaquer(this.cible, capacite);
        this.tour++;
    }

    /*
     * Applique tous les effets de STATUT de fin de tour et incrémente le tour de 1
     */
    public void finDeTour() {
        effetFinDeTour(attaquant);
        effetFinDeTour(cible);
        attaquant.regenererEnergie(REGEN_ENERGIE_FIN_DE_TOUR);
        cible.regenererEnergie(REGEN_ENERGIE_FIN_DE_TOUR);
    }

    private void effetFinDeTour(Canard canard) {
        switch (attaquant.getStatut()) {
            case BRULURE:
                System.out.println(canard.getNom() + " souffre de sa brûlure");
                canard.subirDegats((int) (canard.getPointsDeVieCombat() * (1 - Statut.DEGATS_BRULURE)));
                break;
            case EMPOISONNEMENT:
                System.out.println(canard.getNom() + " souffre du poison");
                canard.subirDegats((int) (canard.getPointsDeVieCombat() * (1 - Statut.DEGATS_POISON)));
                break;
            default:
                break;
        }
    }

    /*
     * Retourne true si la cible est KO
     */
    public boolean combatTermine() {
        return this.cible.estKO() || this.attaquant.estKO();
    }

}
