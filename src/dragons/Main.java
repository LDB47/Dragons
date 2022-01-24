package dragons;

import static dragons.DBConnect.openConnection;
import java.sql.SQLException;

/**
 *
 * @author laure
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        openConnection();
        boolean bool;
        int choice;
        
        Utilities util = new Utilities();
        
        do {
            choice = util.showMenu();
            util.menuChoice(choice);
            bool = util.quit(choice);
        } 
        while (bool);
    }
    
}
