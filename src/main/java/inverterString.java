public class inverterString {
    public static void main(String[] args) {
        String nome = "caua";

        int contagem = nome.length();
        String reverse = "";

        for (int x = nome.length() - 1; x >= 0; x--) {
            reverse += nome.charAt(x);
        }

        System.out.println(reverse);
    }
}
