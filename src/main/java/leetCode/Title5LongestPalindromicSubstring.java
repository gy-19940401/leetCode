package leetCode;

/**
 * @Description: 最长回文子串
 * @author: GanYang
 * @Date: 2022/9/29 14:03
 * explain : 给你一个字符串 s，找到 s 中最长的回文子串。
 * url : https://leetcode.cn/problems/longest-palindromic-substring/
 */
public class Title5LongestPalindromicSubstring {

    public static void main(String[] args) {
        String s="assa";
        System.out.println(longestPalindrome(s));
    }

    public static String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        String result = "";
        int head, tail;
        for (int index = 0; index < s.length(); index++) {
            head = index - 1;
            tail = index + 1;
            StringBuilder tmp = new StringBuilder(s.charAt(index) + "");
            // 移动到后面第一个与当前字符不相等的位置
            while (tail < s.length() && s.charAt(tail) == s.charAt(index)) {
                tmp.append(s.charAt(index));
                tail++;
            }
            while (head >= 0 && tail < s.length()) {
                if (s.charAt(head) == s.charAt(tail)) {
                    tmp = new StringBuilder(s.charAt(head) + tmp.append(s.charAt(tail)).toString());
                } else {
                    break;
                }
                head--;
                tail++;
            }
            if (tmp.length() > result.length()) {
                result = tmp.toString();
            }
        }
        return result;
    }
}
