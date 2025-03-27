package combat.capaciteSpeciale;

import modele.canard.Canard;

public class CapaciteSpecialeEau implements CapaciteSpeciale {

    @Override
    public void activer(Canard canardCible) {
        canardCible.soigner(20);
    }

}
