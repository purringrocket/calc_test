import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input;
        System.out.println("Type a valid expression or \"exit\" to close an app:");

        do {
            input = in.nextLine();
            System.out.println(calc(input));
        } while (!input.equals("exit"));
    }

    public static String calc(String input) {
        Calculator calculator = new Calculator();
        String result = "";
        try {
            result = calculator.validateAndCalc(input.replaceAll("\\s+",""));
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        return result;
    }
}