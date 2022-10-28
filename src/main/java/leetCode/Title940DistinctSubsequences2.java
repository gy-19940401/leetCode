package leetCode;

/**
 * @Description: 不同的子序列2
 * @author: GanYang
 * @Date: 2022/10/14 14:23
 * 给定一个字符串 s，计算 s 的 不同非空子序列 的个数。因为结果可能很大，所以返回答案需要对 10^9 + 7 取余 。
 * 字符串的 子序列 是经由原字符串删除一些（也可能不删除）字符但不改变剩余字符相对位置的一个新字符串。
 * 例如，"ace" 是 "abcde" 的一个子序列，但 "aec" 不是。
 * https://leetcode.cn/problems/distinct-subsequences-ii/
 * <p>
 * <p>
 * 根据题意我们设dp[i]为前i个字符可以组成的不同的子序列，则有
 * <p>
 * dp[i] = dp[i - 1] + newCount - repeatCount
 * <p>
 * 其中
 * ---- newCount为加上 s[i] 后新增的子序列个数，
 * ---- repeatCount 为重复的子序列个数
 * 于是我们只需要计算 newCount 和 repeatCount 即可
 * <p>
 * ---- newCount的值比较好计算，就是 dp[i - 1]。
 * ---- 计算repeatCount，
 * -------- 我们观察遍历到的第二个字符b，出现重复的序列为b和ab，而这两个序列正好是上一次添加b（
 * 也就是第②步）的时候新增的两个序列。
 * -------- 于是我们可以使用数组preCount来记录上一次该字符新增的个数，该个数就是repeatCount。
 * <p>
 * 由于dp[i]仅和dp[i-1]有关，我们可以滚动存储。
 * <p>
 * 最后我们把空串减去即可。
 * <p>
 * 链接：https://leetcode.cn/problems/distinct-subsequences-ii/solution/bu-tong-by-capital-worker-vga3/
 */
public class Title940DistinctSubsequences2 {
    public static void main(String[] args) {
        String s = "yezruvnatuipjeohsymapyxgfeczkevoxipckunlqjauvllfpwezhlzpbkfqazhexabomnlxkmoufneninbxxguuktvupmpfspwxiouwlfalexmluwcsbeqrzkivrphtpcoxqsueuxsalopbsgkzaibkpfmsztkwommkvgjjdvvggnvtlwrllcafhfocprnrzfoyehqhrvhpbbpxpsvomdpmksojckgkgkycoynbldkbnrlujegxotgmeyknpmpgajbgwmfftuphfzrywarqkpkfnwtzgdkdcyvwkqawwyjuskpvqomfchnlojmeltlwvqomucipcwxkgsktjxpwhujaexhejeflpctmjpuguslmzvpykbldcbxqnwgycpfccgeychkxfopixijeypzyryglutxweffyrqtkfrqlhtjweodttchnugybsmacpgperznunffrdavyqgilqlplebbkdopyyxcoamfxhpmdyrtutfxsejkwiyvdwggyhgsdpfxpznrccwdupfzlubkhppmasdbqfzttbhfismeamenyukzqoupbzxashwuvfkmkosgevcjnlpfgxgzumktsexvwhylhiupwfwyxotwnxodttsrifgzkkedurayjgxlhxjzlxikcgerptpufocymfrkyayvklsalgmtifpiczwnozmgowzchjiop";
        System.out.println(distinctSubseqIi(s));
    }

    public static int distinctSubseqIi(String s) {
        int result = 1;
        int pow = (int) Math.pow(10, 9) + 7;
        int[] charAtLastAdd = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char charAt = s.charAt(i);
            int newCount = result;
            result = ((result * 2) % pow - charAtLastAdd[charAt - 'a'] % pow + pow) % pow;
            charAtLastAdd[charAt - 'a'] = newCount;
        }
        /**
         * 防止返回是 1000000006时因为加 ”“ 导致的 错误 返回 -1
         */
        return (result - 1 + pow) % pow;
    }

}
