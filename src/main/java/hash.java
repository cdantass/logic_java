import java.util.HashMap;
import java.util.Map;

public class hash {
    public static void main(String[] args) {

        int[] numeros = {1, 1, 2, 3, 3, 3, 7, 4};

        Map<Integer, Integer> mapa = new HashMap<>();

        for (int numero : numeros) {

            if (mapa.containsKey(numero)) {
                mapa.put(numero, mapa.get(numero) + 1);
            } else {
                mapa.put(numero, 1);
            }
        }
        System.out.println(mapa);
    }
}