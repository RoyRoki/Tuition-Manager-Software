import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TuitionDao {
    public static void createDB(String DBName) throws SQLException {
        Connection conn = DataBase.connect();
        String Quary = quary.createDB(DBName);

        Statement stm = conn.createStatement();
        stm.execute(Quary);
    }

    public static void createBatch(String BatchName) throws SQLException {
        Connection conn = DataBase.connect();
        String Quary = quary.createTB(BatchName);
        Statement stm = conn.createStatement();
        stm.execute(Quary);
        conn.close();
    }
    public static ArrayList<String> ShowBatches() throws SQLException {
        Connection conn = DataBase.connect();
        String Quary = quary.showTB();
        Statement stm = conn.createStatement();

        ArrayList<String> Batches = new ArrayList<>();
        ResultSet rs = stm.executeQuery(Quary);       
        while (rs.next()) {
            Batches.add(rs.getString(1));
        }
        conn.close();
        return Batches;
    }
    public static void addStudent(String BatchName, String Name, String Mobile) throws SQLException {
        Connection conn = DataBase.connect();
        String Quary = quary.setData(BatchName);
        PreparedStatement pstm = conn.prepareStatement(Quary);
        pstm.setString(1, Name);
        pstm.setString(2, Mobile);
        pstm.setString(3, "");

        pstm.executeUpdate();
        conn.close();
    }
    public static void updateFee(String BatchName,String Name, String fee) throws SQLException {
        Connection conn = DataBase.connect();
        String Quary = quary.updateFees(BatchName);
        PreparedStatement pstm = conn.prepareStatement(Quary);
        pstm.setString(1, fee);
        pstm.setString(2, Name);

        pstm.executeUpdate();
        conn.close();
    }
    public static void deleteST(String BatchName,String Name) throws SQLException {
        Connection conn = DataBase.connect();
        String Quary = quary.deleteData(BatchName);
        PreparedStatement pstm = conn.prepareStatement(Quary);
        pstm.setString(1,Name);

        pstm.executeUpdate();
        conn.close();
    }
    public static void deleteBatch(String BatchName) throws SQLException {
        Connection conn = DataBase.connect();
        String Quary = quary.deletetBatch(BatchName);
        Statement stm = conn.createStatement();

        stm.execute(Quary);
        conn.close();
    }
    public static ArrayList<Students> readData(String BatchName) throws SQLException{
       Connection conn = DataBase.connect();
       String Quary = quary.readData(BatchName);
       Statement stm = conn.createStatement();

       ResultSet rs = stm.executeQuery(Quary);

       ArrayList<Students> StudentData = new ArrayList<>();
       while (rs.next()) {
        Students std = new Students();
        std.Name = rs.getString(1);
        std.Mobile = rs.getString(2);
        std.Fee = rs.getString(3);
 
        StudentData.add(std);       
       }
       return StudentData;
    }
}
