package leetCode;

/**
 * @Description: 无重复字符的最长子串
 * @author: GanYang
 * @Date: 2022/9/29 13:55
 * <p>
 * explain 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * url https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 */
public class Title3LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        String s = "sdasldklasd";
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int result = 0;
        // StringBuilder 完成字符串的拼接效率远高于 String
        StringBuilder tmp = new StringBuilder();

        for (char aChar : chars) {
            //如果字符在不重复的串中
            int charIndex = tmp.indexOf(String.valueOf(aChar));
            if (charIndex >= 0) {
                //字符在数组中存在，重复了
                result = Math.max(result, tmp.length());
                //重复的位置下一位开始截取字符串,然后将最新的字符串拼接在后面
                tmp = new StringBuilder(tmp.substring(charIndex + 1)).append(aChar);
            } else {
                // 将为重复的字符追加到不重复的串中
                tmp.append(aChar);
            }
        }
        return Math.max(result, tmp.length());
    }
}
