import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static int Starting() {
        System.out.println("*********************************************");
        System.out.println("   Welcome to the Tuition Manager Software!");
        System.out.println("*********************************************");
        System.out.println("\n");

        // Print a menu of options
        System.out.println("** Main Menu **");
        System.out.println("1. Update Fees");
        System.out.println("2. Add New Student");
        System.out.println("3. Create New Batch");
        System.out.println("4. Batches Info");
        System.out.println("5. Students Info");
        System.out.println("6. Remove Batch");
        System.out.println("7. Remove Student");
        System.out.println("8. All Info");
        System.out.println("9. Exit");

        System.out.print("Enter your choice (1-9): ");
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }


    public static String getBatch() throws Exception {
        ArrayList<String>BatchNames = TuitionDao.ShowBatches();
        System.out.println("\n");
        for(int i=1; i<=BatchNames.size(); i++) {
            System.out.println(i+". "+BatchNames.get(i-1));
        }
        System.out.println("");
        System.out.print("Enter Batch No. "+"(1-"+BatchNames.size()+"): ");
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        return BatchNames.get((sc.nextInt()-1));
    }



    public static String[] getStudent(String BatchName) throws Exception {
        ArrayList<Students>sNames = TuitionDao.readData(BatchName);
        String data[] = new String[2];
        System.out.println("\n");
        for(int i=1; i<=sNames.size(); i++) {
            System.out.println(i+". "+sNames.get(i-1).Name+"  Fee ("+sNames.get(i-1).Fee+")");
        }
        System.out.println("");
        System.out.print("Enter Student No. "+"(1-"+sNames.size()+"): ");
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        data[0]=sNames.get((input-1)).Name;
        data[1]=sNames.get(input-1).Fee;

        return data;
    }



    public static void updateFee(String BatchName, String sname, String currFee, String input) throws SQLException {
        
        StringBuilder sb = new StringBuilder();
        sb.append(currFee);
        
        if(input.equals("+")) {
            sb.append("+");
            TuitionDao.updateFee(BatchName, sname, sb.toString());
            System.out.println("Update Successful"); 
        } else {
            System.out.println("Update Unsuccessful");
        }

    }

    public static void showBatch() throws Exception {
        ArrayList<String>BatchNames = TuitionDao.ShowBatches();
        
        System.out.println("\n");
        for(int i=1; i<=BatchNames.size(); i++) {
            ArrayList<Students> std= TuitionDao.readData(BatchNames.get(i-1));
            int totalStd = std.size();
            System.out.println(i+". "+BatchNames.get(i-1)+"   (Total Students "+totalStd+"  )");
        }
        
    }

    public static void showStudent(String BatchName) throws Exception {
        ArrayList<Students>sNames = TuitionDao.readData(BatchName);
        System.out.println("\n");
        for(int i=1; i<=sNames.size(); i++) {
            System.out.println(i+". "+sNames.get(i-1).Name+"   No. ( "+sNames.get(i-1).Mobile+" )    "+"  Fee ("+sNames.get(i-1).Fee+")");
        }
    }

    public static void Allinfo () throws Exception {
        ArrayList<String>BatchNames = TuitionDao.ShowBatches();
        for(int i=1; i<=BatchNames.size(); i++) {
            System.out.print("\n"+i+".   [ "+BatchNames.get(i-1)+" ]");
            showStudent(BatchNames.get(i-1));
        }
    }



    public static void main(String[] args) throws Exception {
     int choic=0;
     @SuppressWarnings("resource")
    Scanner sc = new Scanner(System.in);

     do {
        choic = Starting();
        switch(choic) {
            case 1:  
                  String batch = getBatch();
                  String Sdata[] = getStudent(batch);
    
                  System.out.println("Enter '+' If Payment completed (press any key for cancel)");
                  String input = sc.nextLine();
                  updateFee(batch,Sdata[0],Sdata[1],input);
                  System.out.println("\n");
                  break;

            case 2:
                  batch = getBatch();
                  String Name,Mobile;
                  System.out.print("Enter New Student Name :  ");
                  Name=sc.nextLine();
                  System.out.print("Enter Student's Mobile no. :  ");
                  Mobile =sc.nextLine();
                  TuitionDao.addStudent(batch,Name,Mobile);
                  System.out.println("\nAdding Successful");
                  System.out.println("\n");
                  break;

            case 3:
                   System.out.print("Enter New Batch Name :  ");
                   batch = sc.nextLine();
                   TuitionDao.createBatch(batch);
                   System.out.println("\nAdding Successful");
                   System.out.println("\n");
                   break;

            case 4:
                   showBatch();
                   System.out.println("\n");
                   break;

            case 5:
                   batch = getBatch();
                   showStudent(batch);
                   System.out.println("\n");
                   break;

            case 6:
                   batch = getBatch();
                   TuitionDao.deleteBatch(batch);
                   System.out.println("\nDelete Successful");
                   System.out.println("\n");
                   break;

            case 7:
                  batch = getBatch();
                  String SName[] = getStudent(batch);
                  TuitionDao.deleteST(batch, SName[0]);
                  System.out.println("\nDelete Successful");
                  System.out.println("\n");
                  break;
                  
            case 8:
                  Allinfo();
                  System.out.println("\n");
                  break;

            case 9:
                  System.out.println("\n[This Softwere made by Roki Roy]");
                  break;

            default:
                   System.out.println("\nEnter Valid choice (1-9): ");
    
         }
     } while (choic!=9);

    }
}
