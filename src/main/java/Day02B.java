
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

                    /*
                     Part 2:
                    Now, the same rules apply as before, except if removing a single level from an unsafe report would make it safe, the report instead counts as safe.
                    */                    
                    if (!increasing && !decreasing) {
                        for (int i = 0; i < levels.size(); i++) {
                            List<Integer> copy = new ArrayList<>(levels);
                            copy.remove(i);
                            boolean increasingCopy = true;
                            boolean decreasingCopy = true;
                            for (int j = 1; j < copy.size(); j++) {
                                if (copy.get(j) > copy.get(j - 1)) {
                                    decreasingCopy = false;
                                } else if (copy.get(j) < copy.get(j - 1)) {
                                    increasingCopy = false;
                                }else{
                                    //Si son iguales, no se puede eliminar
                                    increasingCopy = false;
                                    decreasingCopy = false;
                                }
                            }
                            if ((increasingCopy || decreasingCopy) && isValid(copy)) {
                                validReports++;
                                break;
                            }
                        }
                    } else  {
                        if (isValid(levels)) {
                            validReports++;
                        }
                    }



                    // if ((increasing || decreasing) && isValid(levels)) {
                    //     validReports++;
                    // }
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