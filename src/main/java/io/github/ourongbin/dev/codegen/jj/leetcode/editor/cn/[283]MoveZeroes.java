package io.github.ourongbin.dev.codegen.jj.leetcode.editor.cn;


//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针 
// 👍 1022 👎 0


class MoveZeroes {
    public static void main(String[] args) {
        MoveZeroes question = new MoveZeroes();
        Solution solution = question.new Solution();
        System.out.println(args);
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void moveZeroes(int[] nums) {
        if (nums.length == 0) {
            return;
        }

        int i = 0;
        int j = 0;
        while (j < nums.length) {
            if (nums[j] != 0) {
                nums[i] = nums[j];
                i++;
            }
            j++;
        }
        while (i < nums.length) {
            nums[i] = 0;
            i++;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}