package ui;

import java.util.Scanner;

import modele.Canard;
import modele.CanardEau;
import modele.CanardFeu;
import modele.CanardGlace;
import modele.CanardVent;
import modele.TypeCanard;

public class Menu {

    public static Canard creationCanard() {
        Scanner scanner = new Scanner(System.in);

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

        System.out.println("Génération des points de vie du canard...");
        // Les points de vies sont générés par rapport aux stats d'un Psykokwak lv 50
        int pv = (int) ((Math.random() * 32) + 125);
        int pa = 100;
        System.out.println("Votre Canard possède " + pv + " points de vie et " + pa + " points d'attaque.\n");

        System.out.println("Création du Canard Terminée !");
        scanner.close();

        switch (typeCanard) {
            case EAU:
                return new CanardEau(nom, pv, pa);
            case FEU:
                return new CanardFeu(nom, pv, pa);
            case VENT:
                return new CanardVent(nom, pv, pa);
            case GLACE:
                return new CanardGlace(nom, pv, pa);
        }

        return (Canard) null;

    }
}
