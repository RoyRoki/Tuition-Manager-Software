public class quary {
    
    static String createDB (String dbName) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE DATABASE ");
        sb.append(dbName);

        return sb.toString();
    }
    
    static String createTB (String TableName) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        sb.append(TableName);
        sb.append(" (");
        sb.append("Name VARCHAR(20) NOT NULL,");  
        sb.append("Mobile VARCHAR(20) NOT NULL,");
        sb.append("Fees VARCHAR(200) NOT NULL"); 
        sb.append(")");
        return sb.toString();
    }
    static String showTB() {
        StringBuilder sb = new StringBuilder();
        sb.append("Show Tables");
        return sb.toString();
      }

    static String setData(String tbName) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(tbName);
        sb.append(" (Name, Mobile, Fees) VALUES (?, ?, ?)");
        return sb.toString();
      }
      
      static String readData(String tbName) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(tbName);
        return sb.toString();
      }
      
      static String updateFees(String tbName) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(tbName);
        sb.append(" SET Fees = ? WHERE Name = ?");
        return sb.toString();
      }
      
      static String deleteData(String tbName) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(tbName);
        sb.append(" WHERE Name = ?"); 
        return sb.toString();
      }
      static String deletetBatch(String tbName) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(tbName);
        return sb.toString();
      }
}
