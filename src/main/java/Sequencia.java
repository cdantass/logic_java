public class Sequencia {
    public static void main(String[] args) {
        int[] numeros = {1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 3, 3, 3, 4, 5, 6, 6, 6, 6,6,6,6,6};

        int count = 1;
        int numero_maior = 0;
        int maior_sequencia = 0;

        for (int x = 1; x < numeros.length; x++) {
            if (numeros[x] == numeros[x - 1]) {
                count++;

            } else {
                if (count > maior_sequencia) {
                    maior_sequencia = count;
                    numero_maior = numeros[x - 1];
                }
                count = 1;
            }
            }
        if (count > maior_sequencia){
            maior_sequencia = count;
            numero_maior = numeros[numeros.length - 1];
        }
        System.out.println("A maior sequência é "+ maior_sequencia+ " e o número mais repetido é "+ numero_maior);

    }
}
