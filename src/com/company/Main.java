package com.company;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static int convertingToArabicNumerals(String number) {
        if (Objects.equals(number, "I")) {
            return 1;
        } else if (Objects.equals(number, "II")) {
            return 2;
        } else if (Objects.equals(number, "III")) {
            return 3;
        } else if (Objects.equals(number, "IV")) {
            return 4;
        } else if (Objects.equals(number, "V")) {
            return 5;
        } else if (Objects.equals(number, "VI")) {
            return 6;
        } else if (Objects.equals(number, "VII")) {
            return 7;
        } else if (Objects.equals(number, "VIII")) {
            return 8;
        } else if (Objects.equals(number, "IX")) {
            return 9;
        } else if (Objects.equals(number, "X")) {
            return 10;
        } else {
            return 0;
        }
    }

    private static String takeOperator(String expression) {
        String resultOperator = null;
        for (int i = 0; i < expression.length(); ++i) {
            char number = expression.charAt(i);

            if (number == '-') {
                resultOperator = "-";
                break;
            } else if (number == '+') {
                resultOperator = "+";
                break;
            } else if (number == '*') {
                resultOperator = "*";
                break;
            } else if (number == '/') {
                resultOperator = "/";
                break;
            }
        }
        return resultOperator;
    }

    private static int calculations(String operator, int numFirst, int numSecond) {
        switch (operator) {
            case "-" -> {
                return (numFirst - numSecond);
            }
            case "+" -> {
                return (numFirst + numSecond);
            }
            case "*" -> {
                return (numFirst * numSecond);
            }
            case "/" -> {
                return (numFirst / numSecond);
            }
        }
        return 0;
    }


    public static void main(String[] args) throws Exception {

        String[] romanNumerals = {
                "O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };

        Scanner in = new Scanner(System.in);

        String expression = in.nextLine().trim();
        String operator = takeOperator(expression);

        if (operator == null) {
            throw new Exception("неизвестная операция");
        }

        String[] numbers = expression.split("[-+*/]");
        String numFirst = numbers[0];
        String numSecond = numbers[1];


        int numFirstInt = convertingToArabicNumerals(numFirst.trim());
        int numSecondInt = convertingToArabicNumerals(numSecond.trim());

        if (numFirstInt == 0 && numSecondInt == 0) {
            int result = calculations(operator, Integer.parseInt(numFirst.trim()), Integer.parseInt(numSecond.trim()));
            System.out.print(result);
        } else if (numFirstInt == 0 || numSecondInt == 0) {
            throw new Exception("используются одновременно разные системы счисления");
        } else {
            int result = calculations(operator, numFirstInt, numSecondInt);
            if (result < 0) {
                throw new Exception("в римской системе нет отрицательных чисел");
            }
            System.out.print(romanNumerals[result]);
        }
    }
}
