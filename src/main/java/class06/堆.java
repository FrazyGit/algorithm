package class06;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 用数组表示二叉树
 * <p>
 * 用一个变量 size 表示 想像中的二叉树的大小
 * 0位置是父节点
 * 一个位置的左孩子节点 2*i+1
 * 一个位置的左孩子节点 2*i+2
 * 一个位置的父节点 （i-1）/2
 *
 *
 * heapinsert
 * 两个终止条件:
 * 1. 来到0位置了, 自己跟自己比较
 * 2. 没有到0位置, 但不如它父亲大
 *
 * heapify: 堆化
 * 把两个孩子中较大的孩子拎出来。把较大的孩子跟 X PK，如果能 PK 过我，我就来到我较大孩子的位置 i，
 * 我较大孩子上去，接下来 X 就会来到下一步在 i 位置继续这样往下沉，周而复始，一直到 X 已经没有孩子了，
 * 或者沉到某一步它较大孩子不再能够干掉 X 了
 *
 *
 * 如何删除:
 * heapsize来表示堆大小
 * 用一个变量t记住最大值
 * 返回前做调整: 拿堆上最后一个位置的数去替0位置的数
 * 然后heapsize--
 * 但是一个新的数字到了0位置, 有可能破坏了堆的规则
 * 需要从0位置出发把整个堆调对
 *
 * @author wlkq
 * @date 2023-03-20 15:01
 */
public class 堆 {


    //大根堆
    public static class MyHeap {

        private int[] heap;
        private int size;
        private final int limit;


        public MyHeap(int limit) {
            heap = new int[limit];
            size = 0;
            this.limit = limit;
        }



        //调整
        private void insert(int index) {
            while (heap[index] > heap[(index - 1) / 2]) {
                swap(heap, index, (index - 1) / 2);
                index = (index - 1) / 2;

            }

        }

        //调整
        private void heapify(int index) {

            int left = 2 * index + 1;

            while (left < size) {
                int last = left + 1 < size && heap[left] < heap[left + 1] ? left + 1 : left;
                last = heap[last] > heap[index] ? last : index;

                if (last == index) {
                    return;
                }

                swap(heap, index, last);
                index = last;
                left = 2 * index + 1;


            }


        }


        public void push(int value) {
            if (limit == size) {
                throw new RuntimeException("堆已满");
            }

            heap[size] = value;
            insert(size++);

        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }


        public int pop() {//弹出栈顶元素
            if (size == 0) {
                throw new RuntimeException("堆为空");
            }
            swap(heap, 0, --size);

            heapify(0);


            return heap[size];

        }


//        堆中某个位置的值发生变化, 由x-->x`
//        从index位置开始做 heapinsert, heapify:
//        如果index位置数变大了, 只可能发生heapinsert往上移动
//        如果index位置数变小了, 只可能发生heapify往下移动
//        同时调用heapinsert, heapify 检查index位置该怎么移动
//        两个其实只能发生一个, 一定能把整个堆都调对
        public void change(int index) {
            heapify(index);
            insert(index);
        }


    }

    public static class RightMaxHeap {
        private int[] arr;
        private final int limit;
        private int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            arr[maxIndex] = arr[--size];
            return ans;
        }


    }

    public static class MyComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }


    public static void main(String[] args) {
        MyHeap aHeap = new MyHeap(5);


        aHeap.push(2);
        aHeap.push(10);
        aHeap.push(102);
        aHeap.push(100);
        aHeap.push(103);

        System.out.println(aHeap.size);
        System.out.println(Arrays.toString(aHeap.heap));

        int pop = aHeap.pop();

        System.out.println(pop);


        System.out.println(aHeap.size);
        System.out.println(Arrays.toString(aHeap.heap));

        aHeap.push(101);

        System.out.println(aHeap.size);
        System.out.println(Arrays.toString(aHeap.heap));

        aHeap.heap[1]=190;

        aHeap.change(1);

        System.out.println(aHeap.size);
        System.out.println(Arrays.toString(aHeap.heap));

//
//        PriorityQueue<Integer> myHeap = new PriorityQueue(new MyComparator());
//
//
//        myHeap.add(2);
//        myHeap.add(10);
//        myHeap.add(102);
//        myHeap.add(100);
//        myHeap.add(103);
//
//        System.out.println(myHeap.size());
//        System.out.println(myHeap.toString());
//
//        Integer poll = myHeap.poll();
//
//        System.out.println(poll);
//        System.out.println(myHeap.toString());
//
//
//        int value = 1000;
//        int limit = 100;
//        int testTimes = 1000000;
//        for (int i = 0; i < testTimes; i++) {
//            int curLimit = (int) (Math.random() * limit) + 1;
//            MyHeap my = new MyHeap(curLimit);
//            RightMaxHeap test = new RightMaxHeap(curLimit);
//            int curOpTimes = (int) (Math.random() * limit);
//            for (int j = 0; j < curOpTimes; j++) {
//                if (my.isEmpty() != test.isEmpty()) {
//                    System.out.println("Oops!");
//                }
//                if (my.isFull() != test.isFull()) {
//                    System.out.println("Oops!");
//                }
//                if (my.isEmpty()) {
//                    int curValue = (int) (Math.random() * value);
//                    my.push(curValue);
//                    test.push(curValue);
//                } else if (my.isFull()) {
//                    if (my.pop() != test.pop()) {
//                        System.out.println("Oops!");
//                    }
//                } else {
//                    if (Math.random() < 0.5) {
//                        int curValue = (int) (Math.random() * value);
//                        my.push(curValue);
//                        test.push(curValue);
//                    } else {
//                        if (my.pop() != test.pop()) {
//                            System.out.println("Oops!");
//                        }
//                    }
//                }
//            }
//        }
//        System.out.println("finish!");

    }


    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;

    }

}
