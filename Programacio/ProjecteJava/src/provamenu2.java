

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class provamenu2 {
    static Connection connexioBD = null;
    static Statement stmt = null;
    static Connection con = null;
    static boolean sortir2 = false;
    static String insNomProd = " ";
    static int idProd;
    static boolean sortir = false;
    static Scanner sc = new Scanner(System.in);
    static Scanner teclat = new Scanner(System.in); // system.in perque llegeixi desde consola

    public static void main(String[] args) throws SQLException {
        connexioBD();

        // creacio menu

        do {
            System.out.println("**********-Menu-***********");
            System.out.println("1. Gestio productes");
            System.out.println("2. Actualitzar stock");
            System.out.println("3. preparar comandes");
            System.out.println("4. analitzar les comandes");
            System.out.println("s. Sortir");
            System.out.println("\nTria una opcio:" + "\n");

            int opcio = teclat.nextInt();

            System.out.println("el numero " + opcio);

            // el switch si tries una opcio surt del menu

            switch (opcio) {
            case 1:
                do {

                    System.out.println("1. Llista tots els porductes");
                    System.out.println("2. Mostrar un producte determinat");
                    System.out.println("2. alta producte");
                    System.out.println("3. Modifica Producte");
                    System.out.println("4. Esborrar producte");
                    System.out.println("s. Sortir");
                    String sa = sc.next();
                    char opcio2 = sa.charAt(0);
                    System.out.println("la opcio: " + opcio2);
                    switch (opcio2) {
                    case '1':
                        mostrarProductes();

                        break;
                    case '2':
                        altaProducte();

                        break;
                    case '3':
                        modificarPorducte();

                        break;
                    case 's':
                        sortir2 = true;
                        break;

                    }

                } while (!sortir2);
                break;
            case 2:
                // ActualitzarEstock();
                break;
            case 3:
                // PrepararComandes();
                break;
            case 4:
                // AnalitzarLesComandes();
                break;
            case 5:
                sortir = true;
                break;
            default:
                System.out.println("opcio no valida");
            }
        } while (!sortir);
    }

    static void connexioBD() {
        String servidor = "jdbc:mysql://localhost:3306/";
        String usuari = "root";
        String passwd = "client";
        String bbdd = "projecte";

        try { // El try intenta fer una connexió amb la base de dades.
            connexioBD = DriverManager.getConnection(servidor + bbdd, usuari, passwd);
            System.out.println("Connexió amb èxit");
        } catch (SQLException e) { // Si la connexió no funciona executarà el codi de dins del catch.
            e.printStackTrace();
        }

    }

    static void llegirArrayMes20() throws SQLException {
        ResultSet rs2 = stmt.executeQuery(
                "select A.nom,A.correu,A.telefon, B.id_proveidor, B.id_producte, C.nom, C.quantitat from proveidor A, porta B, producte C where B.id_proveidor=A.id and b.id_producte=c.id and C.quantitat>20;");
        while (rs2.next())
            System.out.println("nom proveidor: " + rs2.getString(1) + " correu: " + rs2.getString(2) + " Telefon: "
                    + rs2.getString(3) + " id prov: " + rs2.getString(4) + "  id producte: " + rs2.getString(5)
                    + "  nom: " + rs2.getString(6) + "  quantitat: " + rs2.getString(7) + "\b");
        System.out.println("");
    }

    static void mostrarProductes() throws SQLException {
        // ResultSet rs2 = stmt.executeQuery(
        // "select * from producte order by quantitat ;");
        // while (rs2.next())
        // System.out.println(rs2.getString(1));

        String consulta = ("select * from producte order by id;");
        PreparedStatement ps = connexioBD.prepareStatement(consulta);
        ps.executeQuery();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println("id: " + rs.getString("id") + " Preu " + rs.getString("preu") + " codi_cat "
                    + rs.getString("codi_cat") + " nom " + rs.getString("nom") + " quantitat "
                    + rs.getString("quantitat"));
        }

    }

    static void altaProducte() throws SQLException {

        System.out.println("fica el nom del producte:");
        // teclat.nextLine(); // ha trobat el enter al seleccionar el menu 
        String nom = teclat.nextLine();
        System.out.println("ficar el preu de producte ");
        int preu = teclat.nextInt();
        System.out.println("ficar el id de categoria de producte ");
        int id_categoria = teclat.nextInt();
        System.out.println("ficar la quantitat");
        int quantitat = teclat.nextInt();

        String insert = "insert into producte (nom, preu, codi_cat, quantitat) VALUES (?,?,?,?)";
        PreparedStatement sentencia = connexioBD.prepareStatement(insert);
        sentencia.setString(1, nom);
        sentencia.setInt(2, preu);
        sentencia.setInt(3, id_categoria);
        sentencia.setInt(4, quantitat);

        if (sentencia.executeUpdate() != 0) {
            System.out.println("ha insertat " + "nom: " + nom + " preu: " + preu + " id_categoria: " + id_categoria
                    + " quantitat: " + quantitat);
        } else {
            System.out.println("no insertat");
        }

        // String insert = ("insert * from producte order by quantitat ;");
        // PreparedStatement ps = connexioBD.prepareStatement(consulta);
        // ps.executeQuery();
        // ResultSet rs= ps.executeQuery();
        // while (rs.next()) {
        // System.out.println(rs.getString("id")+rs.getString("preu")+rs.getString("codi_cat")+rs.getString("nom")+rs.getString("quantitat"));
        // }

    }

    static void modificarPorducte() throws SQLException {

        do {

            System.out.println("Com vols identificar el producte que vols modificar?");
            System.out.println("1.Amb ID");
            System.out.println("2.Amb el NOM");
            System.out.println("s. Sortir");
            String sa = sc.next();
            char opcio2 = sa.charAt(0);
            System.out.println("la opcio: " + opcio2);
            switch (opcio2) {
            case '1':
                System.out.println("Ficar el ID del producte:");
                idProd = teclat.nextInt();
                System.out.println(idProd);
                String consulta = ("select * from producte where id= " + idProd + ";");
                PreparedStatement ps = connexioBD.prepareStatement(consulta);
                ps.executeQuery();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.println("id: " + rs.getString("id") + " Preu " + rs.getString("preu") + " codi_cat "
                            + rs.getString("codi_cat") + " nom " + rs.getString("nom") + " quantitat "
                            + rs.getString("quantitat"));
                }


                sortir2 = true;
                break;
            case '2':
                System.out.println("Ficar el NOM del producte");
                teclat.nextLine();
                String nomProd = teclat.nextLine();
                String consulta1 = String.format("select * from producte where nom=" + "\"%s\"" + ";",nomProd);  
                System.out.println(consulta1);
                PreparedStatement ps1 = connexioBD.prepareStatement(consulta1);
                ps1.executeQuery();
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    System.out.println("id: " + rs1.getString("id") + " Preu " + rs1.getString("preu") + " codi_cat "
                            + rs1.getString("codi_cat") + " nom " + rs1.getString("nom") + " quantitat "
                            + rs1.getString("quantitat"));
                }
                // String insNomProd = "nom =\"%s\"".formatted(nomProd);
                insNomProd = String.format("nom = \"%s\"  ", nomProd);
                // String insNomProd = "nom =" + "\"nomProd"\"";
                System.out.println(insNomProd);
                sortir2 = true;
                break;

            case 's':
                sortir2 = true;
                break;

            }
            System.out.println(insNomProd);
            System.out.println(idProd);
        } while (!sortir2);

        do {

            System.out.println("Que vols modificar?");
            System.out.println("1.Nom");
            System.out.println("2.Preu");
            System.out.println("3.Quantitat");
            System.out.println("4.Codi categoria");
            System.out.println("s. Sortir");
            String sa = sc.next();
            char opcio2 = sa.charAt(0);
            System.out.println("la opcio: " + opcio2);
            switch (opcio2) {
            case '1':
                System.out.println("Ficar el nom:");
                teclat.nextLine();
                String nomProd = teclat.nextLine();
                insNomProd = String.format("nom = \"%s\"  ", nomProd);
                

                break;
            case '2':
                System.out.println("Ficar el preu:");

                break;

            case '3':
                System.out.println("Ficar el Quantitat:");

                break;
            case '4':
                System.out.println("Ficar el Codi de categoria");

                break;

            case 's':
                sortir2 = true;
                break;
            }

        } while (!sortir2);

        String insert = "Update producte Set ?,?,?,? where ? or ?";
        PreparedStatement sentencia = connexioBD.prepareStatement(insert);
        // sentencia.setString(1, modNomProd);
        // sentencia.setString(2, modQuantitat);
        // sentencia.setString(3, modPreu);
        // sentencia.setString(4, modCodiCat);
        sentencia.setInt(5, idProd);

        // sentencia.setInt(3, id_categoria);
        // sentencia.setInt(4, quantitat);
        if (sentencia.executeUpdate() != 0) {
            System.out.println(" ");
        } else {
            System.out.println("no insertat");
        }

    }
// fer amb el getstring  el programa fer variables per defecte amb la consulta que hem fet i si la variable es cambiaaa es fica el valor nou
}
