import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day08 {
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
        for (String[] antinode : antinodes) {
            int i = Integer.parseInt(antinode[0]);
            int j = Integer.parseInt(antinode[1]);
            antennaPositions.get(i)[j] = "#";
        }

        // 3. Imprimir la matriz
        for (String[] position : antennaPositions) {
            for (String s : position) {
                System.out.print(s);
            }
            System.out.println();
        }
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

    // Calcular los antinodos.
    // An antinode occurs at any point that is perfectly in line with two antennas
    // of the same frequency
    // Same frequency means that the antennas have the same value
    // This means that for any pair of antennas with the same frequency, there are
    // two antinodes, one on either side of them.
    // So, for these two antennas with frequency a, they create the two antinodes
    // marked with #:

    // ..........
    // ...#......
    // ..........
    // ....a.....
    // ..........
    // .....a....
    // ..........
    // ......#...
    // ..........
    // ..........
    public static List<String[]> calculateAntinodes(List<String[]> antennaPositions) {
        List<String[]> antinodes = new ArrayList<>();
        for (int i = 0; i < antennaPositions.size(); i++) {
            for (int j = 0; j < antennaPositions.get(i).length; j++) {
                if (antennaPositions.get(i)[j].equals(".")) {
                    continue;
                }
                String value = antennaPositions.get(i)[j];
                // Check to the right
                if (j + 1 < antennaPositions.get(i).length && antennaPositions.get(i)[j + 1].equals(value)) {
                    antinodes.add(new String[] { String.valueOf(i), String.valueOf(j + 2) });
                }
                // Check to the left
                if (j - 1 >= 0 && antennaPositions.get(i)[j - 1].equals(value)) {
                    antinodes.add(new String[] { String.valueOf(i), String.valueOf(j - 2) });
                }
                // Check below
                if (i + 1 < antennaPositions.size() && antennaPositions.get(i + 1)[j].equals(value)) {
                    antinodes.add(new String[] { String.valueOf(i + 2), String.valueOf(j) });
                }
                // Check above
                if (i - 1 >= 0 && antennaPositions.get(i - 1)[j].equals(value)) {
                    antinodes.add(new String[] { String.valueOf(i - 2), String.valueOf(j) });
                }
            }
        }
        return antinodes;
    }
}
