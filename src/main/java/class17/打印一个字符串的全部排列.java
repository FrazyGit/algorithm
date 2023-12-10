package class17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author gc
 * @date 2023-12-02 13:41
 */
public class 打印一个字符串的全部排列 {

    // str[0..i-1]已经做好决定的
    // str[i...]都有机会来到i位置
    // i终止位置，str当前的样子，就是一-种结果-> ans

    public static void permutation(String s) {
        ArrayList<String> ans = new ArrayList<>();
        process(s.toCharArray(), 0, ans);
        System.out.println(ans.toString());
    }

    public static void process(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
            return;
        }
        for (int i = index; i < str.length; i++) {
            swap(str, index, i);
            process(str, index + 1, ans);
            swap(str, i, index);//恢复现场
        }


    }

    /**
     * 只要非重复的结果
     * 1. set去重（结果时去）
     * 2. 剪枝（过程中去）
     *
     * @param s
     */
    public static void permutation2(String s) {
        ArrayList<String> ans = new ArrayList<>();
        process2(s.toCharArray(), 0, ans);
        System.out.println(ans.toString());


    }

    public static void process2(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
            return;
        }
        //通过AScII码过滤
        boolean[] visited = new boolean[256];
        for (int i = index; i < str.length; i++) {
            if (!visited[str[i]]) {
                visited[str[i]] = true;
                swap(str, index, i);
                process2(str, index + 1, ans);
                swap(str, i, index);//恢复现场

            }
        }


    }

    public static void swap(char[] str, int a, int b) {
        char temp = str[a];
        str[a] = str[b];
        str[b] = temp;

    }

    public static void main(String[] args) {
        String str = "abc";
        permutation(str);
        System.out.println("=================");
        String str2 = "acc";
        permutation(str2);
        permutation2(str2);
    }


}
