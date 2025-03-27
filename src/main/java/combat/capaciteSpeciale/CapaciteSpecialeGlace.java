package combat.capaciteSpeciale;

import combat.Statut;
import modele.Canard;
import modele.TypeCanard;

public class CapaciteSpecialeGlace implements CapaciteSpeciale {

    @Override
    public void activer(Canard canardCible) {
        canardCible.subirDegats((int) (20 * TypeCanard.getMultiplicateur(TypeCanard.GLACE, canardCible.getType())));
        canardCible.setStatut(Statut.GEL);
    }

}
