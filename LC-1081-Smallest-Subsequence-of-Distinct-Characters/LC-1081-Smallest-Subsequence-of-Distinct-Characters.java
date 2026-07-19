1class Solution {
2    public String smallestSubsequence(String s) {
3
4        HashMap<Character, Integer> Unique = new HashMap<>();
5        for (int i = 0; i < s.length(); i++) {
6            Unique.put(s.charAt(i), i);
7        }
8
9        StringBuilder ans = new StringBuilder();
10        HashSet<Character> used = new HashSet<>();
11
12        for (int i = 0; i < s.length(); i++) {
13
14            char ch = s.charAt(i);
15            if (used.contains(ch))
16                continue;
17
18            while (ans.length() > 0 &&
19                   ans.charAt(ans.length() - 1) > ch &&
20                   Unique.get(ans.charAt(ans.length() - 1)) > i) {
21
22                used.remove(ans.charAt(ans.length() - 1));
23                ans.deleteCharAt(ans.length() - 1);
24            }
25
26            ans.append(ch);
27            used.add(ch);
28        }
29
30        return ans.toString();
31    }}