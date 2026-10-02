import java.util.Scanner;

public class Fatorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose the number for fatorial: ");
        int number = scanner.nextInt();

        int result = 1;

        for (int i = 1; i <= number; i++) {
            result = result * i;
        }

        System.out.println(number + "! Resultado: " + result);

        scanner.close();
    }
}