package combat;

import modele.Canard;

public class CapaciteSpecialeGlace implements CapaciteSpeciale {

    @Override
    public void activer(Canard canardCible) {
        canardCible.subirDegats(20);
        canardCible.setStatut(Statut.GEL);
    }

}
