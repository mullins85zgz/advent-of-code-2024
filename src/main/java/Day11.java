/*

As you observe them for a while, you find that the stones have a consistent behavior. 
Every time you blink, the stones each simultaneously change according to the first applicable rule in this list:

If the stone is engraved with the number 0, it is replaced by a stone engraved with the number 1.
If the stone is engraved with a number that has an even number of digits, it is replaced by two stones. 
The left half of the digits are engraved on the new left stone, and the right half of the digits are engraved on the new right stone. 
(The new numbers don't keep extra leading zeroes: 1000 would become stones 10 and 0.)
If none of the other rules apply, the stone is replaced by a new stone; the old stone's number multiplied by 2024 is engraved on the new stone.

Consider the arrangement of stones in front of you. How many stones will you have after blinking 25 times?

 */

import java.math.BigInteger;

public class Day11 {
    public static void main(String[] args) {
        // String input = "125 17";
        String input = "77 515 6779622 6 91370 959685 0 9861";
        for (int i = 0; i < 75; i++) {
            input = lookAndSay(input);
        }

        BigInteger bigInteger = BigInteger.ZERO;
        String[] split = input.split(" ");
        for (String s : split) {
            bigInteger = bigInteger.add(BigInteger.ONE);
        }
        // System.out.println("Part 1: " + input);
        System.out.println(bigInteger);
    }

    private static String lookAndSay(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] split = input.split(" ");
        for (String s : split) {
            if (s.equals("0")) {
                stringBuilder.append("1 ");
            } else if (s.length() % 2 == 0) {
                int half = s.length() / 2;
                String part1 = s.substring(0, half);
                String part2 = s.substring(half, s.length());
                if (Integer.parseInt(part2) == 0) {
                    part2 = "0";
                } else {
                    // Elimina los ceros por la izquierda
                    part2 = String.valueOf(Integer.parseInt(part2));
                }
                stringBuilder.append(part1).append(" ").append(part2).append(" ");
            } else {
                stringBuilder.append(new BigInteger(s).multiply(BigInteger.valueOf(2024))).append(" ");
            }
        }
        return stringBuilder.toString().trim();
    }
}
