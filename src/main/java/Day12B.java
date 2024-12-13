/*
 * Each garden plot grows only a single type of plant and is indicated by a single letter on your map. 
 * When multiple garden plots are growing the same type of plant and are touching (horizontally or vertically), they form a region.
 * 
 * For example:

AAAA
BBCD
BBCC
EEEC
This 4x4 arrangement includes garden plots growing five different types of plants (labeled A, B, C, D, and E), each grouped into their own region.

Each garden plot is a square and so has four sides. 
The perimeter of a region is the number of sides of garden plots in the region that do not touch another garden plot in the same region. 
The type A and C plants are each in a region with perimeter 10. The type B and E plants are each in a region with perimeter 8. 
The lone D plot forms its own region with perimeter 4.

Due to "modern" business practices, the price of fence required for a region is found by multiplying that region's area by its perimeter. 
The total price of fencing all regions on a map is found by adding together the price of fence for every region on the map.

In the first example, region A has price 4 * 10 = 40, region B has price 4 * 8 = 32, region C has price 4 * 10 = 40, 
region D has price 1 * 4 = 4, and region E has price 3 * 8 = 24. So, the total price for the first example is 140.

 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Day12B {
    public static void main(String[] args) throws IOException {
        char[][] garden = {
                { 'A', 'A', 'A', 'A' },
                { 'B', 'B', 'C', 'D' },
                { 'B', 'B', 'C', 'C' },
                { 'E', 'E', 'E', 'C' }
        };

        // Haz que lea garden del fichero Day12_input.txt
        // List<String> lines = Files.readAllLines(Paths.get("res/Day12_input.txt"));
        // int rows = lines.size();
        // int cols = lines.get(0).length();
        // garden = new char[rows][cols];
        // for (int i = 0; i < rows; i++) {
        // garden[i] = lines.get(i).toCharArray();
        // }

        // Paso 1: encuentra todas las regiones y almacenalas en una colección. Pueden
        // existir diferentes regiones con la misma planta
        // Paso 2: calcula el perimetro de cada region
        // Paso 3: calcula el area de cada region
        // Paso 4: calcula el precio de la cerca de cada region

        // Paso 1
        List<List<int[]>> regions = new ArrayList<>();
        boolean[][] visited = new boolean[garden.length][garden[0].length];

        // Iterate through each cell in the garden
        for (int i = 0; i < garden.length; i++) {
            for (int j = 0; j < garden[0].length; j++) {
                if (!visited[i][j]) {
                    List<int[]> region = new ArrayList<>();
                    findRegion(garden, visited, i, j, garden[i][j], region);
                    if (!region.isEmpty()) {
                        regions.add(region);
                    }
                }
            }
        }

        // Under the bulk discount, instead of using the perimeter to calculate the
        // price, you need to use the number of sides each region has.
        // Each straight section of fence counts as a side, regardless of how long it
        // is.

        // Paso 2: Calculate sides for each region
        Map<List<int[]>, Integer> sides = new HashMap<>();
        for (List<int[]> region : regions) {
            Set<Integer> horizontalLines = new HashSet<>();
            Set<Integer> verticalLines = new HashSet<>();

            // Sort cells by position
            region.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

            // Check horizontal lines (top and bottom of the region)
            int currentRow = region.get(0)[0];
            int startCol = region.get(0)[1];
            for (int[] cell : region) {
                if (cell[0] != currentRow) {
                    horizontalLines.add(currentRow);
                    currentRow = cell[0];
                }
            }
            horizontalLines.add(currentRow);

            // Sort by column for vertical lines
            region.sort((a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]);

            // Check vertical lines (left and right of the region)
            int currentCol = region.get(0)[1];
            for (int[] cell : region) {
                if (cell[1] != currentCol) {
                    verticalLines.add(currentCol);
                    currentCol = cell[1];
                }
            }
            verticalLines.add(currentCol);

            sides.put(region, horizontalLines.size() + verticalLines.size());
        }

        // Print sides for each region
        for (List<int[]> region : regions) {
            System.out.println(region + " " + sides.get(region));
        }

        // Paso 3
        Map<List<int[]>, Integer> areas = new HashMap<>();
        for (List<int[]> region : regions) {
            areas.put(region, region.size());
        }

        // Paso 4
        int totalFenceCost = 0;

        for (List<int[]> region : regions) {
            int area = areas.get(region);
            int perimeter = sides.get(region);
            totalFenceCost += area * perimeter;
        }

        System.out.println(totalFenceCost);

    }

    // Helper method to find a region using DFS
    private static void findRegion(char[][] garden, boolean[][] visited, int row, int col, char plant,
            List<int[]> region) {
        if (row < 0 || row >= garden.length || col < 0 || col >= garden[0].length
                || visited[row][col] || garden[row][col] != plant) {
            return;
        }

        visited[row][col] = true;
        region.add(new int[] { row, col });

        // Check all four directions
        findRegion(garden, visited, row + 1, col, plant, region);
        findRegion(garden, visited, row - 1, col, plant, region);
        findRegion(garden, visited, row, col + 1, plant, region);
        findRegion(garden, visited, row, col - 1, plant, region);
    }

}
