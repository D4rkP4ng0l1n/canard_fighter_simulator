package modele.canard;

/**
 * Enumération représentant les différents types de canards dans le simulateur.
 * Chaque type de canard a des interactions spécifiques avec les autres types,
 * définies par une table des multiplicateurs.
 */
public enum TypeCanard {

    EAU, FEU, GLACE, VENT, TOXIQUE, ELECTRIQUE, SOL;

    public static final int NB_TYPE = 4; // Nombre de Types

    /**
     * Table des multiplicateurs entre les différents types de canards.
     * Les lignes représentent le type attaquant, et les colonnes le type cible.
     * Par exemple, {@code tableDesTypes[EAU.ordinal()][FEU.ordinal()]} donne
     * le multiplicateur pour une attaque de type EAU contre un type FEU.
     */
    private static final double[][] tableDesTypes = {
            // EAU FEU GLACE VENT TOXIQUE ELECTRIQUE SOL
            { 1.0, 1.5, 0.5, 0.5, 1.0, 1.0, 1.5 }, // EAU
            { 0.5, 1.0, 1.5, 1.0, 1.5, 1.0, 0.5 }, // FEU
            { 1.0, 0.5, 1.0, 1.5, 0.5, 1.0, 1.5 }, // GLACE
            { 1.5, 1.0, 0.5, 1.0, 1.5, 1.0, 0.5 }, // VENT
            { 1.5, 0.5, 1.5, 1.0, 1.0, 1.0, 0.5 }, // TOXIQUE
            { 1.5, 1.0, 1.0, 1.5, 1.0, 1.0, 0.0 }, // ELECTRIQUE
            { 0.5, 1.5, 0.5, 0.0, 1.5, 1.5, 1.0 } // SOL
    };

    /**
     * Calcule le multiplicateur de dégâts en fonction des types de l'attaquant
     * et de la cible, selon la table des types.
     *
     * @param attaquant Le type de canard qui attaque.
     * @param cible     Le type de canard ciblé.
     * @return Le multiplicateur de dégâts correspondant.
     */
    public static double getMultiplicateur(TypeCanard attaquant, TypeCanard cible) {
        return tableDesTypes[attaquant.ordinal()][cible.ordinal()];
    }

}
