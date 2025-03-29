package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modele.canard.Canard;
import modele.canard.CanardEau;
import modele.canard.CanardFeu;
import modele.canard.CanardGlace;
import modele.canard.CanardVent;
import modele.canard.TypeCanard;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);

    public static int menuPrincipal() {

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

            switch (choix) {
                case "0":
                    break;
                case "1":
                    Canard canardCombat = selectionnerUnCanard(canards);
                    System.out.println(canardCombat);
                    break;
                case "2":
                    if (canards.size() < 3) {
                        System.out.println(choix = "Commande inconnue !");
                        break;
                    }
                    List<Canard> canardsAChoisir = new ArrayList<>(canards);
                    List<Canard> canardsCombat = new ArrayList<>(3);
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

    private void afficherCombat(List<Canard> canards) {

    }
}
