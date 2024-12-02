
/*
 The unusual data (your puzzle input) consists of many reports, one report per line.
 Each report is a list of numbers called levels that are separated by spaces. For example:

7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9

 This example data contains six reports each containing five levels.

 The engineers are trying to figure out which reports are safe. The Red-Nosed reactor safety systems can only tolerate levels that are either gradually increasing or gradually decreasing. So, a report only counts as safe if both of the following are true:

 The levels are either all increasing or all decreasing.
 Any two adjacent levels differ by at least one and at most three.

 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day02 {
    public static void main(String[] args) {
                    try {
                //Lectura del fichero en 2 columnas
                List<String> lines = Files.readAllLines(Paths.get("res/Day02_input.txt"));

                int validReports = 0;
                for (String line : lines) {
                    String[] parts = line.split("\\s+");
                    List<Integer> levels = new ArrayList<>();
                    for (String part : parts) {
                        levels.add(Integer.parseInt(part));
                    }

                    boolean increasing = true;
                    boolean decreasing = true;
                    for (int i = 1; i < levels.size(); i++) {
                        if (levels.get(i) > levels.get(i - 1)) {
                            decreasing = false;
                        } else if (levels.get(i) < levels.get(i - 1)) {
                            increasing = false;
                        }else{
                            //Si son iguales, no se puede eliminar
                            increasing = false;
                            decreasing = false;
                        }
                    }

                    if ((increasing || decreasing) && isValid(levels)) {
                        validReports++;
                    }
                }

                System.out.println(validReports);

            } catch (IOException e) {
                e.printStackTrace();
            } 
            
    }

    private static boolean isValid(List<Integer> levels) {
        for (int i = 1; i < levels.size(); i++) {
            if (Math.abs(levels.get(i) - levels.get(i - 1)) < 1 || Math.abs(levels.get(i) - levels.get(i - 1)) > 3) {
                return false;
            }
        }
        return true;
    }
}