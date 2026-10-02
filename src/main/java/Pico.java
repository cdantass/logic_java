public class Pico {
    public static void main(String[] args) {
        int[] numeros = {1,5,3,8,6,10,2};

        int pico_encontrado = 0;
        int maior_pico = 0;

        for (int x = 1; x < numeros.length - 1; x++){
            if (numeros[x] > numeros[x - 1] && numeros[x] > numeros[x + 1]){
                pico_encontrado = numeros[x];

                if (numeros[x] > maior_pico){
                    maior_pico = numeros[x];
                }
            }
        }
        System.out.println(pico_encontrado);

        System.out.println("O maior pico é "+ maior_pico);
    }
}