package Quiz2;
public class Main {
    public static void main(String[] args) {
        String sample = "hello";

        System.out.println(containsCharacter(sample, 'o')); // true
        System.out.println(containsCharacterBug(sample, 'o'));   // false because of the bug
    }

        public static boolean containsCharacter(String text, char target) {
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * Buggy function:
     * BUG: The loop stops at text.length() - 1 instead of checking
     * every character. Because of this, the final character in the string is never checked.
     */
    public static boolean containsCharacterBug(String text, char target) {
        for (int i = 0; i < text.length() - 1; i++) {
            if (text.charAt(i) == target) {
                return true;
            }
        }
        return false;
    }

}