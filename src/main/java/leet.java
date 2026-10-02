import java.util.Arrays;

public class leet {

    public static void main(String[] args) {

        int[] numeros = {1, 5, 4, 5};

        int i = numeros[1];
        int j = numeros[3];

        if (numeros.length < 2 || numeros.length > 500) {
            System.out.println("Tamanho inválido");
        }
        for (int numero : numeros) {
            if (numero < 1 || numero > 1000) {
                System.out.println("Valor inválido");
            }
        }

        int calculo = (i - 1) * (j - 1);

        System.out.println(calculo);
    }
}