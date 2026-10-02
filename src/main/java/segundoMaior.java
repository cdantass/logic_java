public class segundoMaior {
    public static void main(String[] args) {
        int[] numeros = {10,20,30,25,50,70};

        int maior = 0;
        int segundo = 0;

        for (int x = 0; x < numeros.length; x++){
            if (numeros[x] > maior){
                maior = numeros[x];

            }

        for (int z = 0; z < numeros.length; z++){
            if (numeros[z] > maior && segundo < maior){
                segundo = numeros[z];
            }
        }
        }

        System.out.println(maior);
        System.out.println(segundo);

        String nome = "caua";
        int count = 0;

        for (int w = 0; w < nome.length(); w++){
            count +=1;
        }

        System.out.println(count);


        String frase = "Java spring boot";
        String espaco = "";

        for (int p = 0; p < frase.length(); p++){
            if (frase.charAt(p) != ' '){
                espaco += frase.charAt(p);
            }
        }
        System.out.println(espaco);
    }


}
