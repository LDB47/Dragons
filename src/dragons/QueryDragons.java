package dragons;

import static dragons.DBConnect.accessDataBase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author laure
 */
public class QueryDragons {

    /**
     * 
     * @return all dragons from database
     * @throws SQLException 
     */
    public static List<Dragon> getAll() throws SQLException {

        List<Dragon> dragons = new ArrayList<>();

        try {
            String query = "SELECT * FROM dragons;";
            PreparedStatement declaration = accessDataBase.prepareStatement(query);

            ResultSet resultat = declaration.executeQuery(query);

            while (resultat.next()) {
                Dragon dra = new Dragon();
                dra.setId_dragon(resultat.getInt("id_dragon"));
                dra.setDragon(resultat.getString("dragon"));
                dra.setSexe(resultat.getString("sexe"));
                dra.setLongueur(resultat.getInt("longueur"));
                dra.setNombre_ecailles(resultat.getInt("nombre_ecailles"));
                dra.setCrache_feu(resultat.getString("crache_feu"));
                dra.setComportement_amoureux(resultat.getString("comportement_amoureux"));
                System.out.println(dra.toString());
            };
        } catch (Exception e) {
            System.err.println(
                    "Erreur d'affichage de dragons: " + e.getMessage()
            );
        }
        return dragons;
    }
    
    /**
     * 
     * @return a dragon selected by name
     * @throws SQLException 
     */
    public static Dragon getDragon() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Dragon dra = new Dragon();
        System.out.println("Quel est le nom du dragon que vous voulez afficher?");
        String nom = scanner.nextLine();

        try {
            String query = "SELECT * FROM dragons WHERE dragon = ?";
            PreparedStatement declaration = accessDataBase.prepareStatement(query);
            declaration.setString(1, nom);
            ResultSet resultat = declaration.executeQuery(query);

            while (resultat.next()) {
                dra.setId_dragon(resultat.getInt("id_dragon"));
                dra.setDragon(resultat.getString("dragon"));
                dra.setSexe(resultat.getString("sexe"));
                dra.setLongueur(resultat.getInt("longueur"));
                dra.setNombre_ecailles(resultat.getInt("nombre_ecailles"));
                dra.setCrache_feu(resultat.getString("crache_feu"));
                dra.setComportement_amoureux(resultat.getString("comportement_amoureux"));
                System.out.println(dra.toString());
            };
        } catch (Exception e) {
            System.err.println(
                    "Erreur d'affichage de dragon: " + e.getMessage()
            );
        }
        return dra;
    }
    
    /**
     * Create new dragon in database
     * @return boolean - if insertion succeeded
     */
    public static boolean create() {
        boolean success = false;
        System.out.println("Créez un nouveau dragon:");
        Dragon dra = new Dragon();
        dra.createNewDragon();
        try {
            String query = "INSERT INTO dragons (id_dragon , dragon, sexe, longueur, nombre_ecailles, crache_feu, comportement_amoureux) VALUES ( ?, ? , ? , ? , ? , ?, ? )";
            PreparedStatement declaration = accessDataBase.prepareStatement(query);
            declaration.setInt(1, dra.getId_dragon());
            declaration.setString(2, dra.getDragon());
            declaration.setString(3, dra.getSexe());
            declaration.setInt(4, dra.getLongueur());
            declaration.setInt(5, dra.getNombre_ecailles());
            declaration.setString(6, dra.getCrache_feu());
            declaration.setString(7, dra.getComportement_amoureux());
            boolean executeCreate = declaration.execute();
            success = (executeCreate);
        } catch (SQLException e) {
            System.err.println("Erreur ajout de dragon: " + e.getMessage());
        }
        return success;
    }

    public static boolean delete() {
        Scanner scanner = new Scanner(System.in);
        boolean success = false;
        System.out.println("Quel est le nom du dragon que vous voulez supprimer?");
        String name = scanner.nextLine();
        try {
            String query = "DELETE FROM dragons WHERE dragon = ?";
            PreparedStatement declaration = accessDataBase.prepareStatement(query);
            declaration.setString(1, name);
            int executeUpdate = declaration.executeUpdate();
            success = (executeUpdate == 1);
            System.out.println("Le dragon a bien été supprimé de la base de données!");
        } catch (SQLException e) {
            System.err.println("Erreur suppression de dragon: " + e.getMessage());
        }
        return success;
    }
    
    public static boolean update() throws SQLException {
        boolean success = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est le nom du dragon que vous voulez modifier?");
        String name = scanner.nextLine();
        int userChoiceUpdate = Utilities.showUpdate();
        String column = Utilities.updateChoice(userChoiceUpdate);
        System.out.println("Entrez la nouvelle valeur: ");
        String newValue = scanner.nextLine();
        
        try {
            String query = "UPDATE dragons SET "+ column +" = ?  WHERE dragon = ?";
            PreparedStatement declaration = accessDataBase.prepareStatement(query);
            declaration.setString(1, newValue);
            declaration.setString(2, name);
            int executeUpdate = declaration.executeUpdate();
            success = (executeUpdate == 1);
            System.out.println("Le dragon a bien été modifié!");
        } catch (SQLException e) {
            System.err.println("Erreur modification dragon: "
                    + e.getMessage());
        }

        return success;
    }

}
