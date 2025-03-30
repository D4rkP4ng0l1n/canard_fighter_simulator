package combat.capaciteSpeciale;

import combat.Statut;
import modele.canard.Canard;

public class CapaciteSpecialeElectrique implements CapaciteSpeciale {

    // Capacité Spéciale de type ELECTRIQUE : Paralyse la cible
    // Fait en sorte que la prochaine attaque du lanceur soit super efficace
    // ( Statut Surcharge )

    @Override
    public void activer(Canard canardActif, Canard canardCible) {
        System.out.println((canardActif.getNom() + " UTILISE SA CAPACITE SPECIALE !"));
        if (canardCible.getStatut() != Statut.PARALYSIE) {
            canardCible.retirerStatut();
            canardCible.appliquerEffetStatut(Statut.SURCHARGE);
        }
        if (canardActif.getStatut() != Statut.SURCHARGE) {
            canardActif.retirerStatut();
            canardActif.appliquerEffetStatut(Statut.SURCHARGE);
        }
    }

    @Override
    public String toString() {
        return "Capacité Spéciale Electrique : Paralyse le canard Cible et la prochaine attaque sera super efficace.";
    }

}
