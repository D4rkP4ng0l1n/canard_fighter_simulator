package combat.capacite;

import modele.canard.TypeCanard;

public enum Capacite {

    // Capacités pour les tests
    FEU_TEST("Feu test", TypeCanard.FEU, 0, 10, 100),
    EAU_TEST("Eau test", TypeCanard.EAU, 0, 10, 100),
    GLACE_TEST("Glace test", TypeCanard.GLACE, 0, 10, 100),
    VENT_TEST("Vent test", TypeCanard.VENT, 0, 10, 100),

    // Capacités de type FEU
    BRAISE_CANARD("Braise Canard", TypeCanard.FEU, 3, 25, 100), // Starter
    BEC_FLAMME("Bec Flamme", TypeCanard.FEU, 5, 40, 90),
    PLUMES_INCANDESCENTES("Plumes Incandescentes", TypeCanard.FEU, 8, 60, 85),
    EXPLOSION_PLUMES("Explosion de Plumes", TypeCanard.FEU, 12, 90, 70),
    CHALEUR_ETOUFFANTE("Chaleur Étouffante", TypeCanard.FEU, 7, 55, 80),
    FEU_FOLLET("Feu Follet", TypeCanard.FEU, 4, 35, 95),

    // Capacités de type EAU
    GOUTTELETTE("Gouttelette", TypeCanard.EAU, 2, 20, 100), // Starter
    ONDE_CANARDEQUE("Onde Canardeque", TypeCanard.EAU, 4, 35, 95),
    TSUNAMI_PLUMES("Tsunami de Plumes", TypeCanard.EAU, 10, 70, 80),
    VAGUE_ECRASANTE("Vague Écrasante", TypeCanard.EAU, 11, 85, 75),
    TOURBILLON_CANARD("Tourbillon Canard", TypeCanard.EAU, 6, 50, 90),
    PLUIE_GLISSANTE("Pluie Glissante", TypeCanard.EAU, 5, 40, 95),

    // Capacités de type GLACE
    GIVRE_CANARD("Givre Canard", TypeCanard.GLACE, 3, 25, 100), // Starter
    BEC_GIVRE("Bec Givré", TypeCanard.GLACE, 6, 45, 90),
    TEMPETE_NEIGEUSE("Tempête Neigeuse", TypeCanard.GLACE, 9, 65, 75),
    AVALANCHE_PLUMES("Avalanche de Plumes", TypeCanard.GLACE, 12, 95, 65),
    GLACE_TRANCHANTE("Glace Tranchante", TypeCanard.GLACE, 7, 55, 85),
    CRISTAUX_GELANTS("Cristaux Gelants", TypeCanard.GLACE, 5, 40, 90),

    // Capacités de type VENT
    SOUFFLE_LEGER("Souffle Léger", TypeCanard.VENT, 2, 20, 100), // Starter
    RAFALBEC("Rafalbec", TypeCanard.VENT, 4, 30, 100),
    OURAPLUME("Ouraplume", TypeCanard.VENT, 7, 55, 85),
    TORNAPLUME("Tornaplume", TypeCanard.VENT, 11, 80, 75),
    VENT_VIF("Vent Vif", TypeCanard.VENT, 6, 50, 90),
    OURAGAN_CANARD("Ouragan Canard", TypeCanard.VENT, 9, 70, 80);

    private final String nom;
    private final TypeCanard type;
    private final int cout;
    private final int degats;
    private final int precision;

    Capacite(String nom, TypeCanard type, int cout, int degats, int precision) {
        this.nom = nom;
        this.type = type;
        this.cout = cout;
        this.degats = degats;
        this.precision = precision;
    }

    public String getNom() {
        return nom;
    }

    public TypeCanard getType() {
        return type;
    }

    public int getCout() {
        return cout;
    }

    public int getDegats() {
        return degats;
    }

    public int getPrecision() {
        return precision;
    }

    @Override
    public String toString() {
        return this.nom + "(" + this.type + ", " + this.degats + ", " + this.precision + ", (" + this.cout + ")";
    }
}
