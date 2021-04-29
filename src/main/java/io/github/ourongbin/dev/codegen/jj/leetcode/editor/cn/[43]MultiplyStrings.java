package io.github.ourongbin.dev.codegen.jj.leetcode.editor.cn;


//给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。 
//
// 示例 1: 
//
// 输入: num1 = "2", num2 = "3"
//输出: "6" 
//
// 示例 2: 
//
// 输入: num1 = "123", num2 = "456"
//输出: "56088" 
//
// 说明： 
//
// 
// num1 和 num2 的长度小于110。 
// num1 和 num2 只包含数字 0-9。 
// num1 和 num2 均不以零开头，除非是数字 0 本身。 
// 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。 
// 
// Related Topics 数学 字符串 
// 👍 622 👎 0


class MultiplyStrings {
    public static void main(String[] args) {
        MultiplyStrings question = new MultiplyStrings();
        Solution solution = question.new Solution();
        System.out.println(solution.multiply("2", "3"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String multiply(String num1, String num2) {
            String res = "0";
            for (int i = num2.length() - 1; i >= 0; --i) {
                res = addStrings(multiply(num1, num2.charAt(i) - '0', num2.length() - 1 - i), res);
            }
            return res;
        }

        public String multiply(String num1, int num2, int zeros) {
            if ("0".equals(num1) || num2 == 0) {
                return "0";
            }

            StringBuilder sb = new StringBuilder();
            int carry = 0;
            for (int i = num1.length() - 1; i >= 0 || carry > 0; --i) {

                int tmp = i >= 0 ? (num1.charAt(i) - '0') * num2 + carry : carry;
                carry = tmp / 10;
                tmp = tmp % 10;

                sb.append(tmp);
            }

            sb.reverse();

            while (zeros > 0) {
                sb.append(0);
                zeros--;
            }

            return sb.toString();
        }

        public String addStrings(String num1, String num2) {
            if (num1 == null || num1.length() == 0 || num2 == null || num2.length() == 0) {
                throw new RuntimeException();
            }
            StringBuilder sb = new StringBuilder();
            int carry = 0;
            for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0 || carry > 0; i--, j--) {
                int a = i >= 0 ? num1.charAt(i) - '0' : 0;
                int b = j >= 0 ? num2.charAt(j) - '0' : 0;
                int tmp = a + b + carry;
                carry = tmp / 10;
                tmp %= 10;
                sb.append(tmp);
            }
            return sb.reverse().toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}