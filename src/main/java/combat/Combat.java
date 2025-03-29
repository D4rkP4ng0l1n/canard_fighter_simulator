package combat;

import java.util.Random;

import combat.capacite.Capacite;
import modele.canard.Canard;

public class Combat {

    public static final float COEFF_EQUILIBRAGE = 0.1f; // Coefficient d'équilibrage pour les dégâts infligés

    private Canard attaquant, cible;
    private int tour; // Tour actuel du combat

    // ---------- CONSTRUCTEURS ---------- //

    /*
     * Constructeur de la classe Combat. Il prend en paramètre deux objets Canard et
     * attribut à chacun leur rôle pour le premier tour. Le canard le plus rapide
     * sera le premier attaquant. Si leur vitesse est la même, le choix du premier
     * attaquant sera fait au hasard.
     */
    public Combat(Canard canard1, Canard canard2) {
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

    // ---------- SETTERS ---------- //

    /*
     * Set le canard attaquant du combat
     */
    private void setAttaquant(Canard canard) {
        this.attaquant = canard;
    }

    /*
     * Set le canard cible du combat
     */
    private void setCible(Canard canard) {
        this.cible = canard;
    }

    // ---------- METHODES ---------- //

    /*
     * Change le tour du combat. Le canard qui était attaquant devient le canard
     * attaqué et vice versa. Le tour est incrémenté de 1.
     */
    private void changerTour() {
        Canard temp = this.attaquant;
        this.attaquant = this.cible;
        this.cible = temp;
        this.tour++;
    }

    /*
     * Joue un tour de combat
     */
    public void jouerTour(Capacite capacite) {
        this.attaquant.attaquer(this.cible, capacite);
        if (this.cible.estKO()) {
            System.out.println(this.cible + " a été mis KO !");
        } else {
            this.changerTour();
        }
    }

}
