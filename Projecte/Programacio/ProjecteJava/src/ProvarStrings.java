public class ProvarStrings {
    public static void main(String[] args) {

        String cad = "es molt interessant";
        String cad2 = "123123:12";
        System.out.println(cad);
        System.out.println(cad.charAt(0));
        System.out.println(cad.isEmpty()); // comprovar si hi ha contingut en el fitxer
        System.out.println(cad.concat(" i molt profitos")); // afegeix la cadena concat a $cad 
        System.out.println(cad.contains(" molt ")); // comprovar si conta aixo   si la frase te "molt " tornara true  el java es sensible a lletres majuscules i minuscules
        System.out.println(cad.endsWith("at ")); //comprovar si acaba amb " at" 
        System.out.println(cad.equals("Es molt interessant")); // comprovar si te squesta cadena
        System.out.println(cad.equalsIgnoreCase("Es molt interessant")); // fa lo mateix pero li dona igual si es majuscula o minuscula 
        System.out.println(cad2.indexOf(":",7)); // buscar la posicio de la lletra a partir de la posicio 7
        System.out.println(cad2.lastIndexOf(":"));
        System.out.println(cad2.substring(3, 6)); // imprimir la cadena desde la posicio 3 fins a la posicio 6   tambe es pot imprimir desde sense ficar el final 
        System.out.println(cad2.substring(2)); // imprimir desde la posicio 2
        System.out.println(cad2.substring(0,cad2.indexOf(":"))); 

        int numprod = Integer.parseInt(  cad2.substring(0,cad2.indexOf(":"))); // pasar la variable String a Int nome ses pot passar si no te cap caracter
        System.out.println("El numero de producte "+ numprod + " Integer ");


        int PosSep = cad2.indexOf(":");

        int numcantit = Integer.parseInt(cad2.substring(PosSep+1)); // si no fico +1 agafa el : i no al pot convertir a integer 
        System.out.println("La cantitat "+ numcantit + " Integer ");




        for(int i=0;i<cad.length();i++){
            System.out.print(cad.charAt(i));
        }
    }
}