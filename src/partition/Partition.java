/*
 * Project: sort
 *
 * File Created at 2019-07-21
 *
 * Copyright 2019 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */
package partition;

/**
 * @author hejianglong
 * @date 2019-07-21 09:51
 * @email hejlong@163.com
 * @Desc
 */
public class Partition {

    private int num = 0;

    public static void main(String[] args) {
        Partition sort = new Partition();
        int[] a = new int[]{1,5,6,2,3,4};
        System.out.println(sort.count(a, 6));
    }

    /**
     * 求逆序度
     * @param a
     * @param n
     * @return
     */
    private int count(int[] a, int n) {
        num = 0;
        mergeSortCounting(a, 0, n - 1);
        return num;
    }

    private void mergeSortCounting(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int middle = (start + end) / 2;
        mergeSortCounting(a, start, middle);
        mergeSortCounting(a, middle + 1, end);
        merge(a, start, middle, end);
    }

    private void merge(int[] a, int start, int middle, int end) {
        int i = start, j = middle + 1, k = 0;
        int[] tmp = new int[end - start + 1];
        while (i <= middle && j <= end) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                num += middle - i + 1;
                tmp[k++] = a[j++];
            }
        }
        while (i <= middle) {
            tmp[k++] = a[i++];
        }
        while (j <= end) {
            tmp[k++] = a[j++];
        }
        for (i = 0; i < end - start; i++) {
            a[start + i] = tmp[i];
        }
    }
}
