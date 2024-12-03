/*
 
The computer appears to be trying to run a program, but its memory (your puzzle input) is corrupted. All of the instructions have been jumbled up!

It seems like the goal of the program is just to multiply some numbers. It does that with instructions like mul(X,Y), where X and Y are each 1-3 digit numbers. For instance, mul(44,46) multiplies 44 by 46 to get a result of 2024. Similarly, mul(123,4) would multiply 123 by 4.

However, because the program's memory has been corrupted, there are also many invalid characters that should be ignored, even if they look like part of a mul instruction. Sequences like mul(4*, mul(6,9!, ?(12,34), or mul ( 2 , 4 ) do nothing.

For example, consider the following section of corrupted memory:

xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
Only the four highlighted sections are real mul instructions. Adding up the result of each instruction produces 161 (2*4 + 5*5 + 11*8 + 8*5).

Scan the corrupted memory for uncorrupted mul instructions. What do you get if you add up all of the results of the multiplications?

Part 2:

There are two new instructions you'll need to handle:

The do() instruction enables future mul instructions.
The don't() instruction disables future mul instructions.
Only the most recent do() or don't() instruction applies. At the beginning of the program, mul instructions are enabled.

 */

 import java.util.regex.Pattern;
 import java.nio.file.Files;
 import java.nio.file.Paths;
 import java.util.List;
 import java.util.regex.Matcher;
 import java.math.BigInteger;
 
public class Day03B {
    public static void main(String[] args) {
        String input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
        System.out.println(mul(input));

        try {
            // Lectura del fichero en 2 columnas
            List<String> lines = Files.readAllLines(Paths.get("res/Day03_input.txt"));
            BigInteger sum = BigInteger.ZERO;
            String totalLines = "";
            for (String line : lines) {
                totalLines += line;
            }
            sum = sum.add(mul(totalLines));
            System.out.println(sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BigInteger mul(String input) {
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)");
        Matcher matcher = pattern.matcher(input);
        BigInteger sum = BigInteger.ZERO;
        boolean enabled = true;

        while (matcher.find()) {
            String match = matcher.group();
            System.out.println(match);
            if (match.startsWith("do()")) {
            enabled = true;
            } else if (match.startsWith("don't()")) {
            enabled = false;
            } else if (enabled && match.startsWith("mul(")) {
            String[] numbers = match.substring(4, match.length() - 1).split(",");
            BigInteger num1 = new BigInteger(numbers[0]);
            BigInteger num2 = new BigInteger(numbers[1]);
            sum = sum.add(num1.multiply(num2));
            }
        }
        return sum;
    }
}