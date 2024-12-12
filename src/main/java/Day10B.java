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

public class Day10B {
    public static void main(String[] args) {
        int[][] map = readMap();
        int score = 0;

        // Given the map, we need to find the trailheads.
        // A trailhead is any position that starts one or more hiking trails - here,
        // these positions will always have height 0.
        // Assembling more fragments of pages, you establish that a trailhead's score is
        // the number of 9-height positions reachable from that trailhead via a hiking
        // trail.

        // We need to find the trailheads
        // We need to find the 9-height positions reachable from that trailhead via a
        // hiking trail
        // We need to calculate the score of each trailhead

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) {
                    score += findTrails(map, i, j);
                }
            }
        }

        System.out.println(score);

    }

    public static int findTrails(int[][] map, int i, int j) {
        boolean[][] visited = new boolean[map.length][map[0].length];
        boolean[][] reachedNine = new boolean[map.length][map[0].length];
        return findTrailsHelper(map, i, j, visited, reachedNine);
    }

    private static int findTrailsHelper(int[][] map, int i, int j, boolean[][] visited, boolean[][] reachedNine) {
        int score = 0;
        int[] dx = { 0, 0, 1, -1 };
        int[] dy = { 1, -1, 0, 0 };

        visited[i][j] = true;

        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];

            if (x >= 0 && x < map.length && y >= 0 && y < map[x].length && !visited[x][y]) {
                if (map[x][y] == 9 && map[i][j] == 8 && !reachedNine[x][y]) {
                    score++;
                    // reachedNine[x][y] = true;
                    // System.out.println("Reached 9 at: " + x + ", " + y);
                } else if (map[x][y] == map[i][j] + 1) {
                    score += findTrailsHelper(map, x, y, visited, reachedNine);
                }
            }
        }

        visited[i][j] = false;
        return score;
    }

    public static int[][] readMap() {

        int lineSize = 0;
        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("res/Day10_input.txt"))) {
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
