import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {

    static Connection conn = null;
    public static Connection connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/";
            String dbname = "TutionData";
            String userName = "root";
            String passWord = "#@!Roki8653";

           conn = DriverManager.getConnection(url+dbname, userName, passWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
