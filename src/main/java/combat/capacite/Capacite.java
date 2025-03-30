package combat.capacite;

import modele.canard.TypeCanard;

public enum Capacite {

    // Capacités pour les tests
    FEU_TEST("Feu test", TypeCanard.FEU, 0, 0, 10, 100),
    EAU_TEST("Eau test", TypeCanard.EAU, 0, 0, 10, 100),
    GLACE_TEST("Glace test", TypeCanard.GLACE, 0, 0, 10, 100),
    VENT_TEST("Vent test", TypeCanard.VENT, 0, 0, 10, 100),

    // Capacités de type FEU
    BRAISE_CANARD("Braise Canard", TypeCanard.FEU, 0, 3, 3, 100), // Starter
    BEC_FLAMME("Bec Flamme", TypeCanard.FEU, 10, 5, 4, 90),
    PLUMES_INCANDESCENTES("Plumes Incandescentes", TypeCanard.FEU, 8, 20, 6, 85),
    EXPLOSION_PLUMES("Explosion de Plumes", TypeCanard.FEU, 30, 12, 9, 70),
    CHALEUR_ETOUFFANTE("Chaleur Étouffante", TypeCanard.FEU, 40, 7, 5, 80),
    FEU_FOLLET("Feu Follet", TypeCanard.FEU, 50, 4, 3, 95),

    // Capacités de type EAU
    GOUTTELETTE("Gouttelette", TypeCanard.EAU, 0, 2, 2, 100), // Starter
    ONDE_CANARDEQUE("Onde Canardeque", TypeCanard.EAU, 10, 4, 4, 95),
    TSUNAMI_PLUMES("Tsunami de Plumes", TypeCanard.EAU, 20, 10, 7, 80),
    VAGUE_ECRASANTE("Vague Écrasante", TypeCanard.EAU, 30, 11, 8, 75),
    TOURBILLON_CANARD("Tourbillon Canard", TypeCanard.EAU, 40, 6, 5, 90),
    PLUIE_GLISSANTE("Pluie Glissante", TypeCanard.EAU, 50, 5, 4, 95),

    // Capacités de type GLACE
    GIVRE_CANARD("Givre Canard", TypeCanard.GLACE, 0, 3, 2, 100), // Starter
    BEC_GIVRE("Bec Givré", TypeCanard.GLACE, 10, 6, 4, 90),
    TEMPETE_NEIGEUSE("Tempête Neigeuse", TypeCanard.GLACE, 20, 9, 6, 75),
    AVALANCHE_PLUMES("Avalanche de Plumes", TypeCanard.GLACE, 30, 12, 9, 65),
    GLACE_TRANCHANTE("Glace Tranchante", TypeCanard.GLACE, 40, 7, 5, 85),
    CRISTAUX_GELANTS("Cristaux Gelants", TypeCanard.GLACE, 50, 5, 4, 90),

    // Capacités de type VENT
    SOUFFLE_LEGER("Souffle Léger", TypeCanard.VENT, 0, 2, 3, 100), // Starter
    RAFALBEC("Rafalbec", TypeCanard.VENT, 10, 4, 3, 100),
    OURAPLUME("Ouraplume", TypeCanard.VENT, 20, 7, 5, 85),
    TORNAPLUME("Tornaplume", TypeCanard.VENT, 30, 11, 8, 75),
    VENT_VIF("Vent Vif", TypeCanard.VENT, 40, 6, 5, 90),
    OURAGAN_CANARD("Ouragan Canard", TypeCanard.VENT, 50, 9, 7, 80);

    private final String nom;
    private final TypeCanard type;
    private final int niveauApprentissage;
    private final int cout;
    private final int degats;
    private final int precision;
    private final Effet effet;

    Capacite(String nom, TypeCanard type, int niveauApprentissage, int cout, int degats, int precision) {
        this.nom = nom;
        this.type = type;
        this.niveauApprentissage = niveauApprentissage;
        this.cout = cout;
        this.degats = degats;
        this.precision = precision;
        this.effet = null;
    }

    Capacite(String nom, TypeCanard type, int niveauApprentissage, int cout, int degats, int precision, Effet effet) {
        this.nom = nom;
        this.type = type;
        this.niveauApprentissage = niveauApprentissage;
        this.cout = cout;
        this.degats = degats;
        this.precision = precision;
        this.effet = effet;
    }

    public String getNom() {
        return nom;
    }

    public TypeCanard getType() {
        return type;
    }

    public int getNiveauApprentissage() {
        return niveauApprentissage;
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

    public Effet getEffet() {
        return effet;
    }

    @Override
    public String toString() {
        return this.nom + " (" + this.type + ") [" + this.degats + " Dégâts] - [Précision : " + this.precision
                + "%] (Cout : " + this.cout + " PE)";
    }
}
