package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

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

    public int coinChange2(int[] coins, int amount) {
        if (amount < 0 || coins.length < 1) return -1;
        if (amount == 0) return 0;
        int length = coins.length;
        System.out.println("coinChange new iteration");
        System.out.println("length " + length);
        int[] countCoinsArray = new int[length];
        Integer[] arrayCoins = Arrays.stream(coins).boxed().sorted(Comparator.reverseOrder()).toArray(Integer[]::new);
        int currentAmount = amount;
        for (int i = 0; i < length; i++) {
            countCoinsArray[i] = currentAmount / arrayCoins[i];
            currentAmount -= (countCoinsArray[i] * arrayCoins[i]);
        }
        if (currentAmount == 0) {
            return Arrays.stream(countCoinsArray).sum();
        } else {
            return -1;
        }
    }

    public int coinChange3(int[] coins, int amount) {
        if (amount < 0 || coins.length < 1) return -1;
        if (amount == 0) return 0;
        int length = coins.length;
        int[] countCoinsArray = new int[length];
        Integer[] sortedCoins = Arrays.stream(coins).boxed().sorted(Comparator.reverseOrder()).toArray(Integer[]::new);
        int currentAmount = amount;
        System.out.println("coinChange new iteration");
        System.out.println("length " + length);
        System.out.println("bigger coin " + sortedCoins[0]);
        System.out.println("amount " + amount);
        for (int i = 0; i < length; i++) {
            countCoinsArray[i] = currentAmount / sortedCoins[i];
            if (currentAmount % sortedCoins[i] == 0) {
                return countCoinsArray[i];
            } else {
                System.out.println("countCoinsArray.length " + countCoinsArray.length);
                if (countCoinsArray.length > 1) {
                    for (int j = 0; j < countCoinsArray[i]; j++) {
                        int newCurrentAmount = amount - ((countCoinsArray[i] - j) * sortedCoins[i]);
                        int coinVal = coinChange3(copyCoins(sortedCoins, i), newCurrentAmount);
                        if (coinVal != -1 && coinVal != 0) {
                            countCoinsArray[i + 1] = coinVal;
                            countCoinsArray[i] -= j;
                            if (newCurrentAmount == (countCoinsArray[i + 1] * sortedCoins[i + 1])) {
                                return Arrays.stream(countCoinsArray).sum();
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    private int[] copyCoins(Integer[] sortedCoins, int trimVal) {
        int[] coins = new int[sortedCoins.length - 1 - trimVal];
        for (int i = 0; i < coins.length; i++) {
            coins[i] = sortedCoins[i + 1 + trimVal];
        }
        LinkedList a = new LinkedList();
        return coins;
    }

    public int coinChange(int[] coins, int amount) {
        if (amount < 0 || coins.length < 1) return -1;
        if (amount == 0) return 0;
        int[] arrayAmount = new int[amount + 1];
        Arrays.fill(arrayAmount, Integer.MAX_VALUE);
        arrayAmount[0] = 0;
        for (int coin : coins) {
            for (int i = coin; i < amount + 1; i++) {
                arrayAmount[i] = Math.min(arrayAmount[i], arrayAmount[i - coin] + 1);
            }
        }
        if (arrayAmount[arrayAmount.length - 1] < Integer.MAX_VALUE) {
            return arrayAmount[arrayAmount.length - 1];
        } else
            return -1;
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
