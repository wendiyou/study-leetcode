package dynamic_programming.medium;

/**
 * 96. 不同的二叉搜索树
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 */
public class UniqueBinarySearchTrees_96 {
    public int numTrees(int n) {
        return fun(n);
    }

    /**
     * 动态规划，思路：
     * <p>
     * 给定一个有序序列 1 ... n，为了根据序列构建一棵二叉搜索树。
     * 我们可以遍历每个数字 i，将该数字作为树根，1 ... (i-1) 序列将成为左子树，(i+1) ... n 序列将成为右子树。
     * 于是，我们可以递归地从子序列构建子树。
     * 在上述方法中，由于根各自不同，每棵二叉树都保证是独特的。
     * <p>
     * 可见，问题可以分解成规模较小的子问题。因此，我们可以存储并复用子问题的解，而不是递归的（也重复的）解决这些子问题，这就是动态规划法。
     * <p>
     * 算法
     * <p>
     * 问题是计算不同二叉搜索树的个数。为此，我们可以定义两个函数：
     * <p>
     * G(n)G(n): 长度为n的序列的不同二叉搜索树个数。
     * <p>
     * F(i, n)F(i,n): 以i为根的不同二叉搜索树个数(1 \leq i \leq n1≤i≤n)。
     * <p>
     * 可见，
     * <p>
     * G(n)G(n) 是我们解决问题需要的函数。
     * <p>
     * 稍后我们将看到，G(n)G(n) 可以从 F(i, n)F(i,n) 得到，而 F(i, n)F(i,n) 又会递归的依赖于 G(n)G(n)。
     * <p>
     * 首先，根据上一节中的思路，不同的二叉搜索树的总数 G(n)G(n)，是对遍历所有 i (1 <= i <= n) 的 F(i, n)F(i,n) 之和。换而言之：
     * <p>
     * G(n) = \sum_{i=1}^{n} F(i, n) \qquad \qquad (1)
     * G(n)=
     * i=1
     * ∑
     * n
     * ​
     * F(i,n)(1)
     * <p>
     * 特别的，对于边界情况，当序列长度为 1 （只有根）或为 0 （空树）时，只有一种情况。亦即：
     * <p>
     * G(0) = 1, \qquad G(1) = 1
     * G(0)=1,G(1)=1
     * <p>
     * 给定序列 1 ... n，我们选出数字 i 作为根，则对于根 i 的不同二叉搜索树数量 F(i, n)F(i,n)，是左右子树个数的笛卡尔积，如下图所示:
     * <p>
     * <p>
     * <p>
     * 举例而言，F(3, 7)F(3,7)，以 3 为根的不同二叉搜索树个数。为了以 3 为根从序列 [1, 2, 3, 4, 5, 6, 7] 构建二叉搜索树，
     * 我们需要从左子序列 [1, 2] 构建左子树，从右子序列 [4, 5, 6, 7] 构建右子树，然后将它们组合(即笛卡尔积)。
     * 巧妙之处在于，我们可以将 [1,2] 构建不同左子树的数量表示为 G(2)G(2), 从 [4, 5, 6, 7]` 构建不同右子树的数量表示为 G(4)G(4)。
     * 这是由于 G(n)G(n) 和序列的内容无关，只和序列的长度有关。于是，F(3,7) = G(2) \cdot G(4)F(3,7)=G(2)⋅G(4)。
     * 概括而言，我们可以得到以下公式：
     * <p>
     * F(i, n) = G(i-1) \cdot G(n-i) \qquad \qquad (2)
     * F(i,n)=G(i−1)⋅G(n−i)(2)
     * <p>
     * 将公式 (1)，(2) 结合，可以得到 G(n)G(n) 的递归表达公式：
     * <p>
     * G(n) = \sum_{i=1}^{n}G(i-1) \cdot G(n-i) \qquad \qquad (3)
     * G(n)=
     * i=1
     * ∑
     * n
     * ​
     * G(i−1)⋅G(n−i)(3)
     * <p>
     * 为了计算函数结果，我们从小到大计算，因为 G(n)G(n) 的值依赖于 G(0) … G(n-1)G(0)…G(n−1)。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees/solution/bu-tong-de-er-cha-sou-suo-shu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    private int fun(int n) {
        int[] g = new int[n + 1];
        g[0] = 1;
        g[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                g[i] += g[j - 1] * g[i - j];
            }
        }
        return g[n];
    }
}
