package com.company;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static String availableStrings = "0123456789+-*/IVX";
    public static String[] romanNumerals = {
            "O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);

        String expression = in.nextLine().trim().replaceAll(" ", "");

        containsAllCharacters(expression);
        String operator = takeOperator(expression);

        String[] numbers = expression.split("[-+*/]");

        if (numbers.length > 2)
            throw new InvalidSizeOfExpression();

        String numFirst = numbers[0];
        String numSecond = numbers[1];


        int numFirstInt = convertingToArabicNumerals(numFirst);
        int numSecondInt = convertingToArabicNumerals(numSecond);

        if (numFirstInt == 0 && numSecondInt == 0) {
            if (Integer.parseInt(numFirst) < 1 || Integer.parseInt(numFirst) > 10 ||
                    Integer.parseInt(numSecond) < 1 || Integer.parseInt(numSecond) > 10)
                throw new InvalidNumbers();

            int result = calculations(operator, Integer.parseInt(numFirst), Integer.parseInt(numSecond));
            System.out.print(result);
        } else if (numFirstInt == 0 || numSecondInt == 0) {
            throw new NotSameNumberSystemsException();
        } else {
            if (numFirstInt < 1 || numFirstInt > 10 || numSecondInt < 1 || numSecondInt > 10)
                throw new InvalidNumbers();

            int result = calculations(operator, numFirstInt, numSecondInt);
            if (result <= 0)
                throw new NotNegativeNumbers();
            System.out.print(romanNumerals[result]);
        }
    }

    private static void containsAllCharacters(String expression) throws Exception {
        for (char i : expression.toCharArray()) {
            if (availableStrings.indexOf(i) == -1)
                throw new InvalidSymbols();
        }
    }

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

    private static String takeOperator(String expression) throws UnknownOperation {
        char number = 0;
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

}


class NotSameNumberSystemsException extends Exception {
    public NotSameNumberSystemsException() {
        super("Используются одновременно разные системы счисления");
    }
}

class InvalidNumbers extends Exception {
    public InvalidNumbers() {
        super("Введены числа недопустимого размера");
    }
}

class NotNegativeNumbers extends Exception {
    public NotNegativeNumbers() {
        super("В римской системе нет отрицательных чисел");
    }
}

class UnknownOperation extends Exception {
    public UnknownOperation() {
        super("Неизвестная операция");
    }
}

class InvalidSymbols extends Exception {
    public InvalidSymbols() {
        super("Введены недопустимые символы");
    }
}

class InvalidSizeOfExpression extends Exception {
    public InvalidSizeOfExpression() {
        super("Недопустимые размер выражения");
    }
}