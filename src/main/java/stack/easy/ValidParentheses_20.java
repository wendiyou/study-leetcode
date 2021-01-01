package stack.easy;

import java.util.Stack;

/**
 * 20. 有效的括号
 * <p>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。 左括号必须以正确的顺序闭合。 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()" 输出: true 示例 2:
 * <p>
 * 输入: "()[]{}" 输出: true 示例 3:
 * <p>
 * 输入: "(]" 输出: false 示例 4:
 * <p>
 * 输入: "([)]" 输出: false 示例 5:
 * <p>
 * 输入: "{[]}" 输出: true
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/valid-parentheses 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidParentheses_20 {

    public boolean isValid(String s) {
        return fun(s);
    }

    private boolean fun(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(' || arr[i] == '[' || arr[i] == '{') {
                stack.push(arr[i]);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char temp = stack.pop();
                if (arr[i] == ')' && temp != '(') {
                    return false;
                } else if (arr[i] == ']' && temp != '[') {
                    return false;
                } else if (arr[i] == '}' && temp != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
