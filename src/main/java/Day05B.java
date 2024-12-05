/*

Safety protocols clearly indicate that new pages for the safety manuals must be printed in a very specific order. 
The notation X|Y means that if both page number X and page number Y are to be produced as part of an update, page number X must be printed at some point before page number Y.

The Elf has for you both the page ordering rules and the pages to produce in each update (your puzzle input), 
but can't figure out whether each update has the pages in the right order.

Reading the file:
The first section specifies the page ordering rules, one per line. 
The first rule, 47|53, means that if an update includes both page number 47 and page number 53, then page number 47 must be printed at some point before page number 53. (47 doesn't necessarily need to be immediately before 53; other pages are allowed to be between them.)

The second section specifies the page numbers of each update. Because most safety manuals are different, 
the pages needed in the updates are different too. The first update, 75,47,61,53,29, means that the update consists 
of page numbers 75, 47, 61, 53, and 29.

 */

 import java.math.BigInteger;
 import java.nio.file.Files;
 import java.nio.file.Paths;
 import java.util.ArrayList;
 import java.util.List;
 
 public class Day05B {
     public static void main(String[] args) {
         try {
             //Lecutra de las reglas y los updates
             List<String> lines = Files.readAllLines(Paths.get("res/Day05_input.txt"));
 
             ArrayList<String> rules = new ArrayList<>();
             ArrayList<String[]> updates = new ArrayList<>();
             boolean rulesSection = true;
             for (String line : lines) {
                 if (line.isEmpty()) {
                     rulesSection = false;
                     continue;
                 }
                 if(rulesSection) {
                     processRule(line, rules);
                 } else {
                     processUpdate(line, updates);
                 }
             }
 
             // Paso 2: se recorre cada update, y se verifica si se cumple la regla.
             // Para cumplir una regla, el primer número de la regla debe estar antes que el segundo dentro de los elementos del update
             // Si es correcto lo añadiremos a la coleccion rightUpdates
             ArrayList<String[]> rightUpdates = new ArrayList<>();
             for (String[] update : updates) {
                 boolean right = true;
                 for (String rule : rules) {
                     String[] parts = rule.split("\\|");
                     int index1 = -1;
                     int index2 = -1;
                     for (int i = 0; i < update.length; i++) {
                         if (update[i].equals(parts[0])) {
                             index1 = i;
                         }
                         if (update[i].equals(parts[1])) {
                             index2 = i;
                         }
                     }
                     if (index1 != -1 && index2 != -1 && index1 > index2) {
                         right = false;
                         break;
                     }
                 }
                 //En la parte 2 usamos los updates no correctos
                 if (!right) {
                     rightUpdates.add(update);
                 }
             }

            //Paso 3: queremos usar las reglas para ordenar los updates incorrectos
            //Para ello, vamos a recorrer los updates incorrectos y vamos a ir moviendo los elementos de la lista

            for (String[] update : rightUpdates) {
                boolean sorted;
                do {
                    sorted = true;
                    for (String rule : rules) {
                        String[] parts = rule.split("\\|");
                        int index1 = -1;
                        int index2 = -1;
                        for (int i = 0; i < update.length; i++) {
                            if (update[i].equals(parts[0])) {
                                index1 = i;
                            }
                            if (update[i].equals(parts[1])) {
                                index2 = i;
                            }
                        }
                        if (index1 != -1 && index2 != -1 && index1 > index2) {
                            // Si se cumple la regla, movemos el elemento de la lista
                            String aux = update[index1];
                            update[index1] = update[index2];
                            update[index2] = aux;
                            sorted = false;
                        }
                    }
                } while (!sorted);
            }
 
             // Paso 4: se imprime el resultado de los updates correctos
             for (String[] update : rightUpdates) {
                 for (String page : update) {
                     System.out.print(page + " ");
                 }
                 System.out.println();
             }
 
             //Paso 5: se obtiene el elemento de enmedio de cada update y se va sumando. Usaremos BigInteger para evitar desbordamiento
             BigInteger sum = BigInteger.ZERO;
             for (String[] update : rightUpdates) {
                 int middle = update.length / 2;
                 sum = sum.add(new BigInteger(update[middle]));
             }
 
             System.out.println("Suma de los elementos del medio de los updates correctos: " + sum);
             
 
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
 
     private static void processRule(String line, ArrayList<String> rules) {
         String[] parts = line.split("\\|");
         rules.add(parts[0] + "|" + parts[1]);
         // System.out.println(parts[0] + " must be printed before " + parts[1]);
     }
 
     private static void processUpdate(String line, ArrayList<String[]> updates) {
         //Rellena updates con los números de la línea
         String[] parts = line.split(",");
         updates.add(parts);
     }
 }
 