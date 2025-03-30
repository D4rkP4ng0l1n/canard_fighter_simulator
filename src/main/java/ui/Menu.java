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
                "0. Quitter\n1. Créer un Canard\n2. Afficher la liste de mes Canards\n3. Ajouter de l'exp\n4. Lancer un Combat\n5. Lancer le mode Canarogue");
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
                break;
            case "5":
                codeRetour = 5;
                break;
            default:
                codeRetour = -1;
                break;
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

    public static void ajouterExperience(List<Canard> canards) {
        System.out.println(" ---------- AJOUTER EXPERIENCE ---------- ");

        if (canards.isEmpty()) {
            System.out.println("Vous n'avez aucun canard !");
            return;
        }

        Canard canard = selectionnerUnCanard(canards);

        int expAjoutee = -1;
        while (expAjoutee < 0) {
            System.out.print("Combien d'expérience souhaitez-vous ajouter à " + canard.getNom() + " ?\n>>> ");
            String input = scanner.nextLine();
            try {
                expAjoutee = Integer.parseInt(input);
                if (expAjoutee < 0) {
                    System.out.println("L'expérience ne peut pas être négative !");
                }
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide.");
            }
        }

        canard.gagnerExperience(expAjoutee);
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
                    menuCombat3v3(canardsCombat, Musique.MUSIQUE_COMBAT_DRESSEUR);
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

    private static void menuCombat3v3(List<Canard> canards, String musiqueCombat) {
        musique.playMusic(musiqueCombat);

        System.out.println(" ---------- COMBAT 3V3 ---------- ");

        List<Canard> equipeJoueur = new ArrayList<>(canards);
        List<Canard> equipeAdverse = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            equipeAdverse.add(genererCanardAleatoire(canards.get(0).getNiveau()));
        }

        System.out.println("Votre équipe :");
        afficherListCanards(equipeJoueur);

        System.out.println("Équipe adverse :");
        afficherListCanards(equipeAdverse);

        int indexJoueur = 0;
        int indexAdverse = 0;

        while (!equipeJoueur.isEmpty() && !equipeAdverse.isEmpty()) {
            Canard canardJoueur = equipeJoueur.get(indexJoueur);
            Canard canardAdverse = equipeAdverse.get(indexAdverse);

            System.out.println("Début du combat entre " + canardJoueur.getNom() + " et " + canardAdverse.getNom());

            Combat combat = new Combat(canardJoueur, canardAdverse);
            while (!combat.combatTermine()) {
                afficherEtatCanard(canardJoueur);
                afficherEtatCanard(canardAdverse);

                System.out.println("1. Attaquer\n2. Changer de Canard");
                System.out.print(">>> ");
                String choix = scanner.nextLine();

                if (choix.equals("2")) {
                    System.out.println("Choisissez un nouveau canard :");
                    equipeJoueur.remove(canardJoueur);
                    canardJoueur = selectionnerUnCanard(equipeJoueur);
                    equipeJoueur.add(0, canardJoueur);
                    System.out.println(canardJoueur.getNom() + " entre en combat !");
                    continue;
                }

                Set<String> capacitesValides = new HashSet<>();
                capacitesValides.add("0");
                for (int i = 0; i < canardJoueur.getCapacites().size(); i++) {
                    capacitesValides.add(String.valueOf(i + 1)); // Pour un affichage 1, 2, 3, 4
                }
                capacitesValides.add("5");
                String choixCapacite = "-1";
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
                            canardJoueur.utiliserCapaciteSpeciale(canardAdverse);
                            break;
                        default:
                            break;
                    }
                }
                if (!choixCapacite.equals("5"))
                    combat.jouerPhase(capaciteJoueur);
                combat.changerAttaquant();
            }

            if (canardJoueur.getPointsDeVieCombat() <= 0) {
                System.out.println(canardJoueur.getNom() + " est KO !");
                equipeJoueur.remove(canardJoueur);
                if (!equipeJoueur.isEmpty()) {
                    indexJoueur = 0;
                }
            }
            if (canardAdverse.getPointsDeVieCombat() <= 0) {
                System.out.println(canardAdverse.getNom() + " est KO !");
                equipeAdverse.remove(canardAdverse);
                if (!equipeAdverse.isEmpty()) {
                    indexAdverse = 0;
                }
            }
        }

        if (equipeJoueur.isEmpty()) {
            System.out.println("Vous avez perdu le combat 3v3 !");
        } else {
            System.out.println("Félicitations ! Vous avez gagné le combat 3v3 !");
        }
    }

    private static void afficherEtatCanard(Canard canard) {
        System.out.println(canard.getNom() + " (" + canard.getType() + ") [Niveau "
                + canard.getNiveau() + "] "
                + "[Energie : " + canard.getEnergie() + "/" + Canard.MAX_ENERGIE + "] - (PV : "
                + canard.getPointsDeVieCombat() + "/" + canard.getPointsDeVie() + ")");
    }

    /*
     * Permet au canard d'apprendre une capacité. Si il possède déjà 4 capacités
     * alors l'utilisateur devra en choisir une à supprimer
     */
    public static void apprendreCapacite(Canard canard, Capacite capacite) {
        if (canard.getCapacites().size() < 4) {
            System.out.println(canard.getNom() + " a appris " + capacite.getNom() + " !");
            canard.ajouterCapacite(capacite);
        } else {

            System.out.println(canard.getNom() + " souhaite apprendre " + capacite);
            String oublie = "-1";

            Set<String> valeursValides = new HashSet<>();
            for (int i = 0; i < canard.getCapacites().size() + 1; i++) {
                valeursValides.add(String.valueOf(i + 1)); // Pour un affichage 1, 2, 3, 4
            }

            while (!valeursValides.contains(oublie)) { // Tant que l'utilisateur n'a pas choisi une valeur valide
                System.out.println(
                        canard.getNom()
                                + " connait déjà 4 capacités, il doit en oublier une pour apprendre une nouvelle." +
                                "\nLaquelle doit-il oublier ?");
                for (int i = 0; i < canard.getCapacites().size(); i++) {
                    System.out.println((i + 1) + ". " + canard.getCapacites().get(i));
                }
                System.out.println("5. " + capacite);
                System.out.print(">>>");
                oublie = scanner.nextLine();
                switch (oublie) {
                    case "1":
                        afficherCapaciteOublie(canard, capacite, 0);
                        break;
                    case "2":
                        afficherCapaciteOublie(canard, capacite, 1);
                        break;
                    case "3":
                        afficherCapaciteOublie(canard, capacite, 2);
                        break;
                    case "4":
                        afficherCapaciteOublie(canard, capacite, 3);
                        break;
                    case "5":
                        System.out.println(canard.getNom() + " n'apprend pas " + capacite.getNom());
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static void afficherCapaciteOublie(Canard canard, Capacite capacite, int position) {
        System.out.println(canard.getNom() + " oublie " + canard.getCapacites().get(position).getNom() + " et apprend "
                + capacite.getNom());
        canard.setCapacite(capacite, position);
    }
}
