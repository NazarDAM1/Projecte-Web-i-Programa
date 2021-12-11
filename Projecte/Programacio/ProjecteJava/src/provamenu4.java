
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.time.LocalDate;

public class provamenu4 {
    static Connection connexioBD = null;
    static Statement stmt = null;
    static Connection con = null;
    static int idProd;
    static boolean sortir = false;
    static boolean sortir2 = false;
    static boolean sortir3 = false;
    static boolean sortir4 = false;
    static int id;
    static int preu;
    static int codiCat;
    static String nom;
    static int quantitat;
    static String ruta_img;
    static Scanner sc = new Scanner(System.in);
    static Scanner teclat = new Scanner(System.in); // system.in perque llegeixi desde consola
    static final String ENTRADESPENDETS = "files/ENTRADES PENDENTS/";
    static final String ESNTRADESSORTIDA = "files/ENTRADES PROCESSADES/";

    static int id_producte;

    public static void main(String[] args) throws SQLException, IOException {
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

            String sa = sc.next();
            char opcio = sa.charAt(0);

            System.out.println("el numero " + opcio);

            // el switch si tries una opcio surt del menu

            switch (opcio) {
                case '1':
                    do {

                        System.out.println("1. Llista tots els porductes");
                        System.out.println("2. Mostrar un producte determinat");
                        System.out.println("3. alta producte");
                        System.out.println("4. Modifica Producte");
                        System.out.println("5. Esborrar producte");
                        System.out.println("s. Sortir");

                        String sa1 = sc.next();
                        char opcio2 = sa1.charAt(0);
                        System.out.println("la opcio: " + opcio2);

                        switch (opcio2) {
                            case '1':
                                mostrarProductes();
                                sortir2 = false;
                                break;

                            case '2':
                                mostrarProducteDeterminat();
                                sortir2 = false;
                                break;

                            case '3':
                                altaProducte();
                                sortir2 = false;
                                break;
                            case '4':
                                modificarPorducte();

                                sortir2 = false;
                                break;
                            case '5':
                                esborrarProducte();

                                sortir2 = false;
                                break;
                            case 's':
                                sortir2 = true;
                                break;

                        }

                    } while (!sortir2);
                    break;
                case '2':
                    actualitzarStock();
                    break;
                case '3':
                    PrepararComandes();
                    break;
                case '4':
                    llegirFile();

                    break;
                case 's':
                    tancarConnexioBD();
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
        String passwd = "Client";
        String bbdd = "projecte";

        try { // El try intenta fer una connexió amb la base de dades.
            connexioBD = DriverManager.getConnection(servidor + bbdd, usuari, passwd);
            System.out.println("Connexió amb èxit");
        } catch (SQLException e) { // Si la connexió no funciona executarà el codi de dins del catch.
            e.printStackTrace();
        }

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
            System.out.println(
                    "id: " + rs.getInt("id") + " Preu " + rs.getString("preu") + " codi_cat " + rs.getString("codi_cat")
                            + " nom " + rs.getString("nom_prod") + " quantitat " + rs.getString("quantitat")
                            + " ruta IMG " + rs.getString("img"));
        }

    }

    static void mostrarProducteDeterminat() throws SQLException {

        do {

            System.out.println("com vols identificar el producte");
            System.out.println("1. Per ID");
            System.out.println("2. Per NOM");
            System.out.println("s. sortir");
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
                        System.out.println(
                                "id: " + rs.getInt("id") + " Preu " + rs.getInt("preu") + " euros" + " codi_cat "
                                        + rs.getInt("codi_cat") + " nom " + rs.getString("nom_prod") + " quantitat "
                                        + rs.getInt("quantitat") + " ruta IMG " + rs.getString("img"));
                    }

                    sortir3 = false;
                    break;
                case '2':
                    System.out.println("Ficar el NOM del producte");
                    // teclat.nextLine();
                    String nomProd = teclat.nextLine();
                    String consulta1 = String.format("select * from producte where nom_prod=" + "\"%s\"" + ";",
                            nomProd);
                    // System.out.println(consulta1);
                    PreparedStatement ps1 = connexioBD.prepareStatement(consulta1);
                    ps1.executeQuery();
                    ResultSet rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        System.out.println("id: " + rs1.getInt("id") + " Preu " + rs1.getInt("preu") + " codi_cat "
                                + rs1.getInt("codi_cat") + " nom " + rs1.getString("nom_prod") + " quantitat "
                                + rs1.getInt("quantitat") + " ruta IMG " + rs1.getString("img"));
                    }

                    sortir3 = false;
                    break;

                case 's':
                    sortir3 = true;
                    break;
            }

        } while (!sortir3);

    }

    static void altaProducte() throws SQLException {

        System.out.println("Fica el nom del producte:");
        teclat.nextLine();// ha trobat el enter al seleccionar el menu
        String nom = teclat.nextLine();
        System.out.println(nom);
        System.out.println("ficar el preu de producte ");
        int preu = teclat.nextInt();
        System.out.println(preu);
        System.out.println("ficar el id de categoria de producte ");
        int id_categoria = teclat.nextInt();
        System.out.println(id_categoria);
        System.out.println("ficar la quantitat");
        int quantitat = teclat.nextInt();
        System.out.println(quantitat);
        System.out.println("Ficar el nom de la imatge:");

        teclat.nextLine();
        String img = teclat.nextLine();

        img = "img_productes/" + img + ".png";
        System.out.println(img);
        System.out.println("Ficar la descripcio (maxim 90 caracters)):");
        // teclat.nextLine();
        String descripcio = teclat.nextLine();
        System.out.println(descripcio);
        System.out.println("Ficar ID de material de quin esta fet el producte:");
        // teclat.nextLine();
        int id_mat = teclat.nextInt();
        System.out.println(id_mat);
        System.out.println("Aquet producte ha portat algun proveidor?");
        System.out.println("1.Si");
        System.out.println("2.No");
        int opcio = teclat.nextInt();

        if (opcio == 1) {
            System.out.println("ficar la id del proveidor");

            int id_proveidor = teclat.nextInt();

            String insert = "insert into producte (nom_prod, preu, codi_cat, quantitat, img, descripcio) VALUES (?,?,?,?,?,?)";
            PreparedStatement sentencia = connexioBD.prepareStatement(insert);
            sentencia.setString(1, nom);
            sentencia.setInt(2, preu);
            sentencia.setInt(3, id_categoria);
            sentencia.setInt(4, quantitat);
            sentencia.setString(5, img);
            sentencia.setString(6, descripcio);
            if (sentencia.executeUpdate() != 0) {
                System.out.println("ha insertat " + "nom: " + nom + " preu: " + preu + " id_categoria: " + id_categoria
                        + " quantitat: " + quantitat + " Descripcio " + descripcio + " imatge " + img);
            } else {
                System.out.println("no insertat");
            }

            String consulta1 = String.format(" SELECT id FROM producte ORDER BY id DESC LIMIT 1;");
            PreparedStatement ps1 = connexioBD.prepareStatement(consulta1);
            ps1.executeQuery();
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                id_producte = rs1.getInt("id");

            }

            String insert1 = "insert into porta (id_proveidor,id_producte) VALUES (?, ?);";
            PreparedStatement sentencia1 = connexioBD.prepareStatement(insert1);
            sentencia1.setInt(1, id_proveidor);
            sentencia1.setInt(2, id_producte);
            sentencia1.executeUpdate();

            System.out.println(id_producte);
            String insert2 = "insert into fet (codi_mat, id_prod) VALUES (?,?)";
            PreparedStatement sentencia2 = connexioBD.prepareStatement(insert2);
            sentencia2.setInt(1, id_mat);
            sentencia2.setInt(2, id_producte);
            sentencia2.executeUpdate();

        } else {
            String insert = "insert into producte (nom_prod, preu, codi_cat, quantitat, img, descripcio) VALUES (?,?,?,?,?,?)";
            PreparedStatement sentencia = connexioBD.prepareStatement(insert);
            sentencia.setString(1, nom);
            sentencia.setInt(2, preu);
            sentencia.setInt(3, id_categoria);
            sentencia.setInt(4, quantitat);
            sentencia.setString(5, img);
            sentencia.setString(6, descripcio);
            if (sentencia.executeUpdate() != 0) {
                System.out.println("ha insertat " + "nom: " + nom + " preu: " + preu + " id_categoria: " + id_categoria
                        + " quantitat: " + quantitat + " Descripcio " + descripcio + " imatge " + img);
            } else {
                System.out.println("no insertat");
            }

            String consulta1 = String.format(" SELECT id FROM producte ORDER BY id DESC LIMIT 1;");
            PreparedStatement ps1 = connexioBD.prepareStatement(consulta1);
            ps1.executeQuery();
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                id_producte = rs1.getInt("id");

            }
            System.out.println(id_producte);
            String insert2 = "insert into fet (codi_mat, id_prod) VALUES (?,?)";
            PreparedStatement sentencia2 = connexioBD.prepareStatement(insert2);
            sentencia2.setInt(1, id_mat);
            sentencia2.setInt(2, id_producte);
            sentencia2.executeUpdate();

        }

        System.out.println("Aquet producte es semblant amb algun altre ");
        System.out.println("1.Si ");
        System.out.println("2.No ");

        opcio = teclat.nextInt();

        if (opcio == 1) {
            System.out.println("ficar el id de producte amb quin es semblant");
            int id_producteSemblant = teclat.nextInt();

            String insert2 = "insert into equival (id_producte1, id_producte2) VALUES (?,?)";
            PreparedStatement sentencia2 = connexioBD.prepareStatement(insert2);
            sentencia2.setInt(1, id_producteSemblant);
            sentencia2.setInt(2, id_producte);
            sentencia2.executeUpdate();

            System.out.println(id_producte + " " + id_producteSemblant);

            String insert = "insert into equival (id_producte1, id_producte2) VALUES (?,?)";
            PreparedStatement sentencia = connexioBD.prepareStatement(insert);
            sentencia.setInt(1, id_producte);
            sentencia.setInt(2, id_producteSemblant);
            sentencia.executeUpdate();
            System.out.println(id_producte + " " + id_producteSemblant);

        }

    }

    static void modificarPorducte() throws SQLException {

        do {

            System.out.println("com vols identificar el producte que vols modificar");
            System.out.println("1. Per ID");
            System.out.println("2. Per NOM");
            System.out.println("s. sortir");
            String sa = sc.next();
            char opcio2 = sa.charAt(0);
            System.out.println("la opcio: " + opcio2);
            switch (opcio2) {
                case '1':
                    System.out.println("Ficar el ID del producte:");
                    idProd = teclat.nextInt();
                    String consulta = ("select * from producte where id= " + idProd + ";");
                    PreparedStatement ps = connexioBD.prepareStatement(consulta);
                    ps.executeQuery();
                    ResultSet rs = ps.executeQuery();

                    // id = rs.getInt("id");
                    // preu = rs.getInt("preu");
                    // codiCat = rs.getInt("codi_cat");
                    // nom = rs.getString("nom");
                    // quantitat = rs.getInt("quantitat");

                    // PERQUE SI FICO LES VARIABLES A FORA EM GENERA UN ERROR ? I HE DE FICAR DINS
                    // DEL WHILE

                    while (rs.next()) {
                        id = rs.getInt("id");
                        preu = rs.getInt("preu");
                        codiCat = rs.getInt("codi_cat");
                        nom = rs.getString("nom_prod");
                        quantitat = rs.getInt("quantitat");
                        System.out.println(
                                "id: " + id + " Preu " + preu + " euros" + " codi_cat " + codiCat + " nom " + nom
                                        + " quantitat " + quantitat);
                    }
                    sortir3 = false;
                    modificarProducte2();
                    break;
                case '2':
                    System.out.println("Ficar el NOM del producte");
                    teclat.nextLine();
                    String nomProd = teclat.nextLine();
                    String consulta1 = String.format("select * from producte where nom_prod=" + "\"%s\"" + ";",
                            nomProd);
                    PreparedStatement ps1 = connexioBD.prepareStatement(consulta1);
                    ps1.executeQuery();
                    ResultSet rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        id = rs1.getInt("id");
                        preu = rs1.getInt("preu");
                        codiCat = rs1.getInt("codi_cat");
                        nom = rs1.getString("nom_prod");
                        quantitat = rs1.getInt("quantitat");
                        ruta_img = rs1.getString("quantitat");
                        System.out.println(
                                "id: " + id + " Preu " + preu + " euros" + " codi_cat " + codiCat + " nom " + nom
                                        + " quantitat " + quantitat);
                    }

                    sortir3 = false;
                    modificarProducte2();
                    break;

                case 's':
                    sortir3 = true;
                    break;
            }

        } while (!sortir3);
    }

    static void modificarProducte2() throws SQLException {
        do {

            System.out.println("Que vols modifica ?");
            System.out.println("1. Nom");
            System.out.println("2. Preu");
            System.out.println("3. Codi de categoria");
            System.out.println("4. La quantitat");
            System.out.println("s. Sortir");

            String sa = sc.next();
            char opcio2 = sa.charAt(0);
            System.out.println("la opcio: " + opcio2);
            switch (opcio2) {
                case '1':
                    System.out.println("fica el nom de producte");
                    teclat.nextLine();
                    nom = teclat.nextLine();

                    break;
                case '2':
                    System.out.println("fica el preu de producte");
                    preu = teclat.nextInt();

                    break;
                case '3':
                    System.out.println("Codi de la categoria");
                    codiCat = teclat.nextInt();
                    break;
                case '4':
                    System.out.println("La quantitat");
                    quantitat = teclat.nextInt();
                    break;
                case 's':
                    sortir4 = true;
                    break;
            }
            String insert = "Update producte Set nom_prod = ?,preu = ?,codi_cat = ?, quantitat = ? where id = ?;";
            PreparedStatement sentencia = connexioBD.prepareStatement(insert);
            String nomProd = "%s".formatted(nom);
            sentencia.setString(1, nomProd);
            sentencia.setInt(2, preu);
            sentencia.setInt(3, codiCat);
            sentencia.setInt(4, quantitat);
            sentencia.setInt(5, idProd);

            if (sentencia.executeUpdate() != 0) {
                System.out.println(" ");
            } else {
                System.out.println("no insertat");
            }

        } while (!sortir4);

    }

    static void esborrarProducte() throws SQLException {
        do {

            System.out.println("Com vols identificar el producte per esborrar?");
            System.out.println("1. ID");
            System.out.println("2. NOM");
            System.out.println("s. Sortir");

            String sa = sc.next();
            char opcio2 = sa.charAt(0);

            System.out.println("la opcio: " + opcio2);
            switch (opcio2) {
                case '1':
                    System.out.println("fica la ID");
                    // teclat.nextLine();
                    idProd = teclat.nextInt();
                    String consulta2 = String.format("select * from producte where id=" + idProd);
                    PreparedStatement ps2 = connexioBD.prepareStatement(consulta2);
                    ps2.executeQuery();
                    ResultSet rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        id = rs2.getInt("id");
                        nom = rs2.getString("nom_prod");
                    }
                    sortir3 = false;
                    break;
                case '2':
                    System.out.println("fica el nom de producte");

                    nom = teclat.nextLine();
                    String nomProd = teclat.nextLine();
                    String consulta1 = String.format("select * from producte where nom_prod=" + "\"%s\"" + ";",
                            nomProd);
                    PreparedStatement ps1 = connexioBD.prepareStatement(consulta1);
                    ps1.executeQuery();
                    ResultSet rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        id = rs1.getInt("id");
                        nom = rs1.getString("nom_prod");
                    }
                    sortir3 = false;
                    break;
                case 's':
                    sortir3 = true;
                    break;
            }

            System.out.println("el producte amb ID: " + id + " i amb el nom :" + nom + " esta eliminat");

            try {
                PreparedStatement st2 = connexioBD
                        .prepareStatement("DELETE FROM equival WHERE id_producte1 = ? or id_producte2 = ? " + ";");
                st2.setInt(1, id);
                st2.setInt(2, id);
                st2.executeUpdate();
                PreparedStatement st3 = connexioBD.prepareStatement("DELETE FROM fet WHERE id_prod = ? " + ";");
                st3.setInt(1, id);
                st3.executeUpdate();
                PreparedStatement st4 = connexioBD.prepareStatement("DELETE FROM porta WHERE id_producte = ? " + ";");
                st4.setInt(1, id);
                st4.executeUpdate();
                PreparedStatement st = connexioBD.prepareStatement("DELETE FROM producte WHERE id = ? " + ";");
                st.setInt(1, id);
                st.executeUpdate();

            } catch (Exception e) {
                System.out.println(e);
            }

        } while (!sortir3);

    }

    static void actualitzarStock() throws IOException, SQLException {

        File fitxer = new File(ENTRADESPENDETS);
        fitxer.mkdirs();

        if (fitxer.isDirectory()) {

            File[] fitxers = fitxer.listFiles();

            for (int i = 0; i < fitxers.length; i++) {
                System.out.println(fitxers[i].getName());
                actualitzarFitxerBD(fitxers[i]);
                moureFitxerAProcessat(fitxers[i]);
            }
        }

        File fitxer3 = new File(ESNTRADESSORTIDA);
        fitxer3.mkdirs();

    }

    static void actualitzarFitxerBD(File fitxer) throws IOException, SQLException {
        FileReader reader = new FileReader(fitxer);

        BufferedReader buffer = new BufferedReader(reader);

        String linea;

        while ((linea = buffer.readLine()) != null) {
            System.out.println(linea);
            int posSep = linea.indexOf(":");

            int id = Integer.parseInt(linea.substring(0, posSep));
            int unitats = Integer.parseInt(linea.substring(posSep + 1));

            String update = "UPDATE producte SET quantitat=quantitat+? WHERE id=?";
            PreparedStatement actualitzar = connexioBD.prepareStatement(update);
            actualitzar.setInt(1, unitats);
            actualitzar.setInt(2, id);

            actualitzar.executeUpdate();

        }
        reader.close();
        buffer.close();

    }

    static void moureFitxerAProcessat(File file) throws IOException {
        FileSystem sistemaFicheros = FileSystems.getDefault();
        Path origen = sistemaFicheros.getPath(ENTRADESPENDETS + file.getName());
        Path desti = sistemaFicheros.getPath(ESNTRADESSORTIDA + file.getName());

        Files.move(origen, desti, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("S'ha mogut a PROCESSATS el fixer" + file.getName());

    }

    static void PrepararComandes() throws SQLException, IOException {

        String proveidorAct = null;
        String proveidorAct2 = null;

        String consulta1 = String.format(
                "select A.nom_prov, B.id_producte, C.nom_prod,C.quantitat from proveidor A, porta B, producte C where B.id_proveidor=A.id and b.id_producte=c.id and C.quantitat<20000 order by A.nom_prov;");
        // System.out.println(consulta1);
        PreparedStatement ps1 = connexioBD.prepareStatement(consulta1);
        ps1.executeQuery();
        ResultSet rs1 = ps1.executeQuery();

        while (rs1.next()) {
            FilterWriter fw = null;
            BufferedWriter bf = null;
            PrintWriter pw = null;
            String CREACIOCOMANDES = null;
            proveidorAct = rs1.getString("A.nom_prov");

            LocalDate data = LocalDate.now();
            CREACIOCOMANDES = "Comandes Generades/" + proveidorAct + data + ".txt";

            if (!proveidorAct.equals(proveidorAct2)) {
                // System.out.println("canvi de proveidor a " + proveidorAct);

                try {

                    File myObj = new File(CREACIOCOMANDES);
                    if (myObj.createNewFile()) {
                        System.out.println("File creat: " + myObj.getName());

                        FileOutputStream f = new FileOutputStream(CREACIOCOMANDES, true);

                        String informacioEmpresa = "|***************************COMANDA************************************|\n|Nom Brico Depo							       |\n|Adreça Tarrega 25200						       |\n|Telefon 626262626						       |\n|----------------------------------------------------------------------|\n";
                        System.out.println(informacioEmpresa);
                        byte[] byteArr = informacioEmpresa.getBytes(); // converting string into byte array
                        f.write(byteArr);
                        f.close();

                    }

                } catch (IOException e) {
                    System.out.println("Hi ha un error.");
                    e.printStackTrace();
                }

            }

            // sempre demanarem tantes quantitats per tenir 500 unitats de estoc
            int quantitat = rs1.getInt("C.quantitat");
            int estock = 500 - quantitat;

            FileOutputStream f = new FileOutputStream(CREACIOCOMANDES, true);
            String informacio = (" " + rs1.getInt("B.id_producte") + "\t \t \t" + rs1.getString("C.nom_prod") + "\t \t "
                    + estock + "\n");

            byte[] byteArr = informacio.getBytes(); // converting string into byte array
            f.write(byteArr);
            f.close();

            System.out.println(informacio);

            String format = "%-20s %5d\n";
            System.out.format(format, "test", 1);
            System.out.format(format, "test2213213213213", 20);
            System.out.format(format, "test3", 5000);

            proveidorAct2 = proveidorAct;
        }
    }

    // static void PrepararComandes() throws SQLException, IOException {

    // String consulta1 = String.format(
    // "select A.nom,A.correu,A.telefon, B.id_proveidor, B.id_producte, C.nom,
    // C.quantitat from proveidor A, porta B, producte C where B.id_proveidor=A.id
    // and b.id_producte=c.id and C.quantitat<20000 order by a.nom;");
    // // System.out.println(consulta1);
    // PreparedStatement ps1 = connexioBD.prepareStatement(consulta1);
    // ps1.executeQuery();
    // ResultSet rs1 = ps1.executeQuery();
    // if (rs1.next()) // retorna true si trobar
    // {
    // String proveidorAct = rs1.getString("A.nom");

    // }4

    // }

    static void llegirFile() throws NumberFormatException, IOException, SQLException {
        // Comandes Generades/matFusta2021-12-02.txt

        FileReader reader = new FileReader("Comandes Generades/llegir file.txt");

        BufferedReader buffer = new BufferedReader(reader);

        String linea = Files.readAllLines(Paths.get("Comandes Generades/llegir file.txt")).get(2);

        while ((linea = buffer.readLine()) != null) {

            int posSep = linea.indexOf(": \t\t");

            int unitats2 = Integer.parseInt(linea.substring(posSep + 1));

            System.out.println(unitats2);

        }
        reader.close();
        buffer.close();

    }

    // FileReader reader = new FileReader(fitxer);

    // BufferedReader buffer = new BufferedReader(reader);

    // String linea;

    // while ((linea = buffer.readLine()) != null) {
    // System.out.println(linea);
    // int posSep = linea.indexOf(":");

    // int id = Integer.parseInt(linea.substring(0, posSep));
    // int unitats = Integer.parseInt(linea.substring(posSep + 1));

    // String update = "UPDATE producte SET quantitat=quantitat+? WHERE id=?";
    // PreparedStatement actualitzar = connexioBD.prepareStatement(update);
    // actualitzar.setInt(1, unitats);
    // actualitzar.setInt(2, id);

    // actualitzar.executeUpdate();

    // }
    // reader.close();
    // buffer.close();

    static void tancarConnexioBD() throws SQLException {
        connexioBD.close();
    }

}
