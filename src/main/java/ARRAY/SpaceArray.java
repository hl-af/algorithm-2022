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

    /**
     * 自己尝试实现这个方法
     * @param stringBuffer
     * @return
     */
    public String replaceSpaceMe(StringBuffer stringBuffer) {
        int num = 0;
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (stringBuffer.charAt(i) == ' ') {
                num++;
            }
        }
        int fast = stringBuffer.length() - 1;
        stringBuffer.setLength(num * 2 + stringBuffer.length());
        int slow = stringBuffer.length() - 1;
        for (;fast >= 0;fast--) {
            if (stringBuffer.charAt(fast) != ' ') {
                stringBuffer.setCharAt(slow--, stringBuffer.charAt(fast));
            }else {
                stringBuffer.setCharAt(slow--,'0');
                stringBuffer.setCharAt(slow--,'2');
                stringBuffer.setCharAt(slow--, '%');
            }
        }
        return stringBuffer.toString();
    }
    @Test
    public void testSpaceArray() {
        StringBuffer stringBuffer = new StringBuffer("i love coding");
//        StringBuffer stringBuffer = new StringBuffer(" ");
//        System.out.println(replaceSpace(stringBuffer));
        System.out.println(replaceSpaceMe(stringBuffer));
    }


    /**
     * 在咕泡有很多小姐姐,她们给我们的学习增加了很多快乐，
     * 她们每个人都有一个花名，例如文文、田田、颖子、荣荣、媛媛、梦晴、书昀等等
     * 假定每个人的名字长度不小于5个字母，为了压缩存储，我们可以将相同字的后一个用"~"来表示。
     * 例如，wenwen，rongrong就用wen~,rong~来表示，而yingzi、shuyun等都保持不变
     * 名字之间以空格隔开，现在请你设计一个算法实现上述压缩功能。
     * 要求：
     * 1.功能在compressName()中实现，你可以将String 转换后保存到一个数组里，除此之外不得再申请O(n)级别的空间
     * 2.请保证提交的代码能执行，不能执行或者执行结果不对，视为0分
     * 3.最晚提交时间：2022年1月3日晚12点，过期提交作废
     * 4.请将homework.task3下的题目做完后一起打包发到荣荣邮箱，包名必须有你的编号（参考编号列表），如果不按规定找不到你，视为0分
     * 5.出题不易，一起都为了你的学习，请不要随便外传题目
     */
    public String compressName(String name) {
        char[] array = name.toCharArray();
        for (int i = 0; i < array.length; i++) {
            int slow = i;
            if (array[i] == ' ') {
                int fast = i - 1; //指向末尾

            }
        }
        return null;
    }

    public boolean isDualName(String name,int start ,int end){
        int slow = start;
        int fast = (end + start) / 2;
        while (fast < name.length()) {
            if (name.charAt(slow) != name.charAt(fast)) {
                return false;
            }else {
                slow++;
                fast++;
            }
        }
        return true;
    }
}
