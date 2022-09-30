package daily;

/**
 * @Description: 字符串轮转
 * @author: GanYang
 * @Date: 2022/9/29 22:14
 * <p>
 * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle 是erbottlewat旋转后的字符串）。
 * <p>
 * https://leetcode.cn/problems/string-rotation-lcci/
 * <p>
 * 解释：1、当 s1 与 s2 不相等的时候，恒为false
 * 2、因为存在轮转 s1+s1 一定包含 s2
 */
public class Day221029StringRotationLcci {
    public static void main(String[] args) {
        String s1 = "PvcvpkpHwaXQxpgGzURBvHRMvCsCPPmlKBSzXDWSvrxLBPdAvRpgcIwNOVQDdwPIElrAFqmb";
        String s2 = "SvrxLBPdAvRpgcIwNOVQDdwPIElrAFqmbPvcvpkpHwaXQxpgGzURBvHRMvCsCPPmlKBSzXDW";

        System.out.println(isFlipedString(s1, s2));
    }

    public static boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        StringBuilder stringBuilder = new StringBuilder(s1).append(s1);
        return stringBuilder.toString().contains(s2);
    }
}
