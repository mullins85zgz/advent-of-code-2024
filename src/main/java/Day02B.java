
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

 Part 2:
 Now, the same rules apply as before, except if removing a single level from an unsafe report would make it safe, the report instead counts as safe.

 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day02B {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("res/Day02_input.txt"));
        int count = 0;
        int lineaLeida = 0;
        for (String line : lines) {
            lineaLeida++;
            List<Integer> levels = new ArrayList<>();
            for (String level : line.split(" ")) {
                levels.add(Integer.parseInt(level));
            }
            if (isSafe(levels)) {
                count++;
            }

            if (lineaLeida == 25) {
                if (isSafe(levels)) {
                    System.out.println("Linea " + lineaLeida + " es segura");
                } else {
                    System.out.println("Linea " + lineaLeida + " NO es segura");
                }
            }
        }
        System.out.println(count);
    }

    private static boolean isSafe(List<Integer> levels) {
        if (isSafe(levels, 0, levels.size() - 1)) {
            return true;
        }
        for (int i = 0; i < levels.size(); i++) {
            List<Integer> copy = new ArrayList<>(levels);
            copy.remove(i);
            if (isSafe(copy, 0, copy.size() - 1)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSafe(List<Integer> levels, int start, int end) {
        boolean increasing = true;
        boolean decreasing = true;
        for (int i = start; i < end; i++) {
            if (levels.get(i) < levels.get(i + 1)) {
                decreasing = false;
            }
            if (levels.get(i) > levels.get(i + 1)) {
                increasing = false;
            }
            if (levels.get(i) == levels.get(i + 1)) {
                increasing = false;
                decreasing = false;
            }
            if (!increasing && !decreasing) {
                return false;
            }
            // (Math.abs(levels.get(i) - levels.get(i - 1)) < 1 || Math.abs(levels.get(i) -
            // levels.get(i - 1)) > 3)
            if (Math.abs(levels.get(i) - levels.get(i + 1)) > 3) {
                return false;
            }
        }
        return true;
    }

}