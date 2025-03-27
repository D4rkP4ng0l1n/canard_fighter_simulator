package combat.capaciteSpeciale;

import combat.Statut;
import modele.Canard;
import modele.TypeCanard;

public class CapaciteSpecialeFeu implements CapaciteSpeciale {

    @Override
    public void activer(Canard canardCible) {
        canardCible.subirDegats((int) (20 * TypeCanard.getMultiplicateur(TypeCanard.FEU, canardCible.getType())));
        canardCible.setStatut(Statut.BRULURE);
    }

}
