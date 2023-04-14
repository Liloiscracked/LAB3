public class dummy {
    public static void main(String[] args) {
        System.out.println(areSimilar("puinter","printer"));
    }
    public static boolean areSimilar(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();

        if (Math.abs(len1 - len2) > 1) {
            return false;
        }

        int i = 0, j = 0, diffCount = 0;
        while (i < len1 && j < len2) {
            if (s1.charAt(i) != s2.charAt(j)) {
                if (++diffCount > 1) {
                    return false;
                }
                if (len1 > len2) {
                    i++;
                } else if (len2 > len1) {
                    j++;
                } else {
                    i++;
                    j++;
                }
            } else {
                i++;
                j++;
            }
        }
        if (i < len1 || j < len2) {
            diffCount++;
        }
        return diffCount == 1;
    }
}
