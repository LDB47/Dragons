package dragons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laure
 */
public class DBConnect {

    static Connection accessDataBase = null;

    public static void openConnection() {
        String url = "jdbc:mysql://127.0.0.1/java_dragons2";
        String utilisateur = "root";
        String motDePasse = "";
        try {
            System.out.println("Connexion réussie!");
            accessDataBase = DriverManager.getConnection(
                    url, utilisateur, motDePasse);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }

    public static boolean testConnection() {
        boolean flag = false;
        try {
            if (accessDataBase != null) {
                if (!accessDataBase.isClosed()) {
                    System.out.println("Connexion au serveur... OK");
                    flag = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
        return flag;
    }

    public static void closeConnection() {
        if (accessDataBase != null) {
            try {
                accessDataBase.close();
                System.out.println("Close connection");
            } catch (SQLException e) {
                System.err.println(
                        "Erreur fermeture: " + e.getMessage()
                );
            }
        }
    }
    
//    public static Connection connect() {
//        Connection conn = null;
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/java_dragons2", "root", "");
//            System.out.println("Connexion réussie !");
//        } catch (ClassNotFoundException | SQLException e) {
//            System.out.println(e);
//        }
//        return conn;
//    }
}
