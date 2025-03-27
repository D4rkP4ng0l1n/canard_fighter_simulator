package combat;

import modele.Canard;

public class CapaciteSpecialeEau implements CapaciteSpeciale {

    @Override
    public void activer(Canard canardCible) {
        canardCible.soigner(20);
    }

}
