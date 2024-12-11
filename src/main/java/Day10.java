/*
 * Input
 * The topographic map indicates the height at each position using a scale from 0 (lowest) to 9 (highest).
 * 
 * A hiking trail is any path that starts at height 0, ends at height 9, and always increases by a height of exactly 1 at each step
 * Hiking trails never include diagonal steps - only up, down, left, or right (from the perspective of the map).
 * 
 * A trailhead is any position that starts one or more hiking trails - here, these positions will always have height 0. 
 * Assembling more fragments of pages, you establish that a trailhead's score is the number of 9-height positions reachable from that trailhead via a hiking trail.
 * 
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day10 {
    public static void main (String[] args) {
        int[][] map = readMap();
        int score = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 0) {
                    score += countPaths(map, i, j);
                }
            }
        }

        System.out.println(score);

    }

    public static int countPaths(int[][] map, int i, int j) {
        int count = 0;
        if (map[i][j] == 9) {
            return 1;
        }
        if (map[i][j] == 0) {
            map[i][j] = 1;
            if (i > 0 && map[i - 1][j] == 1) {
                count += countPaths(map, i - 1, j);
            }
            if (i < map.length - 1 && map[i + 1][j] == 1) {
                count += countPaths(map, i + 1, j);
            }
            if (j > 0 && map[i][j - 1] == 1) {
                count += countPaths(map, i, j - 1);
            }
            if (j < map[0].length - 1 && map[i][j + 1] == 1) {
                count += countPaths(map, i, j + 1);
            }
            map[i][j] = 0;
        }
        return count;
    }



    public static int[][] readMap() {
        
        int lineSize = 0;
        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("res/Day10_inputTest.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
                lineSize = line.length();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[][] map = new int[lines.size()][lineSize];

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < lineSize; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        return map;
    }
}
