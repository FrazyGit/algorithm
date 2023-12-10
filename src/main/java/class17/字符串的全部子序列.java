package class17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author gc
 * @date 2023-12-02 10:34
 */
public class 字符串的全部子序列 {

    public static List<String> subs(String str) {
        char[] chars = str.toCharArray();
        ArrayList<String> ans = new ArrayList<>();
        process(chars, 0, "", ans);
        return ans;

    }

    /**
     * @param str   字符串数组
     * @param index 来到的位置
     * @param path  之前做的决定
     * @param ans   最后返回的结果
     */
    private static void process(char[] str, int index, String path, List<String> ans) {
        if (index == str.length) {
            ans.add(path);
            return;
        }
        //要当前位置
        process(str, index + 1, path + str[index], ans);
        //不要当前位置
        process(str, index + 1, path, ans);

    }


    /**
     * 要求不出现重复值
     * @param str
     * @return
     */
    public static Set<String> subs2(String str) {
        char[] chars = str.toCharArray();
        Set<String> ans = new HashSet<>();
        process1(chars, 0, "", ans);
        return ans;

    }

    /**
     * @param str   字符串数组
     * @param index 来到的位置
     * @param path  之前做的决定
     * @param ans   最后返回的结果
     */
    private static void process1(char[] str, int index, String path, Set<String> ans) {
        if (index == str.length) {
            ans.add(path);
            return;
        }
        //要当前位置
        process1(str, index + 1, path + str[index], ans);
        //不要当前位置
        process1(str, index + 1, path, ans);

    }




    public static void main(String[] args) {
        String s="abcc";
        List<String> subs = subs(s);
        System.out.println(subs.toString());

        Set<String> subs2 = subs2(s);
        System.out.println(subs2.toString());
    }
}
