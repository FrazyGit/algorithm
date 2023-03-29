package class08;

import class03.write.Node;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Random;

/**
 * 1）单个字符串中，字符从前到后的加到一棵多叉树上
 * 2）字符放在路上，节点上有专属的数据项（常见的是pass和end值）
 * 3）所有样本都这样添加，如果没有路就新建，如有路就复用
 * 4）沿途节点的pass值增加1，每个字符串结束时来到的节点end值增加1
 * <p>
 * 可以完成前缀相关的查询
 *
 * 字符串种类很多时, 用HashMap的形式表示下级的路
 *
 * @author wlkq
 * @date 2023-03-27 8:21
 */
public class 前缀树 {


    public static class TrieNode {
        public Integer pass;
        public Integer end;

        public TrieNode[] next;

        public TrieNode() {
            pass = 0;
            end = 0;
            next = new TrieNode[26];//只有小写字母的情况
        }


    }

//    class Node {
//        public int pass;
//        public int end;
//        public HashMap<Integer, Node> nexts;
//
//        public Node() {
//            pass = 0;
//            end = 0;
//            nexts = new HashMap<>();
//        }
//    }


    public static class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String value) {

            if (value == null) {
                return;
            }

            TrieNode cur = root;

//            cur.pass++;

            char[] chars = value.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                int path = chars[i] - 'a';
                if (cur.next[path] == null) {
                    cur.next[path] = new TrieNode();
                }
                cur = cur.next[path];
                cur.pass++;

            }
            cur.end++;

        }

        public int search(String value) {
            TrieNode cur = root;

            char[] chars = value.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                int path = chars[i] - 'a';
                if (cur.next[path] == null) {
                    return 0;
                }
                cur = cur.next[path];

            }

            return cur.end;
        }

        public int pre(String value) {
            TrieNode cur = root;

            char[] chars = value.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                int path = chars[i] - 'a';
                if (cur.next[path] == null) {
                    return 0;
                }
                cur = cur.next[path];
            }

            return cur.pass;
        }

        public void delete(String value) {
            if (search(value) != 0) {
                TrieNode cur = root;

                char[] chars = value.toCharArray();

                for (int i = 0; i < chars.length; i++) {
                    int path = chars[i] - 'a';
                    if (--cur.next[path].pass == 0) {
                        cur.next[path] = null;
                        return;
                    }
                    cur = cur.next[path];


                }
                cur.end--;

            }


        }
    }


    public static class Right {

        private HashMap<String, Integer> box;

        public Right() {
            box = new HashMap<>();
        }

        public void insert(String word) {
            if (!box.containsKey(word)) {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }
        }

        public void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            if (!box.containsKey(word)) {
                return 0;
            } else {
                return box.get(word);
            }
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String cur : box.keySet()) {
                if (cur.startsWith(pre)) {
                    count += box.get(cur);
                }
            }
            return count;
        }
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            Trie trie1 = new Trie();
//            Trie2 trie2 = new Trie2();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
//                    trie2.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
//                    trie2.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
//                    int ans2 = trie2.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1 != ans3) {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.pre(arr[j]);
//                    int ans2 = trie2.prefixNumber(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    if (ans1 != ans3) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");

    }
}
