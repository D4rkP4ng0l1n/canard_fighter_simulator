import java.util.ArrayList;
import java.util.List;

import modele.canard.Canard;
import modele.objet.Objet;
import modele.objet.PotionEnergie;
import modele.objet.PotionSoin;
import ui.Menu;

public class App {

    public static void main(String[] args) {

        List<Canard> canards = new ArrayList<>();
        int selecteur = -1;

        while (selecteur != 0) {
            selecteur = Menu.menuPrincipal();
            switch (selecteur) {
                case 1:
                    canards.add(Menu.creationCanard());
                    break;
                case 2:
                    Menu.afficherListCanards(canards);
                    break;
                case 3:
                    Menu.ajouterExperience(canards);
                    break;
                case 4:
                    if (canards.size() < 1) {
                        System.out.println("Vous ne pouvez pas lancer un combat si vous n'avez pas de canard !");
                    } else {
                        Menu.menuCombat(canards);
                    }
                    break;
                case 5:
                    List<Objet> potions = new ArrayList<>();

                    // Ajouter 3 potions de soin
                    potions.add(new PotionSoin(50));
                    potions.add(new PotionSoin(100));
                    potions.add(new PotionSoin(150));

                    // Ajouter 3 potions d'énergie
                    potions.add(new PotionEnergie(5));
                    potions.add(new PotionEnergie(10));
                    potions.add(new PotionEnergie(15));
                    Menu.modeRoguelike(canards, potions);
                    break;
            }
        }
        System.out.println("Merci d'avoir joué !");
    }
}