package greedy_algorithm.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 452. 用最少数量的箭引爆气球
 * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以y坐标并不重要，
 * 因此只要知道开始和结束的x坐标就足够了。开始坐标总是小于结束坐标。平面内最多存在104个气球。
 * <p>
 * 一支弓箭可以沿着x轴从不同点完全垂直地射出。在坐标x处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend，
 * 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。
 * 我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
 * <p>
 * Example:
 * <p>
 * 输入:
 * [[10,16], [2,8], [1,6], [7,12]]
 * <p>
 * 输出:
 * 2
 * <p>
 * 解释:
 * 对于该样例，我们可以在x = 6（射爆[2,8],[1,6]两个气球）和 x = 11（射爆另外两个气球）。
 */
public class FindMinArrowShots_452 {
    public int findMinArrowShots(int[][] points) {
        return fun1(points);
    }

    /**
     * 参考代码
     * 思路：按照气球结束编辑从小到大排序，相同结束边界的，按照起始边界从小到大排序。
     * 当后一个气球的开始边界小于前面气球的结束边界，表明当前气球与前面的气球存在重叠区，可以共用一只箭；
     * 当当前气球开始边界大于前面气球结束边界，那么必须使用一只新的箭，并且同时更新end边界为当前气球结束边界。
     *
     * @param points
     * @return
     */
    private int fun1(int[][] points) {
        if (points.length < 2) {
            return points.length;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });
        int result = 1;
        int end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > end) {
                result++;
                end = points[i][1];
            }
        }
        return result;
    }
}
