package combat.capaciteSpeciale;

import modele.Canard;

public class CapaciteSpecialeVent implements CapaciteSpeciale {

    @Override
    public void activer(Canard canardCible) {
        canardCible.subirDegats(40);
    }
}
