/*
As the search for the Chief continues, a small Elf who lives on the station tugs on your shirt;
 she'd like to know if you could help her with her word search (your puzzle input). She only has to find one word: XMAS.

This word search allows words to be horizontal, vertical, diagonal, written backwards, or even overlapping other words. 
It's a little unusual, though, as you don't merely need to find one instance of XMAS - you need to find all of them. 

 */


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day04 {
    public static void main(String[] args) {
        String example = "MMMSXXMASM\r\n" + //
                        "MSAMXMSMSA\r\n" + //
                        "AMXSXMAAMM\r\n" + //
                        "MSAMASMSMX\r\n" + //
                        "XMASAMXAMM\r\n" + //
                        "XXAMMXXAMA\r\n" + //
                        "SMSMSASXSS\r\n" + //
                        "SAXAMASAAA\r\n" + //
                        "MAMMMXMMMM\r\n" + //
                        "MXMXAXMASX";
        

        char[][] grid = convertToGrid(example);
        for (char[] row : grid) {
            for (char c : row) {
            System.out.print(c + " ");
            }
            System.out.println();
        }

        System.out.println("XMAS appears " + countXMAS(grid) + " times.");

        //Ahora lo haremos desde fichero
        try {
            List<String> lines = Files.readAllLines(Paths.get("res/Day04_input.txt"));

            char[][] grid2 = convertToGrid(String.join("\r\n", lines));
            System.out.println("XMAS appears " + countXMAS(grid2) + " times.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static char[][] convertToGrid(String input) {
        String[] lines = input.split("\r\n");
        char[][] grid = new char[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            grid[i] = lines[i].toCharArray();
        }
        return grid;
     }
    
    private static int countXMAS(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 'X') {
                    // Check vertically downwards
                    if (i + 3 < grid.length &&
                        grid[i + 1][j] == 'M' &&
                        grid[i + 2][j] == 'A' &&
                        grid[i + 3][j] == 'S') {
                        count++;
                    }
                    // Check vertically upwards
                    if (i - 3 >= 0 &&
                        grid[i - 1][j] == 'M' &&
                        grid[i - 2][j] == 'A' &&
                        grid[i - 3][j] == 'S') {
                        count++;
                    }

                    // Check horizontally to the right
                    if (j + 3 < grid[i].length &&
                        grid[i][j + 1] == 'M' &&
                        grid[i][j + 2] == 'A' &&
                        grid[i][j + 3] == 'S') {
                        count++;
                    }
                    // Check horizontally to the left
                    if (j - 3 >= 0 &&
                        grid[i][j - 1] == 'M' &&
                        grid[i][j - 2] == 'A' &&
                        grid[i][j - 3] == 'S') {
                        count++;
                    }
                    // Check diagonally down-right
                    if (i + 3 < grid.length && j + 3 < grid[i].length &&
                        grid[i + 1][j + 1] == 'M' &&
                        grid[i + 2][j + 2] == 'A' &&
                        grid[i + 3][j + 3] == 'S') {
                        count++;
                    }
                    // Check diagonally down-left
                    if (i + 3 < grid.length && j - 3 >= 0 &&
                        grid[i + 1][j - 1] == 'M' &&
                        grid[i + 2][j - 2] == 'A' &&
                        grid[i + 3][j - 3] == 'S') {
                        count++;
                    }
                    // Check diagonally up-right
                    if (i - 3 >= 0 && j + 3 < grid[i].length &&
                        grid[i - 1][j + 1] == 'M' &&
                        grid[i - 2][j + 2] == 'A' &&
                        grid[i - 3][j + 3] == 'S') {
                        count++;
                    }
                    // Check diagonally up-left
                    if (i - 3 >= 0 && j - 3 >= 0 &&
                        grid[i - 1][j - 1] == 'M' &&
                        grid[i - 2][j - 2] == 'A' &&
                        grid[i - 3][j - 3] == 'S') {
                        count++;
                    }
                }
            }
        }
        return count;
    }

}
