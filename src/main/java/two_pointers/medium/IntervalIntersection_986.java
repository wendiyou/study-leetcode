package two_pointers.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 986. 区间列表的交集
 *
 * 给定两个由一些 闭区间 组成的列表，每个区间列表都是成对不相交的，并且已经排序。
 *
 * 返回这两个区间列表的交集。
 *
 * （形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b。两个闭区间的交集是一组实数，要么为空集，要么为闭区间。
 * 例如，[1, 3] 和 [2, 4] 的交集为 [2, 3]。）
 *
 * 示例：
 *
 * 输入：A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
 * 输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 *
 * 提示：
 *
 * 0 <= A.length < 1000
 * 0 <= B.length < 1000
 * 0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/interval-list-intersections
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IntervalIntersection_986 {

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        return fun(A,B);
    }

    private int[][] fun(int[][] a,int[][] b) {
        List<int[]> tempResult = new ArrayList<>();
        for (int aIndex = 0,bIndex = 0;aIndex < a.length && bIndex < b.length;) {
            int aLeft = a[aIndex][0];
            int aRight = a[aIndex][1];
            int bLeft = b[bIndex][0];
            int bRight = b[bIndex][1];
            if (aRight <= bLeft) {
                if (aRight == bLeft) {
                    tempResult.add(new int[]{aRight,aRight});
                }
                // a在前，a需要后移
                aIndex++;
            } else if (bRight <= aLeft) {
                if (bRight == aLeft) {
                    tempResult.add(new int[]{bRight,bRight});
                }
                // b在前，b需要后移
                bIndex++;
            } else {
                int left = Math.max(aLeft,bLeft);
                int right = Math.min(aRight,bRight);
                tempResult.add(new int[]{left,right});
                if (aRight <= bRight) {
                    aIndex++;
                } else {
                    bIndex++;
                }
            }
        }
        int[][] result = new int[tempResult.size()][2];
        for (int i = 0;i < tempResult.size();i++) {
            result[i] = tempResult.get(i);
        }
        return result;
    }
}