import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class menu { // nom clase clar
    static Connection con = null;
    static Statement stmt = null;
    static boolean sortir = false;
    static boolean sortir2 = false;
    static Scanner teclat = new Scanner(System.in);
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projecte", "root", "client");
            stmt = con.createStatement();
            Class.forName("com.mysql.cj.jdbc.Driver");

            do {
                System.out.println("**********-Menu-***********");
                System.out.println("1. Gestio productes");
                System.out.println("2. Actualitzar stock");
                System.out.println("3. preparar comandes");
                System.out.println("4. analitzar les comandes");
                System.out.println("s. Sortir");
                System.out.println("\nTria una opcio:" + "\n");
                String s = sc.next();
                char opcio = s.charAt(0);;

                System.out.println("la opcio " + opcio);

                // el switch si tries una opcio surt del menu

                switch (opcio) {

                case '1':

                    do {

                        System.out.println("1. Mostrar productes <20");
                        System.out.println("2. Mostrar productes >20");
                        System.out.println("s. Sortir");
                        String sa = sc.next();
                        char opcio2 = sa.charAt(0);
                        System.out.println("la opcio: " + opcio2);
                        switch (opcio2) {
                        case '1':

                            llegirArrayMenos20();

                            break;
                        case '2':
                            llegirArrayMes20();
                            break;
                        case 's':
                            sortir2 = true;
                            break;

                        }

                    } while (!sortir2);

                    break;
                case '2':
                ActualitzarEstock();

                    break;
                case '3':

                    break;
                case '4':
                    System.out.println("case 4");

                    break;
                case 's':
                    sortir = true;
                    break;
                default:
                    System.out.println("opcio no valida");
                }
            } while (!sortir);

            // utilArrays.valorMinima(notes);
            // utilArrays.valorMaxima(notes);
            // utilArrays.valorMitjana(notes);
            // utilArrays.VisualitzarValor(notes);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void llegirArrayMenos20() throws SQLException {
        ResultSet rs2 = stmt.executeQuery(
                "select A.nom,A.correu,A.telefon, B.id_proveidor, B.id_producte, C.nom, C.quantitat from proveidor A, porta B, producte C where B.id_proveidor=A.id and b.id_producte=c.id and C.quantitat<20;");
        while (rs2.next())
            System.out.println("nom proveidor: " + rs2.getString(1) + " correu: " + rs2.getString(2) + " Telefon: "
                    + rs2.getString(3) + " id prov: " + rs2.getString(4) + "  id producte: " + rs2.getString(5)
                    + "  nom: " + rs2.getString(6) + "  quantitat: " + rs2.getString(7));

    }

    static void llegirArrayMes20() throws SQLException {
        ResultSet rs2 = stmt.executeQuery(
                "select A.nom,A.correu,A.telefon, B.id_proveidor, B.id_producte, C.nom, C.quantitat from proveidor A, porta B, producte C where B.id_proveidor=A.id and b.id_producte=c.id and C.quantitat>20;");
        while (rs2.next())
            System.out.println("nom proveidor: " + rs2.getString(1) + " correu: " + rs2.getString(2) + " Telefon: "
                    + rs2.getString(3) + " id prov: " + rs2.getString(4) + "  id producte: " + rs2.getString(5)
                    + "  nom: " + rs2.getString(6) + "  quantitat: " + rs2.getString(7));
    }
    static void ActualitzarEstock() throws FileNotFoundException{

        try {
            BufferedReader br = new BufferedReader(
                new FileReader( "G:\\Mi unidad\\DAM\\Projecte1\\ProjecteJava\\Actualitzar\\actualitzar.txt"));
                String s = br.readLine();
            while ((s = br.readLine()) !=null){
                System.out.println(s);
            }

            

        } catch (Exception ex){
            return;
        }

    }
}