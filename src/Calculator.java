import java.util.*;

class Calculator {
    TreeMap<Integer, String> romanToArabicTreeMap = new TreeMap<>();
    Map<String, Integer> validRomanNumerals = new HashMap<>();
    List<String> validArabicNumerals = new ArrayList<>();
    String[] validOperators;

    Calculator() {
        romanToArabicTreeMap.put(1, "I");
        romanToArabicTreeMap.put(4, "IV");
        romanToArabicTreeMap.put(5, "V");
        romanToArabicTreeMap.put(9, "IX");
        romanToArabicTreeMap.put(10, "X");
        romanToArabicTreeMap.put(40, "XL");
        romanToArabicTreeMap.put(50, "L");
        romanToArabicTreeMap.put(90, "XC");
        romanToArabicTreeMap.put(100, "C");
        romanToArabicTreeMap.put(400, "CD");
        romanToArabicTreeMap.put(500, "D");
        romanToArabicTreeMap.put(900, "CM");
        romanToArabicTreeMap.put(1000, "M");

        validRomanNumerals.put("I", 1);
        validRomanNumerals.put("II", 2);
        validRomanNumerals.put("III", 3);
        validRomanNumerals.put("IV", 4);
        validRomanNumerals.put("V", 5);
        validRomanNumerals.put("VI", 6);
        validRomanNumerals.put("VII", 7);
        validRomanNumerals.put("VIII", 8);
        validRomanNumerals.put("IX", 9);
        validRomanNumerals.put("X", 10);

        for (int i = 1; i <= 10; i++) {
            validArabicNumerals.add(Integer.toString(i));
        }

        validOperators = new String[] {"-", "+", "/", "*"};
    }

    String validateAndCalc(String input) throws Exception {
        for (String operator: validOperators) {
            if (input.contains(operator)) {
                String[] inputSplit = input.split("[" + operator + "]");
                if (inputSplit.length == 2) {
                    if (isValidArabic(inputSplit[0]) && isValidArabic(inputSplit[1])) {
                        return Integer.toString(calculate(inputSplit[0], inputSplit[1], operator));
                    } else if (isValidRoman(inputSplit[0]) && isValidRoman(inputSplit[1])) {
                        int a = convertRomanToArabic(inputSplit[0]);
                        int b = convertRomanToArabic(inputSplit[1]);
                        int c = calculate(Integer.toString(a), Integer.toString(b), operator);
                        if (c > 1) {
                            return convertArabicToRoman(c);
                        } else {
                            throw new Exception("Result can't be negative in the Roman system");
                        }
                    } else {
                        throw new Exception("Operands must both be either Arabic (1-10) or Roman (I-X)");
                    }
                } else {
                    throw new Exception("Expression must be: operand operator operand");
                }
            }
        }
        throw new Exception("No valid operator found");
    }

    boolean isValidArabic(String numeral) {
        if (validArabicNumerals.contains(numeral)) {
            return true;
        } else {
           return false;
        }
    }

    boolean isValidRoman(String numeral) {
        if (validRomanNumerals.containsKey(numeral)) {
            return true;
        } else {
            return false;
        }
    }

    int calculate(String operand1, String operand2, String operator) {
        int result = 0;
        int a = Integer.parseInt(operand1);
        int b = Integer.parseInt(operand2);

        switch (operator) {
            case "+" -> result = a + b;
            case "-" -> result = a - b;
            case "*" -> result = a * b;
            case "/" -> result = a / b;
        }

        return result;
    }

    int convertRomanToArabic(String romanNumeral) {
        int result = 0;
        result = validRomanNumerals.get(romanNumeral);
        return result;
    }

    String convertArabicToRoman(int number) {
        int l =  romanToArabicTreeMap.floorKey(number);
        if (number == l) {
            return romanToArabicTreeMap.get(number);
        }
        return romanToArabicTreeMap.get(l) + convertArabicToRoman(number - l);
    }
}