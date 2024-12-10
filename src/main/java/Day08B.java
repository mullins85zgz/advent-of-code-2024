import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day08B {

    public static BigInteger totalAntinodesStatic = BigInteger.ZERO;

    public static void main(String[] args) {
        String filePath = "res/Day08_inputTest.txt";
        List<String[]> antennaPositions = readAntennaPositions(filePath);
        for (String[] position : antennaPositions) {
            for (String s : position) {
                System.out.print(s);
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println();

        // Ahora vamos a incorporar los antinodos
        // 1. Calcular los antinodos
        // 2. Incorporar los antinodos a la matriz
        // 3. Imprimir la matriz

        // 1. Calcular los antinodos
        List<String[]> antinodes = calculateAntinodes(antennaPositions);

        // 2. Incorporar los antinodos a la matriz
        BigInteger totalAntiNodes = BigInteger.ZERO;
        for (String[] antinode : antinodes) {
            int i = Integer.parseInt(antinode[0]);
            int j = Integer.parseInt(antinode[1]);
            if (antennaPositions.get(i)[j].equals(".")) {
                antennaPositions.get(i)[j] = "#";
                totalAntiNodes = totalAntiNodes.add(BigInteger.ONE);
            } else if (antennaPositions.get(i)[j].equals("#")) {
                System.out.println("Antinode already exists in position: " + i + ", " + j);
                totalAntiNodes = totalAntiNodes.add(BigInteger.ONE);
            } else if (antennaPositions.get(i)[j].equals(antinode[2])) {
                System.out.println("Antinode already exists in position: " + i + ", " + j);
                totalAntiNodes = totalAntiNodes.add(BigInteger.ONE);
            } else {
                System.out.println("Antinode already exists in position: " + i + ", " + j);
            }

        }

        // 3. Imprimir la matriz
        for (String[] position : antennaPositions) {
            for (String s : position) {
                System.out.print(s);
            }
            System.out.println();
        }

        System.out.println("Total antinodes: " + totalAntiNodes);
        System.out.println("Total antinodes static: " + totalAntinodesStatic);
    }

    public static List<String[]> readAntennaPositions(String filePath) {
        List<String[]> positions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] position = new String[line.length()];
                for (int i = 0; i < line.length(); i++) {
                    position[i] = String.valueOf(line.charAt(i));
                }
                positions.add(position);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return positions;
    }

    public static List<String[]> calculateAntinodes(List<String[]> antennaPositions) {
        List<String[]> antinodes = new ArrayList<>();
        for (int i = 0; i < antennaPositions.size(); i++) {
            for (int j = 0; j < antennaPositions.get(i).length; j++) {
                if (antennaPositions.get(i)[j].equals(".")) {
                    continue;
                }
                String frequency = antennaPositions.get(i)[j];
                for (int k = 0; k < antennaPositions.size(); k++) {
                    for (int l = 0; l < antennaPositions.get(k).length; l++) {
                        if (antennaPositions.get(k)[l].equals(".")) {
                            continue;
                        }
                        // Si la antena es la misma, no hacemos nada
                        if (i == k && j == l) {
                            continue;
                        }
                        if (antennaPositions.get(k)[l].equals(frequency)) {

                            // Imprime las posiciones de las antenas
                            System.out.println("Antenna 1: " + i + ", " + j);
                            System.out.println("Antenna 2: " + k + ", " + l);


                            // Calculamos la distancia entre las antenas
                            // int distance = Math.abs(i - k) + Math.abs(j - l);
                            // Calculamos los antinodos
                            // Antinodo 1
                            int antinode1i = i + (i - k);
                            int antinode1j = j + (j - l);
                            if (antinode1i >= 0 && antinode1i < antennaPositions.size() && antinode1j >= 0
                                    && antinode1j < antennaPositions.get(antinode1i).length) {
                                String[] antinode1 = { String.valueOf(antinode1i), String.valueOf(antinode1j),
                                        frequency };
                                boolean exists = false;
                                for (String[] existingAntinode : antinodes) {
                                    if (existingAntinode[0].equals(String.valueOf(antinode1i)) &&
                                            existingAntinode[1].equals(String.valueOf(antinode1j))) {
                                        exists = true;
                                        break;
                                    }
                                }
                                if (!exists) {
                                    antinodes.add(antinode1);
                                    System.out.println(
                                            "Antinode 1 added: " + antinode1i + ", " + antinode1j + ", " + frequency);
                                    totalAntinodesStatic = totalAntinodesStatic.add(BigInteger.ONE);
                                }
                            }
                            // Antinodos adicionales
                            int prevAntinodei = antinode1i;
                            int prevAntinodej = antinode1j;
                            while (true) {
                                int newAntinodei = prevAntinodei + (i - k);
                                int newAntinodej = prevAntinodej + (j - l);
                                if (newAntinodei >= 0 && newAntinodei < antennaPositions.size() && newAntinodej >= 0
                                        && newAntinodej < antennaPositions.get(newAntinodei).length) {
                                    String[] newAntinode = { String.valueOf(newAntinodei), String.valueOf(newAntinodej),
                                            frequency };
                                    boolean exists = false;
                                    for (String[] existingAntinode : antinodes) {
                                        if (existingAntinode[0].equals(String.valueOf(newAntinodei)) &&
                                                existingAntinode[1].equals(String.valueOf(newAntinodej))) {
                                            exists = true;
                                            break;
                                        }
                                    }
                                    if (!exists) {
                                        antinodes.add(newAntinode);
                                        System.out.println("Additional antinode added: " + newAntinodei + ", " + newAntinodej + ", " + frequency);
                                        totalAntinodesStatic = totalAntinodesStatic.add(BigInteger.ONE);
                                    }
                                    prevAntinodei = newAntinodei;
                                    prevAntinodej = newAntinodej;
                                } else {
                                    break;
                                }
                            }
                            // Antinodo 2
                            int antinode2i = k + (k - i);
                            int antinode2j = l + (l - j);
                            if (antinode2i >= 0 && antinode2i < antennaPositions.size() && antinode2j >= 0
                                    && antinode2j < antennaPositions.get(antinode2i).length) {
                                String[] antinode2 = { String.valueOf(antinode2i), String.valueOf(antinode2j),
                                        frequency };
                                boolean exists = false;
                                for (String[] existingAntinode : antinodes) {
                                    if (existingAntinode[0].equals(String.valueOf(antinode2i)) &&
                                            existingAntinode[1].equals(String.valueOf(antinode2j)) ) {
                                        exists = true;
                                        break;
                                    }
                                }
                                if (!exists) {
                                    antinodes.add(antinode2);
                                    System.out.println(
                                            "Antinode 2 added: " + antinode2i + ", " + antinode2j + ", " + frequency);
                                    totalAntinodesStatic = totalAntinodesStatic.add(BigInteger.ONE);
                                }
                            }
                            // Antinodos adicionales para el segundo antinodo
                            prevAntinodei = antinode2i;
                            prevAntinodej = antinode2j;
                            while (true) {
                                int newAntinodei = prevAntinodei + (k - i);
                                int newAntinodej = prevAntinodej + (l - j);
                                if (newAntinodei >= 0 && newAntinodei < antennaPositions.size() && newAntinodej >= 0
                                        && newAntinodej < antennaPositions.get(newAntinodei).length) {
                                    String[] newAntinode = { String.valueOf(newAntinodei), String.valueOf(newAntinodej),
                                            frequency };
                                    boolean exists = false;
                                    for (String[] existingAntinode : antinodes) {
                                        if (existingAntinode[0].equals(String.valueOf(newAntinodei)) &&
                                                existingAntinode[1].equals(String.valueOf(newAntinodej))) {
                                            exists = true;
                                            break;
                                        }
                                    }
                                    if (!exists) {
                                        antinodes.add(newAntinode);
                                        System.out.println("Additional antinode added: " + newAntinodei + ", " + newAntinodej + ", " + frequency);
                                        totalAntinodesStatic = totalAntinodesStatic.add(BigInteger.ONE);
                                    }
                                    prevAntinodei = newAntinodei;
                                    prevAntinodej = newAntinodej;
                                } else {
                                    break;
                                }
                            }

                        }
                    }
                }
            }
        }
        return antinodes;
    }
}
