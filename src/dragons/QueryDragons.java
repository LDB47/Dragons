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
        Dragon dra = new Dragon();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est le nom du dragon que vous voulez?");
        String nom = scanner.nextLine();

        try {
            String query = "SELECT * FROM dragons WHERE dragon = ?;";
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
    
    

    public static boolean create(String dragon) {
        boolean success = false;
        try {
            String query = "INSERT INTO `dragons` VALUES (?)";
            PreparedStatement declaration = accessDataBase.prepareStatement(query);
            declaration.setString(1, dragon);
            boolean executeCreate = declaration.execute();
            success = (executeCreate);
        } catch (SQLException e) {
            System.err.println("Erreur ajout de dragon: " + e.getMessage());
        }
        return success;
    }

    public static boolean delete(int id) {
        boolean success = false;
        try {
            String query = "DELETE FROM dragons WHERE id_dragon = ?";
            PreparedStatement declaration = accessDataBase.prepareStatement(query);
            declaration.setInt(1, id);
            int executeUpdate = declaration.executeUpdate();
            success = (executeUpdate == 1);
        } catch (SQLException e) {
            System.err.println("Erreur suppression de dragon: " + e.getMessage());
        }
        return success;
    }

}
