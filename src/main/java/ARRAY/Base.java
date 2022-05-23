package ARRAY;

import org.junit.Test;

import javax.swing.plaf.PanelUI;

/**
 * 基础数组题目
 */
public class Base {

    //创建数组
    public void create() {
        //推荐的两种赋值方法
        //创建数组时⼤⼩就是元素的数量，是⽆法再插⼊元素的，如果需要增加新元素就不能这么⽤了。
        int[] arr = new int[]{2, 4, 6, 7, 89, 55};
        int[] nums = {1, 23, 45, 56, 6,};

    }

    //查找数组，给定一个递增序列，如果比目标值比当前位置大，就停下来，插入进去
    /**
     * @param arr
     * @param size 数组已经存储的元素数量，从1开始编号
     * @param element 待插⼊的元素
     * @return
     */
    public void addLargeElement(int[] arr, int size, int element) {
        if (size >= arr.length) {
            throw new IllegalArgumentException();
        }
        int index = size;
        for (int i = 0; i < size; i++) {
            if (arr[i] > element) {
                index = i;
                break;
            }
        }
        for (int i = size; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = element;
        printArray(arr);
    }

    /**
     * 从数组中删除某一个元素key
     * @param arr 数组
     * @param size 数组中元素个数
     * @param key 要删除的元素值
     */
    public void deleteElementFromArray(int[] arr, int size, int key) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                index = i;
            }
        }
        /*if (index >= 0 && index < arr.length - 1) {
            for (int i = index; i < arr.length - 1 ; i++) {
                arr[i] = arr[i + 1];
            }
            arr[arr.length - 1] = 0;
        } else if (index == (arr.length - 1)) {
            arr[index] = 0;
        }*/

        if (index != -1) {
            for (int i = index + 1; i < size; i++) {
                arr[i - 1] = arr[i];
                size--;
            }
        }
        printArray(arr);
        return;
    }

    @Test
    public void testDeleteElementFromArray() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 7, 8, 9, 10};
//        deleteElementFromArray(arr,arr.length,9);
//        deleteElementFromArray(arr,arr.length,10);
        deleteElementFromArray(arr, arr.length, 1);
    }

    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
    }
    public static void main(String[] args) {
        Base base = new Base();
        int[] arr = new int[10];
        for (int i = 0; i < 6; i++) {
            arr[i] = i*2;
        }
        base.addLargeElement(arr, 6, 13);
    }
}
