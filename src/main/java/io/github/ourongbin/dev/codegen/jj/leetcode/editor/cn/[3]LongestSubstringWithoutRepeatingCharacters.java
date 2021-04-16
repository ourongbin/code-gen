package io.github.ourongbin.dev.codegen.jj.leetcode.editor.cn;


//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
//
//
//
// 示例 1:
//
//
//输入: s = "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
//
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3:
//
//
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
//
// 示例 4:
//
//
//输入: s = ""
//输出: 0
//
//
//
//
// 提示：
//
//
// 0 <= s.length <= 5 * 104
// s 由英文字母、数字、符号和空格组成
//
// Related Topics 哈希表 双指针 字符串 Sliding Window
// 👍 5271 👎 0


import java.util.HashSet;
import java.util.Set;

class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters question = new LongestSubstringWithoutRepeatingCharacters();
        Solution solution = question.new Solution();
        System.out.println(args);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int lengthOfLongestSubstring(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            int res = 1;
            Set<Character> occ = new HashSet<>();
            for (int l = 0, r = 0; l < s.length(); ++l) {
                for (; r < s.length(); ++r) {
                    if (!occ.contains(s.charAt(r))) {
                        occ.add(s.charAt(r));
                    } else {
                        break;
                    }
                }
                res = Math.max(res, r - l);
                occ.remove(s.charAt(l));
            }

            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}