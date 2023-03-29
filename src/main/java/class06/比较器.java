package class06;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * 返回负数第一个数放前面
 * 返回正数第二个数放前面
 * 返回0无所谓
 *
 * @author wlkq
 * @date 2023-03-20 15:01
 */
public class 比较器 {


    public static class Student {

        int id;
        int score;

        public Student(Integer id, Integer score) {
            this.id = id;
            this.score = score;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", score=" + score +
                    '}';
        }
    }


    public static class MyCompare implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }


    public static class IdInAgeDe implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.id != o2.id ? o1.id - o2.id : (o2.score - o1.score);
        }

    }
    public static void main(String[] args) {
        TreeMap<Integer, Integer> treeMap = new TreeMap(new MyCompare());

        treeMap.put(1, 1);
        treeMap.put(2, 2);
        treeMap.put(3, 3);
        treeMap.put(4, 4);


        System.out.println(treeMap.toString());

        TreeMap<Student, Integer> treeMap2 = new TreeMap<>(new IdInAgeDe());



        treeMap2.put(new Student(1,100),1);//treemap按key排序
        treeMap2.put(new Student(1,90),2);
        treeMap2.put(new Student(2,100),3);

        System.out.println(treeMap2.toString());


    }

}
