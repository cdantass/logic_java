import java.util.Scanner;

public class calculadora {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Type of the number you want: ");
        int number = scanner.nextInt();

        for (int i = 0; i <= 10; i++){
            int result = number * i;
            System.out.println(number + " X "+i+ " = " +result);

        }
    }
}
