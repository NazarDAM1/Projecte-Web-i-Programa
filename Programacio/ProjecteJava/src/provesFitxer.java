import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;

public class provesFitxer {

    public static void main(String[] args) throws IOException {

        File fitxer = new File("."); // pot ser com fitxer com directori . es el directori actual
        // es millor ficar les rutes relatives perque la aplicacio es pot moure

        System.out.println(fitxer.getAbsolutePath());

        // pregunta si es un directori
        if (fitxer.isDirectory()) {
            System.out.println("es un directori");
        } else {
            System.out.println("no es un directori");
        }

        // crear directori
        File fitxer2 = new File("files/ENTRADES PENDENTS");
        fitxer2.mkdirs();

        if (fitxer2.isDirectory()) {
            System.out.println("es un directori");
            File[] fitxers = fitxer2.listFiles();

            for (int i = 0; i < fitxers.length; i++) {
                System.out.println(fitxers[i].getName());
                actualitzarFitxerBD(fitxers[i]);
            }
            // listar els fitxers que conte
        }

        File fitxer3 = new File("files/ENTRADES PROCESSADES");
        fitxer3.mkdirs();

    }


    static void actualitzarFitxerBD(File fitxer) throws IOException {
        // legeix caracter a caracter
        FileReader reader = new FileReader(fitxer); 
        // legeix linea a linia es mes eficient
        BufferedReader buffer = new BufferedReader(reader);

        String linea;
        while ((linea = buffer.readLine()) != null) {
            System.out.println(linea + " contingut archiu " + fitxer.getName());
        }

    }

}
