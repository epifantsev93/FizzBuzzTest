package org.example;

import java.util.Arrays;
import java.util.HashMap;

public class TestInterview {
    public byte[] fizzBuzzTest(int value) {
        StringBuilder stringBuilder = new StringBuilder();
        if (value % 3 == 0) {
            stringBuilder.append("Fizz");
        }
        if (value % 5 == 0) {
            stringBuilder.append("Buzz");
        }
        if (!stringBuilder.isEmpty()) {
            return stringBuilder.toString().getBytes();
        } else {
            throw new IllegalArgumentException("value not deleted to 3 or 5");
        }
    }

    public void printlnVal() {
        for (int i = 0; i < 1001; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                String valString = String.valueOf(i);
                int sum = 0;
                for (int j = 0; j < valString.length(); j++) {
                    sum = sum + valString.charAt(j) - '0';
                }
                if (sum < 10)
                    System.out.println(i);
            }

        }
    }

    public int coinChange(int[] coins, int amount) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        int countCoins = 0;
        Arrays.sort(coins);
        for (int i = coins.length - 1; i >= 0; i--) {
            int currentCountCoins = amount / coins[i];
            countCoins += currentCountCoins;
            amount -= (currentCountCoins * coins[i]);
        }
        if (amount == 0) {
            return countCoins;
        } else return -1;
    }


    public int romanToInt(String s) {
        HashMap hashMapResult = convert(new StringBuilder().append(s), 0, 'M', 'D', 'C', 100);
        hashMapResult = convert((StringBuilder) hashMapResult.get("str"), (int) hashMapResult.get("sum"), 'C', 'L', 'X', 10);
        hashMapResult = convert((StringBuilder) hashMapResult.get("str"), (int) hashMapResult.get("sum"), 'X', 'V', 'I', 1);
        return (int) hashMapResult.get("sum");
    }

    public HashMap convert(StringBuilder str, int sum, char romNum1, char romNum2, char romNum3, int factor) {
        int length = str.length();
        if (length > 0 && str.charAt(0) == romNum1) {
            if (length > 1 && str.charAt(1) == romNum1) {
                if (length > 2 && str.charAt(2) == romNum1) {
                    sum += (30 * factor);
                    str.delete(0, 3);
                } else {
                    sum += (20 * factor);
                    str.delete(0, 2);
                }
            } else {
                sum += (10 * factor);
                str.deleteCharAt(0);
            }
        }
        length = str.length();
        if (length > 0 && str.charAt(0) == romNum2) {
            sum += (5 * factor);
            str.deleteCharAt(0);
            length = str.length();
        }
        if (length > 0 && str.charAt(0) == romNum3) {
            if (length > 1 && str.charAt(1) == romNum1) {
                sum += (9 * factor);
                str.delete(0, 2);
            } else if (length > 1 && str.charAt(1) == romNum2) {
                sum += (4 * factor);
                str.delete(0, 2);
            } else {
                if (length > 1 && str.charAt(1) == romNum3) {
                    if (length > 2 && str.charAt(2) == romNum3) {
                        sum += (3 * factor);
                        str.delete(0, 3);
                    } else {
                        sum += (2 * factor);
                        str.delete(0, 2);
                    }
                } else {
                    sum += (1 * factor);
                    str.deleteCharAt(0);
                }
            }
        }
        HashMap hashMap = new HashMap<>();
        hashMap.put("str", str);
        hashMap.put("sum", sum);
        return hashMap;
    }
}
