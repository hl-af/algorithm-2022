package ARRAY;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

/**
 * 字符串空格替换为20%
 */
public class SpaceArray {

    /**
     * 使用原地的算法来解决问题
     * @param stringBuffer
     * @return
     */
    public String replaceSpace(StringBuffer stringBuffer) {
        int spaceNum = 0;
        if (stringBuffer == null || stringBuffer.length() == 0) {
            return "";
        }
        for (int i = 0; i < stringBuffer.length(); i++) {
            char ch = stringBuffer.charAt(i);
            if (ch == ' ') {
                spaceNum++;
            }
        }
        int fast = stringBuffer.length() - 1;
        stringBuffer.setLength(stringBuffer.length() + 2 * spaceNum);
        int slow = stringBuffer.length() - 1;
        for (;fast >= 0;fast--) {
            if (stringBuffer.charAt(fast) == ' '){
                stringBuffer.setCharAt(slow--, '0');
                stringBuffer.setCharAt(slow--, '2');
                stringBuffer.setCharAt(slow--, '%');
//                slow = slow - 3;
            }else {
                stringBuffer.setCharAt(slow,stringBuffer.charAt(fast));
                slow--;
            }
        }
        return stringBuffer.toString();
    }

    @Test
    public void testSpaceArray() {
        StringBuffer stringBuffer = new StringBuffer("i love coding");
//        StringBuffer stringBuffer = new StringBuffer(" ");
        System.out.println(replaceSpace(stringBuffer));
    }
}
