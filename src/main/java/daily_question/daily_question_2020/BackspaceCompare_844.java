package daily_question.daily_question_2020;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * 844. 比较含退格的字符串
 * <p>
 * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 * <p>
 * 注意：如果对空文本输入退格字符，文本继续为空。
 * <p>
 * 示例 1：
 * <p>
 * 输入：S = "ab#c", T = "ad#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “ac”。
 * 示例 2：
 * <p>
 * 输入：S = "ab##", T = "c#d#"
 * 输出：true
 * 解释：S 和 T 都会变成 “”。
 * 示例 3：
 * <p>
 * 输入：S = "a##c", T = "#a#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “c”。
 * 示例 4：
 * <p>
 * 输入：S = "a#c", T = "b"
 * 输出：false
 * 解释：S 会变成 “c”，但 T 仍然是 “b”。
 *  
 * 提示：
 * <p>
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S 和 T 只含有小写字母以及字符 '#'。
 * <p>
 * 进阶：
 * <p>
 * 你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/backspace-string-compare
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BackspaceCompare_844 {

    @Test
    public void test() {
        Assert.assertEquals(true, backspaceCompare("ab#c", "ad#c"));
        Assert.assertEquals(true, backspaceCompare("ab##", "c#d#"));
        Assert.assertEquals(true, backspaceCompare("a##c", "#a#c"));
        Assert.assertEquals(false, backspaceCompare("a#c", "b"));
    }

    public boolean backspaceCompare(String S, String T) {
        //return fun(S,T);
        return fun2(S, T);
    }

    /**
     * 进阶解法：O(N) 的时间复杂度和 O(1) 的空间复杂度
     */
    private boolean fun2(String s, String t) {
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        int sLen = sArr.length;
        int tLen = tArr.length;
        int sNeedRemove = 0, tNeedRemove = 0;
        int sIndex = sLen - 1, tIndex = tLen - 1;
        for (; sIndex >= 0 && tIndex >= 0; ) {
            if (sArr[sIndex] == '#') {
                sNeedRemove++;
                sIndex--;
            } else if (tArr[tIndex] == '#') {
                tNeedRemove++;
                tIndex--;
            } else if (sNeedRemove > 0) {
                sIndex--;
                sNeedRemove--;
            } else if (tNeedRemove > 0) {
                tIndex--;
                tNeedRemove--;
            } else {
                if (sArr[sIndex] != tArr[tIndex]) {
                    return false;
                }
                sIndex--;
                tIndex--;
            }
        }
        while (sIndex >= 0 && sNeedRemove >= 0) {
            if (sArr[sIndex] == '#') {
                sNeedRemove++;
            } else {
                if (sNeedRemove <= 0) {
                    return false;
                }
                sNeedRemove--;
            }
            sIndex--;
        }
        if (sIndex >= 0) {
            return false;
        }
        while (tIndex >= 0 && tNeedRemove >= 0) {
            if (tArr[tIndex] == '#') {
                tNeedRemove++;
            } else {
                if (tNeedRemove <= 0) {
                    return false;
                }
                tNeedRemove--;
            }
            tIndex--;
        }
        if (tIndex >= 0) {
            return false;
        }
        return true;
    }

    /**
     * 基本解法：线性空间复杂度
     */
    private boolean fun(String s, String t) {
        Stack<Character> sStack = new Stack<>();
        for (char temp : s.toCharArray()) {
            if (temp == '#') {
                if (!sStack.isEmpty()) {
                    sStack.pop();
                }
            } else {
                sStack.push(temp);
            }
        }
        Stack<Character> tStack = new Stack<>();
        for (char temp : t.toCharArray()) {
            if (temp == '#') {
                if (!tStack.isEmpty()) {
                    tStack.pop();
                }
            } else {
                tStack.push(temp);
            }
        }
        if (sStack.size() != tStack.size()) {
            return false;
        }
        while (!sStack.isEmpty()) {
            char tempS = sStack.pop();
            char tempT = tStack.pop();
            if (tempS != tempT) {
                return false;
            }
        }
        return true;
    }
}
