package com.company;

import java.util.Scanner;


public class Calculator {
    private static int a;
    private static int b;
    private static String symbol;
    private static boolean isRomanNumber = false;


    public static void main(String[] args) {
        startInput();
        operationStart(a, b, symbol);
    }

    public static void startInput(){
        Scanner scanner = new Scanner(System.in);
        String operationLine = scanner.nextLine();
        String[] operation = operationLine.split("\\s");
        a = checkNumbers(operation[0]);
        b = checkNumbers(operation[2]);
        symbol = operation[1];
    }

    public static int checkNumbers(String operationSymbol){
        String[] romanSymbol = {"X", "IX", "VIII", "VII", "VI", "V", "IV", "III", "II", "I"};
        int parser = 0;
        for (int i = 0; i < romanSymbol.length ; ++i) {
            if(romanSymbol[i].equals(operationSymbol))
                parser = 10 - i;
        }
        if(parser == 0)
            return parser = Integer.parseInt(operationSymbol);
        else {
            isRomanNumber = true;
            return parser;
        }
    }
    // 10 15 17
    public static String transformNumber(int result){
        String[] romanSymbol = {"X", "IX", "VIII", "VII", "VI", "V", "IV", "III", "II", "I"};
        String fortyRoman = "L";
        String hundredRoman = "C";
        String parser = "";

        if(result <= 10){
            for (int i = 0; i < romanSymbol.length; i++) {
                if(result == 10 - i)
                    parser += romanSymbol[i];
            }
        } else {
            int howTen = result / 10;
            int romanResult = result - (howTen * 10);

            if(howTen == 10){
                parser += hundredRoman;
            } else if(howTen >= 9){
                parser += romanSymbol[0] + hundredRoman;
            } else if (howTen >= 5){
                parser += fortyRoman;
                howTen -= 5;
                for (int j = 0; j < howTen; j++) {
                    parser += romanSymbol[0];
                }
            } else if(howTen >= 4){
                parser += romanSymbol[0] + fortyRoman;
            } else {
                for (int j = 0; j < howTen; j++) {
                    parser += romanSymbol[0];
                }
            }

            for (int x = 0; x < romanSymbol.length; x++) {
                if (romanResult == 10 - x) {
                    parser += romanSymbol[x];
                }
            }
        }
        isRomanNumber = false;
        return parser;
    }

    public static void operationStart(int a, int b, String symbol){
        int result;
        switch (symbol) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + symbol);
        }
        if(isRomanNumber)
            System.out.println(transformNumber(result));
        else
            System.out.println(result);
    }


}
