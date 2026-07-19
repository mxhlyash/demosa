class Solution {
    public String smallestSubsequence(String s) {

        HashMap<Character, Integer> Unique = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Unique.put(s.charAt(i), i);
        }

        StringBuilder ans = new StringBuilder();
        HashSet<Character> used = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);
            if (used.contains(ch))