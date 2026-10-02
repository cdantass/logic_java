import java.util.Arrays;

public class SumAndMax {
    public static void main(String[] args) {
        int[] numbers = {1,2,3,4,5};

        int max_number = Arrays.stream(numbers).max().getAsInt();
        int soma = 0;
        for (int i = 0; i < numbers.length; i++){
            soma += i;
        }

        int[] numeros_ocorrencia = {1,2,1,4,4,6,6};

        int ocorrencia = 0;
        int num = 1;
        for (int x = 0; x < numeros_ocorrencia.length; x++){
            if (numeros_ocorrencia[x] == num){
                ocorrencia++;
            }

        }

        int[] numeros = {1,2,3,4,5,6,7,8,9};
        int max = numeros[0];
        int min = numeros[0];

        int somando = 0;
        for (int z = 0; z < numeros.length; z++){
            somando +=numeros[z];
            if (numeros[z] > max){
                max = numeros[z];

            if (numeros[z] < min){
                min = numeros[z];
            }

            }

        }
        System.out.println(somando + " é a soma, "+ max + " é o máximo," + min + " é o mínimo");

    }
}