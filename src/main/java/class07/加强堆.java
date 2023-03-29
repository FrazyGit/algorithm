package class07;

import class06.堆;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * heapinsert
 * 两个终止条件:
 * 1. 来到0位置了, 自己跟自己比较
 * 2. 没有到0位置, 但不如它父亲大
 * <p>
 * heapify: 堆化
 * 把两个孩子中较大的孩子拎出来。把较大的孩子跟 X PK，如果能 PK 过我，我就来到我较大孩子的位置 i，
 * 我较大孩子上去，接下来 X 就会来到下一步在 i 位置继续这样往下沉，周而复始，一直到 X 已经没有孩子了，
 * 或者沉到某一步它较大孩子不再能够干掉 X 了
 * 如何删除:
 * <p>
 * <p>
 * heapsize来表示堆大小
 * 用一个变量t记住最大值
 * 返回前做调整: 拿堆上最后一个位置的数去替0位置的数
 * 然后heapsize--
 * 但是一个新的数字到了0位置, 有可能破坏了堆的规则
 * 需要从0位置出发把整个堆调对
 *
 * @author wlkq
 * @date 2023-03-26 14:37
 */
public class 加强堆 {


    public static class HeapPlus<T> {

        private ArrayList<T> heap;

        private HashMap<T, Integer> map;

        private Integer heapSize;

        private Comparator<T> comparator;


        public HeapPlus(Comparator<T> comparator) {
            this.comparator = comparator;
            heap = new ArrayList<>();
            map = new HashMap<>();
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public void push(T node) {
            heap.add(node);
            map.put(node, heapSize);
            heapInsert(heapSize++);

        }

        public T pop() {
            T t = heap.get(0);
            swap(heap, 0, --heapSize);
            heapify(0);
            map.remove(t);
            heap.remove(t);

            return t;


        }


        public void heapInsert(Integer index) {


            while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
                swap(heap, index, (index - 1) / 2);
                index = (index - 1) / 2;

            }


        }

        public void heapify(Integer index) {

            int left = index * 2 + 1;
            while (left < heapSize) {
                //注意left+1在前
                int m = left + 1 < heapSize && comparator.compare(heap.get(left + 1), heap.get(left)) < 0 ? left + 1 : left;
                m = comparator.compare(heap.get(m), heap.get(index)) < 0 ? m : index;

                if (m == index) {
                    return;
                }

                swap(heap, m, index);
                index = m;
                left = index * 2 + 1;


            }


        }


        public void swap(ArrayList<T> arrayList, int i, int j) {
            T temp = arrayList.get(i);
            arrayList.set(i, arrayList.get(j));
            arrayList.set(j, temp);
            map.put(arrayList.get(i), i);
            map.put(arrayList.get(j), j);

        }


        public void resign(T obj) {
            heapInsert(map.get(obj));
            heapify(map.get(obj));

        }

        public void remove(T obj) {
            Integer index = map.get(obj);


            if (index != --heapSize) {//要删除的数是否是最后一个数
                swap(heap, index, heapSize);
                resign(heap.get(index));
            }

            map.remove(obj);
            heap.remove(obj);



    }

    public boolean isContain(T obj) {
        return map.containsKey(obj);
    }


}

public static class MyComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o2 - o1;
    }
}


    public static void main(String[] args) {

        Student s1 = new Student(17, "A同学");
        Student s2 = new Student(10, "B同学");
        Student s3 = new Student(29, "C同学");
        Student s4 = new Student(33, "D同学");
        Student s5 = new Student(54, "E同学");
        Student s6 = new Student(93, "F同学");
        // 生成一个加强堆
        // 排序策略是年龄小的放在堆顶，年龄小根堆
        HeapPlus<Student> heap1 = new HeapPlus<>((a, b) -> a.age - b.age);
        // 把所有学生加入堆
        heap1.push(s1);
        heap1.push(s2);
        heap1.push(s3);
        heap1.push(s4);
        heap1.push(s5);
        heap1.push(s6);

        System.out.println(heap1.pop());
        heap1.push(new Student(93, "G同学"));

        heap1.remove(s4);
//
//
        System.out.println(heap1.isContain(s4));
        // 加入之后
        // 可以把某个同学的年龄改了
        // 比如把s5，也就是E同学
        // 年龄从54改成了4
        s5.age = 4;
//        // 此时堆被破坏了，因为你擅自改了一个同学的年龄
//        // 只需要调用resign方法，就能让堆恢复成年龄小根堆
//        // 而且复杂度是O(log N)，很快的
//        // 系统提供的堆做不到的，加强堆可以
        heap1.resign(s5);
        // 依次弹出所有学生
        // 会发现从年龄小到年龄大依次弹出
        // 说明堆是正确的
        while (!heap1.isEmpty()) {
            Student cur = heap1.pop();
            System.out.println("年龄 : " + cur.age + " , 名字 : " + cur.name);
        }


        System.out.println("======================");

        // 现在展示非基础类型的加强堆用法
        int[] arr = { 3, 3, 2, 5, 3 };
        // arr[0] == 3
        // arr[1] == 3
        // arr[2] == 2
        // arr[3] == 5
        // arr[4] == 3
        // 每个位置的数字一定会自带一个下标，这是一定的!
        // 任何基础类型的元素，天生一定会自带一些类似下标的身份信息的！这是一定的！
        // 生成一个加强堆
        // 加强堆里只放下标即可，因为通过下标可以找到数字
        // 排序策略是 :
        // 数字小的下标，在堆顶
        HeapPlus<Integer> heap2 = new HeapPlus<>((i, j) -> arr[i] - arr[j]);

        // 把数组所有的下标加入堆
        // 就等于加入了所有数字
        heap2.push(0);
        heap2.push(1);
        heap2.push(2);
        heap2.push(3);
        heap2.push(4);

        heap2.remove(arr[1]);
        System.out.println(heap1.isContain(s4));

        // 加入之后
        // 可以把某个下标上的数字改了
        // arr[1]原来是3，现在变成了-9
        arr[1] = -9;
        // 此时堆被破坏了，因为你擅自改了一个下标的数字
        // 只需要调用resign方法，就能让堆恢复
        // 而且复杂度是O(log N)，很快的
        // 系统提供的堆做不到的，加强堆可以
        // 调用resign方法
        heap2.resign(1);

        // 依次弹出所有下标
        // 会发现下标上的数字越小，下标越早弹出
        // 说明堆是正确的
        while (!heap2.isEmpty()) {
            int curIndex = heap2.pop();
            System.out.println("下标 : " + curIndex + " , 数字 :" + arr[curIndex]);
        }

    }

public static class Student {
    public int age;
    public String name;

    public Student(int a, String n) {
        age = a;
        name = n;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}


}
