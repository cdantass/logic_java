class Solution {
    public String mergeAlternately(String word1, String word2) {

        StringBuilder result = new StringBuilder();

        int biggestLength = Math.max(word1.length(), word2.length());

        for (int x = 0; x < biggestLength; x++) {

            if (x < word1.length()) {
                result.append(word1.charAt(x));
            }

            if (x < word2.length()) {
                result.append(word2.charAt(x));
            }
        }

        return result.toString();
    }
}