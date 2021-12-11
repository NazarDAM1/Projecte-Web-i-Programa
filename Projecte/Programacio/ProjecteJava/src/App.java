import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.Scanner;

/**
 * Simple Java program to connect to MySQL database running on localhost and
 * running SELECT and INSERT query to retrieve and add data.
 * 
 * @author Javin Paul
 */
public class App {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projecte", "root", "client");
            Statement stmt = con.createStatement();
            Scanner teclat = new Scanner(System.in); 
            boolean sortir = false;

            do {
                System.out.println("**********-Menu gestor notes-***********");
                System.out.println("1. productes que tinguin mes de 20 unitats en estoc");
                System.out.println("2. Sortir");
                System.out.println("3. manteniment de productes");
                int opcio = teclat.nextInt();
                System.out.println("el numero " + opcio);

                // el switch si tries una opcio surt del menu

                switch (opcio) {
                case 1:
                ResultSet rs2 = stmt.executeQuery("select A.nom,A.correu,A.telefon, B.id_proveidor, B.id_producte, C.nom, C.quantitat from proveidor A, porta B, producte C where B.id_proveidor=A.id and b.id_producte=c.id and C.quantitat>20;");
                while (rs2.next())
                System.out.println("nom proveidor: " + rs2.getString(1) + " correu: " + rs2.getString(2)
                    + " Telefon: " + rs2.getString(3) + " id prov: " + rs2.getString(4) + "  id producte: "
                    + rs2.getString(5) + "  nom: " + rs2.getString(6) + "  quantitat: " + rs2.getString(7));
                    break;
                case 2:
                    sortir = true;
                    break;
                case 3:
                 
                default:
                    System.out.println("opcio no valida");
                }

            } while (!sortir);

            stmt.close();
            // con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
