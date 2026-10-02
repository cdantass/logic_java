import java.util.*;


public class TresMaiores {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 10; i++){
            System.out.println("Digite o número: ");
            int number = scanner.nextInt();
            numbers.add(number);
        }
        numbers.sort(Collections.reverseOrder());
        System.out.println("Os três maiores números são: ");

        System.out.println(numbers.get(0));
        System.out.println(numbers.get(1));
        System.out.println(numbers.get(2));
    }
}
