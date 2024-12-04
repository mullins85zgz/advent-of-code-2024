/*
As the search for the Chief continues, a small Elf who lives on the station tugs on your shirt;
 she'd like to know if you could help her with her word search (your puzzle input). She only has to find one word: XMAS.

This word search allows words to be horizontal, vertical, diagonal, written backwards, or even overlapping other words. 
It's a little unusual, though, as you don't merely need to find one instance of XMAS - you need to find all of them. 

 */

 import java.nio.file.Files;
 import java.nio.file.Paths;
 import java.util.List;
 
 public class Day04B {
     public static void main(String[] args) {
         String example = ".M.S......\r\n" +
                            "..A..MSMS.\r\n" +
                            ".M.S.MAA..\r\n" +
                            "..A.ASMSM.\r\n" +
                            ".M.S.M....\r\n" +
                            "..........\r\n" +
                            "S.S.S.S.S.\r\n" +
                            ".A.A.A.A..\r\n" +
                            "M.M.M.M.M.\r\n" +
                            "..........";         
 
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
                 if (grid[i][j] == 'A') {
                    /*
                     * Looking for the instructions, you flip over the word search to find that this isn't actually an XMAS puzzle;
                     *  it's an X-MAS puzzle in which you're supposed to find two MAS in the shape of an X. One way to achieve that is like this:

                        M.S
                        .A.
                        M.S
                     */
                    int countTotalDiagonals = 0;

                    // Check diagonally down-right from A character if is surrounded by M and S, forming MAS word
                    if (i + 1 < grid.length && j + 1 < grid[i].length &&
                        i - 1 >= 0 && j - 1 >= 0 &&
                        grid[i - 1][j - 1] == 'M' &&
                        grid[i + 1][j + 1] == 'S') {
                        countTotalDiagonals++;
                    }
                    // Check diagonally down-right from A character if is surrounded by S and M, forming SAM word
                    if (i + 1 < grid.length && j + 1 < grid[i].length &&
                        i - 1 >= 0 && j - 1 >= 0 &&
                        grid[i - 1][j - 1] == 'S' &&
                        grid[i + 1][j + 1] == 'M') {
                            countTotalDiagonals++;
                    }
                        
                    // Check diagonally down-left from A character if is surrounded by M and S, forming MAS word
                    if (i + 1 < grid.length && j - 1 >= 0 &&
                        i - 1 >= 0 && j + 1 < grid[i].length &&
                        grid[i - 1][j + 1] == 'M' &&
                        grid[i + 1][j - 1] == 'S') {
                        countTotalDiagonals++;
                    }

                    // Check diagonally down-left from A character if is surrounded by S and M, forming SAM word
                    if (i + 1 < grid.length && j - 1 >= 0 &&
                        i - 1 >= 0 && j + 1 < grid[i].length &&
                        grid[i - 1][j + 1] == 'S' &&
                        grid[i + 1][j - 1] == 'M') {
                        countTotalDiagonals++;
                    }


                    if(countTotalDiagonals > 1){
                        count++;
                    }
                 }
             }
         }
         return count;
     }
 
 }
 