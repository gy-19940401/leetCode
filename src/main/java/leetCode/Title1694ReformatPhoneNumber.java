package leetCode;

/**
 * @Description: 重新格式化电话号码
 * @author: GanYang
 * @Date: 2022/10/1 10:18
 * <p>
 * 给你一个字符串形式的电话号码 number 。number 由数字、空格 ' '、和破折号 '-' 组成。
 * <p>
 * 请你按下述方式重新格式化电话号码。
 * <p>
 * 首先，删除 所有的空格和破折号。
 * 其次，将数组从左到右 每 3 个一组 分块，直到 剩下 4 个或更少数字。剩下的数字将按下述规定再分块：
 * 2 个数字：单个含 2 个数字的块。
 * 3 个数字：单个含 3 个数字的块。
 * 4 个数字：两个分别含 2 个数字的块。
 * 最后用破折号将这些块连接起来。注意，重新格式化过程中 不应该 生成仅含 1 个数字的块，并且 最多 生成两个含 2 个数字的块。
 * <p>
 * 返回格式化后的电话号码。
 * <p>
 * https://leetcode.cn/problems/reformat-phone-number/
 */
public class Title1694ReformatPhoneNumber {
    public static void main(String[] args) {
        String number = "--17-5 229 35-39475 ";
        System.out.println(reformatNumber(number, null));
    }

    public static String reformatNumber(String number, Object... objects) {
        char[] chars = new char[number.length() + number.length() / 3];
        int count = 0, index = 0;
        for (char ch : number.toCharArray()) {
            if ('0' <= ch && ch <= '9') {
                //当前字符是 0-9 的数字
                chars[index++] = ch;
                count++;
                if (count % 3 == 0) {
                    chars[index++] = '-';
                    count = 0;
                }
            }
        }
        //将字符串全部遍历完成后
        //1、数字字符数是 3 的 整数倍
        //2、数组字符数是 3 的 整数倍 +1 需要处理{
        //   将倒数第二位与第三位交换位置
        // }
        //3、数组字符数是 3 的 整数倍 +2 需要处理
        if (chars[index - 1] == '-') {
            return new String(chars, 0, index - 1);
        } else if (chars[index - 2] == '-') {
            chars[index - 2] = chars[index - 3];
            chars[index - 3] = '-';
        }
        return new String(chars, 0, index);
    }

    /**
     * 1、暴力解法
     *
     * @param number 待格式化的字符串
     * @return
     */
    private static String reformatNumber(String number) {
        String replaceAll = number.replaceAll("-", "").replaceAll(" ", "");
        StringBuilder result = new StringBuilder();
        while (replaceAll.length() > 4) {
            result.append(replaceAll.substring(0, 3)).append("-");
            replaceAll = replaceAll.substring(3);
        }
        if (replaceAll.length() <= 3) {
            return result.append(replaceAll).toString();
        } else {
            return result.append(replaceAll.substring(0, 2)).append("-").append(replaceAll.substring(2)).toString();
        }
    }
}
