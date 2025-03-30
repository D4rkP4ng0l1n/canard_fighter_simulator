package ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import combat.Combat;
import combat.capacite.Capacite;
import modele.canard.Canard;
import modele.canard.CanardEau;
import modele.canard.CanardFeu;
import modele.canard.CanardGlace;
import modele.canard.CanardVent;
import modele.canard.TypeCanard;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);
    private static Musique musique = new Musique();

    public static int menuPrincipal() {

        musique.playMusic(Musique.MUSIQUE_MENU);
        int codeRetour = -1;

        System.out.println(" ---------- MENU PRINCIPAL ---------- ");
        System.out.println(
                "0. Quitter\n1. Créer un Canard\n2. Afficher la liste de mes Canards\n3. Lancer un Combat\n4. Lancer le mode Canarogue");
        System.out.print(">>> ");
        String choix = scanner.nextLine();

        switch (choix) {
            case "0":
                codeRetour = 0;
                break;
            case "1":
                codeRetour = 1;
                break;
            case "2":
                codeRetour = 2;
                break;
            case "3":
                codeRetour = 3;
                break;
            case "4":
                codeRetour = 4;
            default:
                codeRetour = -1;
        }
        return codeRetour;
    }

    public static Canard creationCanard() {
        Canard canard = (Canard) null;

        System.out.println(" ---------- CREATION D'UN CANARD ---------- ");

        String nom = "";
        while (nom.isEmpty() || nom == null) {
            System.out.print("Quel est le nom du canard ? >>> ");
            nom = scanner.nextLine();
            System.out.print("\n");
        }

        TypeCanard typeCanard = null;
        while (typeCanard == null) {
            System.out.println("Quel est le type du canard ( Ecrire le numero correspondant au type ) ? ");
            System.out.println("1. Eau\n2. Feu\n3. Vent\n4. Glace");
            System.out.print(">>> ");
            String type = scanner.nextLine();
            switch (type) {
                case "1":
                    typeCanard = TypeCanard.EAU;
                    break;
                case "2":
                    typeCanard = TypeCanard.FEU;
                    break;
                case "3":
                    typeCanard = TypeCanard.VENT;
                    break;
                case "4":
                    typeCanard = TypeCanard.GLACE;
                    break;
                default:
                    System.out.println("Type de canard inconnu !");
            }
        }

        System.out.println("Création du Canard Terminée !");

        switch (typeCanard) {
            case EAU:
                canard = new CanardEau(nom);
                break;
            case FEU:
                canard = new CanardFeu(nom);
                break;
            case VENT:
                canard = new CanardVent(nom);
                break;
            case GLACE:
                canard = new CanardGlace(nom);
                break;
        }

        System.out.println("Votre Canard : \n" + canard.toString());

        return canard;
    }

    public static void afficherListCanards(List<Canard> canards) {
        System.out.println(" ---------- LISTE DE MES CANARDS ---------- ");
        for (int i = 0; i < canards.size(); i++) {
            System.out.println((i + 1) + " - " + canards.get(i));
        }
    }

    public static void menuCombat(List<Canard> canards) {
        System.out.println(" ---------- MENU COMBAT ---------- ");
        String choix = "null";

        while (!choix.equals("0") && !choix.equals("1") && !choix.equals("2")) {

            System.out.println("0. Retour au menu principal\n1. Combat 1 VS 1");
            if (canards.size() >= 3) {
                System.out.println("2. Combat 3 VS 3");
            }
            System.out.print(">>> ");

            choix = scanner.nextLine();
            List<Canard> canardsCombat = new ArrayList<>();

            switch (choix) {
                case "0":
                    break;
                case "1":
                    canardsCombat.add(selectionnerUnCanard(canards));
                    afficherCombat(canardsCombat, Musique.MUSIQUE_COMBAT);
                    break;
                case "2":
                    if (canards.size() < 3) {
                        System.out.println(choix = "Commande inconnue !");
                        break;
                    }
                    List<Canard> canardsAChoisir = new ArrayList<>(canards);
                    for (int i = 0; i < 3; i++) {
                        canardsCombat.add(selectionnerUnCanard(canardsAChoisir));
                        canardsAChoisir.remove(canardsCombat.get(i));
                    }
                    afficherListCanards(canardsCombat);
                    break;
                default:
                    System.out.println("Commande inconnue !");
                    break;
            }
        }

    }

    private static Canard selectionnerUnCanard(List<Canard> canards) {
        if (canards.size() == 1) {
            return canards.get(0); // Si un seul canard, on le sélectionne directement
        }

        System.out.println("Vous avez plusieurs Canards, merci d'en choisir un seul :");

        int indiceCanard = -1;
        while (indiceCanard < 0 || indiceCanard >= canards.size()) {
            Menu.afficherListCanards(canards);
            System.out.print(">>> ");
            String choixCanard1 = scanner.nextLine();

            try {
                int choix = Integer.parseInt(choixCanard1);
                if (choix >= 1 && choix <= canards.size()) {
                    indiceCanard = choix - 1;
                } else {
                    System.out.println("Merci de sélectionner un canard entre 1 et " + canards.size() + " !");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide, merci d'entrer un nombre !");
            }
        }

        return canards.get(indiceCanard);
    }

    private static Canard genererCanardAleatoire(int niveau) {
        Random random = new Random();
        TypeCanard[] types = TypeCanard.values();
        TypeCanard randomType = types[random.nextInt(types.length)];
        switch (randomType) {
            case EAU:
                return new CanardEau("CanardEau", niveau);
            case FEU:
                return new CanardFeu("CanardFeu", niveau);
            case GLACE:
                return new CanardGlace("CanardGlace", niveau);
            case VENT:
                return new CanardVent("CanardVent", niveau);
            default:
                throw new IllegalStateException("Type de canard inconnu : " + randomType);
        }
    }

    private static void afficherCombat(List<Canard> canards, String musiqueCombat) {
        musique.playMusic(musiqueCombat);

        System.out.println(" ---------- COMBAT ---------- ");
        musique.playMusic(Musique.MUSIQUE_COMBAT);

        Canard canardJoueur = canards.get(0);
        Canard canardAleatoire = genererCanardAleatoire(canardJoueur.getNiveau());
        Combat combat = new Combat(canards.get(0), canardAleatoire);

        // On set une liste pour savoir combien de capacites possede le canard du joueur
        Set<String> capacitesValides = new HashSet<>();
        capacitesValides.add("0");
        for (int i = 0; i < canardJoueur.getCapacites().size(); i++) {
            capacitesValides.add(String.valueOf(i + 1)); // Pour un affichage 1, 2, 3, 4
        }
        capacitesValides.add("5");

        String choix, choixCapacite;

        System.out.println(canardAleatoire.getNom() + " sauvage apparait !");
        while (!combat.combatTermine()) {

            afficherEtatCanard(canardAleatoire);
            afficherEtatCanard(canardJoueur);

            choix = "Attente";
            choixCapacite = "Attente";

            if (combat.getAttaquant() == canardAleatoire) {
                // Tour du canard adverse
                Capacite capaciteCanardAleatoire = canardAleatoire.selectionnerCapaciteAuHasard();
                if (capaciteCanardAleatoire.equals(null)) {
                    System.out.println(canardAleatoire.getNom() + " n'a pas assez d'énergie pour attaquer");
                } else {
                    combat.jouerPhase(capaciteCanardAleatoire);
                }
            } else {

                // Tour du canard du joueur
                System.out.println("Que doit faire " + canardJoueur.getNom() + " ?");

                while (!choix.equals("1")) {
                    System.out.println("1. Attaque");
                    System.out.print(">>> ");
                    choix = scanner.nextLine();

                    switch (choix) {
                        case "1":
                            // Le joueur choisi d'attaquer
                            Capacite capaciteJoueur = (Capacite) null;
                            while (!capacitesValides.contains(choixCapacite)) {
                                System.out.println("Sélectionner une capacité (" + canardJoueur.getEnergie() + " PE/"
                                        + Canard.MAX_ENERGIE + ")");
                                System.out.println("0. Retour");
                                canardJoueur.afficherCapacite();
                                System.out.print(">>> ");
                                choixCapacite = scanner.nextLine();

                                switch (choixCapacite) {
                                    case "0":
                                        break;
                                    case "1":
                                        capaciteJoueur = canardJoueur.getCapacites().get(0);
                                        break;
                                    case "2":
                                        if (canardJoueur.getCapacites().size() >= 2) {
                                            capaciteJoueur = canardJoueur.getCapacites().get(1);
                                        } else {
                                            System.out.println("Capacité non disponible");
                                        }
                                        break;
                                    case "3":
                                        if (canardJoueur.getCapacites().size() >= 3) {
                                            capaciteJoueur = canardJoueur.getCapacites().get(2);
                                        } else {
                                            System.out.println("Capacité non disponible");
                                        }
                                        break;
                                    case "4":
                                        if (canardJoueur.getCapacites().size() == 4) {
                                            capaciteJoueur = canardJoueur.getCapacites().get(3);
                                        } else {
                                            System.out.println("Capacité non disponible");
                                        }
                                        break;
                                    case "5":
                                        canardJoueur.utiliserCapaciteSpeciale(canardAleatoire);
                                        break;
                                    default:
                                        break;
                                }
                            }
                            if (!choixCapacite.equals("5"))
                                combat.jouerPhase(capaciteJoueur);
                            break;
                        default:
                            System.out.println("Commande inconnue !");
                            break;
                    }
                }
            }
            combat.changerAttaquant();
        }

        // La fin du combat
        afficherEtatCanard(canardAleatoire);
        afficherEtatCanard(canardJoueur);
        if (canardJoueur.getPointsDeVieCombat() <= 0) {
            System.out.println(canardJoueur.getNom() + " a perdu le combat !");
        } else if (canardAleatoire.getPointsDeVieCombat() <= 0) {
            System.out.println(canardAleatoire.getNom() + " a perdu le combat !");
            canardJoueur.gagnerExperience(canardAleatoire);
        }
        canardJoueur.setCapaciteSpecialeDisponible();
    }

    private static void afficherEtatCanard(Canard canard) {
        System.out.println(canard.getNom() + " (" + canard.getType() + ") [Niveau "
                + canard.getNiveau() + "] "
                + "[Energie : " + canard.getEnergie() + "/" + Canard.MAX_ENERGIE + "] - (PV : "
                + canard.getPointsDeVieCombat() + "/" + canard.getPointsDeVie() + ")");
    }
}
