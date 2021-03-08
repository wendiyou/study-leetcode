package sort.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 57. 插入区间
 *
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 *
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例 2：
 *
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * 示例 3：
 *
 * 输入：intervals = [], newInterval = [5,7]
 * 输出：[[5,7]]
 * 示例 4：
 *
 * 输入：intervals = [[1,5]], newInterval = [2,3]
 * 输出：[[1,5]]
 * 示例 5：
 *
 * 输入：intervals = [[1,5]], newInterval = [2,7]
 * 输出：[[1,7]]
 *
 * 提示：
 *
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= intervals[i][0] <= intervals[i][1] <= 105
 * intervals 根据 intervals[i][0] 按 升序 排列
 * newInterval.length == 2
 * 0 <= newInterval[0] <= newInterval[1] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-interval
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Insert_57 {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] newArr = new int[1][];
        newArr[0] = newInterval;
        return fun(intervals,newArr);
    }

    private int[][] fun(int[][] sourceArr,int[][] newArr) {
        TreeMap<Integer,int[]> map = new TreeMap<>();
        for (int[] cur : sourceArr) {
            map.put(cur[0],cur);
        }
        for (int[] cur : newArr) {
            if (map.containsKey(cur[0])) {
                int[] temp = map.get(cur[0]);
                temp[1] = Math.max(cur[1],temp[1]);
            } else {
                map.put(cur[0],cur);
            }
        }
        List<int[]> list = new ArrayList<>();
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            if (list.isEmpty()) {
                list.add(entry.getValue());
            } else {
                int[] last = list.get(list.size() - 1);
                if (entry.getKey() > last[1]) {
                    list.add(entry.getValue());
                } else {
                    last[1] = Math.max(last[1],entry.getValue()[1]);
                }
            }
        }
        int[][] result = new int[list.size()][];
        return list.toArray(result);
    }
}
