package modele.canard;

public enum TypeCanard {

    EAU, FEU, GLACE, VENT;

    // Modifier ça pour faire littéralement la table des types ( Matrice )
    public static double getMultiplicateur(TypeCanard attaquant, TypeCanard cible) {
        switch (attaquant) {
            case EAU:
                if (cible == FEU)
                    return 2;
                break;
            case FEU:
                if (cible == GLACE)
                    return 2;
                break;
            case GLACE:
                if (cible == VENT)
                    return 2;
                break;
            case VENT:
                if (cible == EAU)
                    return 2;
                break;
        }
        return 1;
    }

}
