package design.medium;

import java.util.Stack;

/**
 * 面试题 03.05. 栈排序
 *
 * 栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。
 * 该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek 返回 -1。
 *
 * 示例1:
 *
 *  输入：
 * ["SortedStack", "push", "push", "peek", "pop", "peek"]
 * [[], [1], [2], [], [], []]
 *  输出：
 * [null,null,null,1,null,2]
 * 示例2:
 *
 *  输入：
 * ["SortedStack", "pop", "pop", "push", "pop", "isEmpty"]
 * [[], [], [], [1], [], []]
 *  输出：
 * [null,null,null,null,null,true]
 * 说明:
 *
 * 栈中的元素数目在[0, 5000]范围内。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-of-stacks-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class SortedStack {

    private Stack<Integer> mainStack;
    private Stack<Integer> tempStack;

    public SortedStack() {
        mainStack = new Stack<>();
        tempStack = new Stack<>();
    }

    public void push(int val) {
        while (!mainStack.isEmpty() && mainStack.peek() < val) {
            tempStack.push(mainStack.pop());
        }
        mainStack.push(val);
        while (!tempStack.isEmpty()) {
            mainStack.push(tempStack.pop());
        }
    }

    public void pop() {
        if (mainStack.isEmpty()) {
            return;
        }
        mainStack.pop();
    }

    public int peek() {
        if (mainStack.isEmpty()) {
            return -1;
        }
        return mainStack.peek();
    }

    public boolean isEmpty() {
        return mainStack.isEmpty();
    }
}

/**
 * Your SortedStack object will be instantiated and called as such:
 * SortedStack obj = new SortedStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.isEmpty();
 */
