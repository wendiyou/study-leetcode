package greedy_algorithm.simple;

import java.util.HashSet;
import java.util.Set;

/**
 * 模拟行走机器人
 * <p>
 * 机器人在一个无限大小的网格上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令：
 * <p>
 * -2：向左转 90 度
 * -1：向右转 90 度
 * 1 <= x <= 9：向前移动 x 个单位长度
 * 在网格上有一些格子被视为障碍物。
 * <p>
 * 第 i 个障碍物位于网格点  (obstacles[i][0], obstacles[i][1])
 * <p>
 * 如果机器人试图走到障碍物上方，那么它将停留在障碍物的前一个网格方块上，但仍然可以继续该路线的其余部分。
 * <p>
 * 返回从原点到机器人的最大欧式距离的平方。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入: commands = [4,-1,3], obstacles = []
 * 输出: 25
 * 解释: 机器人将会到达 (3, 4)
 * 示例 2：
 * <p>
 * 输入: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
 * 输出: 65
 * 解释: 机器人在左转走到 (1, 8) 之前将被困在 (1, 4) 处
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= commands.length <= 10000
 * 0 <= obstacles.length <= 10000
 * -30000 <= obstacle[i][0] <= 30000
 * -30000 <= obstacle[i][1] <= 30000
 * 答案保证小于 2 ^ 31
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/walking-robot-simulation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RobotSim_847 {
    public int robotSim(int[] commands, int[][] obstacles) {
        return fun1(commands, obstacles);
    }

    /**
     * 思路：没到一个拐点，就计算一次到原点的距离，每次保留最大距离
     * </p>
     * 参考代码
     *
     * @param commands
     * @param obstacles
     * @return
     */
    private int fun1(int[] commands, int[][] obstacles) {
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        int x = 0, y = 0, di = 0;

        Set<Long> obsSet = new HashSet<>();
        for (int i = 0; i < obstacles.length; i++) {
            int ox = obstacles[i][0] + 30000;
            int oy = obstacles[i][1] + 30000;
            obsSet.add(((long) ox << 16) + oy);
        }
        int result = 0;
        for (int command : commands) {
            if (command == -2) {
                // left
                di = (di + 3) % 4;
            } else if (command == -1) {
                // right
                di = (di + 1) % 4;
            } else {
                for (int k = 0; k < command; k++) {
                    int nx = x + dx[di];
                    int ny = y + dy[di];
                    long code = (((long) nx + 30000) << 16) + (ny + 30000);
                    if (!obsSet.contains(code)) {
                        x = nx;
                        y = ny;
                        result = Math.max(result, x * x + y * y);
                    } else {
                        break;
                    }
                }
            }
        }
        return result;
    }
}