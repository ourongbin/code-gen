## 双指针（额外空间）

![image.png](https://pic.leetcode-cn.com/1617587324-zDJsgp-image.png)

一个简单的做法是，创建一个和 *nums1* 等长的数组 *arr*，使用双指针将 *num1* 和 *nums2* 的数据迁移到 *arr*。

最后再将 *arr* 复制到 *nums1* 中。

代码：
```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int total = m + n;
        int[] arr = new int[total];
        int idx = 0;
        for (int i = 0, j = 0; i < m || j < n;) {
            if (i < m && j < n) {
                arr[idx++] = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
            } else if (i < m) {
                arr[idx++] = nums1[i++];
            } else if (j < n) {
                arr[idx++] = nums2[j++];
            }
        }
        System.arraycopy(arr, 0, nums1, 0, total);
    }
}
```
* 时间复杂度：*O(m + n)*
* 空间复杂度：*O(m + n)*

***

## 先合并再排序

![image.png](https://pic.leetcode-cn.com/1617587429-qcUlDQ-image.png)

我们还可以将 *nums2* 的内容先迁移到 *nums1* 去，再对 *nums1* 进行排序。

代码：
```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }
}
```
* 时间复杂度：*O((m + n)log{(m + n)})*
* 空间复杂度：*O(1)*

**PS. Java 中的 sort 排序是一个综合排序。包含插入/双轴快排/归并/timsort，这里假定 `Arrays.sort` 使用的是「双轴快排」，并忽略递归带来的空间开销。**

***

## 原地合并（从前往后）

![image.png](https://pic.leetcode-cn.com/1617587288-eTtvcp-image.png)

也可以直接在 *nums1* 进行合并操作，但是需要确保每次循环开始，*nums2* 的指针指向的必然是最小的元素。

因此，我们需要对 *nums2* 执行局部排序。

代码：
```java 
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0;
        while (j < n) {
            if (i >= m) {
                nums1[i] = nums2[j++];
            } else {
                int a = nums1[i], b = nums2[j];
                if (a > b) swap(nums1, i, nums2, j);
                sort(nums2, j, n - 1);
            }
            i++;
        }
    }
    void sort(int[] nums, int l, int r) {
        if (l >= r) return;
        int x = nums[l], i = l - 1, j = r + 1;
        while (i < j) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j) swap(nums, i, nums, j);
        }
        sort(nums, l, j);
        sort(nums, j + 1, r);
    }
    void swap(int[] nums1, int i, int[] nums2, int j) {
        int tmp = nums1[i];
        nums1[i] = nums2[j];
        nums2[j] = tmp;
    }
}
```
* 时间复杂度：*O(n + m^2 log{m})*
* 空间复杂度：忽略递归开销，复杂度为 *O(1)*

***

## 原地合并（从后往前）

![image.png](https://pic.leetcode-cn.com/1617589556-AXvSlX-image.png)

思路和「方法一」是类似的，将遍历方向由「从前往后」调整为「从后往前」即可做到 *O(1)* 空间复杂度。

代码：
```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1;
        int idx = m + n - 1;
        while (i >= 0 || j >= 0) {
            if (i >= 0 && j >= 0) {
                nums1[idx--] = nums1[i] >= nums2[j] ? nums1[i--] : nums2[j--];
            } else if (i >= 0) {
                nums1[idx--] = nums1[i--];
            } else {
                nums1[idx--] = nums2[j--];
            }
        }
    }
}
```
* 时间复杂度：*O(m + n)*
* 空间复杂度：*O(1)*
***

## 最后

**如果有帮助到你，请给题解点个赞和收藏，让更多的人看到 ~ ("▔□▔)/**

**如有不理解的地方，欢迎你在评论区给我留言，我都会逐一回复 ~**

也欢迎你 [关注我](https://leetcode-cn.com/u/ac_oier/) ，提供追求「证明」&「思路」的高质量题解  