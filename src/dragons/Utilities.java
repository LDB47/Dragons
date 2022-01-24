package dragons;

import static dragons.QueryDragons.create;
import static dragons.QueryDragons.delete;
import static dragons.QueryDragons.getAll;
import static dragons.QueryDragons.getDragon;
import static dragons.QueryDragons.update;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author laure
 */
public class Utilities {
    
    
    static int userSelection;
    static int updateSelection;
    
    /**
     * 
     * @return userSelection to move forward the scenario
     */
    public static int showMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Que voulez-vous faire?");
        System.out.println("Choisissez 1 pour créer un dragon, 2 pour afficher "
                + "tous les dragons, 3 pour rechercher un dragon, 4 pour "
                + "modifier un dragon, 5 pour supprimer un dragon, 6 pour "
                + "quitter le programme.");
        userSelection = scanner.nextInt();
        return userSelection;
    }
    
    /**
     * 
     * @param choice
     * @return boolean value to help quit the program
     */
    public boolean quit(int choice) {
        boolean quitProg = true;
        if (choice == 6) {
            quitProg = false;
        }
        return quitProg;
    }
    
    /**
     * Display the menu for the user
     * @param userSelection
     * @throws SQLException 
     */
    public static void menuChoice(int userSelection) throws SQLException {
        switch (userSelection) {
            case 1:
                create();
                break;
            case 2:
                getAll();
                break;
            case 3:
                getDragon();
                break;
            case 4:
                update();
                break;
            case 5:
                delete();
                break;
            case 6:
                System.out.println("Merci. Programme terminé!");
                break;
            default:
                System.out.println("Erreur de saisie!");
                break;
        }
    }
    
    /**
     * 
     * @return the user selection for update
     */
    public static int showUpdate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Que vouslez-vous modifier?");
        System.out.println("Entrez 1 pour le nom, 2 pour le sexe, 3 pour la "
                + "longueur, 4 pour le nombres d'écailles, 5 pour s'il crache "
                + "du feu, 6 pour son comportement amoureux.");
        updateSelection = scanner.nextInt();
        return updateSelection;
    }
    
    /**
     * 
     * @param updateSelection
     * @return name of chosen column
     * @throws SQLException 
     */
    public static String updateChoice(int updateSelection) throws SQLException {
        String column = "";
        switch (updateSelection) {
            case 1:
                column = "dragon";
                break;
            case 2:
                column = "sexe";
                break;
            case 3:
                column = "longueur";
                break;
            case 4:
                column = "nombre_ecailles";
                break;
            case 5:
                column = "crache_feu";
                break;
            case 6:
                column = "comportement_amoureux";
                break;
            default:
                System.out.println("Erreur de saisie!");
                break;
        }
        return column;
    }
    
}
