import java.util.Scanner;

public class NumPrimo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite um número: ");
        int n = scanner.nextInt();

        boolean primo = true;

        if (n <= 1) {
            primo = false;
        } else {
            for (int i = 2; i < n; i++) {
                if (n % i == 0) {
                    primo = false;
                }
            }
        }

        if (primo) {
            System.out.println("Primo");
        } else {
            System.out.println("Não primo");
        }

        scanner.close();
    }
}