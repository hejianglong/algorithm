package works.array;

/**
 * 动态扩容
 * 大小固定的有序数组，支持动态增删改
 * 实现两个有序数组合并为一个有序数组
 * @author hejianglong
 * @date 2019/7/29
 */
public class ArrayWork {

    public static void main(String[] args) {
        Array array = new Array();
        array.add(5);
        array.add(8);
        array.add(7);
        array.add(6);
        array.add(9);
        array.add(3);
        array.add(2);
        array.add(1);
        array.add(4);
        array.print();
        System.out.println("5是否存在: " + array.contains(5));
        System.out.println("删除第三个有序数据");
        array.delete(3);
        array.print();
        System.out.println("更新第三个数据为 20");
        array.update(3, 20);
        array.print();
        System.out.println("合并2个新的有序数组");
        Array array1 = new Array();
        array1.add(1);
        array1.add(3);
        array1.add(5);
        array1.add(7);
        array1.add(9);
        Integer[] result = array1.merge(new Integer[]{2, 4, 6, 8, 10});
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    static class Array {

        Integer[] data;

        // 默认容量
        final static int DEFAULT_CAP = 10;

        int size;

        public Array() { }

        public Array(int size) {
            this.data = new Integer[size];
        }

        public void add(Integer v) {
            if (data == null || data.length == size) {
                resize();
            }
            int index = 0;
            // 找到第一个大于等于插入值的位置
            if (size > 0) {
                index = binarySearchFirstGE(v);
                if (index != -1) {
                    // 将后续位置向后挪移一个
                    for (int i = size; i >= index; i--) {
                        data[i + 1] = data[i];
                    }
                } else {
                    index = size;
                }
            }
            data[index] = v;
            size++;
        }

        public boolean update(Integer index, Integer v) {
            if (index < 0 || index > size) {
                return false;
            }
            int orign = index;
            int lastPosition = binarySearchFirstGE(v);
            lastPosition = lastPosition == -1 ? size : lastPosition;
            for (int i = orign; i < lastPosition - 1; i++) {
                data[i] = data[i + 1];
            }
            data[lastPosition - 1] = v;
            return true;
        }

        public void delete(int index) {
            if (index < 0 || index > size) {
                return;
            }
            for (int i = index; i < size; i++) {
                data[i] = data[i + 1];
            }
            size--;
        }

        public boolean contains(Integer v) {
            if (size == 0) {
                return false;
            }
            int index = binarySearch(v);
            return index != -1;
        }

        /**
         * 合并有序数组
         * @param arr
         */
        public Integer[] merge(Integer[] arr) {
            int i = 0;
            int j = 0;
            int k = 0;
            Integer[] newArr = new Integer[size + arr.length];
            while (i != size && j != arr.length) {
                if (data[i] < arr[j]) {
                    newArr[k++] = data[i++];
                } else {
                    newArr[k++] = arr[j++];
                }
            }
            if (i != size) {
                for (int i1 = i; i1 < size; i1++) {
                    newArr[k++] = data[i1];
                }
            } else {
                for (int i1 = j; i1 < arr.length; i1++) {
                    newArr[k++] = arr[i1];
                }
            }
            return newArr;
        }

        public void print() {
            for (int i = 0; i < size; i++) {
                System.out.print(data[i] + " ");
            }
            System.out.println();
        }

        private int binarySearch(Integer v) {
            int low = 0;
            int height = size;
            while (height >= low) {
                int middle = low + ((height - low) >> 1);
                if (v > data[middle]) {
                    low = middle + 1;
                } else if (v < data[middle]) {
                    height = middle - 1;
                } else {
                    return middle;
                }
            }
            return -1;
        }

        /**
         * 查找第一个大于等于 v 的值的位置
         * @param v
         * @return
         */
        private int binarySearchFirstGE(Integer v) {
            int low = 0;
            int height = size - 1;
            while (low <= height) {
                int middle = low + ((height - low) >> 1);
                if (v > data[middle]) {
                    low = middle + 1;
                } else if (v < data[middle]) {
                    if (middle == 0 || data[middle - 1] <= v) {
                        return middle;
                    }
                    height = middle - 1;
                } else {
                    return middle;
                }
            }
            return -1;
        }

        private void resize() {
            if (data == null) {
                data = new Integer[DEFAULT_CAP];
                return;
            }
            int oldCap = data.length;
            int newCap = oldCap << 1;
            Integer[] newData = new Integer[newCap];
            copy(data, newData);
        }

        private void copy(Integer[] source, Integer[] target) {
            for (int i = 0; i < source.length; i++) {
                target[i] = source[i];
            }
        }
    }
}