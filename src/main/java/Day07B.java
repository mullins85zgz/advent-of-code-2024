
/*
Lee el fichero Day07_input.txt
El primer numero es el resultado esperado
El resto de numeros despues de los 2 puntos son los numeros que se suman o multiplican entre ellos para dar el resultado
Podria darse o no el resultado esperado. 
Solo se permite con operaciones de sumar y multiplicar

Por ejemplo:
190: 10 19

Se considera correcto porque 10 * 19 = 190
Se sumaran al contador BigInteger los valores 10 y 19, es decir, 29

 */
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class Day07B {
    public static void main(String[] args) {
        try {
            File file = new File("res/Day07_input.txt");
            BigInteger sum = new BigInteger("0");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    BigInteger expectedResult = new BigInteger(parts[0].trim());
                    System.out.println("Resultado esperado: " + expectedResult);
                    String[] numbers = parts[1].trim().split(" ");
                    BigInteger[] nums = new BigInteger[numbers.length];
                    for (int i = 0; i < numbers.length; i++) {
                        nums[i] = new BigInteger(numbers[i]);
                    }

                    if (canGetResultHelper(expectedResult, nums, 1, nums[0])) {
                        sum = sum.add(expectedResult);
                        System.out.println("Se puede obtener el resultado esperado con los numeros dados");
                    } else {
                        System.out.println("No se puede obtener el resultado esperado con los numeros dados");
                    }
                }
            }
            System.out.println("Suma: " + sum);
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Crea un metodo que recibe un resultado esperado y un array de numeros y
    // devuelve si se puede obtener el resultado esperado con los numeros dados
    // unicamente usando sumas y multiplicaciones o concatenaciones (por ejemplo 7 y
    // 11 se concatenan para formar 711)

    private static boolean canGetResultHelper(BigInteger expectedResult, BigInteger[] numbers, int index,
            BigInteger currentResult) {
        if (index == numbers.length) {
            return currentResult.equals(expectedResult);
        }

        // Try adding the current number
        if (canGetResultHelper(expectedResult, numbers, index + 1, currentResult.add(numbers[index]))) {
            return true;
        }

        // Try multiplying the current number
        if (canGetResultHelper(expectedResult, numbers, index + 1, currentResult.multiply(numbers[index]))) {
            return true;
        }

        // Try concatenating the current number
        if (canGetResultHelper(expectedResult, numbers, index + 1,
                new BigInteger(currentResult.toString() + numbers[index]))) {
            return true;
        }

        return false;

    }
}
