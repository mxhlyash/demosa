class Solution {
    public String smallestSubsequence(String s) {

        HashMap<Character, Integer> Unique = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Unique.put(s.charAt(i), i);
        }

        StringBuilder ans = new StringBuilder();
        HashSet<Character> used = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);
            if (used.contains(ch))
                continue;

            while (ans.length() > 0 &&
                   ans.charAt(ans.length() - 1) > ch &&
                   Unique.get(ans.charAt(ans.length() - 1)) > i) {

                used.remove(ans.charAt(ans.length() - 1));
                ans.deleteCharAt(ans.length() - 1);
            }

            ans.append(ch);
            used.add(ch);
        }

        return ans.toString();
    }
}