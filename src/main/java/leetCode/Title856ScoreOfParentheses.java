package leetCode;

/**
 * @Description: 括号的分数
 * @author: GanYang
 * @Date: 2022/10/9 18:33
 * <p>
 * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
 * <p>
 * () 得 1 分。
 * AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
 * (A) 得 2 * A 分，其中 A 是平衡括号字符串。
 * https://leetcode.cn/problems/score-of-parentheses/
 */
public class Title856ScoreOfParentheses {

    public static void main(String[] args) {
        String parentheses = "(()(()))";
        System.out.println(scoreOfParentheses(parentheses));
    }

    public static int scoreOfParentheses(String s) {
        int score = 0;
        // 括号的深度
        int depth = 0;
        for (int i = 0; i < s.length(); i++) {
            //遇到左括号 深度 加 1
            if (s.charAt(i) == '(') {
                depth++;
            } else {
                //遇到右括号深度 减 1
                depth--;
                //只有当 前一个字符是 左括号的时候 当前计算结束 累计结果 之后的 右括号 不累计结果
                if (s.charAt(i - 1) == '(') {
                    score += 1 << depth;
                }
            }
        }
        return score;
    }
}
