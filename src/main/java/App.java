import java.util.ArrayList;
import java.util.List;

import modele.Canard;
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
            }
        }
        System.out.println("Merci d'avoir joué !");
    }
}