/*
 The map shows the current position of the guard with ^ (to indicate the guard is currently 
 facing up from the perspective of the map). Any obstructions - crates, desks, alchemical reactors, etc. - are shown as #.

Lab guards in 1518 follow a very strict patrol protocol which involves repeatedly following these steps:

If there is something directly in front of you, turn right 90 degrees.
Otherwise, take a step forward.
Following the above protocol, the guard moves up several times until she reaches an obstacle (in this case, a pile of failed suit prototypes):

Predict the path of the guard. How many distinct positions will the guard visit before leaving the mapped area?

 */

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

public class Day06 {
    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("res/Day06_input.txt"));
            ArrayList<String> map = new ArrayList<>();
            for (String line : lines) {
                map.add(line);
            }
            int x = 0;
            int y = 0;
            int dx = 0;
            int dy = -1;
            int count = 1;

            // Find the initial position of the guard
            for (int i = 0; i < map.size(); i++) {
                int index = map.get(i).indexOf('^');
                if (index != -1) {
                    x = index;
                    y = i;
                    break;
                }
            }

            System.out.println("Initial position: " + x + ", " + y);

            boolean yaGirado = false;

            // Move the guard according to the rules
            HashSet<String> visitedPositions = new HashSet<>();
            String position = x + "," + y;
            visitedPositions.add(position);

            while (true) {

                char c = map.get(y).charAt(x);
                System.out.println("Current char: " + c);
                if (c == '#' && !yaGirado) {

                    // Step back
                    x -= dx;
                    y -= dy;
                    count--;

                    if (dy == 0 && dx == 1) {
                        // Turn down
                        System.out.println("Turn down, count = " + count);
                        dy = 1;
                        dx = 0;
                    } else if (dy == 0 && dx == -1) {
                        // Turn up
                        System.out.println("Turn up, count = " + count);
                        dy = -1;
                        dx = 0;
                    } else if (dy == 1 && dx == 0) {
                        // Turn left
                        System.out.println("Turn left, count = " + count);
                        dy = 0;
                        dx = -1;
                    } else if (dy == -1 && dx == 0) {
                        // Turn right
                        System.out.println("Turn right, count = " + count);
                        dy = 0;
                        dx = 1;
                    }

                    yaGirado = true;

                } else {
                    // Move the guard
                    x += dx;
                    y += dy;
                    yaGirado = false;

                    position = x + "," + y;
                    if (!visitedPositions.contains(position)) {
                        visitedPositions.add(position);
                        count++;
                    }

                }
                // Check if the guard is out of bounds
                if (x < 0 || x >= map.get(0).length() || y < 0 || y >= map.size()) {
                    count--;
                    break;
                }
            }
            System.out.println(count);
            // for (String line : map2) {
            // System.out.println(line);
            // }

            // Print the map with visited positions

            int nuevoContador = 0;
            for (int i = 0; i < map.size(); i++) {
                StringBuilder line = new StringBuilder(map.get(i));
                for (int j = 0; j < line.length(); j++) {
                    position = j + "," + i;
                    if (map.get(i).charAt(j) == '#') {
                        line.setCharAt(j, '#');
                    } else if (visitedPositions.contains(position)) {
                        line.setCharAt(j, 'o');
                        nuevoContador++;
                    }
                }
                System.out.println(line.toString());

            }
            System.out.println("Nuevo contador: " + nuevoContador);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
